package com.dyson.swp.member.service;

import com.dyson.swp.member.vo.MemberVO;

public interface RegisterService {

	public int getMemberEmail(MemberVO mvo); // 회원가입 중 ajax로 중복 이메일 확인

	public int getMemberNick(MemberVO mvo); // 회원가입 중 ajax로 중복 닉네임 확인

	public void insertMember(MemberVO mvo); // 회원가입을 처리하는 메소드
}
