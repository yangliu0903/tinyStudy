package quickstart.servlettemplate;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.tinygroup.template.TemplateContext;
import org.tinygroup.templateservletext.TinyServlet;

public class ServeltTemplateDemo extends TinyServlet{

	private static final long serialVersionUID = 5809152848883767606L;

	@Override
	protected String handleRequest(HttpServletRequest request, HttpServletResponse response, TemplateContext ctx)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        ctx.put("name","Tiny User");
        ctx.put("date",new Date());
        return "index.page";	
    }
	
	//此方法非必须，你可以酌情而定
    @Override
    protected void setContentType(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
    }
	
}
