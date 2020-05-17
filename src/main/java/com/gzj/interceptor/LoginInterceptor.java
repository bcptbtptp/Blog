package com.gzj.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @ClassName LoginInterceptor
 * @Description TODO
 * @Author 42
 * @Date 2020/3/20 下午 8:50
 * @Version 1.0
 */
public class LoginInterceptor extends HandlerInterceptorAdapter
{
	@Override
	public boolean preHandle(HttpServletRequest request,
							 HttpServletResponse response,
							 Object handler) throws Exception {
		if (request.getSession().getAttribute("user") == null) {
			response.sendRedirect("admin");
			return false;
		}
		return true;
	}
}
