package com.samsung.tech.gongnam.dao.connection;

import com.samsung.tech.gongnam.config.DBConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SimpleConnectionMaker {

    public Connection makeNewConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DBConfig.CLASS_NAME);
        Connection conn = DriverManager.getConnection(
                DBConfig.URL,
                DBConfig.USER_NAME,
                DBConfig.PASSWORD);

        return conn;
    }
}
