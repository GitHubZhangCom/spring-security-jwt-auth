package com.jwt.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.jwt.server.filter.JwtAuthenticationFilter;
import com.jwt.server.filter.JwtLoginFilter;

/**
 * Created with IntelliJ IDEA. Date: 2017/11/16 Time: 10:38 Email:
 * hyf_spring@163.com
 *
 * @author huyunfan
 */
@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private ADAuthenticationProvider adAuthProvider;

	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		
		  http.cors().and().csrf().disable().authorizeRequests()
          .antMatchers("/employee/login","/login", "/oauth/authorize", "/css/**", "/images/*").permitAll()
//          .antMatchers("/employee/login").hasRole("API")
//          .antMatchers("/login", "/oauth/authorize").hasRole("USER")
          .anyRequest().authenticated()
          .and()
          .requestMatchers().antMatchers("/employee/login","/login","/oauth/authorize")
          .and()
          .addFilter(new JwtLoginFilter(authenticationManager()))
          .addFilter(new JwtAuthenticationFilter(authenticationManager()));
		  
		  

	}
	
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	
        auth.parentAuthenticationManager(authenticationManager)
        .authenticationProvider(adAuthProvider).authenticationEventPublisher(authenticationEventPublisher());
    }
    
    @Bean
	public AuthenticationEventPublisher authenticationEventPublisher() {
		return new DefaultAuthenticationEventPublisher();
	}

}
