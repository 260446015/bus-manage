package com.example.busmanage.config;

import com.example.busmanage.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author yindwe@yonyou.com
 * @Date 2020/3/19
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserServiceImpl userService;

    public SecurityConfig(UserServiceImpl userService) {
        this.userService = userService;
    }

    // 配置用户及其对应的角色
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return new MyUserDetailService(userService);
    }

    // 配置 URL 访问权限
    @Override
    protected  void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests() // 开启 HttpSecurity 配置
                .antMatchers("/admin/**").hasRole("ADMIN") // admin/** 模式URL必须具备ADMIN角色
                .antMatchers("/user/**").access("hasAnyRole('ADMIN','USER')") // 该模式需要ADMIN或USER角色
                .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')") // 需ADMIN和DBA角色
                .anyRequest().authenticated() // 用户访问其它URL都必须认证后访问（登录后访问）
                .and().formLogin().loginPage("/auth/login").loginProcessingUrl("/login").permitAll() // 开启表单登录并配置登录接口
                .defaultSuccessUrl("/page/bus/showSystem.html")
                .and().csrf().disable(); // 关闭csrf
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
