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

package org.db;

import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;
import static org.db.constant.UserTable.*;
import static org.tinygroup.tinysqldsl.Select.*;
import static org.tinygroup.tinysqldsl.Insert.*;
import static org.tinygroup.tinysqldsl.Delete.*;
import static org.tinygroup.tinysqldsl.Update.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.tinygroup.tinysqldsl.Delete;
import org.tinygroup.tinysqldsl.Insert;
import org.tinygroup.tinysqldsl.Select;
import org.tinygroup.tinysqldsl.Update;
import org.tinygroup.tinysqldsl.Pager;

import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.select.OrderByElement;

import org.db.pojo.User;
import org.db.UserDao;

import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;
import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.util.TinyDSLUtil;

/**
 * <!-- begin-user-doc --> 如果不希望某方法或者变量被覆盖，可以在方法或者变量注释中增加@unmodifiable <!--
 * end-user-doc -->
 */
public class UserDaoImpl extends TinyDslDaoSupport implements UserDao {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	public User add(User user) {
		return getDslTemplate().insertAndReturnKey(user,
				new InsertGenerateCallback<User>() {
					public Insert generate(User t) {
						Insert insert = insertInto(USER_TABLE).values(
								USER_TABLE.USER_ID.value(t.getUserId()),
								USER_TABLE.USER_NAME.value(t.getUserName()),
								USER_TABLE.USER_ALIA.value(t.getUserAlia()),
								USER_TABLE.USER_SEX.value(t.getUserSex()),
								USER_TABLE.USER_NUM.value(t.getUserNum()),
								USER_TABLE.USER_HIGHT.value(t.getUserHight()),
								USER_TABLE.USER_WEIGHT.value(t.getUserWeight())

						);
						return insert;
					}
				});
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	public int edit(User user) {
		if (user == null || user.getUserId() == null) {
			return 0;
		}
		return getDslTemplate().update(user,
				new UpdateGenerateCallback<User>() {
					public Update generate(User t) {
						Update update = update(USER_TABLE)
								.set(USER_TABLE.USER_NAME
										.value(t.getUserName()),
										USER_TABLE.USER_ALIA.value(t
												.getUserAlia()),
										USER_TABLE.USER_SEX.value(t
												.getUserSex()),
										USER_TABLE.USER_NUM.value(t
												.getUserNum()),
										USER_TABLE.USER_HIGHT.value(t
												.getUserHight()),
										USER_TABLE.USER_WEIGHT.value(t
												.getUserWeight())).where(
										USER_TABLE.USER_ID.eq(t.getUserId()));
						return update;
					}
				});
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	public int deleteByKey(Integer pk) {
		if (pk == null) {
			return 0;
		}
		return getDslTemplate().deleteByKey(pk,
				new DeleteGenerateCallback<Serializable>() {
					public Delete generate(Serializable pk) {
						return delete(USER_TABLE).where(
								USER_TABLE.USER_ID.eq(pk));
					}
				});
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	public int deleteByKeys(Integer... pks) {
		if (pks == null || pks.length == 0) {
			return 0;
		}
		return getDslTemplate().deleteByKeys(
				new DeleteGenerateCallback<Serializable[]>() {
					public Delete generate(Serializable[] t) {
						return delete(USER_TABLE).where(
								USER_TABLE.USER_ID.in(t));
					}
				}, pks);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	public User getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, User.class,
				new SelectGenerateCallback<Serializable>() {

					@SuppressWarnings("rawtypes")
					public Select generate(Serializable t) {
						return selectFrom(USER_TABLE).where(
								USER_TABLE.USER_ID.eq(t));
					}
				});
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	public List<User> query(User user, final OrderBy... orderArgs) {
		if (user == null) {
			user = new User();
		}
		return getDslTemplate().query(user, new SelectGenerateCallback<User>() {
			@SuppressWarnings("rawtypes")
			public Select generate(User t) {
				Select select = selectFrom(USER_TABLE).where(
						and(USER_TABLE.USER_NAME.eq(t.getUserName()),
								USER_TABLE.USER_ALIA.eq(t.getUserAlia()),
								USER_TABLE.USER_SEX.eq(t.getUserSex()),
								USER_TABLE.USER_NUM.eq(t.getUserNum()),
								USER_TABLE.USER_HIGHT.eq(t.getUserHight()),
								USER_TABLE.USER_WEIGHT.eq(t.getUserWeight())

						));
				return TinyDSLUtil.addOrderByElements(select, orderArgs);
			}
		});
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	public Pager<User> queryPager(int start, int limit, User user,
			final OrderBy... orderArgs) {
		if (user == null) {
			user = new User();
		}
		return getDslTemplate().queryPager(start, limit, user, false,
				new SelectGenerateCallback<User>() {
					public Select generate(User t) {
						Select select = Select.selectFrom(USER_TABLE)
								.where(and(USER_TABLE.USER_NAME.eq(t
										.getUserName()), USER_TABLE.USER_ALIA
										.eq(t.getUserAlia()),
										USER_TABLE.USER_SEX.eq(t.getUserSex()),
										USER_TABLE.USER_NUM.eq(t.getUserNum()),
										USER_TABLE.USER_HIGHT.eq(t
												.getUserHight()),
										USER_TABLE.USER_WEIGHT.eq(t
												.getUserWeight())

								));
						return TinyDSLUtil
								.addOrderByElements(select, orderArgs);
					}
				});
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	public int[] batchInsert(boolean autoGeneratedKeys, List<User> user) {
		if (CollectionUtil.isEmpty(user)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, user,
				new NoParamInsertGenerateCallback() {

					public Insert generate() {
						return insertInto(USER_TABLE)
								.values(USER_TABLE.USER_NAME
										.value(new JdbcNamedParameter(
												"userName")),
										USER_TABLE.USER_ALIA
												.value(new JdbcNamedParameter(
														"userAlia")),
										USER_TABLE.USER_SEX
												.value(new JdbcNamedParameter(
														"userSex")),
										USER_TABLE.USER_NUM
												.value(new JdbcNamedParameter(
														"userNum")),
										USER_TABLE.USER_HIGHT
												.value(new JdbcNamedParameter(
														"userHight")),
										USER_TABLE.USER_WEIGHT
												.value(new JdbcNamedParameter(
														"userWeight"))

								);
					}
				});
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	public int[] batchInsert(List<User> users) {
		return batchInsert(true, users);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	public int[] batchUpdate(List<User> user) {
		if (CollectionUtil.isEmpty(user)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(user,
				new NoParamUpdateGenerateCallback() {
					public Update generate() {
						return update(USER_TABLE)
								.set(USER_TABLE.USER_NAME
										.value(new JdbcNamedParameter(
												"userName")),
										USER_TABLE.USER_ALIA
												.value(new JdbcNamedParameter(
														"userAlia")),
										USER_TABLE.USER_SEX
												.value(new JdbcNamedParameter(
														"userSex")),
										USER_TABLE.USER_NUM
												.value(new JdbcNamedParameter(
														"userNum")),
										USER_TABLE.USER_HIGHT
												.value(new JdbcNamedParameter(
														"userHight")),
										USER_TABLE.USER_WEIGHT
												.value(new JdbcNamedParameter(
														"userWeight"))

								).where(USER_TABLE.USER_ID
										.eq(new JdbcNamedParameter("userId")));
					}
				});
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	public int[] batchDelete(List<User> user) {
		if (CollectionUtil.isEmpty(user)) {
			return new int[0];
		}

		return getDslTemplate().batchDelete(user,
				new NoParamDeleteGenerateCallback() {
					public Delete generate() {
						return delete(USER_TABLE)
								.where(and(
										USER_TABLE.USER_NAME
												.eq(new JdbcNamedParameter(
														"userName")),
										USER_TABLE.USER_ALIA
												.eq(new JdbcNamedParameter(
														"userAlia")),
										USER_TABLE.USER_SEX
												.eq(new JdbcNamedParameter(
														"userSex")),
										USER_TABLE.USER_NUM
												.eq(new JdbcNamedParameter(
														"userNum")),
										USER_TABLE.USER_HIGHT
												.eq(new JdbcNamedParameter(
														"userHight")),
										USER_TABLE.USER_WEIGHT
												.eq(new JdbcNamedParameter(
														"userWeight"))

								));
					}
				});
	}
}
