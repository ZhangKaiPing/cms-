package com.zhangkaiping.service.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zhangkaiping.domain.Links;
import com.zhangkaiping.service.LinksService;

public class LinksServiceImplTest extends JunitParent{
	@Autowired
	private LinksService linksService;
	
	
	@Test
	public void testInsert() {
		Links links=new Links();
		links.setUrl("http://61.134.39.117:90/license!getExpireDateOfDays.action");
		links.setText("监控");
		
		linksService.insert(links);
		
		
	}

	@Test
	public void testSelects() {
	}

}
