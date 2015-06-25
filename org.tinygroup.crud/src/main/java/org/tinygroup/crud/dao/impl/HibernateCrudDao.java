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
package org.tinygroup.crud.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.tinygroup.crud.dao.CrudDbDao;
import org.tinygroup.crud.pojo.User;

public class HibernateCrudDao extends HibernateDaoSupport implements CrudDbDao<User>{

	public void addUser(User user) {
          getHibernateTemplate().save(user);		
	}

	public void updateUser(User user) {
		getHibernateTemplate().update(user);
	}

	public void deleteUser(User user) {
		getHibernateTemplate().delete(user);
	}

	@SuppressWarnings("unchecked")
	public List<User> queryUsers(User user) {
		if(user==null){
			return getHibernateTemplate().loadAll(User.class);
		}
		return getHibernateTemplate().findByExample(user);
	}

	public User queryUserById(String id) {
		return (User) getHibernateTemplate().get(User.class, id);
	}

}
