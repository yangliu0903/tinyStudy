package org.tinygroup.dts.client.sample.action;

import org.tinygroup.dts.annotation.TwoPhaseBusinessAction;
import org.tinygroup.dts.base.BusinessActionContext;

/**
 * B银行参与者，执行加钱操作
 */
public interface SecondAction {

    /**
     * 一阶段方法
     * 
     * @param businessActionContext
     * @param accountNo
     * @param amount
     */
    @TwoPhaseBusinessAction(name = "secondAction", commitMethod = "commit", rollbackMethod = "rollback")
    public void prepare_add(BusinessActionContext businessActionContext,String accountNo,double amount);

    public boolean commit(BusinessActionContext businessActionContext);

    public boolean rollback(BusinessActionContext businessActionContext);

}
