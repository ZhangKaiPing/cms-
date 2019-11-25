package com.zhangkaiping.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhangkaiping.dao.ChannelMapper;
import com.zhangkaiping.domain.Channel;
import com.zhangkaiping.service.ChannelService;
@Service
public class ChannelServiceImpl implements ChannelService {
	
	@Autowired
	private ChannelMapper channelMapper;
	@Override
	public List<Channel> selects() {
		// TODO Auto-generated method stub
		return channelMapper.selects();
	}

}
