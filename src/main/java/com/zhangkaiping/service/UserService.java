package com.zhangkaiping.service;

import com.github.pagehelper.PageInfo;
import com.zhangkaiping.domain.User;
import com.zhangkaiping.vo.UserVO;

public interface UserService {
	PageInfo<User> selects(User user,Integer page,Integer pageSize);
	
	boolean update(User user);
	
	 boolean insertSelective(UserVO userVO);

	 //登录
	User login(User user);
}
