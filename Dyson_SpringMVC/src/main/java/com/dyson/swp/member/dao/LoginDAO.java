package com.dyson.swp.member.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dyson.swp.member.vo.MemberVO;

@Repository
public class LoginDAO {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	public MemberVO getMember(MemberVO mvo) {
		return mybatis.selectOne("LoginMapper.getMember", mvo);
	}

	public MemberVO getCheckLoginBefore(String loginCookie) {
		return mybatis.selectOne("LoginMapper.getCheckLoginBefore", loginCookie);
	}

	public void keepLogin(String mem_email, String sessionId, Date expire) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("mem_email", mem_email);
		paramMap.put("mem_sessionkey", sessionId);
		paramMap.put("mem_sessionlimit", expire);
		mybatis.update("LoginMapper.updateKeepLogin", paramMap);
	}
}
