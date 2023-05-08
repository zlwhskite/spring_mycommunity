package com.myCommunity.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.slf4j.Slf4j;

public class LoginCheckInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse
			response, Object handler) throws Exception {
		String requestURI = request.getRequestURI();

		System.out.println("인증 체크 인터셉터 실행 = " + requestURI);
		HttpSession session = request.getSession(false);
		
		if(session == null || session.getAttribute("user") == null) {
			System.out.println("미인증 사용자 요청");
			
			response.sendRedirect("/login");
			return false;
		}
		//정상 호출, 다음 인터셉터나 컨트롤러가 호출된다.
		return true;
	}
	
}
