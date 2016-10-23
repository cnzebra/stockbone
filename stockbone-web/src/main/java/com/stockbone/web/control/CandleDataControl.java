package com.stockbone.web.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.stockbone.components.common.Page;
import com.stockbone.components.stock.CandleData;
import com.stockbone.components.stock.ShareChange;
import com.stockbone.components.stock.Stock;
import com.stockbone.knife.CowKnife;
import com.stockbone.rind.Rind;
import com.stockbone.rind.impl.RindFromJPKAExcel;
import com.stockbone.spatula.Spatula;
import com.stockbone.spatula.impl.IronSpatula;
import com.stockbone.spatula.impl.SteelSpatual;
import com.stockbone.web.components.CandleDataQueryParameter;
import com.stockbone.web.components.ShareChangeQueryParameter;
import com.stockbone.web.service.CandleDataService;
import com.stockbone.web.service.ShareChangeService;

@Controller
@RequestMapping("/candledata")
public class CandleDataControl {

	private static Logger logger = LoggerFactory.getLogger(CandleDataControl.class);
	private Spatula ironSpatula = new IronSpatula();
	private SteelSpatual steelSpatula = new SteelSpatual();

	@Autowired
	private CandleDataService candleDataService;
	@Autowired
	private ShareChangeService shareChangeService;

	@RequestMapping(value = "/cowKnife", method = RequestMethod.GET)
	public String cowKnife(HttpServletRequest request, HttpServletResponse response) {
		String code = request.getParameter("code");
		code = StringUtils.defaultString(code, "600009");
		CandleDataQueryParameter parameter = new CandleDataQueryParameter();
		parameter.setCode(code);
		Page<CandleData> page = candleDataService.query(parameter);
		ShareChangeQueryParameter shareParam = new ShareChangeQueryParameter();
		shareParam.setCode(code);
		Page<ShareChange> changePage = shareChangeService.query(shareParam);
		CowKnife knife = new CowKnife();
		List<CandleData> candles = knife.cut(page.getContent(), changePage.getContent());
		StringBuffer average = new StringBuffer();
		StringBuffer price = new StringBuffer();
		StringBuffer ma60 = new StringBuffer();
		for (CandleData candle : candles) {
			price.append(candle.getOpen() + ",");
			average.append(candle.getAveragePrice() + ",");
			ma60.append(candle.getMa60() + ",");
		}
		request.setAttribute("average", StringUtils.substringBeforeLast(average.toString(), ","));
		request.setAttribute("price", StringUtils.substringBeforeLast(price.toString(), ","));
		request.setAttribute("ma60", StringUtils.substringBeforeLast(ma60.toString(), ","));
		steelSpatula.setShareChanges(changePage.getContent());
//		List list = ironSpatula.cook(candles);
		request.setAttribute("meat", steelSpatula.cook(candles));
		request.setAttribute("code", code);
		return "/candledata/cowKnife";
	}

	@RequestMapping(value = "/uploadCandleData", method = RequestMethod.POST)
	@ResponseBody
	public String uploadCandleData(@RequestParam MultipartFile candleDataFile, @RequestParam String code) {
		String result = "SUCCESS";
		try {
			Rind rind = new RindFromJPKAExcel();
			Stock stock = new Stock();
			stock.setCode(code);
			List<CandleData> datas = rind.rind(stock, candleDataFile.getInputStream());
			candleDataService.addCandleData(datas);
		} catch (IOException e) {
			result = "ERROR";
			logger.error(e.getMessage(), e);
		}
		return result;
	}

}
