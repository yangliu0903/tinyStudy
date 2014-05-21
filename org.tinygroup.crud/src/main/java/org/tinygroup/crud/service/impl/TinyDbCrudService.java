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
public class TinyDbCrudService implements CrudDbService {
	
	private CrudDbDao crudDbDao;
	
	public CrudDbDao getCrudDbDao() {
		return crudDbDao;
	}

	public void setCrudDbDao(CrudDbDao crudDbDao) {
		this.crudDbDao = crudDbDao;
	}

	@ServiceMethod(serviceId = "addUserTiny")
	@ServiceViewMapping(value="/queryUsersTiny.servicepage",type="redirect")
	public void addUser(User user) {
         crudDbDao.addUser(user);
	}
	@ServiceMethod(serviceId = "updateUserTiny")
	@ServiceViewMapping(value="/queryUsersTiny.servicepage",type="redirect")
	public void updateUser(User user) {
         crudDbDao.updateUser(user);
	}
	@ServiceMethod(serviceId = "deleteUserTiny")
	@ServiceViewMapping(value="/queryUsersTiny.servicepage",type="redirect")
	public void deleteUserById(int id) {
		User user=getUserById(id);
		crudDbDao.deleteUser(user);
	}
	@ServiceMethod(serviceId = "queryUsersTiny")
	@ServiceResult(name = "users")
	@ServiceViewMapping("/service/tinydb/list.page")
	public List<User> queryUsers(User user) {
		return crudDbDao.queryUsers(user);
	}
	@ServiceMethod(serviceId = "queryUserByIdTiny")
	@ServiceResult(name = "user")
	@ServiceViewMapping("/service/tinydb/operate.page")
	public User getUserById(Integer id) {
		if(id==null){
			return null;
		}
		return crudDbDao.queryUserById(id);
	}

}
