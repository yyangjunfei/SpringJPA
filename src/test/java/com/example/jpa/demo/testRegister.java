package com.example.jpa.demo;
import com.example.jpa.demo.Dao.UserDao;
import com.example.jpa.demo.Entity.User;
import com.example.jpa.demo.Service.MyUserDetailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class testRegister {

    @Autowired
    private MyUserDetailService myUserDetailService;

    @Autowired
    private UserDao userDao;

    @Test
    public void registerUser(){

        User user=  User.builder().userName("yangjunfei").userPwd("1234").userAcount("yangjunfei").build();
        myUserDetailService.register(user);
    }


    @Test
    public void loginUser(){
        myUserDetailService.loadUserByUsername("yangjunfei");

    }



    @Test
    public void findUser(){
        User user= userDao.findByUserAcountAndUserPwd("yangjunfei","1234");
        System.out.println("user.getUserId():"+user.getUserId());
    }

}
