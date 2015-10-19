package org.tinygroup.dts.client.sample.action.dao;

import java.sql.SQLException;

import org.tinygroup.dts.client.sample.action.AccountTransaction;

/**
 * 交易流水
 */
public interface AccountTransactionDAO {

    void addTransaction(AccountTransaction accountTransaction) throws SQLException;
    
    AccountTransaction findTransaction(String txId) throws SQLException;
 
    void deleteTransaction(String txId) throws SQLException;
}
