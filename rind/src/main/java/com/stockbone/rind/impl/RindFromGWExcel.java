package com.stockbone.rind.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stockbone.components.stock.CandleData;
import com.stockbone.components.stock.Stock;
import com.stockbone.rind.Rind;
import com.stockbone.utils.date.DateUtils;
import com.stockbone.utils.random.RandomUtils;
import com.stockbone.utils.valid.Preconditions;

/**
 * 大智慧excel解析。不准确。不要使用
 * @author qics
 *
 */
public class RindFromGWExcel implements Rind {

	private static Logger logger = LoggerFactory.getLogger(RindFromGWExcel.class);

	@Override
	public List<CandleData> rind(Stock stock, File file) {
		Preconditions.checkArgument(stock != null && file != null && file.exists(), "stock is null or file:{} do not exist.", new Object[] { file.getName() });
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage(), e);
		}
		return rind(stock, in);
	}

	@Override
	public List<CandleData> rind(Stock stock, InputStream in) {
		Preconditions.checkArgument(stock != null && in != null, "stock is null or FileInputStream is null.");
		List<CandleData> list = new ArrayList<CandleData>();
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			isr = new InputStreamReader(in, "UTF-8");
			br = new BufferedReader(isr);
			String newLine = br.readLine();// 标题行
			while ((newLine = br.readLine()) != null) {
				String[] datas = newLine.split("	");
				CandleData candleData = new CandleData();
				candleData.setId(RandomUtils.randomUUID());
				candleData.setStock(stock);
				candleData.setTime(DateUtils.getLong(datas[0], "yyyy-M-d"));
				candleData.setOpen(Float.valueOf(datas[1]));
				candleData.setHigh(Float.valueOf(datas[2]));
				candleData.setLow(Float.valueOf(datas[3]));
				candleData.setClose(Float.valueOf(datas[4]));
				String vol = datas[5].endsWith("00") ? datas[5] : datas[5] + "00";
				candleData.setVolume(Long.valueOf(vol));
				candleData.setAmount(Float.valueOf(datas[6]));
				candleData.setTradePeriod(60 * 24);
				list.add(candleData);
			}
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage(), e);
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return list;
	}

}
