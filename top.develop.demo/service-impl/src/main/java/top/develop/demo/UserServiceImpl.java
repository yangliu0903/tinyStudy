package top.develop.demo;

import java.util.List;

import top.develop.demo.pojo.UserPojo;
import top.develop.demo.service.UserService;

/**
 * @author yancheng11334
 *
 */
public class UserServiceImpl implements UserService<UserPojo>{

    private UserBusiness<UserPojo> userBusiness;

	public UserBusiness<UserPojo> getUserBusiness() {
		return userBusiness;
	}

	public void setUserBusiness(UserBusiness<UserPojo> userBusiness) {
		this.userBusiness = userBusiness;
	}

	public void addUser(UserPojo user) {
		userBusiness.addUser(user);
	}

	public void updateUser(UserPojo user) {
		userBusiness.updateUser(user);
	}

	public void deleteUserById(String id) {
		userBusiness.deleteUserById(id);
	}
	
	public UserPojo getUserById(String id) {
		return userBusiness.getUserById(id);
	}
	
	public List<UserPojo> queryUsers(UserPojo pojo) {
		return userBusiness.queryUsers(pojo);
	}
}
