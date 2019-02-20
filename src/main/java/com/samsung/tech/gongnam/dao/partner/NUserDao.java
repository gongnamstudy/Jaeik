package com.samsung.tech.gongnam.dao.partner;

import com.samsung.tech.gongnam.config.DBConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NUserDao  {

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DBConfig.CLASS_NAME);
        Connection conn = DriverManager.getConnection(
                DBConfig.URL,
                DBConfig.USER_NAME,
                DBConfig.PASSWORD);

        return conn;
    }

}
