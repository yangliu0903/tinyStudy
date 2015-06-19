/**
* <p>Copyright: Copyright (c) 2015</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package org.tinygroup.dslcrud;

import java.util.List;

import org.tinygroup.beancontainer.BeanContainerFactory;
import org.tinygroup.dslcrud.service.CrudDbService;
import org.tinygroup.weblayer.WebContext;
import org.tinygroup.weblayer.mvc.WebContextAware;
import org.tinygroup.weblayer.mvc.annotation.Controller;
import org.tinygroup.weblayer.mvc.annotation.RequestMapping;
import org.tinygroup.weblayer.mvc.annotation.ResultKey;
import org.tinygroup.weblayer.mvc.annotation.View;


/**
 * 通过MVC方式包装无状态的服务
 * @author yancheng11334
 *
 */
@Controller()
public class TUserAction implements WebContextAware{

	private CrudDbService<TUser> crudDbService;
	private WebContext webContext;

	public CrudDbService<TUser> getCrudDbService() {
		if(crudDbService==null){
			crudDbService = BeanContainerFactory.getBeanContainer(
					getClass().getClassLoader()).getBean("dslCrudServiceWrapper");
		}
		return crudDbService;
	}

	public void setCrudDbService(CrudDbService<TUser> crudDbService) {
		this.crudDbService = crudDbService;
	}
	
	@RequestMapping(value={"/userAdd.do"})
	@View(value="/getList.do")
	public void addUserMethod() {
		TUser user= new TUser();
		user.setAge(Integer.parseInt(webContext.getRequest().getParameter("age")));
		user.setName(webContext.getRequest().getParameter("name"));
		getCrudDbService().addUser(user);
	}
	
	@RequestMapping(value={"/userUpdate.do"})
	@View(value="/getList.do")
	public void updateUserMethod() {
		TUser user= new TUser();
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
	public TUser operateMethod(){
		String id = webContext.getRequest().getParameter("id");
		if(id!=null){
		   return getCrudDbService().getUserById(id);
		}
		return null;
	}
	
	
	@RequestMapping(value={"/getList.do"})
	@View("/crud/list.page")
	@ResultKey(value="users")
	public List<TUser> getUserListMethod(){
		TUser user= new TUser();
		return getCrudDbService().queryUsers(user);
	}

	public void setContext(WebContext context) {
		this.webContext = context;
	}
	
}
