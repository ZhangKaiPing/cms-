package com.zhangkaiping.dao;

import java.util.List;

import com.zhangkaiping.domain.Channel;

public interface ChannelMapper {
	List<Channel> selects();
	
    int deleteByPrimaryKey(Integer id);

    int insert(Channel record);

    int insertSelective(Channel record);

    Channel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Channel record);

    int updateByPrimaryKey(Channel record);
}