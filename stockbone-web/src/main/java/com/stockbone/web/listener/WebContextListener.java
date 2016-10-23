package com.stockbone.web.listener;

import java.util.Calendar;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.stockbone.web.utils.Razor;

public class WebContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		Timer timer = new Timer();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 15);
		calendar.set(Calendar.MINUTE, 46);
		timer.scheduleAtFixedRate(new Razor(), calendar.getTime(), 1 * 60 * 60 * 24 * 1000);
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// do nothing
	}

}
