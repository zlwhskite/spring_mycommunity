package com.myCommunity.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.PatternMatchUtils;

import com.myCommunity.attendance.AttendanceService;

public class LoginCheckFilter implements Filter{
	
	private static final String[] whitelist = {"/", "/boards", "/users", "/users/*", "/login", "/logout", 
			"/boards/*", "/comments/create", "/h2-console", "/h2-console/*", "/css/*"};
		
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String requestURI = httpRequest.getRequestURI();
		
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		try {
			System.out.println("인증 체크 필터 시작 = " + requestURI);
			
			if(isLoginCheckPath(requestURI)) {
				System.out.println("인증 체크 로직 실행 = " + requestURI);
				HttpSession session = httpRequest.getSession(false);
				
				if(session == null || session.getAttribute("user") == null) {
					System.out.println("미인증 사용자 요청 = " + requestURI);
					httpResponse.sendRedirect("/login");
					
					return;
				}
			}
			
			chain.doFilter(request, response);
			
		} catch(Exception e) {
			throw e; 
		} finally {
			System.out.println("인증 체크 필터 종료 = " + requestURI);
		}
		
	}
	
	private boolean isLoginCheckPath(String requestURI) {
		return !PatternMatchUtils.simpleMatch(whitelist, requestURI);
	}
	
}
