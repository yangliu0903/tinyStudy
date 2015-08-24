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

import java.util.ArrayList;
import java.util.List;

import org.tinygroup.dslcrud.inter.TUserDao;
import org.tinygroup.dslcrud.pojo.TUser;
import org.tinygroup.dslcrud.service.CrudDbService;
import org.tinygroup.dslcrud.service.pojo.UserPojo;

/**
 * @author yancheng11334
 *
 */
public class DslCrudService implements CrudDbService<UserPojo>{

	private TUserDao userDao;
	
	public TUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(TUserDao userDao) {
		this.userDao = userDao;
	}

	public void addUser(UserPojo user) {
		userDao.add(convertTUser(user));
	}

	public void updateUser(UserPojo user) {
		userDao.edit(convertTUser(user));
	}

	public void deleteUserById(String id) {
		userDao.deleteByKey(Integer.parseInt(id));
	}
	
	public UserPojo getUserById(String id) {
		return convertUserPojo(userDao.getByKey(Integer.parseInt(id)));
	}
	
	public List<UserPojo> queryUsers(UserPojo pojo) {
		List<TUser> list = userDao.query(convertTUser(pojo));
		List<UserPojo> result = new ArrayList<UserPojo>();
		if(list!=null){
		   for(TUser user:list){
			   result.add(convertUserPojo(user)); 
		   }
		}
		return result;
	}

	protected UserPojo convertUserPojo(TUser user){
		if(user==null){
		   return null;
		}else{
		   UserPojo pojo = new UserPojo();
		   pojo.setAge(user.getAge());
		   pojo.setId(user.getId());
		   pojo.setName(user.getName());
		   return pojo;
		}
	}
	
	protected TUser convertTUser(UserPojo pojo){
		if(pojo==null){
		   return null;
		}else{
		   TUser user = new TUser();
		   user.setAge(pojo.getAge());
		   user.setId(pojo.getId());
		   user.setName(pojo.getName());
		   return user;
		}
	}
}
