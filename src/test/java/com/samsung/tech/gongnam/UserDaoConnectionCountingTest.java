package com.samsung.tech.gongnam;

import com.samsung.tech.gongnam.dao.UserDao;
import com.samsung.tech.gongnam.dao.connection.counting.CountingConnectionMaker;
import com.samsung.tech.gongnam.dao.factory.CountingDaoFactory;
import com.samsung.tech.gongnam.domain.User;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class UserDaoConnectionCountingTest {

    @Test
    public void CountingFactory() throws SQLException, ClassNotFoundException {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(CountingDaoFactory.class);

        UserDao userDao = context.getBean("userDao", UserDao.class);

        userDao.deleteAll();

        int id = 100;
        User user = new User();
        user.setId("id_" + id);
        user.setName("name_" + id);
        user.setPassword("password_" + id);

        userDao.add(user);

        System.out.println(user.getId() + " 등록 성공 !!");

        User user2 = userDao.get(user.getId());
        System.out.println(user2.getName());
        System.out.println(user2.getPassword());
        System.out.println(user2.getId() + " 조회 성공 !!");

        CountingConnectionMaker ccm = context.getBean("connectionMaker", CountingConnectionMaker.class);
        System.out.println("Connection counter : " + ccm.getCounter());
    }
}
