package com.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Hello world!
 *
 */
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class App 
{
    public static void main( String[] args ) {
    	SpringApplication.run(App.class, args);
    }
    
    @RequestMapping("/")
    String firstMethod() {
        return "Getting up first Spring boot Application";
    }
}
