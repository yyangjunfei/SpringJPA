package com.example.jpa.demo.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @GetMapping("hello")
    public String hello() {
        return "hello spring security";
    }     //测试

    @GetMapping("index")
    public Object index(Authentication authentication) {
        // return SecurityContextHolder.getContext().getAuthentication();   //获取用户登陆信息
        return authentication;
    }

    @GetMapping("/auth/admin")
    @PreAuthorize("hasAuthority('admin')")    //权限注解标明只有拥有“admin”权限的人才能访问
    public String authenticationTest() {
        return "您拥有admin权限，可以查看";
    }
}
