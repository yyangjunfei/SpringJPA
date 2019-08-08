package com.example.jpa.demo.Config;
import com.example.jpa.demo.Handler.JwtInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/***
 * web相关的定制化配置
 * @author Yang
 * @date 2019-8-2
 * @version 1.0
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // WebMvcConfigurerAdapter 这个类在SpringBoot2.0已过时，官方推荐直接实现WebMvcConfigurer 这个接口

    @Bean
    public JwtInterceptor jwtInterceptor() {
        return new JwtInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration jwtInterceptorRegistration = registry.addInterceptor(jwtInterceptor());
        // 配置拦截器的拦截规则和放行规则
        jwtInterceptorRegistration.addPathPatterns("/jwt/**")
                .excludePathPatterns("/jwt/login");
    }
}
