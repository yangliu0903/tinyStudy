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
import static org.db.constant.ScoreTable.*;
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

import org.db.pojo.Score;
import org.db.ScoreDao;

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
public class ScoreDaoImpl extends TinyDslDaoSupport implements ScoreDao {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	public Score add(Score score) {
		return getDslTemplate().insertAndReturnKey(score,
				new InsertGenerateCallback<Score>() {
					public Insert generate(Score t) {
						Insert insert = insertInto(SCORE_TABLE)
								.values(SCORE_TABLE.SCORE_ID.value(t
										.getScoreId()),
										SCORE_TABLE.SCORE_POINT.value(t
												.getScorePoint()),
										SCORE_TABLE.SCORE_CLASS_ID.value(t
												.getScoreClassId()),
										SCORE_TABLE.SCORE_USER_ID.value(t
												.getScoreUserId())

								);
						return insert;
					}
				});
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	public int edit(Score score) {
		if (score == null || score.getScoreId() == null) {
			return 0;
		}
		return getDslTemplate().update(score,
				new UpdateGenerateCallback<Score>() {
					public Update generate(Score t) {
						Update update = update(SCORE_TABLE)
								.set(SCORE_TABLE.SCORE_POINT.value(t
										.getScorePoint()),
										SCORE_TABLE.SCORE_CLASS_ID.value(t
												.getScoreClassId()),
										SCORE_TABLE.SCORE_USER_ID.value(t
												.getScoreUserId()))
								.where(SCORE_TABLE.SCORE_ID.eq(t.getScoreId()));
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
						return delete(SCORE_TABLE).where(
								SCORE_TABLE.SCORE_ID.eq(pk));
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
						return delete(SCORE_TABLE).where(
								SCORE_TABLE.SCORE_ID.in(t));
					}
				}, pks);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	public Score getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, Score.class,
				new SelectGenerateCallback<Serializable>() {

					@SuppressWarnings("rawtypes")
					public Select generate(Serializable t) {
						return selectFrom(SCORE_TABLE).where(
								SCORE_TABLE.SCORE_ID.eq(t));
					}
				});
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	public List<Score> query(Score score, final OrderBy... orderArgs) {
		if (score == null) {
			score = new Score();
		}
		return getDslTemplate().query(score,
				new SelectGenerateCallback<Score>() {
					@SuppressWarnings("rawtypes")
					public Select generate(Score t) {
						Select select = selectFrom(SCORE_TABLE).where(
								and(SCORE_TABLE.SCORE_POINT.eq(t
										.getScorePoint()),
										SCORE_TABLE.SCORE_CLASS_ID.eq(t
												.getScoreClassId()),
										SCORE_TABLE.SCORE_USER_ID.eq(t
												.getScoreUserId())

								));
						return TinyDSLUtil
								.addOrderByElements(select, orderArgs);
					}
				});
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	public Pager<Score> queryPager(int start, int limit, Score score,
			final OrderBy... orderArgs) {
		if (score == null) {
			score = new Score();
		}
		return getDslTemplate().queryPager(start, limit, score, false,
				new SelectGenerateCallback<Score>() {
					public Select generate(Score t) {
						Select select = Select.selectFrom(SCORE_TABLE).where(
								and(SCORE_TABLE.SCORE_POINT.eq(t
										.getScorePoint()),
										SCORE_TABLE.SCORE_CLASS_ID.eq(t
												.getScoreClassId()),
										SCORE_TABLE.SCORE_USER_ID.eq(t
												.getScoreUserId())

								));
						return TinyDSLUtil
								.addOrderByElements(select, orderArgs);
					}
				});
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	public int[] batchInsert(boolean autoGeneratedKeys, List<Score> score) {
		if (CollectionUtil.isEmpty(score)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, score,
				new NoParamInsertGenerateCallback() {

					public Insert generate() {
						return insertInto(SCORE_TABLE).values(
								SCORE_TABLE.SCORE_POINT
										.value(new JdbcNamedParameter(
												"scorePoint")),
								SCORE_TABLE.SCORE_CLASS_ID
										.value(new JdbcNamedParameter(
												"scoreClassId")),
								SCORE_TABLE.SCORE_USER_ID
										.value(new JdbcNamedParameter(
												"scoreUserId"))

						);
					}
				});
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	public int[] batchInsert(List<Score> scores) {
		return batchInsert(true, scores);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	public int[] batchUpdate(List<Score> score) {
		if (CollectionUtil.isEmpty(score)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(score,
				new NoParamUpdateGenerateCallback() {
					public Update generate() {
						return update(SCORE_TABLE).set(
								SCORE_TABLE.SCORE_POINT
										.value(new JdbcNamedParameter(
												"scorePoint")),
								SCORE_TABLE.SCORE_CLASS_ID
										.value(new JdbcNamedParameter(
												"scoreClassId")),
								SCORE_TABLE.SCORE_USER_ID
										.value(new JdbcNamedParameter(
												"scoreUserId"))

						).where(SCORE_TABLE.SCORE_ID.eq(new JdbcNamedParameter(
								"scoreId")));
					}
				});
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	public int[] batchDelete(List<Score> score) {
		if (CollectionUtil.isEmpty(score)) {
			return new int[0];
		}

		return getDslTemplate().batchDelete(score,
				new NoParamDeleteGenerateCallback() {
					public Delete generate() {
						return delete(SCORE_TABLE)
								.where(and(
										SCORE_TABLE.SCORE_POINT
												.eq(new JdbcNamedParameter(
														"scorePoint")),
										SCORE_TABLE.SCORE_CLASS_ID
												.eq(new JdbcNamedParameter(
														"scoreClassId")),
										SCORE_TABLE.SCORE_USER_ID
												.eq(new JdbcNamedParameter(
														"scoreUserId"))

								));
					}
				});
	}
}
