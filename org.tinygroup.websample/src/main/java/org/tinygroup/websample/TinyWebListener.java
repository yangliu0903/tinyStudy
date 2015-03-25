package org.tinygroup.websample;

import java.util.Map;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestEvent;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;

import org.tinygroup.weblayer.BasicTinyConfig;
import org.tinygroup.weblayer.listener.TinyRequestAttributeListener;
import org.tinygroup.weblayer.listener.TinyRequestListener;
import org.tinygroup.weblayer.listener.TinyServletContextAttributeListener;
import org.tinygroup.weblayer.listener.TinyServletContextListener;
import org.tinygroup.weblayer.listener.TinySessionActivationListener;
import org.tinygroup.weblayer.listener.TinySessionAttributeListener;
import org.tinygroup.weblayer.listener.TinySessionBindingListener;
import org.tinygroup.weblayer.listener.TinySessionListener;

/**
 * web监听器测测试类
 * 
 * @author renhui
 * 
 */
public class TinyWebListener implements TinyServletContextListener,
		TinyRequestListener, TinyServletContextAttributeListener,
		TinyRequestAttributeListener, TinySessionAttributeListener,
		TinySessionBindingListener, TinySessionActivationListener,
		TinySessionListener {

	private BasicTinyConfig basicTinyConfig;

	// public void printConfigParam() {
	// Map<String, String> params = basicTinyConfig.getParameterMap();
	// for (String key : params.keySet()) {
	// System.out.println("---key:" + key + "value:" + params.get(key)
	// + "---");
	// }
	//
	// }

	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("-------sessionCreated--------");
		// printConfigParam();
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("-------sessionDestroyed--------");
		// printConfigParam();
	}

	public void sessionWillPassivate(HttpSessionEvent se) {
		System.out.println("-------sessionWillPassivate--------");
		// printConfigParam();
	}

	public void sessionDidActivate(HttpSessionEvent se) {
		System.out.println("-------sessionDidActivate--------");
		// printConfigParam();
	}

	public void valueBound(HttpSessionBindingEvent event) {
		System.out.println("-------valueBound--------");
		// printConfigParam();
	}

	public void valueUnbound(HttpSessionBindingEvent event) {
		System.out.println("-------valueUnbound--------");
		// printConfigParam();
	}

	public void attributeAdded(HttpSessionBindingEvent se) {
		System.out.println("-------sessionAttributeAdded--------");
		// printConfigParam();
	}

	public void attributeRemoved(HttpSessionBindingEvent se) {
		System.out.println("-------sessionAttributeRemoved--------");
		// printConfigParam();
	}

	public void attributeReplaced(HttpSessionBindingEvent se) {
		System.out.println("-------sessionAttributeReplaced--------");
		// printConfigParam();
	}

	public void attributeAdded(ServletRequestAttributeEvent srae) {
		System.out.println("-------requestAttributeAdded--------");
		// printConfigParam();
	}

	public void attributeRemoved(ServletRequestAttributeEvent srae) {
		System.out.println("-------requestAttributeRemoved--------");
		// printConfigParam();
	}

	public void attributeReplaced(ServletRequestAttributeEvent srae) {
		System.out.println("-------requestAttributeReplaced--------");
		// printConfigParam();
	}

	public void attributeAdded(ServletContextAttributeEvent scab) {
		System.out.println("-------contextAttributeAdded--------");
		// printConfigParam();
	}

	public void attributeRemoved(ServletContextAttributeEvent scab) {
		System.out.println("-------contextAttributeRemoved--------");
		// printConfigParam();
	}

	public void attributeReplaced(ServletContextAttributeEvent scab) {
		System.out.println("-------contextAttributeReplaced--------");
		// printConfigParam();
	}

	public void requestDestroyed(ServletRequestEvent sre) {
		System.out.println("-------requestDestroyed--------");
		// printConfigParam();
	}

	public void requestInitialized(ServletRequestEvent sre) {
		System.out.println("-------requestInitialized--------");
		// printConfigParam();
	}

	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("-------contextInitialized--------");
		// printConfigParam();
	}

	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("-------contextDestroyed--------");
		// printConfigParam();
	}

	public int getOrder() {
		return 0;
	}

	public void setBasicConfig(BasicTinyConfig basicTinyConfig) {
		this.basicTinyConfig = basicTinyConfig;
	}

}
