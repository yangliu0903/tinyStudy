package org.tinygroup.secondaction.action.dao;

import java.sql.SQLException;

import org.tinygroup.secondaction.action.AccountTransaction;

/**
 * 交易流水
 */
public interface AccountTransactionDAO {

    void addTransaction(AccountTransaction accountTransaction) throws SQLException;
    
    AccountTransaction findTransaction(String txId) throws SQLException;
 
    void deleteTransaction(String txId) throws SQLException;
}
