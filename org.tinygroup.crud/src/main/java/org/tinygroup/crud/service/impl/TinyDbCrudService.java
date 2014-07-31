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
import org.tinygroup.tinydb.DbOperatorFactory;
import org.tinygroup.tinydb.exception.TinyDbException;
import org.tinygroup.tinydb.operator.DBOperator;
@ServiceComponent()
public class TinyDbCrudService extends BeanSupport implements CrudDbService<Bean>{
	
    private DBOperator operator;
	
	private String beanType;
	
	public void setBeanType(String beanType) {
		this.beanType = beanType;
	}

	 /** 初始化bean。 */
    protected void init() throws Exception {
    	Assert.assertNotNull(beanType, "beanType must not null");
    	operator=DbOperatorFactory.getDBOperator(getClass().getClassLoader());
    }
	
	
	@ServiceMethod(serviceId = "addUserTiny")
	@ServiceViewMapping(value="/queryUsersTiny.servicepage?@beantype=TUser",type="redirect")
	public void addUser(Bean TUser) {
		try {
			operator.insert(TUser);
		} catch (TinyDbException e) {
			throw new RuntimeException(e);
		}
	}
	@ServiceMethod(serviceId = "updateUserTiny")
	@ServiceViewMapping(value="/queryUsersTiny.servicepage?@beantype=TUser",type="redirect")
	public void updateUser(Bean TUser) {
		try {
			operator.update(TUser);
		} catch (TinyDbException e) {
			throw new RuntimeException(e);
		}
	}
	@ServiceMethod(serviceId = "deleteUserTiny")
	@ServiceViewMapping(value="/queryUsersTiny.servicepage?@beantype=TUser",type="redirect")
	public void deleteUserById(String id) {
		try {
			operator.deleteById(id,beanType);
		} catch (TinyDbException e) {
			throw new RuntimeException(e);
		}
	}
	@ServiceMethod(serviceId = "queryUsersTiny")
	@ServiceResult(name = "users")
	@ServiceViewMapping("/crud/service/tinydb/list.page")
	public List<Bean> queryUsers(Bean TUser) {
		if(TUser==null){
			TUser=new Bean(beanType);
		}
		try {
			Bean[] beans = operator.getBeans(TUser);
			if(beans!=null){
				return Arrays.asList(beans);
			}
			return null;
		} catch (TinyDbException e) {
			throw new RuntimeException(e);
		}
	}
	@ServiceMethod(serviceId = "queryUserByIdTiny")
	@ServiceResult(name = "user")
	@ServiceViewMapping("/crud/service/tinydb/operate.page")
	public Bean getUserById(String id) {
		if(id==null){
			return null;
		}
		try {
			return operator.getBean(id,beanType);
		} catch (TinyDbException e) {
			throw new RuntimeException(e);
		}
	}
}
