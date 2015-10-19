package org.tinygroup.dts.client.sample.runner;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tinygroup.dts.client.sample.activity.TransferService;

/**
 * 测试类
 */
public class XtsRunner {
    /**
     * spring上下文
     */
    static ClassPathXmlApplicationContext applicationContext;
    /**
     * 转账服务
     */
    static TransferService                transferService;

    /**
     * 发起方（activity的数据库）
     */
    static DataSource                     activityDataSource;
    /**
     * 第一个参与者（action）的数据库
     */
    static DataSource                     firstActionDataSource;
    /**
     * 第二个参与者（action）的数据库
     */
    static DataSource                     secondActionDataSource;

    static {
        applicationContext = new ClassPathXmlApplicationContext(new String[]{
            "spring/activity_spring_context.xml",
            "spring/first_action_spring_context.xml",
            "spring/second_action_spring_context.xml"});
        transferService = (TransferService) applicationContext.getBean("transferService");
        activityDataSource = (DataSource) applicationContext.getBean("activityDataSource");
        firstActionDataSource = (DataSource) applicationContext
            .getBean("firstActionAccountDataSource");
        secondActionDataSource = (DataSource) applicationContext
            .getBean("secondActionAccountDataSource");
    }

    public static void main(String[] args) throws Exception {

        //数据准备
        prepare();

        //转账
        System.out.println("准备从张三账户转出100到李四账户");
        transferService.transfer("zhangsan", "lisi", 100);
        System.out.println("转出成功");
        //数据检查
        checkAccount();
    }

    /**
     * 检查账户数据，看看转账成功之后，资金是否到位
     */
    private static void checkAccount() {
        checkFirstActionData();
        checkSecondActionData();
    }

    /**
     * 检查第一个参与者的账户数据
     */
    private static void checkFirstActionData() {
        Connection conn = null;
        try {
            conn = firstActionDataSource.getConnection();
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("select * from account where account_no = 'zhangsan'");
            rs.next();
            System.out.print("检查第一个参与者数据成功，数据:");
            System.out.print("账户=" + rs.getString(1));
            System.out.print(",余额=" + rs.getDouble(2));
            System.out.println(",冻结金额=" + rs.getDouble(3));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 检查第二个参与者数据
     */
    private static void checkSecondActionData() {
        Connection conn = null;
        try {
            conn = secondActionDataSource.getConnection();
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("select * from account where account_no = 'lisi'");
            rs.next();
            System.out.print("检查第二个参与者数据成功，数据:");
            System.out.print("账户:" + rs.getString(1));
            System.out.print(",余额:" + rs.getDouble(2));
            System.out.println(",冻结金额:" + rs.getDouble(3));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @throws Exception 
     * 
     */
    private static void prepare() throws Exception {
        //创建发起方（activity）的xts框架的表，business_activity和business_action表
        createActivityTable();
        //创建第一个参与者（action）的业务表，account账户表，account_transaction账户流水表
        createActionTable(firstActionDataSource);
        //创建第二个参与者（action）的业务表，account账户表，account_transaction账户流水表
        createActionTable(secondActionDataSource);
        //准备第一个参与者（action）数据，户名：张三
        prepareFirstActionData();
        //准备第二个参与者（action）数据，户名：李四
        prepareSecondActionData();
    }

    /**
     * 
     */
    private static void prepareFirstActionData() {
        Connection conn = null;
        try {
            conn = firstActionDataSource.getConnection();
            Statement s = conn.createStatement();
            try {
                s.execute("insert into account values('zhangsan',120,10)");
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.print("准备第一个参与者数据成功，数据:");
            System.out.print("账户=zhangsan");
            System.out.print(",余额=120");
            System.out.println(",冻结金额=10");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void prepareSecondActionData() {
        Connection conn = null;
        try {
            conn = secondActionDataSource.getConnection();
            Statement s = conn.createStatement();
            try {
                s.execute("insert into account values('lisi',40,0)");
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.print("准备第二个参与者数据成功，数据:");
            System.out.print("账户=lisi");
            System.out.print(",余额=40");
            System.out.println(",冻结金额=0");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static void createActivityTable() {
        Connection conn = null;
        try {
            conn = activityDataSource.getConnection();
            Statement s = conn.createStatement();
            try {
                s.execute("drop table if exists business_activity");
                s.execute("create table business_activity(TX_ID VARCHAR(256) not null,STATE VARCHAR(1) not null,GMT_CREATE    TIMESTAMP not null,GMT_MODIFIED        TIMESTAMP not null,CONTEXT             VARCHAR(2048))");
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                s.execute("drop table if exists business_action");
                s.execute("create table business_action(ACTION_ID    VARCHAR(64) not null,TX_ID        VARCHAR(256) not null,NAME         VARCHAR(64) not null,STATE        VARCHAR(1) not null ,GMT_CREATE   TIMESTAMP not null,GMT_MODIFIED TIMESTAMP not null,CONTEXT      VARCHAR(2048) )");
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("创建发起方（activity）的business_activity和business_action表成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static void createActionTable(DataSource actionDataSource) {
        Connection conn = null;
        try {
            conn = actionDataSource.getConnection();
            Statement s = conn.createStatement();
            try {
                s.execute("drop table if exists account");
                s.execute("create table account(account_no varchar(256),amount DOUBLE,freezed_amount DOUBLE)");
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                s.execute("drop table if exists account_transaction");
                s.execute("create table account_transaction(tx_id varchar(256) not null,account_no varchar(256) not null,type varchar(256) not null,amount DOUBLE)");
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("创建参与者（action）的account和account_transaction表成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
