package org.tinygroup.crud.service;

import java.util.List;

/**
 * 增删改查的数据库服务接口
 * @author renhui
 *
 */
public interface CrudDbService<T> {

	public void addUser(T user);
	public void updateUser(T user);
	public void deleteUserById(int id);
	public T getUserById(Integer id);
	public List<T> queryUsers(T user);
	
}
