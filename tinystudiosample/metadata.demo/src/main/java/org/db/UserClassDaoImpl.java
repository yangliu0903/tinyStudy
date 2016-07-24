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
import static org.db.constant.UserClassTable.*;
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

import org.db.pojo.UserClass;
import org.db.UserClassDao;

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
public class UserClassDaoImpl extends TinyDslDaoSupport implements UserClassDao {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	public UserClass add(UserClass userClass) {
		return getDslTemplate().insertAndReturnKey(userClass,
				new InsertGenerateCallback<UserClass>() {
					public Insert generate(UserClass t) {
						Insert insert = insertInto(USER_CLASS_TABLE)
								.values(USER_CLASS_TABLE.CLASS_ID.value(t
										.getClassId()),
										USER_CLASS_TABLE.CLASS_NAME.value(t
												.getClassName()),
										USER_CLASS_TABLE.CLASS_OWNER_ID.value(t
												.getClassOwnerId())

								);
						return insert;
					}
				});
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	public int edit(UserClass userClass) {
		if (userClass == null || userClass.getClassId() == null) {
			return 0;
		}
		return getDslTemplate().update(userClass,
				new UpdateGenerateCallback<UserClass>() {
					public Update generate(UserClass t) {
						Update update = update(USER_CLASS_TABLE).set(
								USER_CLASS_TABLE.CLASS_NAME.value(t
										.getClassName()),
								USER_CLASS_TABLE.CLASS_OWNER_ID.value(t
										.getClassOwnerId())).where(
								USER_CLASS_TABLE.CLASS_ID.eq(t.getClassId()));
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
						return delete(USER_CLASS_TABLE).where(
								USER_CLASS_TABLE.CLASS_ID.eq(pk));
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
						return delete(USER_CLASS_TABLE).where(
								USER_CLASS_TABLE.CLASS_ID.in(t));
					}
				}, pks);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	public UserClass getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, UserClass.class,
				new SelectGenerateCallback<Serializable>() {

					@SuppressWarnings("rawtypes")
					public Select generate(Serializable t) {
						return selectFrom(USER_CLASS_TABLE).where(
								USER_CLASS_TABLE.CLASS_ID.eq(t));
					}
				});
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	public List<UserClass> query(UserClass userClass,
			final OrderBy... orderArgs) {
		if (userClass == null) {
			userClass = new UserClass();
		}
		return getDslTemplate().query(userClass,
				new SelectGenerateCallback<UserClass>() {
					@SuppressWarnings("rawtypes")
					public Select generate(UserClass t) {
						Select select = selectFrom(USER_CLASS_TABLE).where(
								and(USER_CLASS_TABLE.CLASS_NAME.eq(t
										.getClassName()),
										USER_CLASS_TABLE.CLASS_OWNER_ID.eq(t
												.getClassOwnerId())

								));
						return TinyDSLUtil
								.addOrderByElements(select, orderArgs);
					}
				});
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	public Pager<UserClass> queryPager(int start, int limit,
			UserClass userClass, final OrderBy... orderArgs) {
		if (userClass == null) {
			userClass = new UserClass();
		}
		return getDslTemplate().queryPager(start, limit, userClass, false,
				new SelectGenerateCallback<UserClass>() {
					public Select generate(UserClass t) {
						Select select = Select.selectFrom(USER_CLASS_TABLE)
								.where(and(USER_CLASS_TABLE.CLASS_NAME.eq(t
										.getClassName()),
										USER_CLASS_TABLE.CLASS_OWNER_ID.eq(t
												.getClassOwnerId())

								));
						return TinyDSLUtil
								.addOrderByElements(select, orderArgs);
					}
				});
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	public int[] batchInsert(boolean autoGeneratedKeys,
			List<UserClass> userClass) {
		if (CollectionUtil.isEmpty(userClass)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, userClass,
				new NoParamInsertGenerateCallback() {

					public Insert generate() {
						return insertInto(USER_CLASS_TABLE).values(
								USER_CLASS_TABLE.CLASS_NAME
										.value(new JdbcNamedParameter(
												"className")),
								USER_CLASS_TABLE.CLASS_OWNER_ID
										.value(new JdbcNamedParameter(
												"classOwnerId"))

						);
					}
				});
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	public int[] batchInsert(List<UserClass> userClasss) {
		return batchInsert(true, userClasss);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	public int[] batchUpdate(List<UserClass> userClass) {
		if (CollectionUtil.isEmpty(userClass)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(userClass,
				new NoParamUpdateGenerateCallback() {
					public Update generate() {
						return update(USER_CLASS_TABLE).set(
								USER_CLASS_TABLE.CLASS_NAME
										.value(new JdbcNamedParameter(
												"className")),
								USER_CLASS_TABLE.CLASS_OWNER_ID
										.value(new JdbcNamedParameter(
												"classOwnerId"))

						).where(USER_CLASS_TABLE.CLASS_ID
								.eq(new JdbcNamedParameter("classId")));
					}
				});
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	public int[] batchDelete(List<UserClass> userClass) {
		if (CollectionUtil.isEmpty(userClass)) {
			return new int[0];
		}

		return getDslTemplate().batchDelete(userClass,
				new NoParamDeleteGenerateCallback() {
					public Delete generate() {
						return delete(USER_CLASS_TABLE)
								.where(and(
										USER_CLASS_TABLE.CLASS_NAME
												.eq(new JdbcNamedParameter(
														"className")),
										USER_CLASS_TABLE.CLASS_OWNER_ID
												.eq(new JdbcNamedParameter(
														"classOwnerId"))

								));
					}
				});
	}
}
