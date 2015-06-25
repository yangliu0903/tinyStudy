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
package org.tinygroup.dslcrud.service.impl;

import java.util.List;

import org.tinygroup.dslcrud.TUser;
import org.tinygroup.dslcrud.TUserDao;
import org.tinygroup.dslcrud.service.CrudDbService;

/**
 * @author yancheng11334
 *
 */
public class DslCrudService implements CrudDbService<TUser>{

	private TUserDao userDao;
	
	public TUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(TUserDao userDao) {
		this.userDao = userDao;
	}

	public void addUser(TUser user) {
		userDao.insertTUser(user);
	}

	public void updateUser(TUser user) {
		userDao.updateTUser(user);
	}

	public void deleteUserById(String id) {
		userDao.deleteTUser(Integer.valueOf(id));
	}
	
	public TUser getUserById(String id) {
		return userDao.getTUserById(Integer.valueOf(id));
	}
	
	public List<TUser> queryUsers(TUser user) {
		return userDao.queryTUsers(user);
	}

}
