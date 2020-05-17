package com.gzj.config;

import com.gzj.interceptor.LoginInterceptor;
import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @ClassName WebConfig
 * @Description TODO
 * @Author 42
 * @Date 2020/3/20 下午 8:54
 * @Version 1.0
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport
{
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginInterceptor()).
				addPathPatterns("/admin/**").
				excludePathPatterns("/admin").
				excludePathPatterns("/admin/login").
				excludePathPatterns("/static/**");
	}

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**")
				.addResourceLocations("classpath:/static/")
				.addResourceLocations("classpath:/templates/");
		super.addResourceHandlers(registry);
	}

	@Override
	protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(new PageableHandlerMethodArgumentResolver());	}
}
