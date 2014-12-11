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
package org.tinygroup.webservicesample;


//@XmlAccessorType(value=XmlAccessType.FIELD)  
public class UserImpl implements User2{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6165366361015284657L;
	private String name;
	private String age;
	
	public UserImpl(String name,String age){
		this.name = name;
		this.age = age;
	}
	public UserImpl(){
	}
	public String getName() {
		return name;
	}

	public String getAge() {
		return age;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAge(String age) {
		this.age = age;
	}

}
