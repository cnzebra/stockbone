package com.stockbone.web.service;

import java.util.List;

import com.stockbone.components.common.Page;
import com.stockbone.components.stock.ShareChange;
import com.stockbone.web.components.ShareChangeQueryParameter;

public interface ShareChangeService {

	int addShareChange(List<ShareChange> list);

	int removeShareChange(String code, long changeDate);

	int mergr(String code);
	
	Page<ShareChange> query(ShareChangeQueryParameter parameter);
}
