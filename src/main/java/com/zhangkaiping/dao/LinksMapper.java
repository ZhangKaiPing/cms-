package com.zhangkaiping.dao;

import java.util.List;

import com.zhangkaiping.domain.Links;

public interface LinksMapper {
	/**
	 * 
	 * @Title: 增加 
	 * @Description: TODO
	 * @param links
	 * @return
	 * @return: int
	 */
	int insert(Links links);
	
	/**
	 * 
	 * @Title: selects
	 * @Description: 、查询列表
	 * @return
	 * @return: List<Links>
	 */
	List<Links>selects();
}
