package org.tinygroup.dslcrudservice;

import java.util.List;

public interface CrudDbService<T> {
	public void addUser(T user);
    public void updateUser(T user);
    public void deleteUserById(String id);
    public T getUserById(String id);
    public List<T> queryUsers(T user);
}