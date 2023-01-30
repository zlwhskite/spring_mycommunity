package com.myCommunity.filter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.myCommunity.attendance.AttendanceService;

import lombok.extern.slf4j.Slf4j;


public class LogFilter implements Filter {
	@Autowired
	AttendanceService attService;
	
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("log filter init");
	}
	

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String requestURI = httpRequest.getRequestURI();
		
		String uuid = UUID.randomUUID().toString();
		
		
		try {
			System.out.println("uuid = " + uuid + ", request = "  + requestURI);
			chain.doFilter(request, response);
		} catch(Exception e) {
			throw e;
		}
		
	}
	
	
	@Override
	public void destroy() {
		System.out.println("log filter destroy");
		System.out.println();
	}

}
