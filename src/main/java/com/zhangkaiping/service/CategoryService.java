package com.zhangkaiping.service;

import java.util.List;

import com.zhangkaiping.domain.Category;

public interface CategoryService {
	List<Category> selectsByChannelId(Integer channelId);
}
