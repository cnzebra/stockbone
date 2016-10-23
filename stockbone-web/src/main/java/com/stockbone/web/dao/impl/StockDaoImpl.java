package com.stockbone.web.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stockbone.components.common.Page;
import com.stockbone.components.stock.Exchange;
import com.stockbone.components.stock.Stock;
import com.stockbone.utils.reflect.BeanWrapper;
import com.stockbone.web.components.StockQueryParameter;
import com.stockbone.web.dao.StockDao;

@Repository("stockDao")
public class StockDaoImpl implements StockDao {

	@Autowired
	public SqlSession session;

	@Override
	public int insertStock(Stock stock) {
		if (isExist(stock.getCode())) {
			return 0;
		}
		return session.insert("Stock.insertStock", stock);
	}
	
	@Override
	public int deleteStock(String code) {
		return session.delete("Stock.deleteStock", code);
	}

	@Override
	public boolean isExist(String code) {
		int count = (Integer)session.selectOne("Stock.isExist", code);
		return count > 0;
	}

	@Override
	public Page<Stock> select(StockQueryParameter parameter) {
		Page<Stock> page = new Page<Stock>();
		List<Stock> content = new ArrayList<Stock>();
		List<Map<String, Object>> list = session.selectList("Stock.selectStock", parameter);
		for (Map<String, Object> map : list) {
			Stock data = BeanWrapper.wrapper(Stock.class, map);
			data.setExchange(Enum.valueOf(Exchange.class, (String) map.get("exchange")));
			content.add(data);
		}
		page.setContent(content);
		return page;
	}

}
