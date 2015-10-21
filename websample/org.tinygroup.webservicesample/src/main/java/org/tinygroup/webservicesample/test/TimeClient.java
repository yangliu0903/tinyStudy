package org.tinygroup.webservicesample.test;  
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.tinygroup.webservicesample.UserImpl;
public class TimeClient {  
    public static void main(String[] args) throws Exception{  
        URL url = new URL("http://localhost:10100/myweb?wsdl");  
        // 第一个参数是服务的URI  
        // 第二个参数是在WSDL发布的服务名  
        QName qname = new QName("http://test.webservicesample.tinygroup.org/","TimeServerImplService");  
        // 创建服务  
        Service service = Service.create(url, qname);  
        // 提取端点接口，服务“端口”。  
        TimeServer eif = service.getPort(TimeServer.class);  
        System.out.println(eif.getTimeAsString());  
        System.out.println(eif.getTimeAsElapsed());  
        System.out.println(eif.getUserImpl(new UserImpl("dsd", "11")));
    }  
}  