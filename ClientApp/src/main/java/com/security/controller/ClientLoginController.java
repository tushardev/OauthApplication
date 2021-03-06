package com.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
			ex.printStackTrace();
			String error = "Below Error in Logging to the resource application \n" + ex.getMessage();
			return error;
		}
		return userLoginResult;
	}

}
