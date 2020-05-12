package com.dyson.swp.member.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dyson.swp.common.SecurityUtil;
import com.dyson.swp.member.service.RegisterServiceImpl;
import com.dyson.swp.member.vo.MemberVO;

@Controller
public class RegisterController {
	
	@Inject
	private RegisterServiceImpl registerService;
	
	@Inject
	private SecurityUtil securityUtil;
	
	/* 회원가입 화면 출력 */
	@GetMapping("/register")
	public String registerView() { return "/register/register"; }
	
	/* 회원가입시 입력받은 Email이 db에 존재하는지 체크 */
	@PostMapping("/getMemberEmail")
	@ResponseBody
	public int getMemberEmail(MemberVO mvo) {
		int result = registerService.getMemberEmail(mvo);
		return result;
	}
	
	/* 회원가입시 입력받은 nickName이 db에 존재하는지 체크 */
	@PostMapping("/getMemberNick")
	@ResponseBody
	public int getMemberNick(MemberVO mvo) {
		int result = registerService.getMemberNick(mvo);
		return result;
	}
	
	/* 회원가입 처리 */
	@PostMapping("/insertMember")
	@ResponseBody
	public void insertMember(MemberVO mvo, HttpServletRequest request) {
		mvo.setMem_cre_ip(request.getRemoteAddr()); //회원가입시 회원 ip 정보 값
		mvo.setMem_pw(securityUtil.encryptSHA256(mvo.getMem_pw())); //입력 받은 PW를 암호화 한다.
		registerService.insertMember(mvo); //DB에 insert 처리
	}

}
