package com.gzj.web;

import com.gzj.pojo.Type;
import com.gzj.service.BlogService;
import com.gzj.service.TypeService;
import com.gzj.vo.BlogQuery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @ClassName TypeShowController
 * @Description TODO
 * @Author 42
 * @Date 2020/3/30 下午 4:57
 * @Version 1.0
 */
@Controller
public class TypeShowController
{
	@Autowired
	private TypeService typeService;

	@Autowired
	private BlogService blogService;

	@GetMapping("/types/{id}")
	public String types(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
						@PathVariable Long id, Model model) {
		List<Type> types = typeService.listTypeTop(10000);
		if (id == -1) {
			id = types.get(0).getId();
		}
		BlogQuery blogQuery = new BlogQuery();
		blogQuery.setTypeId(id);
		model.addAttribute("types", types);
		model.addAttribute("page", blogService.listBlog(pageable, blogQuery));
		model.addAttribute("activeTypeId", id);
		return "types";
	}
}
