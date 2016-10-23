package com.stockbone.web.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stockbone.components.common.Page;
import com.stockbone.components.stock.ShareChange;
import com.stockbone.utils.reflect.BeanWrapper;
import com.stockbone.web.components.ShareChangeQueryParameter;
import com.stockbone.web.dao.ShareChangeDao;

@Repository("shareChangeDao")
public class ShareChangeDaoImpl implements ShareChangeDao {

	@Autowired
	public SqlSession session;

	@Override
	public int insertShareChange(List<ShareChange> list) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		return session.insert("ShareChange.insertShareChange", map);
	}
	
	@Override
	public int deleteStock(String code, long changeDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", code);
		map.put("changeDate", changeDate);
		return session.delete("ShareChange.deleteShareChange", map);
	}

	@Override
	public Page<ShareChange> select(ShareChangeQueryParameter parameter) {
		Page<ShareChange> page = new Page<ShareChange>();
		List<ShareChange> content = new ArrayList<ShareChange>();
		List<Map<String, Object>> list = session.selectList("ShareChange.selectShareChange", parameter);
		for (Map<String, Object> map : list) {
			ShareChange data = BeanWrapper.wrapper(ShareChange.class, map);
			content.add(data);
		}
		page.setContent(content);
		return page;
	}

	@Override
	public int merge(String code) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", code);
		return session.delete("ShareChange.merge", map);
	}

}
