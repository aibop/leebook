package com.myboot.community.controller;

import javax.swing.Spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myboot.community.dto.AccessTokenDTO;
import com.myboot.community.dto.GithubUser;
import com.myboot.community.provider.GithubProvider;

@Controller
public class AuthorizeController {
	@Autowired
	private GithubProvider githubProvider;
	
	@GetMapping("/callback")
	public String callback(@RequestParam(name="code") String code, 
							@RequestParam(name="state") String state) {
		
		AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
		String clientId = "dc8a38f8ed5290839a80";
		accessTokenDTO.setClientId(clientId);
		String clientSecret = "64a7e200dbad1994cde105ad4555763f98e50b1c";
		accessTokenDTO.setClientSecret(clientSecret );
		accessTokenDTO.setCode(code);
		accessTokenDTO.setState(state);
		accessTokenDTO.setRedirectUri("http://localhost:8080/callback");
		String accessToken =  githubProvider.getAccessToken(accessTokenDTO);
		
		GithubUser user = githubProvider.getUser(accessToken);
		System.out.println(user.getName());
		return "index";
	}
	
}
