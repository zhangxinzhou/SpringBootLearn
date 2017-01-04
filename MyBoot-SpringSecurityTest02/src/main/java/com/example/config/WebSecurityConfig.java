package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.example.security.CustomeUserService;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	CustomeUserService customeUserService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customeUserService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()	         
		         .antMatchers("/admin/**").hasRole("ADMIN")  //admin只对admin开放
		         .antMatchers("/user/**").hasAnyRole("ADMIN","USER")//user对admin和user开放
		         .antMatchers("/visitor/**").permitAll()  //visitor对所有人开放
		         .anyRequest().authenticated()      //其他,只要是登录用户就能访问
		         .and()
		    .formLogin()
		         .loginPage("/login")                //登录页面访问的地址
		         .defaultSuccessUrl("/index")        //登录成功后转向的页面
		         .failureUrl("/login?error")         //登录失败后转入的页面
		         .permitAll()                        
		         .and()
		    .logout()
		         .logoutUrl("/custome-logout")      //自定义注销的url路径,如果不设置则默认/logout
		         .logoutSuccessUrl("/login")        //注销成功后转向的页面,这也是默认的设置
		         .deleteCookies("JSESSIONID")       //注销就删掉cookies
		         .permitAll();
		
		
		http.sessionManagement()
		         .maximumSessions(1).and()         //只允许一个用户登录,第二个登录的用户会把第一个t下线
		         .invalidSessionUrl("/login");     //session过期之后转向login
		
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		//默认不拦截的路径:/css/**,/js/**,/images/**,/webjars/**,/**/favicon.ico,/error(这些是默认的配置)
		web.ignoring()
		   .antMatchers("/test/**"); //以static设置无效,故在static下建立test,让security忽略test,
		                             //如果还有其他的可以.antMatchers("/test/**","/1/**","/2/**")
	}

	

	
	
	
}
