package org.tinygroup.dts.client.sample.activity;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.tinygroup.dts.api.BusinessActivityControlService;
import org.tinygroup.dts.client.sample.action.FirstAction;
import org.tinygroup.dts.client.sample.action.SecondAction;
import org.tinygroup.dts.domain.BusinessActivityId;

/**
 * 跨行转账服务，账户A和账户B分别属于两个假象系统，数据存于2个不同的数据库
 * 从A银行账户'zhangsan'转钱到B银行账户'lisi'，也就是A扣钱，B加钱的操作
 * 为了保证一致性，使用dts做分布式事务实现
 */
public class TransferService {

    /**
     * 发起方自己的事务模版
     */
    private TransactionTemplate            transactionTemplate;

    /**
     * A银行的参与者
     */
    private FirstAction                    firstAction;

    /**
     * B银行的参与者
     */
    private SecondAction                   secondAction;

    /**
     * dts的事务控制服务
     */
    private BusinessActivityControlService businessActivityControlService;

    /**
     * 执行转账操作
     * 
     * @param from
     * @param to
     * @param amount
     */
    public void transfer(final String from, final String to, final double amount) {

        /**
         * 注意：开启dts服务必须包含在发起方的本地事务模版中
         */
        transactionTemplate.execute(new TransactionCallback() {

            public Object doInTransaction(TransactionStatus status) {
                System.out.println("开始启动xts分布式事务活动");
                //启动分布式事务，注意接口参数，第一个是业务标示（需要xts管理员分配），第二个是业务系统指定的业务号（为null，则xts框架自动分配一个），第三个是分布式事务的全局上下文信息
                BusinessActivityId businessActivityId = businessActivityControlService.start(
                    "001001", null, null);
                System.out.println("启动分布式事务成功，事务号：" + businessActivityId.toStringForm());
                System.out.println("一阶段----准备从A银行执行扣钱操作");
                //第一个参与者扣钱操作
                firstAction.prepare_minus(null, from, amount);
                System.out.println("一阶段----从A银行执行扣钱操作成功");
                System.out.println("一阶段----准备从B银行执行加钱操作");
                //第二个参与者价钱操作
                secondAction.prepare_add(null, to, amount);
                System.out.println("一阶段----从B银行执行加钱操作");
                return null;
            }
        });
        System.out.println("二阶段----转账成功，钱已到位");
    }

    public TransactionTemplate getTransactionTemplate() {
        return transactionTemplate;
    }

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    public FirstAction getFirstAction() {
        return firstAction;
    }

    public void setFirstAction(FirstAction firstAction) {
        this.firstAction = firstAction;
    }

    public SecondAction getSecondAction() {
        return secondAction;
    }

    public void setSecondAction(SecondAction secondAction) {
        this.secondAction = secondAction;
    }

    public BusinessActivityControlService getBusinessActivityControlService() {
        return businessActivityControlService;
    }

    public void setBusinessActivityControlService(BusinessActivityControlService businessActivityControlService) {
        this.businessActivityControlService = businessActivityControlService;
    }

}
