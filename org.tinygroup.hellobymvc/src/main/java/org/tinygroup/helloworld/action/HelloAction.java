package org.tinygroup.helloworld.action;

import org.tinygroup.weblayer.WebContext;
import org.tinygroup.weblayer.mvc.WebContextAware;
import org.tinygroup.weblayer.mvc.annotation.Controller;
import org.tinygroup.weblayer.mvc.annotation.RequestMapping;
import org.tinygroup.weblayer.mvc.annotation.ResultKey;
import org.tinygroup.weblayer.mvc.annotation.View;

@Controller()
public class HelloAction implements WebContextAware{

	private WebContext webContext;
	
	public void setContext(WebContext webContext) {
		this.webContext = webContext;
	}
	
	@RequestMapping(value={"/helloByMvc.do"})
	@View(value="/helloworld/helloresult.page")
	@ResultKey(value="result")
	public String sayHelloMethod(String name) {
		if (name == null) {
			name = "world";
		}
		return  String.format("Hello, %s", name);
	}

}
