package com.myCommunity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Value("${file.dir}")
	private String fileDir;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//스프링부트 외부 리소스 허용
		/*
		 * addResourceHandler에 설정한 곳에 요청이 오면 
		 * addResourceLocations에 설정한 외부 경로로 자동으로 변환해줌
		 * "file:/// + 외부경로주소" '/' 3개 주의! 
		*/
		registry.addResourceHandler("/file/**")
			.addResourceLocations("file:///" + fileDir);
		}

}
