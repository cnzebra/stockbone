package com.stockbone.knife;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.stockbone.components.stock.CandleData;
import com.stockbone.components.stock.ShareChange;
import com.stockbone.utils.ShareChangeUtils;

public class CowKnife {

	private ShareChangeUtils shareChangeUtils = new ShareChangeUtils();

	public List<CandleData> cut(List<CandleData> flesh, List<ShareChange> changes) {
		List<CandleData> meat = new ArrayList<CandleData>();
		Queue<Float> queue = new LinkedList<Float>();
		float price = -1;
		for (int i = 0; i < flesh.size(); i++) {
			CandleData data = flesh.get(i);
			ShareChange change = shareChangeUtils.getCurrentChange(data, changes);
			if (price < 0) {
				price = data.getOpen();
			}
			float averagePrice = getAveragePrice(change.getAShares(), price, data.getAmount(), data.getVolume());
			queue.offer(data.getOpen());
			if (i >= 60) {
				queue.poll();
			}
			data.setAveragePrice(averagePrice);
			data.setMa60(getMA60(queue));
			price = averagePrice;
			meat.add(data);
		}
		return meat;
	}

	public float getMA60(Queue<Float> queue) {
		List<Float> mas = new ArrayList<Float>();
		mas.addAll(queue);
		float totalMA = 0;
		for (Float ma : mas) {
			totalMA += ma;
		}
		return totalMA / 60;
	}

	/**
	 * 计算平均价格
	 * 
	 * @param totalNum流通股本
	 * @param price当前均价
	 * @param amount成交额
	 * @param volume成交量
	 * @return
	 */
	public float getAveragePrice(long totalShares, float price, float amount, float volume) {
		// 换手率
		float turnoverRate = volume / totalShares;
		float preAmount = totalShares * (1 - turnoverRate) * price;
		return (preAmount + amount) / totalShares;
	}

}
