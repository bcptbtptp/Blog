package com.gzj.web.admin;

import com.gzj.pojo.Type;
import com.gzj.service.TypeService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @ClassName TypeController
 * @Description TODO
 * @Author 42
 * @Date 2020/3/24 上午 10:11
 * @Version 1.0
 */
@Controller
@RequestMapping("/admin")
public class TypeController
{
	@Autowired
	private TypeService typeService;

	@GetMapping("/types")
	public String listTypes(@PageableDefault(size = 8, sort = {"id"}, direction = Sort.Direction.DESC)
										Pageable pageable, Model model) {

		model.addAttribute("page", typeService.listType(pageable));
		return "admin/types";
	}

	@GetMapping("/types/input")
	public String input(Model model) {
		model.addAttribute("type", new Type());
		return "admin/types-input";
	}

	@PostMapping("/types")
	public String post(@Valid Type type, BindingResult result, RedirectAttributes attributes) {
		Type oldtype = typeService.getTypeByName(type.getName());
		if (oldtype != null) {
			result.rejectValue("name", "nameError", "该分类名称已存在");
		}
		if (result.hasErrors()) {
		    return "admin/types-input";
		}
		Type t = typeService.saveType(type);
		if (t == null) {
			attributes.addFlashAttribute("message","新增失败");
		} else {
			attributes.addFlashAttribute("message","新增成功");
		}
		return "redirect:/admin/types";
	}

	@GetMapping("/types/{id}/input")
	public String editType(@PathVariable Long id, Model model) {
		model.addAttribute("type", typeService.getType(id));
		return "admin/types-input";
	}

	@PostMapping("/types/{id}")
	public String editPost(@Valid Type type, BindingResult result,
						   @PathVariable Long id, RedirectAttributes attributes) {
		Type oldtype = typeService.getTypeByName(type.getName());
		if (oldtype != null) {
			result.rejectValue("name", "nameError", "该分类名称已存在");
		}
		if (result.hasErrors()) {
			return "admin/types-input";
		}
		Type t = typeService.updateType(id, type);
		if (t == null) {
			attributes.addFlashAttribute("message","更新失败");
		} else {
			attributes.addFlashAttribute("message","更新成功");
		}
		return "redirect:/admin/types";
	}

	@GetMapping("/types/{id}/delete")
	public String delete(@PathVariable Long id, RedirectAttributes attributes) {
		typeService.deleteType(id);
		attributes.addFlashAttribute("message","删除成功");
		return "redirect:/admin/types";
	}
}
