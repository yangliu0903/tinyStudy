package org.tinygroup.websample;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * web监听器测测试类
 * 
 * @author renhui
 * 
 */
public class WebListener implements ServletContextListener,
		ServletRequestListener, ServletContextAttributeListener,
		ServletRequestAttributeListener, HttpSessionAttributeListener,
		HttpSessionBindingListener, HttpSessionActivationListener,
		HttpSessionListener {

	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("-------sessionCreated--------");
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("-------sessionDestroyed--------");
	}

	public void sessionWillPassivate(HttpSessionEvent se) {
		System.out.println("-------sessionWillPassivate--------");
	}

	public void sessionDidActivate(HttpSessionEvent se) {
		System.out.println("-------sessionDidActivate--------");
	}

	public void valueBound(HttpSessionBindingEvent event) {
		System.out.println("-------valueBound--------");
	}

	public void valueUnbound(HttpSessionBindingEvent event) {
		System.out.println("-------valueUnbound--------");
	}

	public void attributeAdded(HttpSessionBindingEvent se) {
		System.out.println("-------sessionAttributeAdded--------");
	}

	public void attributeRemoved(HttpSessionBindingEvent se) {
		System.out.println("-------sessionAttributeRemoved--------");
	}

	public void attributeReplaced(HttpSessionBindingEvent se) {
		System.out.println("-------sessionAttributeReplaced--------");
	}

	public void attributeAdded(ServletRequestAttributeEvent srae) {
		System.out.println("-------requestAttributeAdded--------");
	}

	public void attributeRemoved(ServletRequestAttributeEvent srae) {
		System.out.println("-------requestAttributeRemoved--------");
	}

	public void attributeReplaced(ServletRequestAttributeEvent srae) {
		System.out.println("-------requestAttributeReplaced--------");
	}

	public void attributeAdded(ServletContextAttributeEvent scab) {
		System.out.println("-------contextAttributeAdded--------");
	}

	public void attributeRemoved(ServletContextAttributeEvent scab) {
		System.out.println("-------contextAttributeRemoved--------");
	}

	public void attributeReplaced(ServletContextAttributeEvent scab) {
		System.out.println("-------contextAttributeReplaced--------");
	}

	public void requestDestroyed(ServletRequestEvent sre) {
		System.out.println("-------requestDestroyed--------");
	}

	public void requestInitialized(ServletRequestEvent sre) {
		System.out.println("-------requestInitialized--------");
	}

	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("-------contextInitialized--------");
	}

	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("-------contextDestroyed--------");
	}

}
