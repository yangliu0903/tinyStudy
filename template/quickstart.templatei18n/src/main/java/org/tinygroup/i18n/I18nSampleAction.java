package org.tinygroup.i18n;


import org.tinygroup.weblayer.WebContext;
import org.tinygroup.weblayer.mvc.WebContextAware;
import org.tinygroup.tinymvc.annotation.Controller;
import org.tinygroup.tinymvc.annotation.RequestMapping;
import org.tinygroup.tinymvc.annotation.ResultKey;
import org.tinygroup.tinymvc.annotation.View;

@Controller
public class I18nSampleAction implements WebContextAware{
	private WebContext webContext;

	/**
	 * 国际化访问方式：
	 * 默认：localhost：port/pro/hello.do
	 * 中文：localhost：port/pro/hello.do？_lang=en_US:UTF-8
	 * 英文：localhost：port/pro/hello.do？_lang=zh_CN:UTF-8
	 *
	 * @return
	 */
	@RequestMapping(value={"/hello.do"})
	@View(value= "/hello.page")
	@ResultKey(value = "world")
	public String helloI18nMethod() {
		return "TinyTemplate I18n国际化";
	}

	public void setContext(WebContext webContext) {
		this.webContext = webContext;
	}
}
