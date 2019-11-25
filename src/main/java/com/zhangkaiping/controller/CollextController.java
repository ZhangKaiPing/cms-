package com.zhangkaiping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.zhangkaiping.service.CollectService;

@Controller
public class CollextController {
	@Autowired
	private CollectService collectService;
}
