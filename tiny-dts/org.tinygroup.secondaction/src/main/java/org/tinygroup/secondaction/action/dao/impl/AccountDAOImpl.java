package org.tinygroup.secondaction.action.dao.impl;

import java.sql.SQLException;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.tinygroup.secondaction.action.Account;
import org.tinygroup.secondaction.action.dao.AccountDAO;

public class AccountDAOImpl extends SqlMapClientDaoSupport implements AccountDAO {

    public void addAccount(Account account) throws SQLException {
        getSqlMapClient().insert("addAccount", account);
    }

    public void updateAmount(Account account) throws SQLException {
        getSqlMapClient().update("updateAmount", account);
    }

    public Account getAccount(String accountNo) throws SQLException {
        return (Account) getSqlMapClient().queryForObject("getAccount", accountNo);
    }

    public void updateFreezedAmount(Account account) throws SQLException {
        getSqlMapClient().update("updateFreezedAmount", account);
    }

}
