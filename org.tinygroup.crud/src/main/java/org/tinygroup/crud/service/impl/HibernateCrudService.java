package org.tinygroup.crud.service.impl;

import java.util.List;

import org.tinygroup.crud.dao.CrudDbDao;
import org.tinygroup.crud.pojo.User;
import org.tinygroup.crud.service.CrudDbService;
import org.tinygroup.service.annotation.ServiceComponent;
import org.tinygroup.service.annotation.ServiceMethod;
import org.tinygroup.service.annotation.ServiceResult;
import org.tinygroup.service.annotation.ServiceViewMapping;
@ServiceComponent()
public class HibernateCrudService implements CrudDbService<User> {
	
	private CrudDbDao<User> crudDbDao;
	
	public CrudDbDao<User> getCrudDbDao() {
		return crudDbDao;
	}

	public void setCrudDbDao(CrudDbDao<User> crudDbDao) {
		this.crudDbDao = crudDbDao;
	}
	@ServiceMethod(serviceId = "addUser")
	@ServiceViewMapping(value="/queryUsers.servicepage",type="redirect")
	public void addUser(User user) {
         crudDbDao.addUser(user);
	}
	@ServiceMethod(serviceId = "updateUser")
	@ServiceViewMapping(value="/queryUsers.servicepage",type="redirect")
	public void updateUser(User user) {
         crudDbDao.updateUser(user);
	}
	@ServiceMethod(serviceId = "deleteUser")
	@ServiceViewMapping(value="/queryUsers.servicepage",type="redirect")
	public void deleteUserById(int id) {
		User user=getUserById(id);
		crudDbDao.deleteUser(user);
	}
	@ServiceMethod(serviceId = "queryUsers")
	@ServiceResult(name = "users")
	@ServiceViewMapping("/crud/service/hibernate/list.page")
	public List<User> queryUsers(User user) {
		return crudDbDao.queryUsers(user);
	}
	@ServiceMethod(serviceId = "queryUserById")
	@ServiceResult(name = "user")
	@ServiceViewMapping("/crud/service/hibernate/operate.page")
	public User getUserById(Integer id) {
		if(id==null){
			return null;
		}
		return crudDbDao.queryUserById(id);
	}

}
