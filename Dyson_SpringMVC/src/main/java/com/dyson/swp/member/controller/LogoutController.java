package com.dyson.swp.member.controller;

import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import com.dyson.swp.interceptor.SessionNames;
import com.dyson.swp.member.service.LoginService;
import com.dyson.swp.member.vo.MemberVO;

@RequestMapping("/logout")
@Controller
public class LogoutController {
	
	@Inject
	LoginService loginService;

	/* 로그아웃 처리 */
	@GetMapping
	public String logout(HttpSession httpSession, HttpServletRequest request, HttpServletResponse response) throws Exception{
		MemberVO user = (MemberVO)httpSession.getAttribute(SessionNames.LOGIN);
		httpSession.removeAttribute(SessionNames.LOGIN);
		httpSession.invalidate();
		
		Cookie loginCookie = WebUtils.getCookie(request, SessionNames.LOGIN_COOKIE);
		if (loginCookie != null) {
			loginCookie.setPath("/");
			loginCookie.setMaxAge(0);
			response.addCookie(loginCookie);
			loginService.keppLogin(user.getMem_email(), httpSession.getId(), new Date());
		}
		return "redirect:/"; // 메인 페이지로 이동
	}
}
