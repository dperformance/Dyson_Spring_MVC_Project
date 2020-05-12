package com.dyson.swp.interceptor;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.dyson.swp.member.service.LoginService;
import com.dyson.swp.member.vo.MemberVO;

public class AuthInterceptor extends HandlerInterceptorAdapter implements SessionNames{

	@Inject
	LoginService loginService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//session은 server에 저장하는 값 (브라우저마다)
			HttpSession session = request.getSession();
			if (session.getAttribute(LOGIN) == null) { //Login이 안되있을때
				Cookie loginCookie = WebUtils.getCookie(request, SessionNames.LOGIN_COOKIE);
				
				if (loginCookie != null) {
					MemberVO loginUser = loginService.checkLoginBefore(loginCookie.getValue());
					if (loginUser != null) { //user가 존재 하면
						session.setAttribute(LOGIN, loginUser);
						return true;
					}
				}
				
				saveAttemptedLocation(request, session);
				
			}
		
		return true;
	}

	private void saveAttemptedLocation(HttpServletRequest request, HttpSession session) throws IOException {
		System.out.println("===> [AuthInterCeptor] - postHandle 진입 \n");
		String uri = request.getRequestURI(); 
		String query = request.getQueryString();
		if (StringUtils.isNotEmpty(query)) {
			uri += "?" + query;
			System.out.println("uri : " + uri);
			session.setAttribute(ATTEMPTED, uri);
		}
	}
}