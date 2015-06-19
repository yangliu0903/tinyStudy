/**
* <p>Copyright: Copyright (c) 2015</p>
* <p>Company: 恒生电子股份有限公司</p>
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
