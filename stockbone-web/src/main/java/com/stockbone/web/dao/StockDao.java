package com.stockbone.web.dao;

import com.stockbone.components.common.Page;
import com.stockbone.components.stock.Stock;
import com.stockbone.web.components.StockQueryParameter;

public interface StockDao {

	int insertStock(Stock stock);

	int deleteStock(String code);

	boolean isExist(String code);

	Page<Stock> select(StockQueryParameter parameter);


}
