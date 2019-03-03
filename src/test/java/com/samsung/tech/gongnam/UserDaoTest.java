package com.samsung.tech.gongnam;

import com.samsung.tech.gongnam.dao.UserDao;
import com.samsung.tech.gongnam.dao.factory.DaoFactory;
import com.samsung.tech.gongnam.domain.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.SQLException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class UserDaoTest {

    @Test
    public void addAndGet() throws SQLException, ClassNotFoundException {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(DaoFactory.class);

        UserDao userDao = context.getBean("userDao", UserDao.class);

        userDao.deleteAll();
        assertThat(userDao.getCount(), is(0));

        User user1 = new User();
        user1.setId("jaeik92.lee");
        user1.setName("Jaeik Lee");
        user1.setPassword("password");
        userDao.add(user1);

        User user2 = new User();
        user2.setId("i2222");
        user2.setName("n2222");
        user2.setPassword("p2222");
        userDao.add(user2);

        assertThat(userDao.getCount(), is(2));

        User getUser1 = userDao.get(user1.getId());
        assertThat(getUser1.getName(), is(user1.getName()));
        assertThat(getUser1.getPassword(), is(user1.getPassword()));

        User getUser2 = userDao.get(user2.getId());
        assertThat(getUser2.getName(), is(user2.getName()));
        assertThat(getUser2.getPassword(), is(user2.getPassword()));
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void getUserFailure() throws SQLException, ClassNotFoundException {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(DaoFactory.class);

        UserDao userDao = context.getBean("userDao", UserDao.class);
        userDao.deleteAll();

        assertThat(userDao.getCount(), is(0));

        userDao.get("invalid_id");
    }

    @Test
    public void count() throws SQLException, ClassNotFoundException {
        ApplicationContext context
                = new AnnotationConfigApplicationContext(DaoFactory.class);

        UserDao userDao = context.getBean("userDao", UserDao.class);
        User user1 = new User("jaeik92.lee", "Jaeik Lee", "password");
        User user2 = new User("sixhustle", "Hustle", "hpassword");
        User user3 = new User("callcall", "C Call", "cpassword");

        userDao.deleteAll();
        assertThat(userDao.getCount(), is(0));

        userDao.add(user1);
        assertThat(userDao.getCount(), is(1));

        userDao.add(user2);
        assertThat(userDao.getCount(), is(2));

        userDao.add(user3);
        assertThat(userDao.getCount(), is(3));
    }
}
