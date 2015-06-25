/**
 *  Copyright (c) 1997-2013, www.tinygroup.org (tinygroup@126.com).
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
package org.tinygroup.tinyonlineinterface;

import java.util.List;
import org.tinygroup.tinydb.exception.TinyDbException;

public interface ITinyDbOnlineService<T> {
	//获取所有章节
	public List<T> queryAllChapers(T Chapter) throws TinyDbException;
   //通过章节id查询该章节的所有子项,并且按itemId排序
	public List<T> queryItemByChapId(int chapId)throws TinyDbException;
}
