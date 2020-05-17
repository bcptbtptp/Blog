package com.gzj.handler;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName ControllerExceptionHandler
 * @Description TODO
 * @Author 42
 * @Date 2020/3/19 上午 11:29
 * @Version 1.0
 */
@ControllerAdvice
public class ControllerExceptionHandler
{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@ExceptionHandler(Exception.class)
	public ModelAndView exceptionHandler(HttpServletRequest request, Exception e) throws Exception{
		logger.error("Request url:{}, Exception:{}",request.getRequestURL(), e);
		if (AnnotationUtils.findAnnotation(e.getClass(),
				ResponseStatus.class) != null) {
			throw e;
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("url", request.getRequestURL());
		mv.addObject("exception",e);
		mv.setViewName("error/error");
		return mv;
	}
}
