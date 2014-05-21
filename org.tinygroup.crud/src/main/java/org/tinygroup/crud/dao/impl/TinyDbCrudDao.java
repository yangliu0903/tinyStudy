package org.tinygroup.crud.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.tinygroup.crud.dao.CrudDbDao;
import org.tinygroup.crud.pojo.User;
import org.tinygroup.tinydb.Bean;
import org.tinygroup.tinydb.BeanOperatorManager;
import org.tinygroup.tinydb.operator.DBOperator;
import org.tinygroup.tinydb.util.TinyBeanUtil;

public class TinyDbCrudDao implements CrudDbDao{
	
	private DBOperator operator;
	
	private BeanOperatorManager manager;
	
	public void setManager(BeanOperatorManager manager) {
		this.manager = manager;
		operator=manager.getDbOperator("user");
	}

	public void addUser(User user) {
		Bean bean=TinyBeanUtil.object2Bean(user);
		operator.insert(bean);
	}

	public void updateUser(User user) {
		Bean bean=TinyBeanUtil.object2Bean(user);
		operator.update(bean);
	}

	public void deleteUser(User user) {
		Bean bean=TinyBeanUtil.object2Bean(user);
		operator.delete(bean);		
	}

	public List<User> queryUsers(User user) {
		Bean bean=null;
		if(user!=null){
			bean=TinyBeanUtil.object2Bean(user);
		}else{
			bean=new Bean("user");
		}
		Bean[] beans= operator.getBeans(bean);
		List<User> users=new ArrayList<User>();
		if(beans!=null&&beans.length>0){
			for (Bean bean2 : beans) {
				users.add(TinyBeanUtil.bean2Object(bean2, User.class));
			}
		}
		return users;
	}

	public User queryUserById(int id) {
		Bean bean=operator.getBean(id);
		return TinyBeanUtil.bean2Object(bean, User.class);
	}

}
