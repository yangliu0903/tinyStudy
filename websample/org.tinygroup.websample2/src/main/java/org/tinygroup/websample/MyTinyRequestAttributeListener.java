package org.tinygroup.websample;

import javax.servlet.ServletRequestAttributeEvent;

import org.tinygroup.weblayer.listener.TinyRequestAttributeListener;
import org.tinygroup.weblayer.listener.impl.SimpleBasicTinyConfigAware;

public class MyTinyRequestAttributeListener extends SimpleBasicTinyConfigAware
		implements TinyRequestAttributeListener {

	public void attributeAdded(ServletRequestAttributeEvent srae) {
		System.out.println(basicTinyConfig.getInitParameter("name"));
		System.out.println("-----attributeAdded----");

	}

	public void attributeRemoved(ServletRequestAttributeEvent srae) {
		System.out.println("-----attributeRemoved----");
	}

	public void attributeReplaced(ServletRequestAttributeEvent srae) {
		System.out.println("-----attributeReplaced----");
	}

	public int getOrder() {
		return DEFAULT_PRECEDENCE;
	}

}
