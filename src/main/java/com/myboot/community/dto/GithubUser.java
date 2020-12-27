package com.myboot.community.dto;

public class GithubUser {
	private String name;
	private Long idLong;
	private String bio;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getIdLong() {
		return idLong;
	}
	public void setIdLong(Long idLong) {
		this.idLong = idLong;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	
	@Override
	public String toString() {
		return "GithubUser [name=" + name + ", idLong=" + idLong + ", bio=" + bio + "]";
	}
	
	
}
