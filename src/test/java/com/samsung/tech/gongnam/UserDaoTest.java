package com.samsung.tech.gongnam;

import com.samsung.tech.gongnam.dao.UserDao;
import com.samsung.tech.gongnam.dao.factory.DaoFactory;
import com.samsung.tech.gongnam.domain.User;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class UserDaoTest {

    @Test
    public void userDaoTest() throws SQLException, ClassNotFoundException {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(DaoFactory.class);

        UserDao userDao = context.getBean("userDao", UserDao.class);

        userDao.deleteAll();

        int id = 100;
        User user = new User();
        user.setId("id_" + id);
        user.setName("name_" + id);
        user.setPassword("password_" + id);

        userDao.add(user);
        System.out.println(user.getName() + " 등록 성공");

        User user2 = userDao.get(user.getId());
        System.out.println(user2.getName() + " 조회 성공");

        if(!user.getName().equalsIgnoreCase(user2.getName())) {
            System.out.println("테스트 실패 (name)");
        } else if(!user.getPassword().equals(user2.getPassword())) {
            System.out.println("테스트 실패 (password)");
        } else {
            System.out.println("조회 테스트 성공");
        }
    }
}
