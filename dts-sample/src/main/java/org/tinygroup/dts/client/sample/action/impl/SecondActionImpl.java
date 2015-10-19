package org.tinygroup.dts.client.sample.action.impl;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.tinygroup.dts.base.BusinessActionContext;
import org.tinygroup.dts.client.sample.action.Account;
import org.tinygroup.dts.client.sample.action.AccountTransaction;
import org.tinygroup.dts.client.sample.action.SecondAction;
import org.tinygroup.dts.client.sample.action.dao.AccountDAO;
import org.tinygroup.dts.client.sample.action.dao.AccountTransactionDAO;
import org.tinygroup.dts.client.sample.exception.TransactionFailException;

/**
 * B银行参与者服务
 */
public class SecondActionImpl implements SecondAction {

    private AccountDAO            accountDAO;

    private AccountTransactionDAO accountTransactionDAO;

    private TransactionTemplate   transactionTemplate;

    public void prepare_add(final BusinessActionContext businessActionContext,
                            final String accountNo, final double amount) {
        transactionTemplate.execute(new TransactionCallback() {
            public Object doInTransaction(TransactionStatus status) {
                try {
                    //先记一笔账户操作流水
                    AccountTransaction accountTransaction = new AccountTransaction();
                    accountTransaction.setTxId(businessActionContext.getTxId());
                    accountTransaction.setAccountNo(accountNo);
                    accountTransaction.setAmount(amount);
                    accountTransaction.setType("add");
                    accountTransactionDAO.addTransaction(accountTransaction);
                    //再递增冻结金额，表示这部分钱已经被冻结，不能使用
                    Account account = accountDAO.getAccount(accountNo);
                    double freezedAmount = account.getFreezedAmount() + amount;
                    account.setFreezedAmount(freezedAmount);
                    accountDAO.updateFreezedAmount(account);
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
                    Account account = accountDAO.getAccount(accountTransaction.getAccountNo());
                    //加钱
                    double amount = account.getAmount() + accountTransaction.getAmount();
                    account.setAmount(amount);
                    accountDAO.updateAmount(account);
                    //冻结金额相应减少
                    account.setFreezedAmount(account.getFreezedAmount()
                                             - accountTransaction.getAmount());
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
                    AccountTransaction accountTransaction = accountTransactionDAO
                        .findTransaction(businessActionContext.getTxId());
                    Account account = accountDAO.getAccount(accountTransaction.getAccountNo());
                    account.setFreezedAmount(account.getFreezedAmount()
                                             - accountTransaction.getAmount());
                    accountDAO.updateFreezedAmount(account);

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
