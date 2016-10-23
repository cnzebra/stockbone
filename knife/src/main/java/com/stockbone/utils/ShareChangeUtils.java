package com.stockbone.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.stockbone.components.stock.CandleData;
import com.stockbone.components.stock.ShareChange;
import com.stockbone.utils.valid.Preconditions;

public class ShareChangeUtils {

	public ShareChange getCurrentChange(CandleData candleData, List<ShareChange> changes) {
		ShareChange change = null;
		Preconditions.checkNotNull(changes);
		Collections.sort(changes, new Comparator<ShareChange>() {
			@Override
			public int compare(ShareChange o1, ShareChange o2) {
				Long d1 = o1.getChangeDate();
				Long d2 = o2.getChangeDate();
				return d1.compareTo(d2);
			}
		});
		for (int i = 0; i < changes.size(); i++) {
			ShareChange temp = changes.get(i);
			if (i == 0) {
				change = temp;
			}
			if (candleData.getTime() >= temp.getChangeDate()) {
				change = temp;
			}
		}
		return change;
	}

}
