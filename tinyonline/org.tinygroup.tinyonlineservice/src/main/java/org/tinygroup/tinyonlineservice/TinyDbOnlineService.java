package org.tinygroup.tinyonlineservice;
import java.util.Arrays;
import java.util.List;

import org.tinygroup.beancontainer.BeanContainerFactory;
import org.tinygroup.commons.tools.Assert;
import org.tinygroup.service.annotation.ServiceComponent;
import org.tinygroup.service.annotation.ServiceMethod;
import org.tinygroup.service.annotation.ServiceParameter;
import org.tinygroup.service.annotation.ServiceResult;
import org.tinygroup.support.BeanSupport;
import org.tinygroup.tinydb.DbOperatorFactory;
import org.tinygroup.tinydb.exception.TinyDbException;
import org.tinygroup.tinydb.operator.DBOperator;
import org.tinygroup.tinydb.order.OrderBean;
import org.tinygroup.tinydb.order.impl.OrderByBeanDefault;
import org.tinygroup.tinydb.query.Conditions;
import org.tinygroup.tinyonlineinterface.ITinyDbOnlineService;
import org.tinygroup.tinydb.Bean;

import com.alibaba.fastjson.JSON;


@ServiceComponent(bean="tinyDbOnlineService")
public class TinyDbOnlineService extends BeanSupport  implements ITinyDbOnlineService<Bean>{
	 private String beanType;
	 private DBOperator operator;

	public void setBeanType(String beanType) {
		this.beanType = beanType;
	}
	/** 初始化bean。 */
	 protected void init() throws Exception {
	 Assert.assertNotNull(beanType, "beanType must not null");
	 DbOperatorFactory factory = BeanContainerFactory.getBeanContainer(
	 getClass().getClassLoader()).getBean("tinyDBOperatorFactory");
	 operator = factory.getDBOperator();
	 }
	   @ServiceMethod(serviceId = "queryChaptersTiny")
		@ServiceResult(name = "chapters")
		public List<Bean> queryAllChapers(@ServiceParameter(name = "Chapter") Bean Chapter) {
			if (Chapter == null) {
				Chapter = new Bean(beanType);
			}
			try {
				Bean[] beans = operator.getBeans(Chapter);
				if (beans != null) {
					return Arrays.asList(beans);
				}
				return null;
			} catch (TinyDbException e) {
				throw new RuntimeException(e);
			}
		}
	   @ServiceMethod(serviceId = "queryItemByChapId")
		@ServiceResult(name = "items")
	   public List<Bean> queryItemByChapId(@ServiceParameter(name = "chapId") int chapId){
		   if(chapId <1){
			    return null;
		   }
		   try{
			   Conditions conditions=new Conditions();
			   conditions.condition("chapId", "=", chapId);
			   OrderBean orderBeans[] ={  new OrderByBeanDefault("itemSeqnum",OrderBean.ASC)  };
			    Bean[]beans = operator.getBeans("Item", conditions, orderBeans);
			 
			    //System.out.println(JSON.toJSONString(beans));
			    return     Arrays.asList(beans);
		   }catch(TinyDbException e){
				throw new RuntimeException(e);
		   }
	   }
}
