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

import org.springframework.ui.ModelMap;
import org.tinygroup.crud.dao.CrudDbDao;
import org.tinygroup.crud.pojo.User;

/**
 * COC RESTFUL风格的控制器
 * 
 * @author renhui
 * 
 */
public class TUsersController {

	private CrudDbDao<User> hibernateCrudDao;

	public CrudDbDao<User> getHibernateCrudDao() {
		return hibernateCrudDao;
	}

	public void setHibernateCrudDao(CrudDbDao<User> hibernateCrudDao) {
		this.hibernateCrudDao = hibernateCrudDao;
	}

	// GET /tUsers
	// 用户列表页
	public void doIndex(ModelMap model, String form) {
		model.put("users", hibernateCrudDao.queryUsers(null));
	}

	// GET /tUsers/new
	// 新建个form表单页
	public void doNew(ModelMap model) {
		User demo = new User();
		demo.setAge(11);
		demo.setName("test");
		model.put("user", demo);
	}

	// POST /tUsers
	// 提交表单action
	public String doCreate(ModelMap model, User user) {
		hibernateCrudDao.addUser(user);
		return "redirect:/tUsers";
	}

	// GET /tUsers/:id
	// 获取某个用户信息
	public String doShow(ModelMap model, String id) {
		User user = hibernateCrudDao.queryUserById(id);
		model.put("user", user);
		return "show";
	}

	// GET /tUsers/:id/edit
	// 获取某个用户的信息的编辑页面
	public void doEdit(ModelMap model, String id) {
		User user = hibernateCrudDao.queryUserById(id);
		model.put("user", user);
	}

	// PUT /tUsers/:id
	// 修改某个用户信息action
	public String doUpdate(String id, User user) {
		user.setId(id);
		hibernateCrudDao.updateUser(user);
		return "redirect:/tUsers";
	}

	// DELETE /tUsers/:id
	// 删除某个用户信息action
	public String doDestroy(String id) {
		User user = hibernateCrudDao.queryUserById(id);
		hibernateCrudDao.deleteUser(user);
		return "redirect:/tUsers";
	}
}