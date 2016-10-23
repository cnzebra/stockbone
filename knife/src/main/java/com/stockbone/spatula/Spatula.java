package com.stockbone.spatula;

import java.util.List;

import com.stockbone.components.stock.CandleData;

public interface Spatula {

	List<Object> cook(List<CandleData> meat);

}
