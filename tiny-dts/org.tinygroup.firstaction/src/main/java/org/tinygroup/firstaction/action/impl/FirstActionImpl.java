package org.tinygroup.firstaction.action.impl;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.tinygroup.dts.base.BusinessActionContext;
import org.tinygroup.firstaction.action.Account;
import org.tinygroup.firstaction.action.AccountTransaction;
import org.tinygroup.firstaction.action.FirstAction;
import org.tinygroup.firstaction.action.dao.AccountDAO;
import org.tinygroup.firstaction.action.dao.AccountTransactionDAO;
import org.tinygroup.firstaction.exception.TransactionFailException;

/**
 * A银行参与者服务
 */
public class FirstActionImpl implements FirstAction {

    //账户dao
    private AccountDAO            accountDAO;
    //账户流水dao
    private AccountTransactionDAO accountTransactionDAO;
    //A银行事务模版
    private TransactionTemplate   transactionTemplate;

    public void prepare_minus(final BusinessActionContext businessActionContext,
                              final String accountNo, final double amount) {
        transactionTemplate.execute(new TransactionCallback() {
        	
            public Object doInTransaction(TransactionStatus status) {
                try {
                    //先记一笔账户操作流水
                    AccountTransaction accountTransaction = new AccountTransaction();
                    accountTransaction.setTxId(businessActionContext.getTxId());
                    accountTransaction.setAccountNo(accountNo);
                    accountTransaction.setAmount(amount);
                    accountTransaction.setType("minus");
                    accountTransactionDAO.addTransaction(accountTransaction);
                    //再递增冻结金额，表示这部分钱已经被冻结，不能使用
                    Account account = accountDAO.getAccount(accountNo);
                    if (account.getAmount() - amount < 0) {
                        throw new TransactionFailException("余额不足");
                    }
                    double freezedAmount = account.getFreezedAmount() + amount;
                    account.setFreezedAmount(freezedAmount);
                    accountDAO.updateFreezedAmount(account);
//                    throw new RuntimeException();
                } catch (Exception e) {
                    throw new TransactionFailException("一阶段操作失败", e);
                }

                return null;
            }
        });
    }

    public boolean commit(final BusinessActionContext businessActionContext) {

       return (Boolean) transactionTemplate.execute(new TransactionCallback() {

            public Object doInTransaction(TransactionStatus status) {
                try {
                    //找到账户操作流水
                    AccountTransaction accountTransaction = accountTransactionDAO
                        .findTransaction(businessActionContext.getTxId());
                    if(accountTransaction==null){
                    	return true;
                    }
                    Account account = accountDAO.getAccount(accountTransaction.getAccountNo());
                    //扣钱
                    double amount = account.getAmount() - accountTransaction.getAmount();
                    if (amount < 0) {
                        throw new TransactionFailException("余额不足");
                    }
                    account.setAmount(amount);
                    accountDAO.updateAmount(account);
                    //冻结金额相应减少
                    account.setFreezedAmount(account.getFreezedAmount() - accountTransaction.getAmount());
                    accountDAO.updateFreezedAmount(account);
                } catch (Exception e) {
                    throw new TransactionFailException("二阶段操作失败", e);
                }
                return true;
            }
        });

    }

    public boolean rollback(final BusinessActionContext businessActionContext) {
       return (Boolean) transactionTemplate.execute(new TransactionCallback() {

            public Object doInTransaction(TransactionStatus status) {
                try {
                    //回滚冻结金额
                    AccountTransaction accountTransaction = accountTransactionDAO
                        .findTransaction(businessActionContext.getTxId());
                    if(accountTransaction==null){
                    	return true;
                    }
                    Account account = accountDAO.getAccount(accountTransaction.getAccountNo());
                    account.setFreezedAmount(account.getFreezedAmount() - accountTransaction.getAmount());
                    accountDAO.updateFreezedAmount(account);
                    //删除流水
                    accountTransactionDAO.deleteTransaction(businessActionContext.getTxId());
                } catch (Exception e) {
                    throw new TransactionFailException("二阶段操作失败", e);
                }
                return true;
            }
        });
    }

    public AccountDAO getAccountDAO() {
        return accountDAO;
    }

    public void setAccountDAO(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public AccountTransactionDAO getAccountTransactionDAO() {
        return accountTransactionDAO;
    }

    public void setAccountTransactionDAO(AccountTransactionDAO accountTransactionDAO) {
        this.accountTransactionDAO = accountTransactionDAO;
    }

    public TransactionTemplate getTransactionTemplate() {
        return transactionTemplate;
    }

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

}
