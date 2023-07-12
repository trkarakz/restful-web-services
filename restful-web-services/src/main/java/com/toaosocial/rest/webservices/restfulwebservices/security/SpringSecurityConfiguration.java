package com.toaosocial.rest.webservices.restfulwebservices.security;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {

	/**
	 * In Spring security filter chain there is a flow like below :
	 * 	- All requests should be authenticated
	 *  - If a request is not authenticated, a web page is shown
	 *  - CSRF check on POST, PUT requests
	 *  
	 *   We can customize this chain according to our needs as here. 
	 *   Here, we want to skip CSRF control only for PUTs, POSTs
	 * 
	 * @param http
	 * @return
	 * @throws Exception
	 */
	@Bean
	public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
		
		// All requests should be authenticated
		http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
		
		// If request is not authenticated, a web page is shown
		http.httpBasic(withDefaults());
		
		// skip CSRF
		http.csrf().disable();
		
		return http.build();
	}
	
}
