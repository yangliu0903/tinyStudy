package org.tinygroup.crud.service.impl;

import java.util.Arrays;
import java.util.List;

import org.tinygroup.commons.tools.Assert;
import org.tinygroup.crud.service.CrudDbService;
import org.tinygroup.service.annotation.ServiceComponent;
import org.tinygroup.service.annotation.ServiceMethod;
import org.tinygroup.service.annotation.ServiceResult;
import org.tinygroup.service.annotation.ServiceViewMapping;
import org.tinygroup.support.BeanSupport;
import org.tinygroup.tinydb.Bean;
import org.tinygroup.tinydb.BeanOperatorManager;
import org.tinygroup.tinydb.operator.DBOperator;
@ServiceComponent()
public class TinyDbCrudService extends BeanSupport implements CrudDbService<Bean>{
	
    private DBOperator operator;
	
	private BeanOperatorManager manager;
	
	private String beanType;
	
	public void setBeanType(String beanType) {
		this.beanType = beanType;
	}

	public void setManager(BeanOperatorManager manager) {
		this.manager = manager;
	}

	 /** 初始化bean。 */
    protected void init() throws Exception {
    	Assert.assertNotNull(manager, "manager must not null");
    	operator=manager.getDbOperator(beanType);
    }
	
	
	@ServiceMethod(serviceId = "addUserTiny")
	@ServiceViewMapping(value="/queryUsersTiny.servicepage?@beantype=user",type="redirect")
	public void addUser(Bean user) {
		operator.insert(user);
	}
	@ServiceMethod(serviceId = "updateUserTiny")
	@ServiceViewMapping(value="/queryUsersTiny.servicepage?@beantype=user",type="redirect")
	public void updateUser(Bean user) {
		operator.update(user);
	}
	@ServiceMethod(serviceId = "deleteUserTiny")
	@ServiceViewMapping(value="/queryUsersTiny.servicepage?@beantype=user",type="redirect")
	public void deleteUserById(int id) {
		operator.deleteById(id);
	}
	@ServiceMethod(serviceId = "queryUsersTiny")
	@ServiceResult(name = "users")
	@ServiceViewMapping("/crud/service/tinydb/list.page")
	public List<Bean> queryUsers(Bean user) {
		if(user==null){
			user=new Bean(beanType);
		}
		Bean[] beans= operator.getBeans(user);
		return Arrays.asList(beans);
	}
	@ServiceMethod(serviceId = "queryUserByIdTiny")
	@ServiceResult(name = "user")
	@ServiceViewMapping("/crud/service/tinydb/operate.page")
	public Bean getUserById(Integer id) {
		if(id==null){
			return null;
		}
		return operator.getBean(id);
	}

}
