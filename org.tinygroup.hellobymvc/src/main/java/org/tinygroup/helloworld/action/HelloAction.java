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
package org.tinygroup.helloworld.action;

import org.tinygroup.weblayer.WebContext;
import org.tinygroup.weblayer.mvc.WebContextAware;
import org.tinygroup.weblayer.mvc.annotation.Controller;
import org.tinygroup.weblayer.mvc.annotation.RequestMapping;
import org.tinygroup.weblayer.mvc.annotation.ResultKey;
import org.tinygroup.weblayer.mvc.annotation.View;

@Controller()
public class HelloAction implements WebContextAware{

	private WebContext webContext;
	
	public void setContext(WebContext webContext) {
		this.webContext = webContext;
	}
	
	@RequestMapping(value={"/helloByMvc.do"})
	@View(value="/helloworld/helloresult.page")
	@ResultKey(value="result")
	public String sayHelloMethod(String name) {
		if (name == null) {
			name = "world";
		}
		return  String.format("Hello, %s", name);
	}

}
