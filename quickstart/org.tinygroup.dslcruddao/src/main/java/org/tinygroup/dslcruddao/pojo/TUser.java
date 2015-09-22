/**
 *  Copyright (c) 1997-2013, www.tinygroup.org (luo_guo@icloud.com).
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

package org.tinygroup.dslcruddao.pojo;

import java.util.Date;

/** 
 * USER
 * 
 * 用户表
 */
public class TUser {

	/** 
	 * ID
	 * 
	 */
	private Integer id;

	/** 
	 * NAME
	 * 
	 * 用户姓名
	 */
	private String name;

	/** 
	 * AGE
	 * 
	 * 年龄
	 */
	private Integer age;


	public void setId(Integer id){
		this. id = id;
	}

	public Integer getId(){
		return id;
	}

	public void setName(String name){
		this. name = name;
	}

	public String getName(){
		return name;
	}

	public void setAge(Integer age){
		this. age = age;
	}

	public Integer getAge(){
		return age;
	}

}
