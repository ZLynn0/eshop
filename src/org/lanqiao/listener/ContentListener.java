package org.lanqiao.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContentListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		//System.out.println("context消失了");
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		//System.out.println("context出来了");
		
	}

}
