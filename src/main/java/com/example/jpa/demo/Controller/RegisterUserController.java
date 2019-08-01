package com.example.jpa.demo.Controller;
import com.alibaba.fastjson.JSONObject;
import com.example.jpa.demo.Entity.User;
import com.example.jpa.demo.Service.MyUserDetailService;
import com.example.jpa.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/register")
public class RegisterUserController {

    @Autowired
    private MyUserDetailService myUserDetailService;

    @Autowired
    private UserService userService;

    @PostMapping("/add/user")
    public String registerUser(@Param("userJson") String userJson){

        /*User user=  User.builder().userName("yangjunfei").userPwd("1234").userAcount("yangjunfei").build();*/
        User user = JSONObject.parseObject(userJson,User.class);

        User user1 =userService.getUser(user.getUserName());
        if(user1!=null){
            return "用户名已经存在";
        }else {
            if(myUserDetailService.register(user)!=null){
                return "用户名已注册成功！！";
            }
        }
        return null;
    }
}
