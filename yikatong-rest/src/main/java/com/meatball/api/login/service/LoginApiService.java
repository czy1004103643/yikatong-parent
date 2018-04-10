/**
 * Project Name:meatball-rest
 * File Name:LoginApiService.java
 * Package Name:com.meatball.api.login.service
 * Date:2018年3月2日下午3:25:16
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.api.login.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.meatball.api.login.parems.LoginParams;
import com.meatball.login.service.LoginService;
import com.meatball.system.user.model.SysUser;
import com.meatball.vo.ResultMsg;

/**   
 * @Title: LoginApiService.java 
 * @Package com.meatball.api.login.service 
 * @Description: TODO(登陆权限验证) 
 * @author 張翔宇  
 * @date 2018年3月2日 下午3:25:16 
 * @version V1.0   
 */
@Service
public class LoginApiService {
	@Resource
	private LoginService loginService;
	
	/**
	 * @Title: validate 
	 * @Description: TODO(登陆验证) 
	 * @param params
	 * @return ResultMsg    返回类型
	 */
	public ResultMsg validate(LoginParams params) {
		SysUser user = new SysUser();
		user.setAccount(params.getAccount());
		user.setPassword(params.getPassword());
		return loginService.validate(user);
	}
}
