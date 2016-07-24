package org.test;

import org.db.pojo.User;

public interface TestService {

	public void add(User user);
	
	public void delete(int id);
	
	public void get(int id);
	
	public void getAll();
	
}
