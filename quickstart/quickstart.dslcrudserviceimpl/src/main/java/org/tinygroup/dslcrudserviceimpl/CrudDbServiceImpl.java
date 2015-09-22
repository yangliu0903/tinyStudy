package org.tinygroup.dslcrudserviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.tinygroup.dslcruddao.TUserDao;
import org.tinygroup.dslcruddao.pojo.TUser;
import org.tinygroup.dslcrudservice.CrudDbService;
import org.tinygroup.dslcrudservice.pojo.UserPojo;

public class CrudDbServiceImpl implements CrudDbService<UserPojo> {
	
	private TUserDao tUserDao;
	
	public void settUserDao(TUserDao tUserDao) {
		this.tUserDao = tUserDao;
	}

	public void addUser(UserPojo user) {
		tUserDao.add(convertTUser(user));
	}

	public void updateUser(UserPojo user) {
		tUserDao.edit(convertTUser(user));
	}

	public void deleteUserById(String id) {
		tUserDao.deleteByKey(Integer.parseInt(id));
	}
	
	public UserPojo getUserById(String id) {
		return convertUserPojo(tUserDao.getByKey(Integer.parseInt(id)));
	}
	
	public List<UserPojo> queryUsers(UserPojo pojo) {
		List<TUser> list = tUserDao.query(convertTUser(pojo));
		List<UserPojo> result = new ArrayList<UserPojo>();
		if(list!=null){
		   for(TUser user:list){
			   result.add(convertUserPojo(user)); 
		   }
		}
		return result;
	}

	protected UserPojo convertUserPojo(TUser user){
		if(user==null){
		   return null;
		}else{
		   UserPojo pojo = new UserPojo();
		   pojo.setAge(user.getAge());
		   pojo.setId(user.getId());
		   pojo.setName(user.getName());
		   return pojo;
		}
	}
	
	protected TUser convertTUser(UserPojo pojo){
		if(pojo==null){
		   return null;
		}else{
		   TUser user = new TUser();
		   user.setAge(pojo.getAge());
		   user.setId(pojo.getId());
		   user.setName(pojo.getName());
		   return user;
		}
	}
}