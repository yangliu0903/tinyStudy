package org.tinygroup.helloworld.service;


public class HelloWorldXmlService{
	public String sayHello(String name) {
		if (name == null) {
			name = "world";
		}
		return "hello," + name;
	}
}
