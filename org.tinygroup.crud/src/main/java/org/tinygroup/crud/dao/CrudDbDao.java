package org.tinygroup.crud.dao;

import java.util.List;

import org.tinygroup.crud.pojo.User;

public interface CrudDbDao {

	public void addUser(User user);
	public void updateUser(User user);
	public void deleteUser(User user);
	public List<User> queryUsers(User user);
	public User queryUserById(int id);
	
}
