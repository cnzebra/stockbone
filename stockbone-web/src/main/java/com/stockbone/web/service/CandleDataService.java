package com.stockbone.web.service;

import java.util.List;

import com.stockbone.components.common.Page;
import com.stockbone.components.stock.CandleData;
import com.stockbone.web.components.CandleDataQueryParameter;

public interface CandleDataService {

	int addCandleData(List<CandleData> list);

	Page<CandleData> query(CandleDataQueryParameter parameter);

}
