package org.tinygroup.sctestsample;

public class Hello {
	public String hello(String name) {

		return "hello," + name;
	}

	public String hello(User user) {
		return "hello," + user.getName() + ",value:" + user.getValue();
	}
}
