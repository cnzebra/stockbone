package com.stockbone.web.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.stockbone.components.common.Page;
import com.stockbone.components.stock.CandleData;
import com.stockbone.components.stock.Stock;
import com.stockbone.eagle.StockCalendar;
import com.stockbone.eagle.talon.CandleDataTalon;
import com.stockbone.eagle.talon.impl.CandleDataTalonFromGW;
import com.stockbone.utils.concurrent.BaseTask;
import com.stockbone.utils.concurrent.TaskExecutor;
import com.stockbone.utils.date.DateUtils;
import com.stockbone.utils.random.RandomUtils;
import com.stockbone.web.components.StockQueryParameter;
import com.stockbone.web.service.CandleDataService;
import com.stockbone.web.service.StockService;

public class Razor extends TimerTask {

	private static Logger logger = LoggerFactory.getLogger(Razor.class);

	WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
	StockService stockService = (StockService) context.getBean("stockService");
	CandleDataService candleDataService = (CandleDataService) context.getBean("candleDataService");
	CandleDataTalon talon = new CandleDataTalonFromGW();

	@Override
	public void run() {
		StockCalendar stockCalendar = new StockCalendar();
		Date lastTradeDate = stockCalendar.getLastTradeDate();
		if (lastTradeDate.getDate() != new Date().getDate()) {
//			return;
		}
		logger.info("Completing candle data......");
		TaskExecutor executor = new TaskExecutor(10);
		int start = 0;
		int length = 10;
		while (!Thread.interrupted()) {
			StockQueryParameter parameter = new StockQueryParameter();
			parameter.setStart(start);
			parameter.setLength(length);
			Page<Stock> page = stockService.query(parameter);
			List<Stock> list = page.getContent();
			if (list.size() == 0) {
				break;
			}
			start += length;
			List<Future<Boolean>> futures = new ArrayList<Future<Boolean>>();
			for (Stock stock : list) {
				Kite kite = new Kite("Kite-" + RandomUtils.randomUUID(), stock, lastTradeDate);
				Future<Boolean> future = executor.submit(kite);
				futures.add(future);
			}
			try {
				for (Future<Boolean> future : futures) {
					if (!future.get()) {
						throw new IllegalStateException();
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		logger.info("Completed candle data successfully.");
	}

	class Kite extends BaseTask<Boolean> {
		private Stock stock;
		private Date lastTradeDate;

		public Kite(String taskId) {
			super(taskId);
		}

		public Kite(String taskId, Stock stock, Date lastTradeDate) {
			super(taskId);
			this.stock = stock;
			this.lastTradeDate = lastTradeDate;
		}

		@Override
		public Boolean call() throws Exception {
			CandleData data = talon.fetch(stock, lastTradeDate);
			List<CandleData> candleDatas = new ArrayList<CandleData>();
			candleDatas.add(data);
			int line = candleDataService.addCandleData(candleDatas);
			if (line != 1) {
				throw new IllegalStateException("Fetch candleDate error:" + stock.getCode());
			}
			logger.info("Complete candle data:{}--{}", new Object[] { DateUtils.format(lastTradeDate), stock.getCode() });
			return true;
		}
	}

}
