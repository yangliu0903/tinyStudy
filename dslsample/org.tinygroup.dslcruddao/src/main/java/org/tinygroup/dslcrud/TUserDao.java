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

import java.util.List;

import org.tinygroup.tinysqldsl.DslSession;
import org.tinygroup.tinysqldsl.Pager;

public interface TUserDao {

	public DslSession getDslSession();

	public void setDslSession(DslSession dslSession);

	public TUser insertTUser(TUser tUser);

	public int updateTUser(TUser tUser);

	public int deleteTUser(Integer pk);

	public TUser getTUserById(Integer pk);

	public int deleteTUsers(Object... pks);

	public List<TUser> queryTUsers(TUser tUser);

	public Pager<TUser> queryTUsersForPage (int start ,int limit ,TUser tUser);

}
