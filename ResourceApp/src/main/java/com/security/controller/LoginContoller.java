package com.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginContoller {
	
	@RequestMapping(value="/api/v1/login-success", method=RequestMethod.GET)
	@ResponseBody
	public String login() {
		
		return "Login successful for Tushar";
	}
}
