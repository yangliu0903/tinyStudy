package org.tinygroup.dts.client.sample.action;

import org.tinygroup.dts.annotation.TwoPhaseBusinessAction;
import org.tinygroup.dts.base.BusinessActionContext;

/**
 * A银行参与者，执行扣钱操作
 */
public interface FirstAction {

    /**
     * 一阶段方法
     * 
     * @param businessActionContext
     * @param accountNo
     * @param amount
     */
    @TwoPhaseBusinessAction(name = "firstAction", commitMethod = "commit", rollbackMethod = "rollback")
    public void prepare_minus(BusinessActionContext businessActionContext,String accountNo,double amount);

    /**
     * 二阶段的提交方法
     * @param businessActionContext
     * @return
     */
    public boolean commit(BusinessActionContext businessActionContext);

    /**
     * 二阶段的回滚方法
     * @param businessActionContext
     * @return
     */
    public boolean rollback(BusinessActionContext businessActionContext);

}
