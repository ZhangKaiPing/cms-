package com.zhangkaiping.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.zhangkaiping.domain.Links;

public interface LinksService {
	/**
	 * 
	 * @Title: 增加 
	 * @Description: TODO
	 * @param links
	 * @return
	 * @return: int
	 */
	boolean insert(Links links);
	
	/**
	 * 
	 * @Title: selects
	 * @Description: 、查询列表
	 * @return
	 * @return: List<Links>
	 */
	PageInfo<Links>selects(Integer page,Integer pageSize);
}
