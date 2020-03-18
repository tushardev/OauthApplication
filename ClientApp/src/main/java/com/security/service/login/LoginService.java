package com.security.service.login;

import java.util.Arrays;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class LoginService {
	
	public String login(String code) throws Exception {
		
		ResponseEntity<String> response = null;
		RestTemplate restTemplate = new RestTemplate();
		String userDetails = null;
			
		/* Get Access Token using authorization code */
		String credentials = "loginclient:secret";
		String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("Authorization", "Basic " + encodedCredentials);
		
		HttpEntity<String> request = new HttpEntity<String>(headers);
		
		String access_token_url = "http://localhost:8080/resourceapp/oauth/token";
		access_token_url += "?code=" + code;
		access_token_url += "&grant_type=authorization_code";
		access_token_url += "&redirect_uri=http://localhost:8090/clientapp/api/v1/loginSuccess";
		
		response = restTemplate.exchange(access_token_url, HttpMethod.POST, request, String.class);
		System.out.println("Access Token Response ---------" + response.getBody());
		
		
		/* Get the required resource using access token */
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(response.getBody());
		String token = node.path("access_token").asText();
		
		String url = "http://localhost:8080/resourceapp/api/v1/login-success";
		
		HttpHeaders headers1 = new HttpHeaders();
		headers1.add("Authorization", "Bearer " + token);
		HttpEntity<String> entity = new HttpEntity<>(headers1);
		
		ResponseEntity<String> userLoginResponse = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		userDetails = userLoginResponse.getBody();
		
		return userDetails;
	}
}
