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

package org.tinygroup.dslcrud;

import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;
import static org.tinygroup.dslcrud.TUserTable.*;
import static org.tinygroup.tinysqldsl.Select.*;
import static org.tinygroup.tinysqldsl.Insert.*;
import static org.tinygroup.tinysqldsl.Delete.*;
import static org.tinygroup.tinysqldsl.Update.*;

import java.util.List;

import org.tinygroup.tinysqldsl.Delete;
import org.tinygroup.tinysqldsl.DslSession;
import org.tinygroup.tinysqldsl.Insert;
import org.tinygroup.tinysqldsl.Select;
import org.tinygroup.tinysqldsl.Update;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;

public class TUserDaoImpl implements TUserDao {

	private DslSession dslSession;

	public DslSession getDslSession() {
		return dslSession;
	}

	public void setDslSession(DslSession dslSession) {
		this.dslSession = dslSession;
	}

	public TUser insertTUser(TUser tUser) {
		Insert insert = insertInto(T_USER).values(
				//T_USER.ID.value(tUser.getId()),
				T_USER.NAME.value(tUser.getName()),
				T_USER.AGE.value(tUser.getAge()));
		dslSession.execute(insert);
		return tUser;
	}

	public int updateTUser(TUser tUser) {
		Update update = update(T_USER).set(
				T_USER.NAME.value(tUser.getName()),
				T_USER.AGE.value(tUser.getAge())).where(
				T_USER.ID.eq(tUser.getId()));
		return dslSession.execute(update);
	}

	public int deleteTUser(Integer pk) {
		Delete delete = delete(T_USER).where(T_USER.ID.eq(pk));
		return dslSession.execute(delete);
	}

	public int deleteTUsers(Object... pks) {
		Delete delete = delete(T_USER).where(T_USER.ID.in(pks));
		return dslSession.execute(delete);
	}

	@SuppressWarnings({"rawtypes" })
	public TUser getTUserById(Integer pk) {
		Select select = selectFrom(T_USER).where(T_USER.ID.eq(pk));
		return dslSession.fetchOneResult(select, TUser.class);
	}

	@SuppressWarnings({"rawtypes" })
	public List<TUser> queryTUsers(TUser tUser) {
		if(tUser==null){
			tUser=new TUser();
		}
		Select select = selectFrom(T_USER);
//		.where(
//				and(
//				T_USER.NAME.eq(tUser.getName()),
//				T_USER.AGE.eq(tUser.getAge())));
		return dslSession.fetchList(select, TUser.class);
	}

	@SuppressWarnings({"rawtypes" })
	public Pager<TUser> queryTUsersForPage(int start,int limit ,TUser tUser) {
		if(tUser==null){
			tUser=new TUser();
		}
		Select select = MysqlSelect.selectFrom(T_USER).where(
				and(
				T_USER.NAME.eq(tUser.getName()),
				T_USER.AGE.eq(tUser.getAge())));
		return dslSession.fetchPage(select,start, limit, false, TUser.class);
	}

}
