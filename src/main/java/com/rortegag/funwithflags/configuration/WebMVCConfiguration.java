package com.rortegag.funwithflags.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMVCConfiguration implements WebMvcConfigurer
{	
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) 
	{
        registry.addResourceHandler("/img/**", "/css/**", "/js/**").addResourceLocations("classpath:/static/img/", "classpath:/static/css/", "classpath:/static/js/");
    }
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() 
	{
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
}
