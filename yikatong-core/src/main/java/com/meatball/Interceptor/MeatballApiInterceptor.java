/**
 * Project Name:meatball-core
 * File Name:MeatballInterceptor.java
 * Package Name:com.meatball.Interceptor
 * Date:2018年3月5日上午10:16:05
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**   
 * @Title: MeatballInterceptor.java 
 * @Package com.meatball.Interceptor 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 張翔宇  
 * @date 2018年3月5日 上午10:16:05 
 * @version V1.0   
 */
public class MeatballApiInterceptor implements HandlerInterceptor {
	/*@Resource
	private TokenComponent tokenComponent;*/
	/**
	 * 在请求处理之前进行调用（Controller方法调用之前）
	 * 只有返回true才会继续向下执行，返回false取消当前请求
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 获取token验证bean
		/*TokenComponent tokenComponent = SpringUtil.getBean(TokenComponent.class);
		// 验证token是否合法
		String token = request.getParameter("token"); 
		// 如果token为空，则从body中获取数据
		if(StringUtils.isEmpty(token)) {
			// 获取url中token
			token = request.getHeader("token");
            // 验证token是否合法
            tokenComponent.parseJWT(token);
		} else {
			tokenComponent.parseJWT(token);
		}*/
		return true;
	}

	/**
	 * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	/**
	 * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
