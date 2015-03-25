package org.tinygroup.websample;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.tinygroup.weblayer.WebContext;
import org.tinygroup.weblayer.listener.ServletContextHolder;
import org.tinygroup.weblayer.mvc.WebContextAware;
import org.tinygroup.weblayer.mvc.annotation.Controller;
import org.tinygroup.weblayer.mvc.annotation.RequestMapping;

@Controller()
@RequestMapping(value = { "/listener" })
public class ListenerAction implements WebContextAware {

	private WebContext webContext;

	public void setContext(WebContext webContext) {
		this.webContext = webContext;
	}

	@RequestMapping(value = { "/test.do" })
	public void testListenerMethod() {
        String name="name";
        ServletContext servletContext=ServletContextHolder.getServletContext();
        servletContext.setAttribute(name, "contextValue");
        servletContext.removeAttribute(name);
        HttpSession session=webContext.getRequest().getSession();
        session.setAttribute(name, "sessionValue");
        session.setAttribute("listener", new WebListener());
        session.removeAttribute(name);
        session.removeAttribute("listener");
        HttpServletRequest request=webContext.getRequest();
        request.setAttribute(name,"requestValue");
        request.removeAttribute(name);
	}
}
