package com.stockbone.components.stock;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 蜡烛数据
 */
public class CandleData implements Serializable {

	private static final long serialVersionUID = 6275749251913841884L;

	/**
	 * 唯一标识
	 */
	private String id;
	/**
	 * 股票
	 */
	private Stock stock;
	/**
	 * 交易时间
	 */
	private long time;
	/**
	 * 交易间隔(分钟)
	 */
	private long tradePeriod;
	/**
	 * 开盘价
	 */
	private float open;
	/**
	 * 最高价
	 */
	private float high;
	/**
	 * 最低价
	 */
	private float low;
	/**
	 * 最后一次交易价格，相当于收盘价
	 */
	private float close;
	/**
	 * 最后一次交易价格 (今天的收盘价当做加权价格)
	 */
	private float adj;
	/**
	 * 成交量
	 */
	private long volume;
	/**
	 * 成交额
	 */
	private float amount;
	/**
	 * 历史平均价格
	 */
	private float averagePrice;
	/**
	 * 宏观股民情绪
	 */
	private float mood;
	/**
	 * 宏观股民情绪振幅
	 */
	private float moodAmplitude;
	/**
	 * 宏观股民情绪振幅
	 */
	private float aveMood;
	/**
	 * 五日均线
	 */
	private float ma5;
	/**
	 * 15均线
	 */
	private float ma15;
	/**
	 * 30均线
	 */
	private float ma30;
	/**
	 * 60均线
	 */
	private float ma60;
	/**
	 * 120均线
	 */
	private float ma120;

	public String getFormatTime() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(time));
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public long getTradePeriod() {
		return tradePeriod;
	}

	public void setTradePeriod(long tradePeriod) {
		this.tradePeriod = tradePeriod;
	}

	public float getOpen() {
		return open;
	}

	public void setOpen(float open) {
		this.open = open;
	}

	public float getHigh() {
		return high;
	}

	public void setHigh(float high) {
		this.high = high;
	}

	public float getLow() {
		return low;
	}

	public void setLow(float low) {
		this.low = low;
	}

	public float getClose() {
		return close;
	}

	public void setClose(float close) {
		this.close = close;
	}

	public long getVolume() {
		return volume;
	}

	public void setVolume(long volume) {
		this.volume = volume;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public float getAdj() {
		return adj;
	}

	public void setAdj(float adj) {
		this.adj = adj;
	}

	public float getAveragePrice() {
		return averagePrice;
	}

	public void setAveragePrice(float averagePrice) {
		this.averagePrice = averagePrice;
	}

	public float getMa5() {
		return ma5;
	}

	public void setMa5(float ma5) {
		this.ma5 = ma5;
	}

	public float getMa15() {
		return ma15;
	}

	public void setMa15(float ma15) {
		this.ma15 = ma15;
	}

	public float getMa30() {
		return ma30;
	}

	public void setMa30(float ma30) {
		this.ma30 = ma30;
	}

	public float getMa60() {
		return ma60;
	}

	public void setMa60(float ma60) {
		this.ma60 = ma60;
	}

	public float getMa120() {
		return ma120;
	}

	public void setMa120(float ma120) {
		this.ma120 = ma120;
	}

	public float getMood() {
		return mood;
	}

	public void setMood(float mood) {
		this.mood = mood;
	}

	public float getMoodAmplitude() {
		return moodAmplitude;
	}

	public void setMoodAmplitude(float moodAmplitude) {
		this.moodAmplitude = moodAmplitude;
	}

	public float getAveMood() {
		return aveMood;
	}

	public void setAveMood(float aveMood) {
		this.aveMood = aveMood;
	}

	@Override
	public String toString() {
		return "CandleData [id=" + id + ", stock=" + stock + ", time=" + time + ", tradePeriod=" + tradePeriod + ", open=" + open + ", high=" + high + ", low=" + low + ", close=" + close + ", volume=" + volume + ", amount=" + amount + ", adj=" + adj + ", averagePrice=" + averagePrice + ", ma5=" + ma5 + ", ma15=" + ma15 + ", ma30=" + ma30 + ", ma60=" + ma60 + ", ma120=" + ma120 + "]";
	}

}
