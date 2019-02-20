package com.samsung.tech.gongnam.dao.connection.impl;

import com.samsung.tech.gongnam.config.DBConfig;
import com.samsung.tech.gongnam.dao.connection.ConnectionMaker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DConnectionMaker implements ConnectionMaker {

    @Override
    public Connection makeConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DBConfig.CLASS_NAME);
        Connection conn = DriverManager.getConnection(
                DBConfig.URL,
                DBConfig.USER_NAME,
                DBConfig.PASSWORD);

        return conn;
    }

}
