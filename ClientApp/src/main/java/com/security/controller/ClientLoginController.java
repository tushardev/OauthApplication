package com.security.controller;

import java.io.IOException;
import java.util.Arrays;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.security.service.login.LoginService;

@RestController
public class ClientLoginController {
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value="/api/v1/login", method=RequestMethod.GET)
	public ModelAndView login() {
		return new ModelAndView("loginRequest");
	}
	
	
	@RequestMapping(value="/api/v1/loginSuccess", method=RequestMethod.GET)
	public String loginSuccess(@RequestParam("code") String code) {
		String userLoginResult = null;
		
		try {
		userLoginResult = loginService.login(code);
		
		} catch(Exception ex) {
			String error = "Below Error in Logging to the resource application \n" + ex.getMessage();
			return error;
		}
		return userLoginResult;
	}

}
