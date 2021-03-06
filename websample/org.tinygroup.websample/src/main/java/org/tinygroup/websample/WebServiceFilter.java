package org.tinygroup.websample;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.tinygroup.commons.io.StreamUtil;

public class WebServiceFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
//		WebServiceRequest serviceRequest = new WebServiceRequest(
//				(HttpServletRequest) request);
		ServletRequest serviceRequest=request;
		String content = StreamUtil.readText(serviceRequest.getInputStream(),
				"UTF-8", false);
		System.out.println(content);
		content = StreamUtil.readText(serviceRequest.getInputStream(),
				"UTF-8", false);
		System.out.println(content);
		WebServiceResponse webServiceResponse = new WebServiceResponse(
				(HttpServletResponse) response);
		chain.doFilter(serviceRequest, webServiceResponse);
		String param = serviceRequest.getParameter("name");
		System.out.println(param);
		// 从response获取输出流
		byte[] bytes = webServiceResponse.getByteArrayOutputStream()
				.toByteArray().toByteArray();
		String responseContent = new String(bytes);
		System.out.println(responseContent);// 改变响应数据
		response.getOutputStream().write(bytes);// 把改变后的数据重新写到response中
	}

	// public void doFilter(ServletRequest request, ServletResponse response,
	// FilterChain chain) throws IOException, ServletException {
	//
	// String content= StreamUtil.readText(request.getInputStream(),"UTF-8"
	// ,true);
	// System.out.println(content);
	// request.getInputStream().reset();
	// // WebServiceResponse webServiceResponse=new
	// WebServiceResponse((HttpServletResponse) response);
	// chain.doFilter(request, response);
	// //从response获取输出流
	// // byte[]
	// bytes=webServiceResponse.getByteArrayOutputStream().toByteArray().toByteArray();
	// // String responseContent=new String(bytes);
	// // System.out.println(responseContent);//改变响应数据
	// // response.getOutputStream().write(bytes);//把改变后的数据重新写到response中
	// }

	public void destroy() {

	}

	public static void main(String[] args) {

//		Pattern pattern = Pattern.compile("\\b((?!download).*)+\\b");
		Pattern pattern=Pattern.compile("\\b((?!baidu)\\w)+\\b");
		
		System.out.println(pattern.matcher("http://www.baidu.com").matches());
		System.out.println(pattern.matcher("http://www.ffd.com").matches());
//		System.out.println(pattern.matcher("http://www.baidu.com").matches());
//		System.out.println(pattern.matcher("http://www.baidu.com").matches());

		System.out
				.println(pattern
						.matcher(
								"/cl/login/CPAYUSERLOGIN000001/j/locale=JAPAN&o=signIn&loginUserId=hscpay&isFreshAuth=false&userPwd=69730ccbae7227fd9e02bf98f2254552&locale=JAPAN&verifyCode=boder/1/login")
						.matches());
		System.out
		.println(pattern
				.matcher(
						"download/dsd/ff")
				.matches());
		System.out
		.println(pattern
				.matcher(
						"ddf/downloadss/dsd/ff")
				.matches());

	}

}
