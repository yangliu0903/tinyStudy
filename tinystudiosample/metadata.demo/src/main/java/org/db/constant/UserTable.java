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

package org.db.constant;

import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

/**
 * <!-- begin-user-doc --> USER * <!-- end-user-doc -->
 */
public class UserTable extends Table {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	public static final UserTable USER_TABLE = new UserTable();

	/**
	 * <!-- begin-user-doc --> USER_ID * 用户表ID <!-- end-user-doc -->
	 */
	public final Column USER_ID = new Column(this, "user_id");
	/**
	 * <!-- begin-user-doc --> USER_NAME * <!-- end-user-doc -->
	 */
	public final Column USER_NAME = new Column(this, "user_name");
	/**
	 * <!-- begin-user-doc --> USER_ALIA * <!-- end-user-doc -->
	 */
	public final Column USER_ALIA = new Column(this, "user_alia");
	/**
	 * <!-- begin-user-doc --> USER_SEX * <!-- end-user-doc -->
	 */
	public final Column USER_SEX = new Column(this, "user_sex");
	/**
	 * <!-- begin-user-doc --> USER_NUM * 人员号码 <!-- end-user-doc -->
	 */
	public final Column USER_NUM = new Column(this, "user_num");
	/**
	 * <!-- begin-user-doc --> USER_HIGHT * <!-- end-user-doc -->
	 */
	public final Column USER_HIGHT = new Column(this, "user_hight");
	/**
	 * <!-- begin-user-doc --> USER_WEIGHT * <!-- end-user-doc -->
	 */
	public final Column USER_WEIGHT = new Column(this, "user_weight");

	public UserTable() {
		super("user");
	}
}
