package org.tinygroup.fouroperate.service;

import org.tinygroup.service.annotation.ServiceComponent;
import org.tinygroup.service.annotation.ServiceMethod;
import org.tinygroup.service.annotation.ServiceResult;
import org.tinygroup.service.annotation.ServiceViewMapping;

@ServiceComponent()
public class FourOperateAnnotationService{
	@ServiceMethod(serviceId = "additionWithAnno")
	@ServiceResult(name = "result")
	@ServiceViewMapping("/result.page")
	public double addition(double number1,double number2){
		return number1+number2;
	}
	@ServiceMethod(serviceId = "subtractWithAnno")
	@ServiceResult(name = "result")
	@ServiceViewMapping("/result.page")
	public double subtraction(double number1,double number2){
		return number1-number2;
	}
	@ServiceMethod(serviceId = "multiWithAnno")
	@ServiceResult(name = "result")
	@ServiceViewMapping("/result.page")
	public double multi(double number1,double number2){
		return number1*number2;
	}
	@ServiceMethod(serviceId = "divisionWithAnno")
	@ServiceResult(name = "result")
	@ServiceViewMapping("/result.page")
	public double division (double number1,double number2){
		return number1/number2;
	}
}
