package com.stockbone.web.dao;

import java.util.List;

import com.stockbone.components.common.Page;
import com.stockbone.components.stock.ShareChange;
import com.stockbone.web.components.ShareChangeQueryParameter;

public interface ShareChangeDao {

	int insertShareChange(List<ShareChange> list);

	int deleteStock(String code, long changeDate);
	
	int merge(String code);

	Page<ShareChange> select(ShareChangeQueryParameter parameter);


}
