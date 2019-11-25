package com.zhangkaiping.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhangkaiping.dao.ArticleMapper;
import com.zhangkaiping.domain.Article;
import com.zhangkaiping.domain.ArticleWithBLOBs;
import com.zhangkaiping.service.ArticleService;
@Service
public class ArticleServiceImpl implements ArticleService{
	@Autowired
	private ArticleMapper articleMapper;
	@Override
	public PageInfo<Article> selects(Article article, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		List<Article> articles = articleMapper.selects(article);
		PageInfo<Article> pageInfo = new PageInfo<Article>(articles);
		return pageInfo;
	}
	@Override
	public boolean update(ArticleWithBLOBs article) {
		try {
		
			return articleMapper.updateByPrimaryKeySelective(article)>0;
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new RuntimeException("修改失败");
		}
	}
	@Override
	public ArticleWithBLOBs selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return articleMapper.selectByPrimaryKey(id);
	}
	@Override
	public boolean insertSelective(ArticleWithBLOBs record) {
		
		try {
			return articleMapper.insertSelective(record)>0;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("发布失败");
		}
		
		
		
	}

}
