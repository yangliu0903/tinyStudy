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
package top.develop.demo.service;

import java.util.List;

/**
 * 增删改查的数据库服务接口
 * @author renhui
 *
 */
public interface UserService<T> {

	public void addUser(T user);
	public void updateUser(T user);
	public void deleteUserById(String id);
	public T getUserById(String id);
	public List<T> queryUsers(T user);
	
}
