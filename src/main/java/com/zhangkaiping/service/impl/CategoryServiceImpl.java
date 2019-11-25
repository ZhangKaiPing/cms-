package com.zhangkaiping.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhangkaiping.dao.CategoryMapper;
import com.zhangkaiping.domain.Category;
import com.zhangkaiping.service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService{
@Autowired
	private CategoryMapper categoryMapper;
	@Override
	public List<Category> selectsByChannelId(Integer channelId) {
		return categoryMapper.selectsByChannelId(channelId);
	}

}
