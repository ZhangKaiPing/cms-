package com.zhangkaiping.vo;

import com.zhangkaiping.domain.User;

public class UserVO extends User{
	
	
	private String repassword;//确认密码

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	
	
}
