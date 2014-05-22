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

	public User queryUserById(int id) {
		return (User) getHibernateTemplate().get(User.class, id);
	}

}
