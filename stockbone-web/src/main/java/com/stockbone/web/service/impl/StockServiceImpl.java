package com.stockbone.web.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stockbone.components.common.Page;
import com.stockbone.components.stock.ShareChange;
import com.stockbone.components.stock.Stock;
import com.stockbone.eagle.talon.ShareChangeTalon;
import com.stockbone.eagle.talon.impl.DefaultShareChangeTalon;
import com.stockbone.web.components.StockQueryParameter;
import com.stockbone.web.dao.ShareChangeDao;
import com.stockbone.web.dao.StockDao;
import com.stockbone.web.service.StockService;

@Service("stockService")
public class StockServiceImpl implements StockService {

	private static Logger logger = LoggerFactory.getLogger(StockServiceImpl.class);

	@Autowired
	StockDao stockDao;
	@Autowired
	private ShareChangeDao shareChangeDao;
	ShareChangeTalon talon = new DefaultShareChangeTalon();

	@Override
	public int addStock(Stock stock) {
		int row = 0;
		try {
			row = stockDao.insertStock(stock);
			if (row > 0) {
				List<ShareChange> shareChanges = talon.fetchShareChange(stock);
				shareChangeDao.insertShareChange(shareChanges);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return row;
	}

	@Override
	public int removeStock(String code) {
		return stockDao.deleteStock(code);
	}

	@Override
	public Page<Stock> query(StockQueryParameter parameter) {
		return stockDao.select(parameter);
	}

	@Override
	public boolean isExist(String code) {
		return stockDao.isExist(code);
	}

}
