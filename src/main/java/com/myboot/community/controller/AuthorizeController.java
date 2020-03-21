package com.myboot.community.controller;

import javax.swing.Spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	
	@Value("${github.client_id}")
	private String clientId;
	
	@Value("${github.client_secret}")
	private String clientSecret;
	
	@Value("${github.redirect_uri}")
	private String redirect_uri;
	
	@GetMapping("/callback")
	public String callback(@RequestParam(name="code") String code, 
							@RequestParam(name="state") String state) {
		
		AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
		accessTokenDTO.setClient_id(clientId);
		accessTokenDTO.setClient_secret(clientSecret);
		accessTokenDTO.setCode(code);
		accessTokenDTO.setState(state);
		accessTokenDTO.setRedirect_uri(redirect_uri);
		String accessToken =  githubProvider.getAccessToken(accessTokenDTO);
		GithubUser user = githubProvider.getUser(accessToken);
		System.out.println(user.getName());
		return "index";
	}
	
}
