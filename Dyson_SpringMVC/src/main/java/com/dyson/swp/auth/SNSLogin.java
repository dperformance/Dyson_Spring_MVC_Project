package com.dyson.swp.auth;

import com.dyson.swp.member.vo.MemberVO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

public class SNSLogin {
	//token을 가져온다던지 프로필을 받는다던지 모두 OAuth20Service가 해준다.
	private OAuth20Service oauthService;
	private SnsValue sns;
	
	public SNSLogin(SnsValue sns) {
		this.oauthService = new ServiceBuilder(sns.getClientId())
								.apiSecret(sns.getClientSecret())
								.callback(sns.getRedirectUrl())
								.scope("profile")
								.build(sns.getApi20Instance());
		this.sns = sns;
	}

	public Object getNaverAuthURL() {
		return this.oauthService.getAuthorizationUrl();
	}

	public MemberVO getUserProfile(String code) throws Exception {
		OAuth2AccessToken accessToken = oauthService.getAccessToken(code);
		
		OAuthRequest request = new OAuthRequest(Verb.GET, this.sns.getProfileUrl());
		
		oauthService.signRequest(accessToken, request);
		
		Response response = oauthService.execute(request);
		
		return parseJson(response.getBody());
	}

	private MemberVO parseJson(String body) throws Exception {
		MemberVO mvo = new MemberVO();
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.readTree(body);
		if (this.sns.isGoogle()) {
			if (sns.isGoogle()) {
				//구글에서 제공해준 고유 id값(PWD)를 저장한다.
				mvo.setMem_pw(rootNode.get("sub").asText());;
				//이름
				mvo.setMem_name(rootNode.get("name").asText());
				
				//email
				mvo.setMem_email(rootNode.get("email").asText());
				
				//picture
				mvo.setMem_images(rootNode.get("picture").asText());
				
				//Nationality : locale
				//mvo.setMem_nationality(rootNode.get("locale").asText());
			}
			
		} else if (this.sns.isNaver()) {
			JsonNode resNode = rootNode.get("response");
			mvo.setMem_pw(resNode.get("id").asText());
			mvo.setMem_email(resNode.get("email").asText());
			mvo.setMem_name(resNode.get("nickname").asText());
		}
		return mvo;
	}
}