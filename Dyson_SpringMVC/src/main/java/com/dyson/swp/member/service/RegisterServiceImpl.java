package com.dyson.swp.member.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.dyson.swp.member.dao.RegisterDAO;
import com.dyson.swp.member.vo.MemberVO;

@Service
public class RegisterServiceImpl implements RegisterService {

	@Inject
	RegisterDAO registerDAO;
	
	@Override // email 중복 확인
	public int getMemberEmail(MemberVO mvo) { return registerDAO.getMemberEmail(mvo); }
	
	@Override // nickname 중복 확인
	public int getMemberNick(MemberVO mvo) { return registerDAO.getMemberNick(mvo);	}
	
	@Override // 회원가입 처리
	public void insertMember(MemberVO mvo) { registerDAO.insertMember(mvo);	}
}
