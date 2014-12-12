package org.tinygroup.webservicesample.test;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import org.tinygroup.webservicesample.UserImpl;
@WebService
@SOAPBinding(style=Style.RPC)
public interface TimeServer {
	@WebMethod
	String getTimeAsString();
	@WebMethod
	long getTimeAsElapsed();
	@WebMethod
	@WebResult
	UserImpl getUserImpl(@WebParam UserImpl u);
	
	@WebMethod
	String getTimeAsString2(int i ,int j);
}