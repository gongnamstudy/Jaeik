package com.samsung.tech.gongnam;

import com.samsung.tech.gongnam.dao.UserDao;
import com.samsung.tech.gongnam.dao.connection.impl.DConnectionMaker;
import com.samsung.tech.gongnam.dao.factory.DaoFactory;
import com.samsung.tech.gongnam.domain.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

@SpringBootApplication
public class GongnamApplication {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        SpringApplication.run(GongnamApplication.class, args);

        UserDao userDao = new UserDao(new DaoFactory().connectionMaker());

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

    }

}
