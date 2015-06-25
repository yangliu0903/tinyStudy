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
