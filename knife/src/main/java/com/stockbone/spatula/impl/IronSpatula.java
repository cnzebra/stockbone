package com.stockbone.spatula.impl;

import java.util.ArrayList;
import java.util.List;

import com.stockbone.components.stock.CandleData;
import com.stockbone.spatula.Spatula;

public class IronSpatula implements Spatula {

	@Override
	public List<Object> cook(List<CandleData> meat) {
		List<Object> meal = new ArrayList<Object>();
		for (int i = 0; i < meat.size(); i++) {
			CandleData candleData = meat.get(i);
			if (candleData.getAveragePrice() > candleData.getOpen() * 1.01) {
				if (i < 60) {
					continue;
				}
				CandleData pre60Data = meat.get(i - 60);
				float gradient = (candleData.getMa60() - pre60Data.getMa60());
				if (gradient < 0) {
					continue;
				}
				boolean flag = true;
				for (int j = 1; j <= 15; j++) {
					CandleData preData = meat.get(i - j);
					if (candleData.getAveragePrice() < preData.getAveragePrice()) {
						flag = false;
					}
				}
				if (flag) {
					meal.add(candleData);
				}
			}
		}
		return meal;
	}

}
