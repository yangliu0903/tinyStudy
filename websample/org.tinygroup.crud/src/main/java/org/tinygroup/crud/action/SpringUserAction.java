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
package org.tinygroup.crud.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.crud.dao.CrudDbDao;
import org.tinygroup.crud.pojo.User;
import org.tinygroup.springmvc.handler.Form;
import org.tinygroup.weblayer.WebContext;
import org.tinygroup.weblayer.mvc.WebContextAware;

import com.alibaba.fastjson.JSONObject;

@RequestMapping(value={"/springmvc"})
public class SpringUserAction implements WebContextAware{

    private CrudDbDao<User> crudDbDao;
    private WebContext webContext;
    private String value;
	public CrudDbDao<User> getCrudDbDao() {
		return crudDbDao;
	}

	public void setCrudDbDao(CrudDbDao<User> crudDbDao) {
		this.crudDbDao = crudDbDao;
	}
	
	@RequestMapping(value={"/userAdd.shtm"})
	@Form
	public String addUserMethod(String name) {
		User user=new User();
		user.setAge(Integer.parseInt(webContext.getRequest().getParameter("age")));
		user.setName(name);
		crudDbDao.addUser(user);
		getListMethod();
		return "list";
	}
	
	@RequestMapping(value={"/addUser.shtm"})
	public String addUser(User user) {
		crudDbDao.addUser(user);
		getListMethod();
		return "list";
	}
	
	@RequestMapping(value={"/addUsers.shtm"})
	public String addUser(ArrayList<User> users) {
		if(!CollectionUtil.isEmpty(users)){
			for (User user : users) {
				crudDbDao.addUser(user);
			}
		}
		getListMethod();
		return "list";
	}

	@RequestMapping(value={"/userUpdate.shtm"})
	public String updateUserMethod(String id,WebContext webContext) {
		User user=new User();
		user.setId(id);
		user.setAge(Integer.parseInt(webContext.getRequest().getParameter("age")));
		user.setName(webContext.getRequest().getParameter("name"));
		crudDbDao.updateUser(user);
		return getListMethod();
	}

	@RequestMapping(value={"/userDelete.shtm"})
	public String deleteUserByIdMethod(String id) {
		User user=getUserById(id);
		crudDbDao.deleteUser(user);
		return getListMethod();
	}
	
	@RequestMapping(value={"/operate.shtm"})
	public ModelAndView operateMethod(String id){
		User user = getUserById(id);
		webContext.getRequest().setAttribute("user", user);
		return new ModelAndView("operate", "user", user);
	}
	
	@RequestMapping(value={"/list"})
	public String getListMethod(){
		List<User> users = queryUsers((User)webContext.getRequest().getAttribute("user"));
		webContext.getRequest().setAttribute("users", users);
		return "list";
	}
	
	
	@RequestMapping(value={"/listjson.shtm"})
	@ResponseBody
	public List<User> getListJsonMethod(){
		List<User> users = queryUsers((User)webContext.getRequest().getAttribute("user"));
		return users;
	}
	
	@RequestMapping(value={"/liststring.shtm"})
	@ResponseBody
	public String getJsonString(){
		User user=new User();
		user.setAge(12);
		user.setName("实得分方法");
		user.setId("dadsdf");
		return JSONObject.toJSONString(user);
	}
	
	
	public User getUserById(String id) {
		if(id==null){
			return null;
		}
		return crudDbDao.queryUserById(id);
	}

	
	public List<User> queryUsers(User user) {
		return crudDbDao.queryUsers(user);
	}

	public void setContext(WebContext webContext) {
		this.webContext = webContext;
	}

	
	public static void main(String[] args) {
		
		BeanWrapper beanWrapper=new BeanWrapperImpl(User.class);
		beanWrapper.setAutoGrowNestedPaths(true);
		beanWrapper.setPropertyValue("name", "sdfd");
		beanWrapper.setPropertyValue("age",12);
		beanWrapper.setPropertyValue("fileInfos[0].name", "sdss");
		beanWrapper.setPropertyValue("fileInfos[0].size", 1222);
		beanWrapper.setPropertyValue("fileInfos[1].name", "sdss");
		beanWrapper.setPropertyValue("fileInfos[1].size", 13322);
		beanWrapper.setPropertyValue("fInfos[0].name", "sdss");
		beanWrapper.setPropertyValue("fInfos[0].size", 122112);
		beanWrapper.setPropertyValue("fInfos[1].name", "sdss");
		beanWrapper.setPropertyValue("fInfos[1].size", 13322);
		User user=(User) beanWrapper.getWrappedInstance();
		
		System.out.println(user.getName());
		System.out.println(user.getAge());
		System.out.println(user.getFileInfos());
		System.out.println(user.getfInfos());
		
		
		
	}
	
	
	
}
