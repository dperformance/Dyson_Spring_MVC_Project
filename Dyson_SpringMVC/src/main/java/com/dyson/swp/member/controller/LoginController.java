package com.dyson.swp.member.controller;

import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dyson.swp.auth.SNSLogin;
import com.dyson.swp.auth.SnsValue;
import com.dyson.swp.common.SecurityUtil;
import com.dyson.swp.interceptor.SessionNames;
import com.dyson.swp.member.service.LoginService;
import com.dyson.swp.member.vo.MemberVO;

@Controller
public class LoginController {
	
	@Inject
	private LoginService loginService;
	
	@Autowired
	private SecurityUtil securityUtil;
	
	@Inject
	private GoogleConnectionFactory googleConnectionFactory;
	
	@Inject
	private OAuth2Parameters googleOAuth2Parameters;
	
	@Inject
	private SnsValue naverSns;

	@Inject
	private SnsValue googleSns;
	
	/* 로그인 화면 출력 */
	@RequestMapping(value = "/login", method = {RequestMethod.POST,RequestMethod.GET})
	public String loginForm(Model model) { 
		//로그인 화면에 Google url주소 내려주기
		OAuth2Operations oauthOperations = googleConnectionFactory.getOAuthOperations();
		String url = oauthOperations.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE,googleOAuth2Parameters);
		model.addAttribute("google_url", url);
		
		//로그인 화면에 Naver url주소 내려주기
		SNSLogin snsLogin = new SNSLogin(naverSns);
		model.addAttribute("naver_url", snsLogin.getNaverAuthURL());
		
		return "/login/login"; 
	}
	
	/* 로그인 처리 */
	@PostMapping("/userSignin")
	@ResponseBody 
	public String userSignin(HttpServletRequest request, HttpServletResponse response, MemberVO mvo, Model model, HttpSession httpSession) {
		mvo.setMem_pw(securityUtil.encryptSHA256(mvo.getMem_pw()));
		MemberVO member = loginService.getMember(mvo);
		if (member != null) { // 로그인 성공
			System.out.println("-------->>>>>>>>>>>>> login 사용자 데이터 체크 성공 !!!!");
			Date expire = new Date(System.currentTimeMillis() + SessionNames.EXPIRE * 1000); //default milisecond
			loginService.keppLogin(member.getMem_email(), httpSession.getId(), expire);
			httpSession.setAttribute(SessionNames.LOGIN, member);
			if (StringUtils.isNotEmpty(request.getParameter("useCookie"))) { //login.jsp의 Remember Me check여부 확인
				System.out.println("-------->>>>>>>>>>>>> useCookie  체크 성공 !!!!");
				Cookie loginCookie = new Cookie(SessionNames.LOGIN_COOKIE, httpSession.getId());
				loginCookie.setPath("/"); //dyson.com/ (도메인별로 key가 생긴다)
				loginCookie.setMaxAge(SessionNames.EXPIRE); //7일선언
				response.addCookie(loginCookie); // client에게 쿠키내용 전달
				System.out.println("-------->>>>>>>>>>>>> Cookie response줌   체크 성공 !!!!");
				return "1";
			}
		
			return "1";
		} 
		return "0";
	}
	
	/* 소셜 로그인(google, naver) 처리 */
	@RequestMapping(value = "/login/oauth2/code/{snsService}", method = {RequestMethod.POST, RequestMethod.GET})
	public String snsUserSign(@PathVariable String snsService, @RequestParam String code, 
			Model model, HttpSession httpSession, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SnsValue sns = null;
		if (StringUtils.equals("naver", snsService)) {
			sns = naverSns;
		} else {
			sns = googleSns;
		}
		System.out.println("sns data : " + sns);
		
		//1. code를 이용해서 access_token 받기
		//2. access_token을 이용해서 사용자 profile 정보 가져오기
		SNSLogin snsLogin = new SNSLogin(sns);
		
		System.out.println("snsLogin : " + snsLogin);

		MemberVO snsMemVO = snsLogin.getUserProfile(code); //1, 2번 동시
		System.out.println("profile : " + snsMemVO);
		
		//3. DB 해당 유저가 존재하는지 체크 (googleid, naverid 컬럼 추가)
		//암호화된 비밀번호로 조회
		String pwBuffer= snsMemVO.getMem_pw();
		String securityPW = securityUtil.encryptSHA256(snsMemVO.getMem_pw());
		snsMemVO.setMem_pw(securityPW);
		
		MemberVO member = loginService.getMember(snsMemVO);
		if (member == null) { //가입되지 않은 사용자이므로 소셜로부터 받아온 데이터를 가지고 회원가입 창으로 이동시킨다.
			snsMemVO.setMem_pw(pwBuffer);
			model.addAttribute("snsMemVO", snsMemVO);
			return "/register/oauth2Register";
		} else { //가입 된 소셜 사용자는 자동 로그인 처리 한다.
			Date expire = new Date(System.currentTimeMillis() + SessionNames.EXPIRE * 1000);
			loginService.keppLogin(member.getMem_email(), httpSession.getId(), expire);
			httpSession.setAttribute(SessionNames.LOGIN, member);
			Cookie loginCookie = new Cookie(SessionNames.LOGIN_COOKIE, httpSession.getId());
			loginCookie.setPath("/"); //dyson.com/ (도메인별로 key가 생긴다)
			loginCookie.setMaxAge(SessionNames.EXPIRE); //7일선언
			response.addCookie(loginCookie); // client에게 쿠키내용 전달
			return "redirect:/";
		}
	}
}

