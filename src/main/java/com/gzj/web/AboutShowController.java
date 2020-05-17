package com.gzj.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName AboutShowController
 * @Description TODO
 * @Author 42
 * @Date 2020/3/30 下午 4:53
 * @Version 1.0
 */
@Controller
public class AboutShowController
{
	@GetMapping("/about")
	public String about() {
		return "about";
	}
}
