package com.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter  {
	
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }
	
	
	/*@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		 //Using in-memory authentication provider here 
		String encodedPassword = passwordEncoder().encode("kadale");
        auth.inMemoryAuthentication().
                withUser("tushar").password(encodedPassword).roles("ADMIN");
	}*/
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/* Commented code For local authorization server flow */
		/*http.authorizeRequests().antMatchers("/").permitAll()
			.antMatchers("/api/v1/login-success").hasAnyRole("ADMIN")
			.anyRequest().authenticated().and().formLogin()
			.permitAll().and().logout().permitAll();
			
			http.csrf().disable();*/
		
		 http.authorizeRequests()
         .anyRequest().authenticated()
         .and()
         .oauth2Login();
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
