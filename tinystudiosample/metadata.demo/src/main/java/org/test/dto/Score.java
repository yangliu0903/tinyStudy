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

package org.test.dto;


/**
 * <!-- begin-user-doc --> SCORE * <!-- end-user-doc -->
 */
public class Score

{

	/**
	 * <!-- begin-user-doc --> TEACHER_ID * 成绩表id <!-- end-user-doc -->
	 */
	private Integer scoreId;

	/**
	 * <!-- begin-user-doc --> SCORE_POINT * <!-- end-user-doc -->
	 */
	private Integer scorePoint;

	/**
	 * <!-- begin-user-doc --> SCORE_CLASS_ID * <!-- end-user-doc -->
	 */
	private Integer scoreClassId;

	/**
	 * <!-- begin-user-doc --> SCORE_USER_ID * <!-- end-user-doc -->
	 */
	private Integer scoreUserId;

	/**
	 * <!-- begin-user-doc --> TEACHER_ID * 成绩表id <!-- end-user-doc -->
	 */
	public void setScoreId(Integer scoreId) {
		this.scoreId = scoreId;
	}

	public Integer getScoreId() {
		return scoreId;
	}

	/**
	 * <!-- begin-user-doc --> SCORE_POINT * <!-- end-user-doc -->
	 */
	public void setScorePoint(Integer scorePoint) {
		this.scorePoint = scorePoint;
	}

	public Integer getScorePoint() {
		return scorePoint;
	}

	/**
	 * <!-- begin-user-doc --> SCORE_CLASS_ID * <!-- end-user-doc -->
	 */
	public void setScoreClassId(Integer scoreClassId) {
		this.scoreClassId = scoreClassId;
	}

	public Integer getScoreClassId() {
		return scoreClassId;
	}

	/**
	 * <!-- begin-user-doc --> SCORE_USER_ID * <!-- end-user-doc -->
	 */
	public void setScoreUserId(Integer scoreUserId) {
		this.scoreUserId = scoreUserId;
	}

	public Integer getScoreUserId() {
		return scoreUserId;
	}

}
