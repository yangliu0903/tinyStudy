package org.tinygroup.fouroperate.service;

import org.tinygroup.service.ServiceInterface;

public class DivisionServiceImpl implements ServiceInterface {

	public String getServiceId() {
		return "divisionService";
	}

	public String getCategory() {
		return null;
	}

	public String getResultKey() {
		return "result";
	}
	
	public Double execute(Double number1,Double number2){
		return number1/number2;
	}

}
