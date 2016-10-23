package com.stockbone.eagle.talon.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.ClientProtocolException;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.stockbone.components.stock.ShareChange;
import com.stockbone.components.stock.Stock;
import com.stockbone.eagle.talon.ShareChangeTalon;
import com.stockbone.utils.date.DateUtils;
import com.stockbone.utils.http.HttpClient;
import com.stockbone.utils.random.RandomUtils;
import com.stockbone.utils.valid.Preconditions;

public class DefaultShareChangeTalon implements ShareChangeTalon {

	@Override
	public List<ShareChange> fetchShareChange(Stock stock) throws Exception {
		List<ShareChange> changes = null;
		Preconditions.checkArgument(stock == null || stock.getExchange() == null || StringUtils.isNotEmpty(stock.getCode()));
		switch (stock.getExchange()) {
		case SH:
			changes = fetchShareChangeFromSSE(stock);
			break;
		case SZ:

			break;
		case HK:

			break;
		}
		return changes;
	}

	/**
	 * 从上证交易所抓取股本变动
	 * 
	 * @param stock
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public List<ShareChange> fetchShareChangeFromSSE(Stock stock) throws Exception {
		List<ShareChange> changes = new ArrayList<ShareChange>();
		String fetchURL = "http://query.sse.com.cn/security/stock/queryEquityChangeAndReason.do?jsonCallBack=jsonpCallback77883&isPagination=false&companyCode=" + stock.getCode() + "&_=" + System.currentTimeMillis();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Accept", "*/*");
		headers.put("Accept-Language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
		headers.put("Connection", "keep-alive");
		headers.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:35.0) Gecko/20100101 Firefox/35.0");
		headers.put("Host", "query.sse.com.cn");
		headers.put("Referer", "http://www.sse.com.cn/assortment/stock/list/stockdetails/capital/index.shtml?COMPANY_CODE=" + stock.getCode() + "&SecurityCode=-");
		String result = HttpClient.getResource(fetchURL, headers);
		Preconditions.checkNotNull(result);
		result = StringUtils.substringAfter(result, "(");
		result = StringUtils.substringBeforeLast(result, ")");
		JSONObject jsonObj = JSONObject.parseObject(result);
		JSONArray changeJsons = jsonObj.getJSONObject("pageHelp").getJSONArray("data");
		for (Object object : changeJsons) {
			ShareChange change = new ShareChange();
			JSONObject changeJson = (JSONObject) object;
			change.setId(RandomUtils.randomUUID());
			change.setCode(stock.getCode());
			change.setTotalShares(changeJson.getLong("totalShares") * 10000);
			change.setAShares(changeJson.getLong("AShares") * 10000);
			change.setBShares(changeJson.getLong("BShares") * 10000);
			change.setChangeReason(changeJson.getString("changeReasonDesc"));
			change.setChangeDate(DateUtils.parse(changeJson.getString("realDate"), "yyyy-MM-dd").getTime());
			changes.add(change);
		}
		return changes;
	}

}
