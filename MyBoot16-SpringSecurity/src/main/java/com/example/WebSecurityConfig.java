package com.example;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity //@EnableWebSecurity注解开启Spring Security的功能,需要手动开启
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	
	/**
	 * configureGlobal(AuthenticationManagerBuilder auth)方法，
	 * 在内存中创建了一个用户，该用户的名称为user，密码为password，用户角色为USER。
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		    .inMemoryAuthentication()
		       .withUser("user").password("password").roles("USER");
	}

	/**
	 * configure(HttpSecurity http)方法
	 * 通过authorizeRequests()定义哪些URL需要被保护、哪些不需要被保护。例如以上代码指定了/和/home不需要任何认证就可以访问，其他的路径都必须通过身份验证。
	 * 通过formLogin()定义当需要用户登录时候，转到的登录页面。
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		       .antMatchers("/","/home").permitAll()
		       .anyRequest().authenticated()
		       .and()
		     .formLogin()
		       .loginPage("/login")
		       .permitAll()
		       .and()
		     .logout()
		       .permitAll();
		     
	}

	
}
