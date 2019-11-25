package com.zhangkaiping.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.zhangkaiping.domain.Collect;
import com.zhangkaiping.domain.User;

public interface CollectService {
	/**
	 * 
	 * @Title: 增加 
	 * @Description: TODO
	 * @param links
	 * @return
	 * @return: int
	 */
	boolean insert(Collect collect);
	
	/**
	 * 
	 * @Title: selects
	 * @Description: 、查询列表
	 * @return
	 * @return: List<Links>
	 */
	PageInfo<Collect>selects(Integer page,Integer pageSize,User user);
	
	
	/**
	 * 删除
	 */
	boolean deleteById(Integer id);

	int selectByText(String title, User user);
}
