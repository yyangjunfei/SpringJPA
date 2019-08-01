package com.example.jpa.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ErrorController {
    @PostMapping("/err")
    public String err(){
        return "err";
    }
}
