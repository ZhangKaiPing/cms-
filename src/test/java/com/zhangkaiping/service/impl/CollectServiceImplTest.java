package com.zhangkaiping.service.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zhangkaiping.domain.Collect;
import com.zhangkaiping.domain.User;
import com.zhangkaiping.service.CollectService;

public class CollectServiceImplTest extends JunitParent{
	@Autowired
	private CollectService collectService;
	
	
	@Test
	public void testInsert() {
		
		Collect c = new Collect();
		User user = new User();
		user.setId(160);
		c.setUrl("http://172.16.10.111/exam/login.do");
		//错误的
		//c.setUrl("aaa");
		c.setUser(user);
		collectService.insert(c);
	}

	@Test
	public void testSelects() {
		
	}

	@Test
	public void testDeleteById() {
	}

}
