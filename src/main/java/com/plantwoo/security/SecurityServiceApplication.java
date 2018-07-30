package com.plantwoo.security;

import com.plantwoo.security.config.JwtSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SpringBootApplication
public class SecurityServiceApplication extends WebSecurityConfigurerAdapter {

	@Autowired
	JwtSecurityConfig jwtSecurityConfig;

	public static void main(String[] args) {
		SpringApplication.run(SecurityServiceApplication.class, args);
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable()
				.authorizeRequests()
				.antMatchers("/login").permitAll()
				.antMatchers("/secure/**").authenticated()
				.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		httpSecurity.addFilterBefore(jwtSecurityConfig.authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);

		httpSecurity.headers().cacheControl();
	}
}
