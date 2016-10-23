package com.stockbone.web.dao;

import java.util.List;

import com.stockbone.components.common.Page;
import com.stockbone.components.stock.CandleData;
import com.stockbone.web.components.CandleDataQueryParameter;

public interface CandleDataDao {

	int insertCandleData(List<CandleData> list);

	Page<CandleData> select(CandleDataQueryParameter parameter);

}
