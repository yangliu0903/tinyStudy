package org.tinygroup.ar;

import java.util.ArrayList;

import org.tinygroup.asinterface.GeneratorServiceIn;
import org.tinygroup.asinterface.ServiceOrg;
import org.tinygroup.asinterface.ServiceUser;
import org.tinygroup.beancontainer.BeanContainer;
import org.tinygroup.beancontainer.BeanContainerFactory;
import org.tinygroup.tinyrunner.Runner;

public class WrapperTest{
	
	public static void main(String[] args) throws Exception {
		Runner.init(null, new ArrayList<String>());
		BeanContainer container = BeanContainerFactory
				.getBeanContainer(WrapperTest.class.getClassLoader());
		Thread.sleep(3000);
		GeneratorServiceIn bean = (GeneratorServiceIn) container
				.getBean("generatorServiceProxy");
		ServiceUser user = new ServiceUser();
		user.setName("username");
		user.setAge(11);
		ServiceOrg org = new ServiceOrg();
		org.setName("hundsun");
		ServiceUser result=bean.userObject(user, org);
		System.out.println(result.getName());
	}

}
