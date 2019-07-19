package com.example.jpa.demo;
import com.example.jpa.demo.Dao.UserDao;
import com.example.jpa.demo.Entity.UserDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FindTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void testLocate() {

        Optional<UserDO> userDOOptional = userDao.findById(2L);

        if (userDOOptional.isPresent()) {
            UserDO userDO = userDOOptional.get();
            System.out.println("name = " + userDO.getName());
            System.out.println("account = " + userDO.getAcount());
        }
    }

    @Test
    public void testFindAll() {
        List<UserDO> userDOList = userDao.findAll(new Sort(Sort.Direction.ASC, "acount"));
        for (UserDO userDO : userDOList) {
            System.out.println("name = " + userDO.getName());
            System.out.println("account = " + userDO.getAcount());
        }
    }

    @Test
    public void testFindByAccount() {
        UserDO userDO = userDao.findByAcount("wentian");
        if (userDO != null) {
            System.out.println("name = " + userDO.getName());
            System.out.println("account = " + userDO.getAcount());
        }
    }

    @Test
    public void testFindByAccountAndPwd() {
        UserDO userDO = userDao.findByAcountAndPwd("wentian","123456");
        if (userDO != null) {
            System.out.println("name = " + userDO.getName());
            System.out.println("account = " + userDO.getAcount());
        }
    }

    @Test
    public void testFindAllByIdGreaterThan() {
        List<UserDO> userDOList = userDao.findAllByIdGreaterThan(2L);
        for(UserDO userDO :userDOList){
            System.out.println("name = " + userDO.getName());
            System.out.println("account = " + userDO.getAcount());
        }
    }

    @Test
    public void testFindTwoName() {
        List<UserDO> userDOList = userDao.findTwoName("风清扬","任我行");
        for(UserDO userDO :userDOList){
            System.out.println("name = " + userDO.getName());
            System.out.println("account = " + userDO.getAcount());
        }
    }

    /* 关联查询*/
   @Test
    public void testFindUsersByRole() {
        List<UserDO> userDOList = userDao.findUsersByRole(2L);
       for(UserDO userDO :userDOList){
           System.out.println("name = " + userDO.getName());
           System.out.println("account = " + userDO.getAcount());
       }
    }

}
