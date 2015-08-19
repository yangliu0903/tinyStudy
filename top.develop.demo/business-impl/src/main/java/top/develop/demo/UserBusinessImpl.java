package top.develop.demo;

import java.util.ArrayList;
import java.util.List;

import top.develop.demo.dao.inter.TUserDao;
import top.develop.demo.dao.inter.pojo.TUser;
import top.develop.demo.pojo.UserPojo;
/**
 * @author yancheng11334
 *
 */
public class UserBusinessImpl implements UserBusiness<UserPojo>{

    private TUserDao userDao;
	
	public TUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(TUserDao userDao) {
		this.userDao = userDao;
	}

	public void addUser(UserPojo user) {
		userDao.insertObject(convertTUser(user));
	}

	public void updateUser(UserPojo user) {
		userDao.updateObject(convertTUser(user));
	}

	public void deleteUserById(String id) {
		userDao.deleteObject(id);
	}
	
	public UserPojo getUserById(String id) {
		return convertUserPojo(userDao.getObjectById(id));
	}
	
	public List<UserPojo> queryUsers(UserPojo pojo) {
		List<TUser> list = userDao.queryObjects(convertTUser(pojo));
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
