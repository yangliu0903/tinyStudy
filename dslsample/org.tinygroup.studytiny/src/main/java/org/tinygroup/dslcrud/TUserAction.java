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
package org.tinygroup.dslcrud;

import java.util.List;

import org.tinygroup.beancontainer.BeanContainerFactory;
import org.tinygroup.dslcrud.service.CrudDbService;
import org.tinygroup.dslcrud.service.pojo.UserPojo;
import org.tinygroup.weblayer.WebContext;
import org.tinygroup.weblayer.mvc.WebContextAware;
import org.tinygroup.tinymvc.annotation.Controller;
import org.tinygroup.tinymvc.annotation.RequestMapping;
import org.tinygroup.tinymvc.annotation.ResultKey;
import org.tinygroup.tinymvc.annotation.View;


/**
 * 通过MVC方式包装无状态的服务
 * @author yancheng11334
 *
 */
@Controller()
public class TUserAction implements WebContextAware{

	private CrudDbService<UserPojo> crudDbService;
	private WebContext webContext;

	public CrudDbService<UserPojo> getCrudDbService() {
		if(crudDbService==null){
			crudDbService = BeanContainerFactory.getBeanContainer(
					getClass().getClassLoader()).getBean("dslCrudServiceWrapper");
		}
		return crudDbService;
	}

	public void setCrudDbService(CrudDbService<UserPojo> crudDbService) {
		this.crudDbService = crudDbService;
	}
	
	@RequestMapping(value={"/userAdd.do"})
	@View(value="/getList.do")
	public void addUserMethod() {
		UserPojo user= new UserPojo();
		user.setAge(Integer.parseInt(webContext.getRequest().getParameter("age")));
		user.setName(webContext.getRequest().getParameter("name"));
		getCrudDbService().addUser(user);
	}
	
	@RequestMapping(value={"/userUpdate.do"})
	@View(value="/getList.do")
	public void updateUserMethod() {
		UserPojo user= new UserPojo();
		user.setId(Integer.parseInt(webContext.getRequest().getParameter("id")));
		user.setAge(Integer.parseInt(webContext.getRequest().getParameter("age")));
		user.setName(webContext.getRequest().getParameter("name"));
		getCrudDbService().updateUser(user);
	}

	@RequestMapping(value={"/userDelete.do"})
	@View(value="/getList.do")
	public void deleteUserByIdMethod() {
		String id = webContext.getRequest().getParameter("id");
		getCrudDbService().deleteUserById(id);
	}
	
	@RequestMapping(value={"/operate.do"})
	@View(value="/crud/operate.page")
	@ResultKey(value="user")
	public UserPojo operateMethod(){
		String id = webContext.getRequest().getParameter("id");
		if(id!=null){
		   return getCrudDbService().getUserById(id);
		}
		return null;
	}
	
	
	@RequestMapping(value={"/getList.do"})
	@View("/crud/list.page")
	@ResultKey(value="users")
	public List<UserPojo> getUserListMethod(){
		UserPojo pojo= new UserPojo();
		return getCrudDbService().queryUsers(pojo);
	}

	public void setContext(WebContext context) {
		this.webContext = context;
	}
	
}
