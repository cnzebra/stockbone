package com.stockbone.web.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stockbone.components.common.Page;
import com.stockbone.components.stock.CandleData;
import com.stockbone.utils.reflect.BeanWrapper;
import com.stockbone.web.components.CandleDataQueryParameter;
import com.stockbone.web.dao.CandleDataDao;

@Repository("candleDataDao")
public class CandleDataDaoImpl implements CandleDataDao {

	@Autowired
	public SqlSession session;

	@Override
	public int insertCandleData(List<CandleData> list) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		return session.insert("CandleData.insertCandleData", map);
	}

	@Override
	public Page<CandleData> select(CandleDataQueryParameter parameter) {
		Page<CandleData> page = new Page<CandleData>();
		List<CandleData> content = new ArrayList<CandleData>();
		List<Map<String, Object>> list = session.selectList("CandleData.selectCandleData", parameter);
		for (Map<String, Object> map : list) {
			CandleData data = BeanWrapper.wrapper(CandleData.class, map);
			content.add(data);
		}
		page.setContent(content);
		return page;
	}

}
