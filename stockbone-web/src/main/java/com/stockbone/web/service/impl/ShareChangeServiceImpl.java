package com.stockbone.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stockbone.components.common.Page;
import com.stockbone.components.stock.ShareChange;
import com.stockbone.web.components.ShareChangeQueryParameter;
import com.stockbone.web.dao.ShareChangeDao;
import com.stockbone.web.service.ShareChangeService;

@Service("shareChangeService")
public class ShareChangeServiceImpl implements ShareChangeService {

	@Autowired
	private ShareChangeDao shareChangeDao;

	@Override
	public int addShareChange(List<ShareChange> list) {
		return shareChangeDao.insertShareChange(list);
	}

	@Override
	public int removeShareChange(String code, long changeDate) {
		return shareChangeDao.deleteStock(code, changeDate);
	}

	@Override
	public Page<ShareChange> query(ShareChangeQueryParameter parameter) {
		return shareChangeDao.select(parameter);
	}

	@Override
	public int mergr(String code) {
		return shareChangeDao.merge(code);
	}

}
