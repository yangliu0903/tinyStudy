/**
 *  Copyright (c) 1997-2013, www.tinygroup.org (tinygroup@126.com).
 *
 *  Licensed under the GPL, Version 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.gnu.org/licenses/gpl.html
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.tinygroup.crud.service.impl;

import java.util.Arrays;
import java.util.List;

import org.tinygroup.beancontainer.BeanContainerFactory;
import org.tinygroup.commons.tools.Assert;
import org.tinygroup.crud.service.CrudDbService;
import org.tinygroup.service.annotation.ServiceComponent;
import org.tinygroup.service.annotation.ServiceMethod;
import org.tinygroup.service.annotation.ServiceParameter;
import org.tinygroup.service.annotation.ServiceResult;
import org.tinygroup.support.BeanSupport;
import org.tinygroup.tinydb.Bean;
import org.tinygroup.tinydb.DbOperatorFactory;
import org.tinygroup.tinydb.exception.TinyDbException;
import org.tinygroup.tinydb.operator.DBOperator;

@ServiceComponent(bean="tinyDbCrudService")
public class TinyDbCrudService extends BeanSupport implements
		CrudDbService<Bean> {

	private DBOperator operator;

	private String beanType;

	public void setBeanType(String beanType) {
		this.beanType = beanType;
	}

	/** 初始化bean。 */
	protected void init() throws Exception {
		Assert.assertNotNull(beanType, "beanType must not null");
		DbOperatorFactory factory = BeanContainerFactory.getBeanContainer(
				getClass().getClassLoader()).getBean("tinyDBOperatorFactory");
		operator = factory.getDBOperator();
	}

	@ServiceMethod(serviceId = "addUserTiny")
	public void addUser(@ServiceParameter(name = "TUser") Bean TUser) {
		try {
			operator.insert(TUser);
		} catch (TinyDbException e) {
			throw new RuntimeException(e);
		}
	}

	@ServiceMethod(serviceId = "updateUserTiny")
	public void updateUser(@ServiceParameter(name = "TUser") Bean TUser) {
		try {
			operator.update(TUser);
		} catch (TinyDbException e) {
			throw new RuntimeException(e);
		}
	}

	@ServiceMethod(serviceId = "deleteUserTiny")
	public void deleteUserById(@ServiceParameter(name = "id") String id) {
		try {
			operator.deleteById(id, beanType);
		} catch (TinyDbException e) {
			throw new RuntimeException(e);
		}
	}

	@ServiceMethod(serviceId = "queryUsersTiny")
	@ServiceResult(name = "users")
	public List<Bean> queryUsers(@ServiceParameter(name = "TUser") Bean TUser) {
		if (TUser == null) {
			TUser = new Bean(beanType);
		}
		try {
			Bean[] beans = operator.getBeans(TUser);
			if (beans != null) {
				return Arrays.asList(beans);
			}
			return null;
		} catch (TinyDbException e) {
			throw new RuntimeException(e);
		}
	}

	@ServiceMethod(serviceId = "queryUserByIdTiny")
	@ServiceResult(name = "user")
	public Bean getUserById(@ServiceParameter(name = "id") String id) {
		if (id == null) {
			return null;
		}
		try {
			return operator.getBean(id, beanType);
		} catch (TinyDbException e) {
			throw new RuntimeException(e);
		}
	}
}
