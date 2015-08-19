package top.develop.demo;

import java.util.List;

/**
 * @author yancheng11334
 *
 */
public interface UserBusiness<T> {

	public void addUser(T user);
	public void updateUser(T user);
	public void deleteUserById(String id);
	public T getUserById(String id);
	public List<T> queryUsers(T user);
	
}
