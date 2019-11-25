package com.zhangkaiping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.zhangkaiping.service.LinksService;

@Controller
public class LinksController {
	@Autowired
	private LinksService linksService;
	
	
	@GetMapping("selects")
	public String selects() {
		return null;
	
		
	}
}
