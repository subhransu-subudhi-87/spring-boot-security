package com.sss.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class MyCustomSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private MyCustomAuthenticationProvider myCustomAuthenticationProvider;
	
/**
 * protected void configure(AuthenticationManagerBuilder auth) method is no longer in use..
 * As we are doing the authentication using MyCustomAuthenticationProvider implementation
 *
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
		UserDetails user = User.withUsername("Tom Cruise").password(this.passwordEncoder().encode("missionimpossible")).authorities("read").build();
		userDetailsService.createUser(user);
		auth.userDetailsService(userDetailsService);
		
	}
	* 
 */
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.authenticationProvider(myCustomAuthenticationProvider);
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//      http.httpBasic();//The type of authentication used is basic-auth
		 http.formLogin();//The type of authentication used is form-based
      http.authorizeRequests().anyRequest().authenticated();//Any request intercepted through this filter should be authorized
	}
	
	@Bean
    @Primary
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
