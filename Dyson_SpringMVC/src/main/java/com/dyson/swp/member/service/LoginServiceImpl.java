package com.dyson.swp.member.service;

import java.util.Date;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.dyson.swp.member.dao.LoginDAO;
import com.dyson.swp.member.vo.MemberVO;

@Service
public class LoginServiceImpl implements LoginService {

	@Inject
	private LoginDAO loginDAO;
	
	@Override // 회원 정보 존재 확인
	public MemberVO getMember(MemberVO mvo) { return loginDAO.getMember(mvo); }

	@Override // session key 정보 조회
	public MemberVO checkLoginBefore(String loginCookie) { return loginDAO.getCheckLoginBefore(loginCookie); }

	@Override // 자동 로그인 or 비 로그인 session 처리
	public void keppLogin(String mem_email, String sessionId, Date expire) { loginDAO.keepLogin(mem_email, sessionId, expire); }
}
