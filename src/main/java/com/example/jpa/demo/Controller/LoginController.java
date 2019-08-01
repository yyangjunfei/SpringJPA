package com.example.jpa.demo.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController {
    @GetMapping("/loginhtml")
    public String loginhtml(){
        return "login";
    }

}
