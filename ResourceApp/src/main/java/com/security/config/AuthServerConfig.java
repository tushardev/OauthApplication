package com.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		
		
		String encodedPassword = new SecurityConfig().passwordEncoder().encode("secret");
		
		clients.inMemory().withClient("loginclient").secret(encodedPassword).authorizedGrantTypes("authorization_code")
        .scopes("read").authorities("CLIENT").redirectUris("http://localhost:8090/clientapp/api/v1/loginSuccess");
	}
}
