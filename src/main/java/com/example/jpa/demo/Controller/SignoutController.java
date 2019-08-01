package com.example.jpa.demo.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignoutController {
    @GetMapping("/signout/success")
    public String signout() {
        return "退出成功，请重新登录";
    }
}
