package org.tinygroup.crud.web.controller;

import java.util.List;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.tinygroup.crud.dao.CrudDbDao;
import org.tinygroup.crud.pojo.User;

/**
 * coc风格的控制器
 * @author renhui
 *
 */
@SessionAttributes("users")
public class UserController {

	private CrudDbDao<User> hibernateCrudDao;

	public CrudDbDao<User> getHibernateCrudDao() {
		return hibernateCrudDao;
	}

	public void setHibernateCrudDao(CrudDbDao<User> hibernateCrudDao) {
		this.hibernateCrudDao = hibernateCrudDao;
	}

	public String addUser(int age, String name) {
		User user = new User();
		user.setAge(age);
		user.setName(name);
		hibernateCrudDao.addUser(user);
		return "redirect:list";
	}

	public String update(String id, int age, String name) {
		User user = new User();
		user.setId(id);
		user.setAge(age);
		user.setName(name);
		hibernateCrudDao.updateUser(user);
		return "forward:list";
	}

	public String delete(String id) {
		User user = getUserById(id);
		hibernateCrudDao.deleteUser(user);
		return "forward:list";
	}

	public String operate(String id) {
		return "operate";
	}

	public void list(ModelMap modelMap) {
		List<User> users= hibernateCrudDao.queryUsers(null);
	    modelMap.put("users", users);
	}

	@ModelAttribute
	public User getUserById(String id) {
		if (id == null) {
			return null;
		}
		return hibernateCrudDao.queryUserById(id);
	}


}
