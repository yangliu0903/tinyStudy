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

package top.develop.demo.dao.inter;

import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;
import static top.develop.demo.dao.inter.constant.TUserTable.*;
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
import top.develop.demo.dao.inter.pojo.TUser;
import top.develop.demo.dao.inter.TUserDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;

import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;

public class TUserDaoImpl extends TinyDslDaoSupport implements TUserDao {

	public TUser insertObject(TUser tUser) {
		return getDslTemplate().insertObject(tUser, new InsertGenerateCallback<TUser>() {
			public Insert generate(TUser t) {
				Insert insert = insertInto(T_USERTABLE).values(
					//T_USERTABLE.ID.value(t.getId()),
					T_USERTABLE.NAME.value(t.getName()),
					T_USERTABLE.AGE.value(t.getAge()));
				return insert;
			}
		});
	}

	public TUser insertObject(boolean autoGeneratedKeys ,TUser tUser) {
		return getDslTemplate().insertObjectAndReturnKey(autoGeneratedKeys ,tUser, new InsertGenerateCallback<TUser>() {

			public Insert generate(TUser t) {
				Insert insert = insertInto(T_USERTABLE).values(
					T_USERTABLE.ID.value(t.getId()),
					T_USERTABLE.NAME.value(t.getName()),
					T_USERTABLE.AGE.value(t.getAge()));
				return insert;
			}
		});
	}

	public int updateObject(TUser tUser) {
		return getDslTemplate().updateObject(tUser, new UpdateGenerateCallback<TUser>() {
			public Update generate(TUser t) {
				Update update = update(T_USERTABLE).set(
					T_USERTABLE.NAME.value(t.getName()),
					T_USERTABLE.AGE.value(t.getAge())).where(
					T_USERTABLE.ID.eq(t.getId()));
				return update;
			}
		});
	}

	public int deleteObject(Serializable pk){
		return getDslTemplate().deleteObject(pk, new DeleteGenerateCallback<Serializable>() {
			public Delete generate(Serializable pk) {
				return delete(T_USERTABLE).where(T_USERTABLE.ID.eq(pk));
			}
		});
	}

	public int deleteObjects(Serializable... pks) {
		return getDslTemplate().deleteObjects(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(T_USERTABLE).where(T_USERTABLE.ID.in(t));
		}
		},pks);
	}

	public TUser getObjectById(Serializable pk) {
		return getDslTemplate().getObjectById(pk, TUser.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(T_USERTABLE).where(T_USERTABLE.ID.eq(t));
			}
		});
	}

	public List<TUser> queryObjects(TUser tUser) {
		if(tUser==null){
			tUser=new TUser();
		}
		return getDslTemplate().queryObjects(tUser, new SelectGenerateCallback<TUser>() {

			@SuppressWarnings("rawtypes")
			public Select generate(TUser t) {
				return selectFrom(T_USERTABLE).where(
				and(
					T_USERTABLE.NAME.eq(t.getName()),
					T_USERTABLE.AGE.eq(t.getAge())));
			}
		});
	}

	public Pager<TUser> queryObjectsForPage(int start,int limit ,TUser tUser) {
		if(tUser==null){
			tUser=new TUser();
		}
		return getDslTemplate().queryObjectsForPage(start, limit, tUser, false, new SelectGenerateCallback<TUser>() {

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

}