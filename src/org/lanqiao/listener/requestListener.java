package org.lanqiao.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class requestListener implements ServletRequestListener {

	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
		//System.out.println("request销毁了");

	}

	@Override
	public void requestInitialized(ServletRequestEvent arg0) {
		//System.out.println("request创建了");

	}

}
