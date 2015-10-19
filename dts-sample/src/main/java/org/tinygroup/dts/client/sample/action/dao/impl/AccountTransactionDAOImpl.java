package org.tinygroup.dts.client.sample.action.dao.impl;

import java.sql.SQLException;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.tinygroup.dts.client.sample.action.AccountTransaction;
import org.tinygroup.dts.client.sample.action.dao.AccountTransactionDAO;

public class AccountTransactionDAOImpl extends SqlMapClientDaoSupport implements AccountTransactionDAO {

    public void addTransaction(AccountTransaction accountTransaction) throws SQLException {
        getSqlMapClient().insert("addAccountTransaction", accountTransaction);
    }

    public AccountTransaction findTransaction(String txId) throws SQLException{
        return (AccountTransaction) getSqlMapClient().queryForObject("getAccountTransaction", txId);
    }

    public void deleteTransaction(String txId) throws SQLException {
        getSqlMapClient().delete("deleteAccountTransaction", txId);
    }

}
