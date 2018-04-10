/**
 * Project Name:meatball-core
 * File Name:GlobalExceptionHandler.java
 * Package Name:com.meatball.component
 * Date:2018年1月29日上午11:18:46
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.component;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.meatball.vo.ResultMsg;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;

/**   
 * @Title: GlobalExceptionHandler.java 
 * @Package com.meatball.component 
 * @Description: TODO(注册全局异常处理) 
 * @author 張翔宇  
 * @date 2018年1月29日 上午11:18:46 
 * @version V1.0   
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	/**
	 * @Title: allExceptionHandler 
	 * @Description: TODO(制定权限不足异常) 
	 * @param request
	 * @param exception
	 * @throws Exception
	 * @return Map<String,Object>    返回类型
	 */
	@ExceptionHandler(value = UnauthorizedException.class)
	public ResultMsg unauthorizedException(UnauthorizedException e) throws Exception {
		return new ResultMsg(403, "权限不足，请联系系统管理员。");
	}
	
	/**
	 * @Title: expiredJwtException 
	 * @Description: TODO(权限校验) 
	 * @param e
	 * @return ResultMsg    返回类型
	 */
	@ExceptionHandler(value = ExpiredJwtException.class)
	public ResultMsg expiredJwtException(ExpiredJwtException e) {
		return new ResultMsg(403, "权限令牌已经过期。");
	}
	
	/**
	 * @Title: SignatureException 
	 * @Description: TODO(权限校验) 
	 * @param e
	 * @return ResultMsg    返回类型
	 */
	@ExceptionHandler(value = SignatureException.class)
	public ResultMsg signatureException(SignatureException e) {
		return new ResultMsg(403, "非法的权限令牌。");
	}
	
	/**
	 * @Title: SignatureException 
	 * @Description: TODO(权限校验) 
	 * @param e
	 * @return ResultMsg    返回类型
	 */
	@ExceptionHandler(value = MalformedJwtException.class)
	public ResultMsg malformedJwtException(MalformedJwtException e) {
		return new ResultMsg(403, "非法的权限令牌。");
	}
	
	/**
	 * @Title: authorizationExceptionHandler 
	 * @Description: TODO(请求被拒绝) 
	 * @param e
	 * @return ResultMsg    返回类型
	 */
	@ExceptionHandler(value = AuthorizationException.class)
	public ResultMsg authorizationExceptionHandler(AuthorizationException e) {
		return new ResultMsg(403, "权限认证失败。");
	}
	
	/**
	 * @Title: nullPointerExceptionHandler 
	 * @Description: TODO(空异常) 
	 * @param exception
	 * @return ResultMsg    返回类型
	 */
	@ExceptionHandler(value = NullPointerException.class)
	public ResultMsg nullPointerExceptionHandler(NullPointerException e) {
		e.printStackTrace();
		return new ResultMsg(500, "The necessary parameters cannot be empty.(必要参数不能为空。)");
	}
	
	/**
	 * @Title: runtimeExceptionHandler 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param e
	 * @return
	 * @return ResultMsg    返回类型
	 */
	@ExceptionHandler(value = RuntimeException.class)
	public ResultMsg runtimeExceptionHandler(RuntimeException e) {
		e.printStackTrace();
		return new ResultMsg(500, "系统繁忙。");
	}
	
}
