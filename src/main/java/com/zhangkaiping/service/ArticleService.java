package com.zhangkaiping.service;

import com.github.pagehelper.PageInfo;
import com.zhangkaiping.domain.Article;
import com.zhangkaiping.domain.ArticleWithBLOBs;

public interface ArticleService {
	/**
	 * 
	 * @Title: selects 
	 * @Description: 文章列表
	 * @param article
	 * @return
	 * @return: List<Article>
	 */
	PageInfo<Article> selects(Article article,Integer page,Integer pageSize);

	boolean update(ArticleWithBLOBs article);

	ArticleWithBLOBs selectByPrimaryKey(Integer id);
	//发布文章
	boolean insertSelective(ArticleWithBLOBs record);
}
