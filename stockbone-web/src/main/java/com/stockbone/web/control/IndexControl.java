package com.stockbone.web.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class IndexControl {

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String index() {
		return "welcome";
	}

}
