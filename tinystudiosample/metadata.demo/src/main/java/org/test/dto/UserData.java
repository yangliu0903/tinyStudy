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
 * <!-- begin-user-doc --> DATA * <!-- end-user-doc -->
 */
public class UserData

{

	/**
	 * <!-- begin-user-doc --> SCORES * <!-- end-user-doc -->
	 */
	private Score[] scores;

	/**
	 * <!-- begin-user-doc --> USER_ID * 用户表ID <!-- end-user-doc -->
	 */
	private Integer userId;

	/**
	 * <!-- begin-user-doc --> SCORES * <!-- end-user-doc -->
	 */
	public void setScores(Score[] scores) {
		this.scores = scores;
	}

	public Score[] getScores() {
		return scores;
	}

	/**
	 * <!-- begin-user-doc --> USER_ID * 用户表ID <!-- end-user-doc -->
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUserId() {
		return userId;
	}

}
