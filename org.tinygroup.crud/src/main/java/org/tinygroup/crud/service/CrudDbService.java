package org.tinygroup.crud.service;

import java.util.List;

import org.tinygroup.crud.pojo.User;

/**
 * 增删改查的数据库服务接口
 * @author renhui
 *
 */
public interface CrudDbService {

	public void addUser(User user);
	public void updateUser(User user);
	public void deleteUserById(int id);
	public User getUserById(Integer id);
	public List<User> queryUsers(User user);
	
}
