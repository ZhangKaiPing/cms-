package com.zhangkaiping.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhangkaiping.dao.LinksMapper;
import com.zhangkaiping.domain.Links;
import com.zhangkaiping.service.LinksService;
import com.zhangkaiping.util.CMSAjaxException;
import com.zhangkaiping.util.PageUtil;
import com.zkp.utils.StreamUtil;
import com.zkp.utils.StringUtil;


@Service
public class LinksServiceImpl implements LinksService {
	@Autowired
	private LinksMapper linksMapper;


	@Override
	public boolean insert(Links links) {
		
			//调用工具栏判断是否是有效的url
			if(!StringUtil.isHttpUrl(links.getUrl()))
					throw new CMSAjaxException(1,"不是有效的url");
			linksMapper.insert(links);
			
			return true;
		
	}

	@Override
	public PageInfo<Links> selects(Integer page,Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		List<Links> list=linksMapper.selects();
		return new PageInfo<Links>(list);
	}

	
}
