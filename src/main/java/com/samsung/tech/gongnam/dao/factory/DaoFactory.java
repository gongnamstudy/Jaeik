package com.samsung.tech.gongnam.dao.factory;

import com.samsung.tech.gongnam.dao.UserDao;
import com.samsung.tech.gongnam.dao.connection.ConnectionMaker;
import com.samsung.tech.gongnam.dao.connection.impl.DConnectionMaker;
import com.samsung.tech.gongnam.dao.connection.impl.NConnectionMaker;
import com.samsung.tech.gongnam.dao.partner.DUserDao;
import com.samsung.tech.gongnam.dao.partner.NUserDao;

public class DaoFactory {

    public UserDao userDao() {
        ConnectionMaker connectionMaker = new DConnectionMaker();
        UserDao userDao = new UserDao(connectionMaker);

        return userDao;
    }

    public NUserDao nUserDao() {
        ConnectionMaker connectionMaker = new NConnectionMaker();
        NUserDao nUserDao = new NUserDao(connectionMaker);

        return nUserDao;
    }

    public DUserDao dUserDao() {
        ConnectionMaker connectionMaker = new DConnectionMaker();
        DUserDao dUserDao = new DUserDao(connectionMaker);

        return dUserDao;
    }
}
