package org.tinygroup.tinyonlineinterface;

import java.util.List;
import org.tinygroup.tinydb.exception.TinyDbException;

public interface ITinyDbOnlineService<T> {
	//获取所有章节
	public List<T> queryAllChapers(T Chapter) throws TinyDbException;
   //通过章节id查询该章节的所有子项,并且按itemId排序
	public List<T> queryItemByChapId(int chapId)throws TinyDbException;
}
