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
 * <!-- begin-user-doc --> USER * <!-- end-user-doc -->
 */
public class User

{

	/**
	 * <!-- begin-user-doc --> USER_ID * 用户表ID <!-- end-user-doc -->
	 */
	private Integer userId;

	/**
	 * <!-- begin-user-doc --> USER_NAME * <!-- end-user-doc -->
	 */
	private String userName;

	/**
	 * <!-- begin-user-doc --> USER_ADRESS * <!-- end-user-doc -->
	 */
	private String userAddress;

	/**
	 * <!-- begin-user-doc --> USER_ALIA * <!-- end-user-doc -->
	 */
	private String userAlia;

	/**
	 * <!-- begin-user-doc --> USER_WEIGHT * <!-- end-user-doc -->
	 */
	private Float userWeight;

	/**
	 * <!-- begin-user-doc --> USER_HIGHT * <!-- end-user-doc -->
	 */
	private Integer userHight;

	/**
	 * <!-- begin-user-doc --> USER_SEX * <!-- end-user-doc -->
	 */
	private Integer userSex;

	/**
	 * <!-- begin-user-doc --> USER_NUM * 人员号码 <!-- end-user-doc -->
	 */
	private Integer userNum;

	/**
	 * <!-- begin-user-doc --> USER_ID * 用户表ID <!-- end-user-doc -->
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUserId() {
		return userId;
	}

	/**
	 * <!-- begin-user-doc --> USER_NAME * <!-- end-user-doc -->
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	/**
	 * <!-- begin-user-doc --> USER_ADRESS * <!-- end-user-doc -->
	 */
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getUserAddress() {
		return userAddress;
	}

	/**
	 * <!-- begin-user-doc --> USER_ALIA * <!-- end-user-doc -->
	 */
	public void setUserAlia(String userAlia) {
		this.userAlia = userAlia;
	}

	public String getUserAlia() {
		return userAlia;
	}

	/**
	 * <!-- begin-user-doc --> USER_WEIGHT * <!-- end-user-doc -->
	 */
	public void setUserWeight(Float userWeight) {
		this.userWeight = userWeight;
	}

	public Float getUserWeight() {
		return userWeight;
	}

	/**
	 * <!-- begin-user-doc --> USER_HIGHT * <!-- end-user-doc -->
	 */
	public void setUserHight(Integer userHight) {
		this.userHight = userHight;
	}

	public Integer getUserHight() {
		return userHight;
	}

	/**
	 * <!-- begin-user-doc --> USER_SEX * <!-- end-user-doc -->
	 */
	public void setUserSex(Integer userSex) {
		this.userSex = userSex;
	}

	public Integer getUserSex() {
		return userSex;
	}

	/**
	 * <!-- begin-user-doc --> USER_NUM * 人员号码 <!-- end-user-doc -->
	 */
	public void setUserNum(Integer userNum) {
		this.userNum = userNum;
	}

	public Integer getUserNum() {
		return userNum;
	}

}
