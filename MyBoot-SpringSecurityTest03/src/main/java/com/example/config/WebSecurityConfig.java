package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.example.security.CustomerUserService;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)//有此注解才能让@PreAuthorize("hasRole('ROLE_ADMIN')")生效
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private CustomerUserService customerUserService;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customerUserService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		          //.anyRequest().permitAll() 任何页面都能访问
		          .anyRequest().authenticated()  //需要登录才能访问
		          .and()
		    .formLogin()
		          .loginPage("/login")
		          .defaultSuccessUrl("/")
		          .permitAll()
		          .and() 
		    .logout()
		          .logoutUrl("/logout")
		          //  .logoutSuccessUrl("/login")
		          .deleteCookies("JSESSIONID")
		          .permitAll();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// 默认不拦截的路径:/css/**,/js/**,/images/**,/webjars/**,/**/favicon.ico,/error(这些是默认的配置)
		// 如果还有其他的可以.antMatchers("/test/**","/1/**","/2/**")
		web.ignoring().antMatchers("/jquery-easyui-1.5.1/**","/images/**","/js/**"); 											
	}
	
}
