package com.zhangkaiping.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhangkaiping.domain.Collect;
import com.zhangkaiping.domain.Links;
import com.zhangkaiping.domain.User;
/**
 * 
 * @ClassName: CollectMapper 
 * @Description: 收藏
 * @author: lenovo
 * @date: 
 */
public interface CollectMapper {
	/**
	 * 
	 * @Title: 增加 
	 * @Description: TODO
	 * @param links
	 * @return
	 * @return: int
	 */
	int insert(Collect collect);
	
	/**
	 * 
	 * @Title: selects
	 * @Description: 、查询列表
	 * @return
	 * @return: List<Links>
	 */
	List<Collect>selects(User user);
	
	/**
     * 
     * @Title: selectByText 
     * @Description: 根据登录人和文章标题查询是否收藏
     * @param text
     * @return
     * @return: int
     */
    int selectByText(@Param("text")String text ,@Param("user")User user);
	/**
	 * 删除
	 */
	int deleteById(Integer id);
}
