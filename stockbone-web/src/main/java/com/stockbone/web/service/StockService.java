package com.stockbone.web.service;

import com.stockbone.components.common.Page;
import com.stockbone.components.stock.Stock;
import com.stockbone.web.components.StockQueryParameter;

public interface StockService {

	int addStock(Stock stock);
	
	int removeStock(String code);
	
	boolean isExist(String code);

	Page<Stock> query(StockQueryParameter parameter);
}
