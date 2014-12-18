/**
 *  Copyright (c) 1997-2013, www.tinygroup.org (luo_guo@icloud.com).
 *
 *  Licensed under the GPL, Version 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.gnu.org/licenses/gpl.html
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.tinygroup.fouroperate.service;

import org.tinygroup.service.annotation.ServiceComponent;
import org.tinygroup.service.annotation.ServiceMethod;
import org.tinygroup.service.annotation.ServiceResult;

@ServiceComponent()
public class FourOperateAnnotationService{
	@ServiceMethod(serviceId = "additionWithAnno")
	@ServiceResult(name = "result")
	public Double addition(Double number1,Double number2){
		return number1+number2;
	}
	@ServiceMethod(serviceId = "subtractWithAnno")
	@ServiceResult(name = "result")
	public Double subtraction(Double number1,Double number2){
		return number1-number2;
	}
	@ServiceMethod(serviceId = "multiWithAnno")
	@ServiceResult(name = "result")
	public Double multi(Double number1,Double number2){
		return number1*number2;
	}
	@ServiceMethod(serviceId = "divisionWithAnno")
	@ServiceResult(name = "result")
	public Double division (Double number1,Double number2){
		return number1/number2;
	}
}
