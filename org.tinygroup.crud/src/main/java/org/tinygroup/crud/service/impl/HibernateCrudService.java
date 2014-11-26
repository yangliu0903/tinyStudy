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
package org.tinygroup.crud.service.impl;

import java.util.List;

import org.tinygroup.crud.dao.CrudDbDao;
import org.tinygroup.crud.pojo.User;
import org.tinygroup.crud.service.CrudDbService;
import org.tinygroup.service.annotation.ServiceComponent;
import org.tinygroup.service.annotation.ServiceMethod;
import org.tinygroup.service.annotation.ServiceResult;
import org.tinygroup.service.annotation.ServiceViewMapping;
@ServiceComponent()
public class HibernateCrudService implements CrudDbService<User> {
	
	private CrudDbDao<User> crudDbDao;
	
	public CrudDbDao<User> getCrudDbDao() {
		return crudDbDao;
	}

	public void setCrudDbDao(CrudDbDao<User> crudDbDao) {
		this.crudDbDao = crudDbDao;
	}
	@ServiceMethod(serviceId = "addUser")
	@ServiceViewMapping(value="/queryUsers.servicepage",type="redirect")
	public void addUser(User user) {
         crudDbDao.addUser(user);
	}
	@ServiceMethod(serviceId = "updateUser")
	@ServiceViewMapping(value="/queryUsers.servicepage",type="redirect")
	public void updateUser(User user) {
         crudDbDao.updateUser(user);
	}
	@ServiceMethod(serviceId = "deleteUser")
	@ServiceViewMapping(value="/queryUsers.servicepage",type="redirect")
	public void deleteUserById(String id) {
		User user=getUserById(id);
		crudDbDao.deleteUser(user);
	}
	@ServiceMethod(serviceId = "queryUsers")
	@ServiceResult(name = "users")
	@ServiceViewMapping("/crud/service/hibernate/list.page")
	public List<User> queryUsers(User user) {
		return crudDbDao.queryUsers(user);
	}
	@ServiceMethod(serviceId = "queryUserById")
	@ServiceResult(name = "user")
	@ServiceViewMapping("/crud/service/hibernate/operate.page")
	public User getUserById(String id) {
		if(id==null){
			return null;
		}
		return crudDbDao.queryUserById(id);
	}

}
