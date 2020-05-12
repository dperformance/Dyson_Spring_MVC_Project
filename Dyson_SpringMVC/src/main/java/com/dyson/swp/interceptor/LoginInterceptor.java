package com.dyson.swp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter implements SessionNames{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("===> [LoginInterCeptor] - preHandle 진입");
		HttpSession session = request.getSession();
		
		if (session.getAttribute(LOGIN) != null) { 	//기존에 LOGIN : loginUser라는 세션값이 존재하면
			session.removeAttribute(LOGIN);			//기존값을 제거해 준다.
		}
		
		return true;
	}
}