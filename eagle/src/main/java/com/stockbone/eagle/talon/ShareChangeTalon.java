package com.stockbone.eagle.talon;

import java.util.List;

import com.stockbone.components.stock.ShareChange;
import com.stockbone.components.stock.Stock;

public interface ShareChangeTalon {

	List<ShareChange> fetchShareChange(Stock stock) throws Exception;

}
