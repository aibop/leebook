package com.myboot.community.controller;

import javax.servlet.http.HttpServletRequest;
import javax.swing.Spring;

import com.myboot.community.mapper.UserMapper;
import com.myboot.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myboot.community.dto.AccessTokenDTO;
import com.myboot.community.dto.GithubUser;
import com.myboot.community.provider.GithubProvider;

import java.util.UUID;

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

	@Autowired
	private UserMapper userMapper;

	@GetMapping("/callback")
	public String callback(@RequestParam(name="code") String code, 
							@RequestParam(name="state") String state,
							HttpServletRequest request) {
		
		AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
		accessTokenDTO.setClient_id(clientId);
		accessTokenDTO.setClient_secret(clientSecret);
		accessTokenDTO.setCode(code);
		accessTokenDTO.setState(state);
		accessTokenDTO.setRedirect_uri(redirect_uri);
		String accessToken =  githubProvider.getAccessToken(accessTokenDTO);
		GithubUser githubUser = githubProvider.getUser(accessToken);
		
		if (githubUser != null) {
			User user = new User();
			user.setName(githubUser.getName());
			user.setToken(UUID.randomUUID().toString());
			user.setAccountNo(String.valueOf(githubUser.getIdLong()));
			userMapper.insert(user);

			// 写cookie 和session
			request.getSession().setAttribute("user", githubUser);
			return "redirect:/";
		}else {
			return "redirect:/";
		}
	}
	
}
