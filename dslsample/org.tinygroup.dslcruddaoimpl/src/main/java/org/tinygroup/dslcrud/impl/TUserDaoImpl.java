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

package org.tinygroup.dslcrud.impl;

import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;
import static org.tinygroup.dslcrud.constant.TUserTable.*;
import static org.tinygroup.tinysqldsl.Select.*;
import static org.tinygroup.tinysqldsl.Insert.*;
import static org.tinygroup.tinysqldsl.Delete.*;
import static org.tinygroup.tinysqldsl.Update.*;

import java.io.Serializable;

import java.util.List;

import org.tinygroup.tinysqldsl.Delete;
import org.tinygroup.tinysqldsl.Insert;
import org.tinygroup.tinysqldsl.Select;
import org.tinygroup.tinysqldsl.Update;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
import org.tinygroup.dslcrud.pojo.TUser;
import org.tinygroup.dslcrud.inter.TUserDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;

import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;

public class TUserDaoImpl extends TinyDslDaoSupport implements TUserDao {

	public TUser add(TUser tUser) {
		return getDslTemplate().insertAndReturnKey(tUser, new InsertGenerateCallback<TUser>() {
			public Insert generate(TUser t) {
				Insert insert = insertInto(T_USERTABLE).values(
					//T_USERTABLE.ID.value(t.getId()),
					T_USERTABLE.NAME.value(t.getName()),
					T_USERTABLE.AGE.value(t.getAge()));
				return insert;
			}
		});
	}

	public int edit(TUser tUser) {
		if(tUser == null){
			return 0;
		}
		return getDslTemplate().update(tUser, new UpdateGenerateCallback<TUser>() {
			public Update generate(TUser t) {
				Update update = Update.update(T_USERTABLE).set(
					T_USERTABLE.NAME.value(t.getName()),
					T_USERTABLE.AGE.value(t.getAge())).where(
					T_USERTABLE.ID.eq(t.getId()));
				return update;
			}
		});
	}

	public int deleteByKey(Integer pk){
		if(pk == null){
			return 0;
		}
		return getDslTemplate().deleteByKey(pk, new DeleteGenerateCallback<Serializable>() {
			public Delete generate(Serializable pk) {
				return delete(T_USERTABLE).where(T_USERTABLE.ID.eq(pk));
			}
		});
	}

	public int deleteByKeys(Integer... pks) {
		if(pks == null || pks.length == 0){
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(T_USERTABLE).where(T_USERTABLE.ID.in(t));
		}
		},pks);
	}

	public TUser getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, TUser.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(T_USERTABLE).where(T_USERTABLE.ID.eq(t));
			}
		});
	}

	public List<TUser> query(TUser tUser) {
		if(tUser==null){
			tUser=new TUser();
		}
		return getDslTemplate().query(tUser, new SelectGenerateCallback<TUser>() {

			@SuppressWarnings("rawtypes")
			public Select generate(TUser t) {
				return selectFrom(T_USERTABLE).where(
				and(
					T_USERTABLE.NAME.eq(t.getName()),
					T_USERTABLE.AGE.eq(t.getAge())));
			}
		});
	}

	public Pager<TUser> queryPager(int start,int limit ,TUser tUser) {
		if(tUser==null){
			tUser=new TUser();
		}
		return getDslTemplate().queryPager(start, limit, tUser, false, new SelectGenerateCallback<TUser>() {

			public Select generate(TUser t) {
				return MysqlSelect.selectFrom(T_USERTABLE).where(
				and(
					T_USERTABLE.NAME.eq(t.getName()),
					T_USERTABLE.AGE.eq(t.getAge())));
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<TUser> tUsers) {
		if (CollectionUtil.isEmpty(tUsers)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, tUsers, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(T_USERTABLE).values(
					T_USERTABLE.NAME.value(new JdbcNamedParameter("name")),
					T_USERTABLE.AGE.value(new JdbcNamedParameter("age")));
			}
		});
	}

	public int[] batchInsert(List<TUser> tUsers){
			return batchInsert(true ,tUsers);
	}

	public int[] batchUpdate(List<TUser> tUsers) {
		if (CollectionUtil.isEmpty(tUsers)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(tUsers, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(T_USERTABLE).set(
					T_USERTABLE.NAME.value(new JdbcNamedParameter("name")),
					T_USERTABLE.AGE.value(new JdbcNamedParameter("age"))).where(
				T_USERTABLE.ID.eq(new JdbcNamedParameter("id")));
			}
		});
	}

	public int[] batchDelete(List<TUser> tUsers) {
		if (CollectionUtil.isEmpty(tUsers)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(tUsers, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(T_USERTABLE).where(and(
				T_USERTABLE.ID.eq(new JdbcNamedParameter("id")),
				T_USERTABLE.NAME.eq(new JdbcNamedParameter("name")),
				T_USERTABLE.AGE.eq(new JdbcNamedParameter("age"))));
			}
		});
	}

	public List<TUser> query(TUser t, OrderBy... orderArgs) {
		return query(t);
	}

	public Pager<TUser> queryPager(int start, int limit, TUser t, OrderBy... orderArgs) {
		return queryPager(start,limit,t);
	}

}
