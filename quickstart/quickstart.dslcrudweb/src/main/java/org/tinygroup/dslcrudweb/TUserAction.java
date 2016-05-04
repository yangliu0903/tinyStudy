package org.tinygroup.dslcrudweb;

import java.util.List;

import org.tinygroup.beancontainer.BeanContainerFactory;
import org.tinygroup.dslcrudservice.CrudDbService;
import org.tinygroup.dslcrudservice.pojo.UserPojo;
import org.tinygroup.weblayer.WebContext;
import org.tinygroup.weblayer.mvc.WebContextAware;
import org.tinygroup.weblayer.mvc.annotation.Controller;
import org.tinygroup.weblayer.mvc.annotation.RequestMapping;
import org.tinygroup.weblayer.mvc.annotation.ResultKey;
import org.tinygroup.weblayer.mvc.annotation.View;

@Controller()
public class TUserAction implements WebContextAware {
	private CrudDbService<UserPojo> crudDbService;
	private WebContext webContext;

	public CrudDbService<UserPojo> getCrudDbService() {
		if(crudDbService==null){
			crudDbService = BeanContainerFactory.getBeanContainer(
					getClass().getClassLoader()).getBean("crudDbServiceImplWrapper");
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
	@View(value="/operate.page")
	@ResultKey(value="user")
	public UserPojo operateMethod(){
		String id = webContext.getRequest().getParameter("id");
		if(id!=null){
		   return getCrudDbService().getUserById(id);
		}
		return null;
	}
	
	
	@RequestMapping(value={"/getList.do"})
	@View("/list.page")
	@ResultKey(value="users")
	public List<UserPojo> getUserListMethod(){
		UserPojo pojo= new UserPojo();
		return getCrudDbService().queryUsers(pojo);
	}

	public void setContext(WebContext context) {
		this.webContext = context;
	}
}