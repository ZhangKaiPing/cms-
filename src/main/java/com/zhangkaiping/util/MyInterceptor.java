package com.zhangkaiping.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/**
 * 
 * @ClassName: AdminInterceptor 
 * @Description: 个人中心后台拦截器
 * @author: lenovo
 * @date: 2019年11月20日 下午2:11:27
 */
public class MyInterceptor extends HandlerInterceptorAdapter {

	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//拦截规则:1.如果session有管理员的session,就放行否则拦截
		//getsession(false).false：ruguo request没有session返回null true:则创建session 默认true
		HttpSession session = request.getSession(false);
		if(null!=session) {
			//从session中获取admin的对象 如果有则放行
			Object object = session.getAttribute("user");
			if(null!=object) {
				return true;//放行
			}
		}
		request.setAttribute("message","请重新登录后再试");
		request.getRequestDispatcher("/WEB-INF/view/passport/login.jsp").forward(request, response);
		return false;
	}
	
}
