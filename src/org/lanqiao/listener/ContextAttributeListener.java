package org.lanqiao.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

public class ContextAttributeListener implements
		ServletContextAttributeListener {

	@Override
	public void attributeAdded(ServletContextAttributeEvent arg0) {
		//System.out.println("向application对象中添加了一个属性"+arg0.getName());
	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent arg0) {
		//System.out.println("向application对象中删除了一个属性"+arg0.getName());

	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent arg0) {
		//System.out.println("向application对象中修改了一个属性");

	}

}
