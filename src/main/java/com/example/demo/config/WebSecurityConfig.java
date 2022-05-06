package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends  WebSecurityConfigurerAdapter {
	/*
	 @Override
	    public void configure(WebSecurity web) throws Exception {
	        web.ignoring().antMatchers("/**"); 
	    }
	 */
	
	 @Bean
	    public UserDetailsService userDetailsService() {
	        return new UserDetailsServiceImpl();
	    }
	 
	 @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	 
//	 @Autowired
//	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//	        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
//	    }
	 
	 @Bean
	    public DaoAuthenticationProvider authenticationProvider() {
	        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	        authProvider.setUserDetailsService(userDetailsService());
	        authProvider.setPasswordEncoder(passwordEncoder());
	         
	        return authProvider;
	    }
	 @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.authenticationProvider(authenticationProvider());
	    }
	 
	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
	    http.csrf().disable();
	        http.authorizeRequests()
	    		.antMatchers("/api/**").permitAll()
	        	.antMatchers("/**").hasAnyRole("ADMIN", "USER")	        
	            .anyRequest().authenticated()
	            .and()
	            .formLogin().permitAll()
	            .and()
	            .logout().permitAll()
	            .and()
	            .exceptionHandling()
                .accessDeniedPage("/403");
	    }
	 
	
   
   

}