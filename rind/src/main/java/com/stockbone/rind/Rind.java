package com.stockbone.rind;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import com.stockbone.components.stock.CandleData;
import com.stockbone.components.stock.Stock;

public interface Rind {

	List<CandleData> rind(Stock stock, File file);

	List<CandleData> rind(Stock stock, InputStream in);

}
