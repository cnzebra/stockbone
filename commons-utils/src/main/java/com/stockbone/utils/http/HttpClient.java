package com.stockbone.utils.http;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class HttpClient {

	/**
	 * 根据url，获取该资源下的相应内容
	 * 
	 * @param url
	 * @param headers
	 * @param charset
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static String getResource(String url, Map<String, String> headers, String charset) throws ClientProtocolException, IOException {
		String result = null;
		charset = (StringUtils.isEmpty(charset)) ? "utf-8" : charset;
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		if (headers != null && headers.size() > 0) {
			Iterator<Entry<String, String>> iterator = headers.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<String, String> entry = iterator.next();
				httpGet.addHeader(entry.getKey(), entry.getValue());
			}
		}
		HttpResponse httpResponse = httpClient.execute(httpGet);
		if (httpResponse.getStatusLine().getStatusCode() == 200) {
			HttpEntity entity = httpResponse.getEntity();
			result = EntityUtils.toString(entity, charset);
		}
		httpClient.getConnectionManager().shutdown();
		return result;
	}

	/**
	 * 根据url，获取该资源下的相应内容
	 * 
	 * @param url
	 * @param headers
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static String getResource(String url, Map<String, String> headers) throws ClientProtocolException, IOException {
		return getResource(url, headers, null);
	}

	/**
	 * 根据url，获取该资源下的相应内容
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static String getResource(String url) throws ClientProtocolException, IOException {
		return getResource(url, null, null);
	}
}
