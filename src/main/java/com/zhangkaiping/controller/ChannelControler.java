package com.zhangkaiping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhangkaiping.domain.Channel;
import com.zhangkaiping.service.ChannelService;

@RequestMapping("channel")
@Controller
public class ChannelControler {
	@Autowired
	private ChannelService channelService;
	
	@RequestMapping("channels")
	@ResponseBody
	public List<Channel> channels(){
		return channelService.selects();
	}
}
