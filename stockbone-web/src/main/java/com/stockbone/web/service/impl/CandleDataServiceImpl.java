package com.stockbone.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stockbone.components.common.Page;
import com.stockbone.components.stock.CandleData;
import com.stockbone.components.stock.Stock;
import com.stockbone.utils.valid.Preconditions;
import com.stockbone.web.components.CandleDataQueryParameter;
import com.stockbone.web.components.StockQueryParameter;
import com.stockbone.web.dao.CandleDataDao;
import com.stockbone.web.dao.StockDao;
import com.stockbone.web.service.CandleDataService;

@Service("candleDataService")
public class CandleDataServiceImpl implements CandleDataService {

	@Autowired
	private CandleDataDao candleDataDao;
	@Autowired
	StockDao stockDao;

	@Override
	public int addCandleData(List<CandleData> list) {
		int line = 0;
		List<CandleData> datas = new ArrayList<CandleData>();
		for (int i = 0; i < list.size(); i++) {
			datas.add(list.get(i));
			if (i % 500 == 0 || i == list.size() - 1) {
				line += candleDataDao.insertCandleData(datas);
				datas.clear();
			}
		}
		return line;
	}

	@Override
	public Page<CandleData> query(CandleDataQueryParameter parameter) {
		Preconditions.checkArgument(parameter != null && parameter.getCode() != null);
		Page<CandleData> page = candleDataDao.select(parameter);
		StockQueryParameter stockParameter = new StockQueryParameter();
		stockParameter.setCode(parameter.getCode());
		Page<Stock> stockPage = stockDao.select(stockParameter);
		if (stockPage.getContent().size() == 1) {
			Stock stock = stockPage.getContent().get(0);
			for (CandleData data : page.getContent()) {
				data.setStock(stock);
			}
		}
		return page;
	}

}
