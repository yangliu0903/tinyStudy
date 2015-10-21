package org.tinygroup.secondaction.action.dao;

import java.sql.SQLException;

import org.tinygroup.secondaction.action.Account;

/**
 * 账户操作dao
 */
public interface AccountDAO {

    void addAccount(Account account) throws SQLException;
    
    void updateAmount(Account account) throws SQLException;
    
    void updateFreezedAmount(Account account) throws SQLException;
    
    Account getAccount(String accountNo) throws SQLException;
}
