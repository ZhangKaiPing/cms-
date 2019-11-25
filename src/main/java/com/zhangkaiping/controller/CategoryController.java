package com.zhangkaiping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhangkaiping.domain.Category;
import com.zhangkaiping.service.CategoryService;

@RequestMapping("category")
@Controller
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@ResponseBody
	@RequestMapping("selects")
	public List<Category> selects(Integer channelId){
		return categoryService.selectsByChannelId(channelId);
	}
}
