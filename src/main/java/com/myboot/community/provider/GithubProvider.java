package com.myboot.community.provider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.Spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.myboot.community.dto.AccessTokenDTO;
import com.myboot.community.dto.GithubUser;

import okhttp3.*;

@Component
public class GithubProvider {
	@Value("${github.access_token_url}")
	private String access_token_url;
	
	@Value("${github.user_url}")
	private String user_url;
	
	public String getAccessToken(AccessTokenDTO accessTokenDTO) {
		MediaType mediaType = MediaType.get("application/json; charset=utf-8");

		OkHttpClient client = new OkHttpClient();
//		System.out.print(JSON.toJSONString(accessTokenDTO));
		RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));

		Request request = new Request.Builder()
				.url(access_token_url)
				.post(body)
				.build();
		try (Response response = client.newCall(request).execute()) {
		    String string = response.body().string();
//		    System.out.println(string);
//		    System.out.println("--------------------->");
//		    outFile(string);
		    String token = string.split("&")[0].split("=")[1];
		    System.out.println(token);
		    return token;
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	 /**
	  * 输出到文件
	  */
	 public static void outFile(String s) {
	  File file = new File("G:/JavaProject/11111111111.html");
	  try (FileOutputStream fop = new FileOutputStream(file)) {
	   // if file doesn't exists, then create it
	   if (!file.exists()) {
	    file.createNewFile();
	   }
	   // get the content in bytes
	   byte[] contentInBytes = s.getBytes();
	   fop.write(contentInBytes);
	   fop.flush();
	   fop.close();
	   System.out.println("Done");
	  } catch (IOException e) {
	   e.printStackTrace();
	  }
	 }
	
	public GithubUser getUser(String accessToken) {
		OkHttpClient client = new OkHttpClient();
		
		Request request = new Request.Builder()
			      .url(user_url+"="+accessToken)
			      .build();

			  try{
				  Response response = client.newCall(request).execute();
				  String string = response.body().string();
				  GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
				  return githubUser;
			  }catch (Exception e) {
					// TODO: handle exception
				}
			  return null;
	}
}
