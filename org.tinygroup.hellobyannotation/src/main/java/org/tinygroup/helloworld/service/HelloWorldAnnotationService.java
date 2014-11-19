package org.tinygroup.helloworld.service;

import org.tinygroup.service.annotation.ServiceComponent;
import org.tinygroup.service.annotation.ServiceMethod;
import org.tinygroup.service.annotation.ServiceResult;
import org.tinygroup.service.annotation.ServiceViewMapping;

@ServiceComponent()
public class HelloWorldAnnotationService{
	@ServiceMethod(serviceId = "helloByAnnotation")
	@ServiceResult(name = "result")
	@ServiceViewMapping("/helloworld/helloresult.page")
	public String sayHello(String name) {
		if (name == null) {
			name = "world";
		}
		return "hello," + name;
	}
}
