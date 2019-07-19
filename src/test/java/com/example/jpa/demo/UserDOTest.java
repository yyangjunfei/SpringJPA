package com.example.jpa.demo;
import com.example.jpa.demo.Dao.UserDao;
import com.example.jpa.demo.Entity.RoleDO;
import com.example.jpa.demo.Entity.UserDO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDOTest {

    @Autowired
    private UserDao userDao;

    @Before
    public void before() {
        UserDO userDO = new UserDO();
        userDO.setId(1L);
        userDO.setName("风清扬");
        userDO.setAcount("fengqy");
        userDO.setPwd("123456");
        userDao.save(userDO);
        userDO = new UserDO();
        userDO.setId(3L);
        userDO.setName("东方不败");
        userDO.setAcount("bubai");
        userDO.setPwd("123456");
        userDao.save(userDO);
        userDO.setId(5L);
        userDO.setName("向问天");
        userDO.setAcount("wentian");
        userDO.setPwd("123456");
        userDao.save(userDO);
    }

    @Test
    public void testAdd() {
        UserDO userDO = new UserDO();
        userDO.setId(2L);
        userDO.setName("任我行");
        userDO.setAcount("renwox");
        userDO.setPwd("123456");
        userDao.save(userDO);
        userDO = new UserDO();
        userDO.setId(4L);
        userDO.setName("令狐冲");
        userDO.setAcount("linghuc");
        userDO.setPwd("123456");
        userDao.save(userDO);
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
