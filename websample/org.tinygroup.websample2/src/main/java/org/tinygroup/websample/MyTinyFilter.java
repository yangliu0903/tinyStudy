package org.tinygroup.websample;

import java.io.IOException;

import javax.servlet.ServletException;

import org.tinygroup.commons.io.StreamUtil;
import org.tinygroup.weblayer.AbstractTinyFilter;
import org.tinygroup.weblayer.WebContext;

public class MyTinyFilter extends AbstractTinyFilter {

	public void preProcess(WebContext context) throws ServletException,
			IOException {
		String content= StreamUtil.readText(context.getRequest().getInputStream(),"UTF-8" ,true);
        System.out.println(content);
	}

	public void postProcess(WebContext context) throws ServletException,
			IOException {
         
//		BufferedServletOutputStream outputStream= (BufferedServletOutputStream)context.getResponse().getOutputStream();
		
		
		
		
		
		
		
//		BufferedWebContext bufferedWebContext=WebContextUtil.findWebContext(context, BufferedWebContext.class);
//		ByteArray beteArray = bufferedWebContext.popByteBuffer();
//		System.out.println(new String(beteArray.toByteArray()));
		
	}

	@Override
	protected void customInit() {

	}

}
