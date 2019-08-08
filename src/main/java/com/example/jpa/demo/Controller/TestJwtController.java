package com.example.jpa.demo.Controller;
import com.example.jpa.demo.Entity.ModelResult;
import com.example.jpa.demo.Entity.User;
import com.example.jpa.demo.Handler.JwtInterceptor;
import com.example.jpa.demo.Utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/jwt")
public class TestJwtController {
    @PostMapping("/login")
    public ModelResult<String> login(@RequestBody User user) {

        ModelResult<String> result = new ModelResult<String>();
        // 这里登录就简单的模拟下
        if ("yangjunfei".equals(user.getUserName()) && "123456".equals(user.getUserPwd())) {
            Map<String, Object> userInfoMap = new HashMap<>();
            userInfoMap.put("userName", "yangjunfei");
            String token = JwtUtils.generateToken(userInfoMap);
            result.setData(token);
            return result;
        }else {
            result.setCode(HttpStatus.BAD_REQUEST.toString());
            result.setMsg("用户名或密码错误");
        }
        return result;
    }

    @GetMapping("/test")
    public String test(HttpServletRequest request) {
        // 登录成功后，从request中获取用户信息
        Claims claims = (Claims) request.getAttribute(JwtInterceptor.USER_INFO_KEY);
        if (null != claims) {
            return (String) claims.get("userName");
        }else {
            return "fail";
        }
    }
}
