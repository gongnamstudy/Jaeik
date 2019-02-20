package com.samsung.tech.gongnam.dao.factory;

import com.samsung.tech.gongnam.dao.UserDao;
import com.samsung.tech.gongnam.dao.connection.ConnectionMaker;
import com.samsung.tech.gongnam.dao.connection.counting.CountingConnectionMaker;
import com.samsung.tech.gongnam.dao.connection.impl.DConnectionMaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CountingDaoFactory {

    @Bean
    public UserDao userDao() {
        return new UserDao(connectionMaker());
    }

    @Bean
    public ConnectionMaker connectionMaker() {
        return new CountingConnectionMaker(realConnectionMaker());
    }

    @Bean
    public ConnectionMaker realConnectionMaker() {
        return new DConnectionMaker();
    }
}
