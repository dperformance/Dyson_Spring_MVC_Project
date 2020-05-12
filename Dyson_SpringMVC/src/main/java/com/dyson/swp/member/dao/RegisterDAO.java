package com.dyson.swp.member.dao;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.dyson.swp.member.vo.MemberVO;

@Repository
public class RegisterDAO {

	@Inject
	private SqlSessionTemplate mybatis;
	
	public int getMemberEmail(MemberVO mvo) {
		return mybatis.selectOne("RegisterMapper.getMemberEmail", mvo);
	}

	public int getMemberNick(MemberVO mvo) {
		return mybatis.selectOne("RegisterMapper.getMemberNick", mvo);
	}

	public void insertMember(MemberVO mvo) {
		mybatis.update("RegisterMapper.insertMember", mvo);
	}
}
