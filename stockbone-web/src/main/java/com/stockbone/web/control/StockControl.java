package com.stockbone.web.control;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stockbone.components.common.Page;
import com.stockbone.components.stock.Stock;
import com.stockbone.utils.valid.Preconditions;
import com.stockbone.web.components.StockQueryParameter;
import com.stockbone.web.service.CandleDataService;
import com.stockbone.web.service.ShareChangeService;
import com.stockbone.web.service.StockService;

@Controller
@RequestMapping("/stock")
public class StockControl {

	private static Logger logger = LoggerFactory.getLogger(StockControl.class);

	@Autowired
	private StockService stockService;
	@Autowired
	private CandleDataService candleDataService;
	@Autowired
	private ShareChangeService shareChangeService;

	@RequestMapping(value = "/addAndEditPage", method = RequestMethod.GET)
	public String addPage() {
		return "/stock/addAndEditPage";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public String add(HttpServletRequest request, Stock stock) {
		Preconditions.checkNotNull(stock);
		Integer row = stockService.addStock(stock);
		return row.toString();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	@ResponseBody
	public String delete(String code) {
		Preconditions.checkNotNull(code);
		Integer row = stockService.removeStock(code);
		return row.toString();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request) {
		Page<Stock> page = stockService.query(new StockQueryParameter());
		request.setAttribute("page", page);
		return "/stock/list";
	}

}
