package com.stockbone.spatula.impl;

import java.util.ArrayList;
import java.util.List;

import com.stockbone.components.stock.CandleData;
import com.stockbone.components.stock.ShareChange;
import com.stockbone.spatula.Spatula;
import com.stockbone.utils.ShareChangeUtils;

public class SteelSpatual implements Spatula {

	private ShareChangeUtils shareChangeUtils = new ShareChangeUtils();

	private List<ShareChange> shareChanges = null;

	@Override
	public List<Object> cook(List<CandleData> meats) {
		List<Object> list = new ArrayList<Object>();
		for (int i = 0; i < meats.size(); i++) {
			CandleData meat = meats.get(i);
			if (i == 0) {
				meat.setMood(1);
				meat.setMoodAmplitude(0);
				meat.setAveMood(1);
				continue;
			} else {
				// 今日均价
				float todayAve = meat.getAmount() / meat.getVolume();
				float yesterdayAve = meats.get(i - 1).getAveragePrice();
				ShareChange shareChange = shareChangeUtils.getCurrentChange(meat, shareChanges);
				float todayMood = ((todayAve - yesterdayAve) / yesterdayAve) * (meat.getVolume() * 1.0f / shareChange.getAShares()) + meats.get(i - 1).getMood();
				meat.setMood(todayMood);
				float yesterdayMood = meats.get(i - 1).getMood();
				meat.setMoodAmplitude((todayMood - yesterdayMood) / yesterdayMood);
				meat.setAveMood((todayMood + meats.get(i - 1).getAveMood()) / 2.0f);
			}
			list.add(meat);
		}
		return list;
	}

	public List<ShareChange> getShareChanges() {
		return shareChanges;
	}

	public void setShareChanges(List<ShareChange> shareChanges) {
		this.shareChanges = shareChanges;
	}

}
