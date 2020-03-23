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

import com.security.model.JWTToken;

@Service
public class LoginService {
	
	public String login(String code) throws Exception {
		
		ResponseEntity<JWTToken> response = null;
		RestTemplate restTemplate = new RestTemplate();
		String userDetails = null;
			
		/* Get Access Token using authorization code */
		/*String credentials = "loginclient:secret";*/
		String credentials = "sb-na-016638a2-874b-4217-9019-4dd48b755af2!t40200:UnYQJ7rXrV1+pWoGxzDAL2FDTig=";
		String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("Authorization", "Basic " + encodedCredentials);
		
		HttpEntity<String> request = new HttpEntity<String>(headers);
		
		/*String access_token_url = "http://localhost:8080/resourceapp/oauth/token";*/
		
		String access_token_url = "https://p2002038304trial.authentication.eu10.hana.ondemand.com/oauth/token";
		access_token_url += "?code=" + code;
		access_token_url += "&grant_type=authorization_code";
		access_token_url += "&redirect_uri=http://localhost:8090/clientapp/api/v1/loginSuccess";
		
		response = restTemplate.exchange(access_token_url, HttpMethod.GET, request, JWTToken.class);
		System.out.println("Access Token Response ---------" + response.getBody());
		
		String token = response.getBody().getAccess_token();
		
		/* Get the required resource using access token */
		/*ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(response.getBody().get);
		String token = node.path("access_token").asText();*/
		
		String url = "http://localhost:8080/resourceapp/api/v1/login-success";
		
		HttpHeaders headers1 = new HttpHeaders();
		headers1.add("Authorization", "Bearer " + token);
		HttpEntity<String> entity = new HttpEntity<>(headers1);
		
		ResponseEntity<String> userLoginResponse = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		userDetails = userLoginResponse.getBody();
		
		return userDetails;
	}
}
