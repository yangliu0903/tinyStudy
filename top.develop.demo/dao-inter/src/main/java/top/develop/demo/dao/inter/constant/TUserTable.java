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

package top.develop.demo.dao.inter.constant;

import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

public class TUserTable extends Table {

	public static final TUserTable T_USERTABLE = new TUserTable();
	/** 唯一ID */
	public final Column ID = new Column(this, "id");
	/** 姓名 */
	public final Column NAME = new Column(this, "name");
	/** 年龄 */
	public final Column AGE = new Column(this, "age");

		private TUserTable() {
			super("t_user");
		}

}
