package com.sss.security.config;

import java.util.Arrays;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class MyCustomAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userName = authentication.getName();
		String password = authentication.getCredentials().toString();
		if("Tom Cruise".equals(userName) && "missionimpossible".equals(password)) {
			return new UsernamePasswordAuthenticationToken(userName, password, Arrays.asList());
		}else {
			throw new BadCredentialsException("Credentials are not correct!!");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		/**
		 * Note : "Authentication authentication" obj is provided by AuthenticationManager
		 *  and this "supports()" method is called by AuthenticationManager to check if this 
		 *  custom AuthenticationProvider class supports UsernamePasswordAuthenticationToken.class
		 * 
		 */
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
