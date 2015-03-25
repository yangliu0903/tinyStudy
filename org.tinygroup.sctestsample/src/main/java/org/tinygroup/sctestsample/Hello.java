package org.tinygroup.sctestsample;

public class Hello {
	public String hello(String name) {
		String a = null;
		a.substring(1);
		return "hello," + name;
	}

	public String hello(User user) {
		return "hello," + user.getName() + ",value:" + user.getValue();
	}
}
