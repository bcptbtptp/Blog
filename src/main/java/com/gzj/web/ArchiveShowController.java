package com.gzj.web;

import com.gzj.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName ArchiveShowController
 * @Description TODO
 * @Author 42
 * @Date 2020/3/30 下午 4:54
 * @Version 1.0
 */
@Controller
public class ArchiveShowController
{
	@Autowired
	private BlogService blogService;

	@GetMapping("/archives")
	public String archives(Model model) {
		model.addAttribute("archiveMap", blogService.archiveBlog());
		model.addAttribute("blogCount", blogService.countBlog());
		return "archives";
	}
}
