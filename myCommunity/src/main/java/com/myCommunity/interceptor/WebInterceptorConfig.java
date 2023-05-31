package com.myCommunity.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebInterceptorConfig implements WebMvcConfigurer {


	@Override
	public void addInterceptors(InterceptorRegistry registry) {
				//인터셉터 등록
		registry.addInterceptor(new LoginCheckInterceptor())
				//인터셉터의 호출 순서를 지정, 낮을 수록 먼저 호출됨
				.order(1)
				//인터셉터를 적용할 URL패턴을 지정
				.addPathPatterns("/**") 
				//인터셉터에서 제외할 패턴을 지정
				.excludePathPatterns("/", "/boards", "/boards/create", "/boards/**/{path:[0-9]+}", "/boards/srt", "/boards/hot/**", 
						"/boards/travel/**", "/boards/hobby/**", "/boards/computer/**", "/boards/stock/**", "/boards/workout/**", "/boards/free/**",
						"/boards/search/**", "/boards/name/**", "/users", "/users/create", "/users/checks", "/users/findUser", "/users/findPwd",
						"/comments/**", "/login", "/logout", "/h2-console", "/file/**", "/h2-console/**", "/css/*");
	}
}
