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
 * <!-- begin-user-doc --> SCORE * <!-- end-user-doc -->
 */
public class ScoreTable extends Table {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	public static final ScoreTable SCORE_TABLE = new ScoreTable();

	/**
	 * <!-- begin-user-doc --> TEACHER_ID * 成绩表id <!-- end-user-doc -->
	 */
	public final Column SCORE_ID = new Column(this, "score_id");
	/**
	 * <!-- begin-user-doc --> SCORE_POINT * <!-- end-user-doc -->
	 */
	public final Column SCORE_POINT = new Column(this, "score_point");
	/**
	 * <!-- begin-user-doc --> SCORE_CLASS_ID * <!-- end-user-doc -->
	 */
	public final Column SCORE_CLASS_ID = new Column(this, "score_class_id");
	/**
	 * <!-- begin-user-doc --> SCORE_USER_ID * <!-- end-user-doc -->
	 */
	public final Column SCORE_USER_ID = new Column(this, "score_user_id");

	public ScoreTable() {
		super("score");
	}
}
