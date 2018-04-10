/**
 * Project Name:meatball-rest
 * File Name:UserApiService.java
 * Package Name:com.meatball.api.user.service
 * Date:2018年3月4日下午3:51:06
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.api.user.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.meatball.api.user.parems.UserParems;
import com.meatball.component.TokenComponent;
import com.meatball.system.user.model.SysUser;
import com.meatball.system.user.service.UserService;
import com.meatball.vo.ResultMsg;

/**   
 * @Title: UserApiService.java 
 * @Package com.meatball.api.user.service 
 * @Description: TODO(用户信息) 
 * @author 張翔宇  
 * @date 2018年3月4日 下午3:51:06 
 * @version V1.0   
 */
@Service
public class UserApiService {
	@Resource
	private UserService service;
	@Resource
	private TokenComponent tokenComponent;
	
	/**
	 * @Title: info 
	 * @Description: TODO(用户详情) 
	 * @param token
	 * @return ResultMsg    返回类型
	 */
	public ResultMsg info(UserParems parems) {
		long userId = tokenComponent.parseJWT(parems.getToken());
		SysUser sysUser = service.info(userId);
		return new ResultMsg(200, sysUser);
	}
}
