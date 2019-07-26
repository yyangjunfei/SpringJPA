package com.example.jpa.demo;
import com.example.jpa.demo.Dao.UserDao;
import com.example.jpa.demo.Entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Add_User {

    @Autowired
    private UserDao userDao;

    @Before
    public void before() {
        User userDO1 = User.builder().userId(1L).userName("风清扬").userAcount("fengqy").userPwd("123456").build();
        userDao.save(userDO1);
        User userDO2 = User.builder().userId(3L).userName("东方不败").userAcount("bubai").userPwd("123456").build();
        userDao.save(userDO2);
        User userDO5 = User.builder().userId(5L).userName("向问天").userAcount("wentian").userPwd("123456").build();
        userDao.save(userDO5);
    }

    @Test
    public void testAdd() {
        User userDO2 = User.builder().userId(2L).userName("任我行").userAcount("renwox").userPwd("123456").build();
        userDao.save(userDO2);
        User userDO4 = User.builder().userId(4L).userName("令狐冲").userAcount("linghuc").userPwd("123456").build();
        userDao.save(userDO4);
    }

    @After
    public void after() {
/*        userDao.deleteById(1L);
        userDao.deleteById(2L);
        userDao.deleteById(3L);
        userDao.deleteById(4L);
        userDao.deleteById(5L);*/
    }

}
