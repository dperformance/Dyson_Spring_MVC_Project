package com.dyson.swp.member.service;

import java.util.Date;

import com.dyson.swp.member.vo.MemberVO;

public interface LoginService {
	
	MemberVO getMember(MemberVO mvo);	//로그인시 회원 데이터 조회

	MemberVO checkLoginBefore(String value); // session key 정보 조회

	void keppLogin(String mem_email, String id, Date expire); // 자동 로그인 or 비 로그인 session 처리
}
