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
		registry.addResourceHandler("/file/**")
			.addResourceLocations("file:///" + fileDir);
		}

}
