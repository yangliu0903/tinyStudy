package org.tinygroup.crud.dao;

import java.util.List;

public interface CrudDbDao<T> {

	public void addUser(T user);
	public void updateUser(T user);
	public void deleteUser(T user);
	public List<T> queryUsers(T user);
	public T queryUserById(int id);
	
}
