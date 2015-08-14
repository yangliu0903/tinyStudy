/**
 *  Copyright (c) 1997-2013, www.tinygroup.org (tinygroup@126.com).
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