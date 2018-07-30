package com.plantwoo.security.config;

import com.plantwoo.security.token.JwtAuthenticationProvider;
import com.plantwoo.security.token.JwtAuthenticationTokenFilter;
import com.plantwoo.security.token.JwtSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.Filter;
import java.util.Collections;

@Configuration
public class JwtSecurityConfig{

    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Bean
    public Filter authenticationTokenFilter() {
        AbstractAuthenticationProcessingFilter jwtAuthenticationTokenFilter = new JwtAuthenticationTokenFilter("/secure/**");
        jwtAuthenticationTokenFilter.setAuthenticationManager(authenticationManager());
        jwtAuthenticationTokenFilter.setAuthenticationSuccessHandler(new JwtSuccessHandler());
        return jwtAuthenticationTokenFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(Collections.singletonList(jwtAuthenticationProvider));
    }
}
