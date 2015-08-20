package org.tinygroup.websample;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/session")
public class SessionTestAction {

	@RequestMapping("/setAttribute")
	public String setAttribute(String value,HttpServletRequest request){
		request.getSession().setAttribute("test", value);
		return "list.page";
	}
	
	@RequestMapping("/getAttribute")
	public String getAttribute(HttpServletRequest request){
		Object value=request.getSession().getAttribute("test");
		System.out.println(value);
		return "list.page";
	}
	
}
