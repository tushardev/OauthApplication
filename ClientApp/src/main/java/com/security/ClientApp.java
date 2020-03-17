package com.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class ClientApp 
{
    public static void main( String[] args ) {
       SpringApplication.run(ClientApp.class, args);
    }
    
    @RequestMapping("/")
    String firstMethod() {
        return "Getting up Client Spring boot Application";
    }
}
