package br.com.fernandaviana.apirestful.services;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.fernandaviana.apirestful.security.UserSecurity;

public class LoginService {
	
	public static UserSecurity authenticated() {
		try {
			return (UserSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
	}
}
