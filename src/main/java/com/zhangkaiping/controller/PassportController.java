package com.zhangkaiping.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zhangkaiping.domain.User;
import com.zhangkaiping.service.UserService;
import com.zhangkaiping.util.CMSException;
import com.zhangkaiping.vo.UserVO;
@RequestMapping("passport")
@Controller
public class PassportController {
	@Autowired
	private UserService userService;
	
	
	@GetMapping("logout")
	public String logout(HttpServletRequest requset) {
		 HttpSession session = requset.getSession(false);
		 if(null!=session) {
			 session.invalidate();
		 }
		return "redirect:/passport/login";
	}
	
	
	
	@GetMapping("login")
	public String login() {
		return "passport/login";
	}
	
	//去登陆
	@PostMapping("login")
	public String login(User user,Model model,HttpSession session) {
		try {
			User u = userService.login(user);
			
			
			if(u.getRole().equals("1")) {//1.管理员 0普通用户
				session.setAttribute("admin", u);
				return "redirect:/admin/index";//管理员进入
			}else {
				session.setAttribute("user", u);
				return "redirect:/my/";//普通注册个人中心
			}
		} catch (CMSException e) {
			e.printStackTrace();
			model.addAttribute("message",e.getMessage());
			//封装的model域 用于提示用户
		}
			catch (Exception e) {
			model.addAttribute("message","请联系管理员");
		}
		
		
		
		return "passport/login";
	}
	
	
	@GetMapping("reg")
	public String reg() {
		return "passport/reg";
	}
	
	@PostMapping("reg")
	public String reg(Model model,UserVO userVO,RedirectAttributes reaAttributes) {
		
		try {
			boolean b=userService.insertSelective(userVO);
			if(b) {
				
				reaAttributes.addFlashAttribute("user",userVO.getUsername());
				reaAttributes.addFlashAttribute("message","恭喜注册成功");
				;
				return "redirect:/passport/login";//注册成功 重定向到登录页面
			}
		} catch (CMSException e) {
			e.printStackTrace();
			model.addAttribute("message",e.getMessage());
			//封装的model域 用于提示用户
		}
			catch (Exception e) {
			model.addAttribute("message","请联系管理员");
		}
		
		
		
		
		return "passport/reg";//注册失败
	}
	
}
