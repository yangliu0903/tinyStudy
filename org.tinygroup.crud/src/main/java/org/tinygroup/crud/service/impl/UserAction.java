package org.tinygroup.crud.service.impl;

import java.util.List;

import org.tinygroup.crud.dao.CrudDbDao;
import org.tinygroup.crud.pojo.User;
import org.tinygroup.weblayer.WebContext;
import org.tinygroup.weblayer.mvc.WebContextAware;
import org.tinygroup.weblayer.mvc.annotation.Controller;
import org.tinygroup.weblayer.mvc.annotation.RequestMapping;
import org.tinygroup.weblayer.mvc.annotation.View;

@Controller()
@RequestMapping(value={"/mvc"})
public class UserAction implements WebContextAware{

    private CrudDbDao<User> crudDbDao;
    private WebContext webContext;
	public CrudDbDao<User> getCrudDbDao() {
		return crudDbDao;
	}

	public void setCrudDbDao(CrudDbDao<User> crudDbDao) {
		this.crudDbDao = crudDbDao;
	}
	
	@RequestMapping(value={"/userAdd.do"})
	@View(value="/crud/mvc/list.page")
	public void addUserMethod() {
		User user=new User();
		user.setAge(Integer.parseInt(webContext.getRequest().getParameter("age")));
		user.setName(webContext.getRequest().getParameter("name"));
		crudDbDao.addUser(user);
		getListMethod();
	}

	@RequestMapping(value={"/userUpdate.do"})
	@View(value="/crud/mvc/list.page")
	public void updateUserMethod() {
		User user=new User();
		user.setId(webContext.getRequest().getParameter("id"));
		user.setAge(Integer.parseInt(webContext.getRequest().getParameter("age")));
		user.setName(webContext.getRequest().getParameter("name"));
		crudDbDao.updateUser(user);
		getListMethod();
	}

	@RequestMapping(value={"/userDelete.do"})
	@View(value="/crud/mvc/list.page")
	public void deleteUserByIdMethod(String id) {
		User user=getUserById(id);
		crudDbDao.deleteUser(user);
		getListMethod();
	}
	
	@RequestMapping(value={"/operate.do"})
	@View(value="/crud/mvc/operate.page")
	public void operateMethod(String id){
		User user = getUserById(id);
		webContext.getRequest().setAttribute("user", user);
	}
	
	@RequestMapping(value={"/list.do"})
	@View(value="/crud/mvc/list.page")
	public void getListMethod(){
		List<User> users = queryUsers((User)webContext.getRequest().getAttribute("user"));
		webContext.getRequest().setAttribute("users", users);
	}
	
	
	public User getUserById(String id) {
		if(id==null){
			return null;
		}
		return crudDbDao.queryUserById(id);
	}

	
	public List<User> queryUsers(User user) {
		return crudDbDao.queryUsers(user);
	}

	public void setContext(WebContext webContext) {
		this.webContext = webContext;
	}

}
