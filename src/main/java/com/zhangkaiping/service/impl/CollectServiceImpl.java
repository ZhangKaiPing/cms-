package com.zhangkaiping.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhangkaiping.dao.CollectMapper;
import com.zhangkaiping.domain.Collect;
import com.zhangkaiping.domain.User;
import com.zhangkaiping.service.CollectService;
import com.zhangkaiping.util.CMSAjaxException;
import com.zkp.utils.StringUtil;


@Service
public class CollectServiceImpl implements CollectService {

		@Autowired
		private CollectMapper collectMapper;
		
	@Override
	public boolean insert(Collect collect) {
		if(!StringUtil.isHttpUrl(collect.getUrl()))
			throw new CMSAjaxException(1, "不是有效的url");
		
		collectMapper.insert(collect);
		return true;
	}

	@Override
	public PageInfo<Collect> selects(Integer page,Integer pageSize,User user) {
		PageHelper.startPage(page, pageSize);
		List<Collect> list = collectMapper.selects(user);
		
		return new PageInfo<Collect>(list);
	}

	@Override
	public boolean deleteById(Integer id) {
		collectMapper.deleteById(id);
		
		return false;
	}

	@Override
	public int selectByText(String text, User user) {
		return collectMapper.selectByText(text, user);
	}

}
