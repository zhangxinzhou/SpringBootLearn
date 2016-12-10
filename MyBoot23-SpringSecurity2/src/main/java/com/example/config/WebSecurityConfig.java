package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.security.CustomUserService;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Bean
	UserDetailsService customeUserService(){
		return new CustomUserService();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customeUserService());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		           .anyRequest().authenticated()
		           .and()
		           .formLogin()
		               .loginPage("/login")
		               .failureUrl("/login?error")
		               .permitAll()
		           .and()
		           .logout().permitAll();
	}
	
	
}
