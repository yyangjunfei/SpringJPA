package com.example.jpa.demo.Config;
import com.example.jpa.demo.Handler.EvolutionaryAuthenticationFailHandler;
import com.example.jpa.demo.Handler.EvolutionaryAuthenticationSuccessHandler;
import com.example.jpa.demo.Handler.MyAuthenticationAccessDeniedHandler;
import com.example.jpa.demo.Handler.MyLogOutSuccessHandler;
import com.example.jpa.demo.Service.MySessionExpiredStrategy;
import com.example.jpa.demo.Service.MyUserDetailService;
import com.example.jpa.demo.Service.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @EnableWebSecurity：此注解会启用Spring Security
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class BrowserSecurityConfig  extends WebSecurityConfigurerAdapter {

    @Resource
    private MyUserDetailService myUserDetailService;

    @Autowired
    private  SecurityProperties  securityProperties;

    @Autowired
    private ValidateCodeFilter validateCodeFilter;

    @Autowired
    private EvolutionaryAuthenticationSuccessHandler evolutionaryAuthenticationSuccessHandler;

    @Autowired
    private EvolutionaryAuthenticationFailHandler evolutionaryAuthenticationFailHandler;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private MySessionExpiredStrategy sessionExpiredStrategy;

    @Autowired
    private MyLogOutSuccessHandler myLogOutSuccessHandler;

    @Autowired
    private MyAuthenticationAccessDeniedHandler myAuthenticationAccessDeniedHandler;

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        jdbcTokenRepository.setCreateTableOnStartup(false);
        return jdbcTokenRepository;
    }

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
        http
                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class) // 添加验证码校验过滤器
                .formLogin()  //表单登录
                .loginPage("/authentication/require")      //自定义登录页面URL
                .loginProcessingUrl("/login")  //自定义表单登录地址
                .successHandler(evolutionaryAuthenticationSuccessHandler)
                .failureHandler(evolutionaryAuthenticationFailHandler)
/*                .successForwardUrl("/home")
                .failureForwardUrl("/err")*/
            .and()
                .exceptionHandling()
                .accessDeniedHandler(myAuthenticationAccessDeniedHandler)   //自定义权限不足处理器来处理权限不足时候的操作。
            .and()
                .logout()
                .logoutUrl("/signout")
                //.logoutSuccessUrl("/signout/success")    //使用Handler 处理
                .logoutSuccessHandler(myLogOutSuccessHandler)
                .deleteCookies("JSESSIONID")
            .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository()) // 配置 token 持久化仓库
                .tokenValiditySeconds(3600) // remember 过期时间，单为秒
                .userDetailsService(myUserDetailService) //处理自动登陆逻辑
            .and()
                .authorizeRequests()   // 授权配置
                .antMatchers("/static","/register/**","/code/image","/session/invalid","/signout/success").permitAll()
                .antMatchers("/authentication/require",securityProperties.getBrower().getLoginPage())//此路径放行 否则会陷入死循环
                .permitAll()
                .anyRequest()      // 任何请求
                .authenticated()   // 都需要身份认证
            .and()
                .sessionManagement() // 添加 Session管理器
                .invalidSessionUrl("/session/invalid") // Session失效后跳转到这个链接
                .maximumSessions(1)    //maximumSessions配置了最大Session并发数量为1个
                //.maxSessionsPreventsLogin(true)   //控制当Session达到最大有效数的时候，不再允许相同的账户登录。
                .expiredSessionStrategy(sessionExpiredStrategy)   //expiredSessionStrategy配置了Session在并发下失效后的处理策略
                .and()
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
