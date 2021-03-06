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
package org.tinygroup.crud.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.crud.dao.CrudDbDao;
import org.tinygroup.crud.pojo.User;
import org.tinygroup.crud.service.CrudDbService;
import org.tinygroup.service.annotation.ServiceComponent;
import org.tinygroup.service.annotation.ServiceMethod;
import org.tinygroup.service.annotation.ServiceResult;
@ServiceComponent(bean="hibernateCrudService")
public class HibernateCrudService implements CrudDbService<User> {
	
	private CrudDbDao<User> crudDbDao;
	
	public CrudDbDao<User> getCrudDbDao() {
		return crudDbDao;
	}

	public void setCrudDbDao(CrudDbDao<User> crudDbDao) {
		this.crudDbDao = crudDbDao;
	}
	@ServiceMethod(serviceId = "addUser")
	@Transactional
	public void addUser(User user) {
         crudDbDao.addUser(user);
	}
	@ServiceMethod(serviceId = "updateUser")
	public void updateUser(User user) {
         crudDbDao.updateUser(user);
	}
	@ServiceMethod(serviceId = "deleteUser")
	public void deleteUserById(String id) {
		User user=getUserById(id);
		crudDbDao.deleteUser(user);
	}
	@ServiceMethod(serviceId = "queryUsers")
	@ServiceResult(name = "users")
	public List<User> queryUsers(User user) {
		return crudDbDao.queryUsers(user);
	}
	@ServiceMethod(serviceId = "queryUserById")
	@ServiceResult(name = "user")
	public User getUserById(String id) {
		if(id==null){
			return null;
		}
		return crudDbDao.queryUserById(id);
	}

}
