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
 * <!-- begin-user-doc --> USER_CLASS * <!-- end-user-doc -->
 */
public class UserClassTable extends Table {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	public static final UserClassTable USER_CLASS_TABLE = new UserClassTable();

	/**
	 * <!-- begin-user-doc --> CLASS_ID * 班级表id <!-- end-user-doc -->
	 */
	public final Column CLASS_ID = new Column(this, "class_id");
	/**
	 * <!-- begin-user-doc --> CLASS_NAME * <!-- end-user-doc -->
	 */
	public final Column CLASS_NAME = new Column(this, "class_name");
	/**
	 * <!-- begin-user-doc --> CLASS_OWNE * <!-- end-user-doc -->
	 */
	public final Column CLASS_OWNER_ID = new Column(this, "class_owner_id");

	public UserClassTable() {
		super("user_class");
	}
}
