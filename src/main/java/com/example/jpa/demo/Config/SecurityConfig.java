package com.example.jpa.demo.Config;
import com.example.jpa.demo.Handler.EvolutionaryAuthenticationFailHandler;
import com.example.jpa.demo.Handler.EvolutionaryAuthenticationSuccessHandler;
import com.example.jpa.demo.Service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import javax.annotation.Resource;

/**
 * @EnableWebSecurity：此注解会启用Spring Security
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private MyUserDetailService myUserDetailService;

    @Autowired
    private  SecurityProperties  securityProperties;

    @Autowired
    private EvolutionaryAuthenticationSuccessHandler evolutionaryAuthenticationSuccessHandler;

    @Autowired
    private EvolutionaryAuthenticationFailHandler evolutionaryAuthenticationFailHandler;

/**
     * 1）HttpSecurity支持cors。
     * 2）默认会启用CRSF，此处因为没有使用thymeleaf模板（会自动注入_csrf参数），
     * 要先禁用csrf，否则登录时需要_csrf参数，而导致登录失败。
     * 3）antMatchers：匹配 "/" 路径，不需要权限即可访问，匹配 "/user" 及其以下所有路径，
     *  都需要 "USER" 权限
     * 4）配置登录地址和退出地址
     */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()  //表单登录
                .loginPage("/authentication/require")      //自定义登录页面URL
                .loginProcessingUrl("/authentication/form")  //自定义表单登录地址
                .successHandler(evolutionaryAuthenticationSuccessHandler)
                .failureHandler(evolutionaryAuthenticationFailHandler)
                .successForwardUrl("/home")
                .failureForwardUrl("/err")
                .and()
                .authorizeRequests()   // 对请求做授权
                .antMatchers("/authentication/require",securityProperties.getBrower().getLoginPage())//此路径放行 否则会陷入死循环
                .permitAll()
                .anyRequest()   // 任何请求
                .authenticated()   // 都需要身份认证
                .and().csrf().disable();  // 暂时将防护跨站请求伪造的功能置为不可用
    }


    // 校验密码
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailService).passwordEncoder(passwordEncoder());
    }

    /**
     * 密码加密
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
