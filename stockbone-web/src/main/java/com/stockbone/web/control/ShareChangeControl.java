package com.stockbone.web.control;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stockbone.components.common.Page;
import com.stockbone.components.stock.ShareChange;
import com.stockbone.components.stock.Stock;
import com.stockbone.eagle.talon.ShareChangeTalon;
import com.stockbone.eagle.talon.impl.DefaultShareChangeTalon;
import com.stockbone.utils.random.RandomUtils;
import com.stockbone.utils.valid.Preconditions;
import com.stockbone.web.components.ShareChangeQueryParameter;
import com.stockbone.web.components.StockQueryParameter;
import com.stockbone.web.service.ShareChangeService;
import com.stockbone.web.service.StockService;

@Controller
@RequestMapping("/sharechange")
public class ShareChangeControl {

	@Autowired
	private StockService stockService;
	@Autowired
	private ShareChangeService shareChangeService;

	@RequestMapping(value = "/addAndEditPage", method = RequestMethod.GET)
	public String addPage() {
		return "/sharechange/addAndEditPage";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public String add(ShareChange shareChange) {
		Preconditions.checkNotNull(shareChange);
		shareChange.setId(RandomUtils.randomUUID());
		List<ShareChange> list = new ArrayList<ShareChange>();
		list.add(shareChange);
		Integer row = shareChangeService.addShareChange(list);
		return row.toString();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	@ResponseBody
	public String delete(String code, Long changeDate) {
		Preconditions.checkArgument(StringUtils.isNotEmpty(code) && changeDate != null);
		Integer row = shareChangeService.removeShareChange(code, changeDate);
		return row.toString();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, ShareChangeQueryParameter param) {
		Page<ShareChange> page = shareChangeService.query(param);
		request.setAttribute("page", page);
		request.setAttribute("shareChangeStockCode", param.getCode());
		return "/sharechange/list";
	}

	@RequestMapping(value = "/merge", method = RequestMethod.GET)
	@ResponseBody
	public String merge(HttpServletRequest request, String code) {
		Integer line = shareChangeService.mergr(code);
		return line.toString();
	}

	@RequestMapping(value = "/complete", method = RequestMethod.GET)
	@ResponseBody
	public String complete(HttpServletRequest request, String code) throws Exception {
		Integer line = 0;
		ShareChangeTalon talon = new DefaultShareChangeTalon();
		StockQueryParameter parameter = new StockQueryParameter();
		parameter.setCode(code);
		Page<Stock> page = stockService.query(parameter);
		if (page != null && page.getContent() != null && page.getContent().size() > 0) {
			List<ShareChange> shareChanges = talon.fetchShareChange(page.getContent().get(0));
			line = shareChangeService.addShareChange(shareChanges);
		}
		return line.toString();
	}

}
