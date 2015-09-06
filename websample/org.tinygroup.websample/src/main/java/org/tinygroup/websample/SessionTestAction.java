package org.tinygroup.websample;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/session")
public class SessionTestAction {

	@RequestMapping("/setAttribute")
	@ResponseBody
	public String setAttribute(String value,HttpServletRequest request){
		request.getSession().setAttribute("test", value);
		return "{setAttribute:"+value+"}";
	}
	
	@RequestMapping("/getAttribute")
	@ResponseBody
	public String getAttribute(HttpServletRequest request){
		Object value=request.getSession().getAttribute("test");
		System.out.println(value);
		return "{getAttribute:"+value+"}";
	}
	
	@RequestMapping("/removeAttribute")
	@ResponseBody
	public String removeAttribute(HttpServletRequest request){
		request.getSession().removeAttribute("test");
		return "{removeAttribute:test}";
	}
	
}
