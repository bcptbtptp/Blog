package com.gzj.web.admin;

import com.gzj.pojo.Blog;
import com.gzj.pojo.User;
import com.gzj.service.BlogService;
import com.gzj.service.TagService;
import com.gzj.service.TypeService;
import com.gzj.vo.BlogQuery;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @ClassName BlogController
 * @Description TODO
 * @Author 42
 * @Date 2020/3/20 下午 8:43
 * @Version 1.0
 */
@Controller
@RequestMapping("/admin")
public class BlogController
{
	private final String INPUT = "admin/blogs-input";
	private final String LIST = "admin/blogs";
	private final String REDIRECT_LIST = "redirect:/admin/blogs";

	@Autowired
	private BlogService blogService;

	@Autowired
	private TypeService typeService;

	@Autowired
	private TagService tagService;

	@GetMapping("/blogs")
	public String blogs(@PageableDefault(size = 2,
			sort = {"updateTime"}, direction = Sort.Direction.DESC)
									Pageable pageable, BlogQuery blog, Model model){
		model.addAttribute("type",typeService.listType());
		model.addAttribute("page", blogService.listBlog(pageable, blog));
		return LIST;
	}

	@PostMapping("/blogs/search")
	public String search(@PageableDefault(size = 2,
			sort = {"updateTime"}, direction = Sort.Direction.DESC)
								Pageable pageable, BlogQuery blog, Model model){
		model.addAttribute("page", blogService.listBlog(pageable, blog));
		return "admin/blogs :: blogList";
	}

	@GetMapping("blogs/input")
	public String input(Model model) {
		saveTypeAndTag(model);
		model.addAttribute("blog", new Blog());
		return INPUT;
	}

	private void saveTypeAndTag(Model model) {
		model.addAttribute("types", typeService.listType());
		model.addAttribute("tags", tagService.listTag());
	}

	@GetMapping("blogs/{id}/input")
	public String editInput(@PathVariable Long id,  Model model) {
		saveTypeAndTag(model);
		Blog blog = blogService.getBlog(id);
		blog.init();
		model.addAttribute("blog", blog);
		return INPUT;
	}

	@PostMapping("/blogs")
	public String post(Blog blog, RedirectAttributes attributes, HttpSession session) {
		blog.setUser((User) session.getAttribute("user"));
		blog.setType(typeService.getType(blog.getType().getId()));
		blog.setTags(tagService.listTag(blog.getTagIds()));
		Blog b;
		if (blog.getId() == null) {
			b =  blogService.saveBlog(blog);
		} else {
			b = blogService.updateBlog(blog.getId(), blog);
		}

		if (b == null ) {
			attributes.addFlashAttribute("message", "操作失败");
		} else {
			attributes.addFlashAttribute("message", "操作成功");
		}
		return REDIRECT_LIST;
	}

	@GetMapping("/blogs/{id}/delete")
	public String delete(@PathVariable Long id, RedirectAttributes attributes) {
		blogService.deleteBlog(id);
		attributes.addFlashAttribute("message", "删除成功");
		return REDIRECT_LIST;
	}
}
