package com.example.jpa.demo.Handler;
import com.alibaba.fastjson.JSON;
import com.example.jpa.demo.Entity.ModelResult;
import com.example.jpa.demo.Utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * jwtToken校验拦截器
 * @author Yang
 * @blog https://www.cnblogs.com/zeng1994/
 * @version 1.0
 */
public class JwtInterceptor extends HandlerInterceptorAdapter {

    public static final String USER_INFO_KEY = "user_info_key";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //  获取用户 token
        String token = request.getHeader(JwtUtils.getHeaderKey());
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter(JwtUtils.getHeaderKey());
        }
        //  token为空
        if(StringUtils.isEmpty(token)) {
            this.writerErrorMsg(HttpStatus.UNAUTHORIZED.toString(),
                    JwtUtils.getHeaderKey() + " can not be blank",
                    response);
            return false;
        }

        //  校验并解析token，如果token过期或者篡改，则会返回null
        Claims claims = JwtUtils.verifyAndGetClaimsByToken(token);
        if(null == claims){
            this.writerErrorMsg(HttpStatus.UNAUTHORIZED.toString(),
                    JwtUtils.getHeaderKey() + "失效，请重新登录",
                    response);
            return false;
        }
        //  校验通过后，设置用户信息到request里，在Controller中从Request域中获取用户信息
        request.setAttribute(USER_INFO_KEY, claims);
        return true;
    }

    /**
     * 利用response直接输出错误信息
     * @param code
     * @param msg
     * @param response
     * @throws IOException
     */

    private void writerErrorMsg (String code, String msg, HttpServletResponse response) throws IOException {
        ModelResult result= ModelResult.builder().Code(code).Msg(msg).build();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

}
