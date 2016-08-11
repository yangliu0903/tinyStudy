package org.tinygroup.websample;

import java.lang.annotation.Annotation;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.commons.tools.AnnotationUtils;
import org.tinygroup.weblayer.WebContext;

@Controller
@RequestMapping("/session")
public class SessionTestAction {
	
	@Value("${cache_region}")
	private String value;

	@RequestMapping("/setAttribute")
	@ResponseBody
	public String setAttribute(String value,HttpServletRequest request){
		request.getSession().setAttribute("test", value);
		return "{setAttribute:"+value+"}";
	}
	
	@RequestMapping("/getAttribute")
	@ResponseBody
	public String getAttribute(HttpServletRequest request,WebContext webContext){
		Object value=request.getSession().getAttribute("test");
		System.out.println(webContext.get("test"));
		System.out.println(value);
		return "{getAttribute:"+value+"}";
	}
	
	@RequestMapping("/removeAttribute")
	@ResponseBody
	public String removeAttribute(HttpServletRequest request){
		request.getSession().removeAttribute("test");
		return "{removeAttribute:test}";
	}
	
	
	public static void main(String[] args) {
		
		Annotation annotation=AnnotationUtils.findAnnotation(SessionTestAction.class, RequestMapping.class);
	    System.out.println(annotation.annotationType());
		
		
	}
}
