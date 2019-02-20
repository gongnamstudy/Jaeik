package com.samsung.tech.gongnam.dao.factory;

import com.samsung.tech.gongnam.dao.UserDao;
import com.samsung.tech.gongnam.dao.connection.ConnectionMaker;
import com.samsung.tech.gongnam.dao.connection.impl.DConnectionMaker;
import com.samsung.tech.gongnam.dao.partner.DUserDao;
import com.samsung.tech.gongnam.dao.partner.NUserDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {

    @Bean
    public UserDao userDao() {
        return new UserDao();
    }

    public NUserDao nUserDao() {
        return new NUserDao(connectionMaker());
    }

    public DUserDao dUserDao() {
        return new DUserDao(connectionMaker());
    }

    @Bean
    public ConnectionMaker connectionMaker() {
        return new DConnectionMaker();
    }
}
