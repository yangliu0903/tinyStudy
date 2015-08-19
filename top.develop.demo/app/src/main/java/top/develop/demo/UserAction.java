package top.develop.demo;

import java.util.List;

import org.tinygroup.beancontainer.BeanContainerFactory;
import org.tinygroup.weblayer.WebContext;
import org.tinygroup.weblayer.mvc.WebContextAware;
import org.tinygroup.weblayer.mvc.annotation.Controller;
import org.tinygroup.weblayer.mvc.annotation.RequestMapping;
import org.tinygroup.weblayer.mvc.annotation.ResultKey;
import org.tinygroup.weblayer.mvc.annotation.View;

import top.develop.demo.pojo.UserPojo;
import top.develop.demo.service.UserService;

/**
 * 通过MVC方式包装无状态的服务
 * @author yancheng11334
 *
 */
@Controller()
public class UserAction implements WebContextAware{

	private UserService<UserPojo> userService;
	private WebContext webContext;
	
	public UserService<UserPojo> getUserService() {
		if(userService==null){
			userService = BeanContainerFactory.getBeanContainer(
					getClass().getClassLoader()).getBean("userServiceImplWrapper");
		}
		return userService;
	}

	public void setUserService(UserService<UserPojo> userService) {
		this.userService = userService;
	}

	@RequestMapping(value={"/userAdd.do"})
	@View(value="/getList.do")
	public void addUserMethod() {
		UserPojo user= new UserPojo();
		user.setAge(Integer.parseInt(webContext.getRequest().getParameter("age")));
		user.setName(webContext.getRequest().getParameter("name"));
		getUserService().addUser(user);
	}
	
	@RequestMapping(value={"/userUpdate.do"})
	@View(value="/getList.do")
	public void updateUserMethod() {
		UserPojo user= new UserPojo();
		user.setId(Integer.parseInt(webContext.getRequest().getParameter("id")));
		user.setAge(Integer.parseInt(webContext.getRequest().getParameter("age")));
		user.setName(webContext.getRequest().getParameter("name"));
		getUserService().updateUser(user);
	}

	@RequestMapping(value={"/userDelete.do"})
	@View(value="/getList.do")
	public void deleteUserByIdMethod() {
		String id = webContext.getRequest().getParameter("id");
		getUserService().deleteUserById(id);
	}
	
	@RequestMapping(value={"/operate.do"})
	@View(value="/crud/operate.page")
	@ResultKey(value="user")
	public UserPojo operateMethod(){
		String id = webContext.getRequest().getParameter("id");
		if(id!=null){
		   return getUserService().getUserById(id);
		}
		return null;
	}
	
	
	@RequestMapping(value={"/getList.do"})
	@View("/crud/list.page")
	@ResultKey(value="users")
	public List<UserPojo> getUserListMethod(){
		UserPojo pojo= new UserPojo();
		return getUserService().queryUsers(pojo);
	}

	public void setContext(WebContext context) {
		this.webContext = context;
	}

}
