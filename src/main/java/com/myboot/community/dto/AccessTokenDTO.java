package com.myboot.community.dto;

public class AccessTokenDTO {
	private String ClientId;
	private String ClientSecret;
	private String RedirectUri;
	private String code;
	private String state;
	
	public String getClientId() {
		return ClientId;
	}
	public void setClientId(String clientId) {
		ClientId = clientId;
	}
	public String getClientSecret() {
		return ClientSecret;
	}
	public void setClientSecret(String clientSecret) {
		ClientSecret = clientSecret;
	}
	public String getRedirectUri() {
		return RedirectUri;
	}
	public void setRedirectUri(String redirectUri) {
		RedirectUri = redirectUri;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
