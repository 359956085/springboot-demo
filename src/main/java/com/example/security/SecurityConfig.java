package com.example.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Bean
    UserDetailsService customUserService(){
        return new CustomUserDetailsService();
    }
	
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService())
            .passwordEncoder(new MyPasswordEncoder());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf() //跨站
            .disable()  //关闭跨站检测
            .authorizeRequests()    //验证策略
            	.antMatchers("/static/**").permitAll()  
            	.antMatchers("/user/**").permitAll()  
                .anyRequest()   //所有请求
                .authenticated()    //需要验证
                .and()
            .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/index")
                .failureUrl("/loginFailure")
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }
}
