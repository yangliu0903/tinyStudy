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

package org.db.pojo;


/**
 * <!-- begin-user-doc --> USER_CLASS * <!-- end-user-doc -->
 */
public class UserClass {

	/**
	 * <!-- begin-user-doc --> CLASS_ID * 班级表id <!-- end-user-doc -->
	 */
	private Integer classId;

	/**
	 * <!-- begin-user-doc --> CLASS_NAME * <!-- end-user-doc -->
	 */
	private String className;

	/**
	 * <!-- begin-user-doc --> CLASS_OWNE * <!-- end-user-doc -->
	 */
	private Integer classOwnerId;

	/**
	 * <!-- begin-user-doc --> CLASS_ID * 班级表id <!-- end-user-doc -->
	 */
	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public Integer getClassId() {
		return classId;
	}

	/**
	 * <!-- begin-user-doc --> CLASS_NAME * <!-- end-user-doc -->
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassName() {
		return className;
	}

	/**
	 * <!-- begin-user-doc --> CLASS_OWNE * <!-- end-user-doc -->
	 */
	public void setClassOwnerId(Integer classOwnerId) {
		this.classOwnerId = classOwnerId;
	}

	public Integer getClassOwnerId() {
		return classOwnerId;
	}

}
