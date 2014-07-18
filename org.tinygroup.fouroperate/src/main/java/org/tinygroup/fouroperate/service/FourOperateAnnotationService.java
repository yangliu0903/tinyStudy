package org.tinygroup.fouroperate.service;

import org.tinygroup.service.annotation.ServiceComponent;
import org.tinygroup.service.annotation.ServiceMethod;
import org.tinygroup.service.annotation.ServiceResult;
import org.tinygroup.service.annotation.ServiceViewMapping;

@ServiceComponent()
public class FourOperateAnnotationService{
	@ServiceMethod(serviceId = "additionWithAnno")
	@ServiceResult(name = "result")
	@ServiceViewMapping("/fouroperate/result.page")
	public Double addition(Double number1,Double number2){
		return number1+number2;
	}
	@ServiceMethod(serviceId = "subtractWithAnno")
	@ServiceResult(name = "result")
	@ServiceViewMapping("/fouroperate/result.page")
	public Double subtraction(Double number1,Double number2){
		return number1-number2;
	}
	@ServiceMethod(serviceId = "multiWithAnno")
	@ServiceResult(name = "result")
	@ServiceViewMapping("/fouroperate/result.page")
	public Double multi(Double number1,Double number2){
		return number1*number2;
	}
	@ServiceMethod(serviceId = "divisionWithAnno")
	@ServiceResult(name = "result")
	@ServiceViewMapping("/fouroperate/result.page")
	public Double division (Double number1,Double number2){
		return number1/number2;
	}
}
