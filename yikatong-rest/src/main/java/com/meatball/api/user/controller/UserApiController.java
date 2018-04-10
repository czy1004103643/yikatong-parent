/**
 * Project Name:meatball-rest
 * File Name:UserApiController.java
 * Package Name:com.meatball.api.user.controller
 * Date:2018年3月4日下午3:45:54
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.api.user.controller;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meatball.api.user.parems.UserParems;
import com.meatball.api.user.service.UserApiService;
import com.meatball.vo.ResultMsg;

import springfox.documentation.annotations.ApiIgnore;

/**   
 * @Title: UserApiController.java 
 * @Package com.meatball.api.user.controller 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 張翔宇  
 * @date 2018年3月4日 下午3:45:54 
 * @version V1.0   
 */
@ApiIgnore
@RestController
@RequestMapping("/api/user")
public class UserApiController {
	@Resource
	private UserApiService service;
	
	/**
	 * @Title: info 
	 * @Description: TODO(用户详情信息) 
	 * @param token
	 * @return ResultMsg    返回类型
	 */
	@PostMapping("/info")
	@RequiresPermissions("system:user:info")
	public ResultMsg info(@RequestBody UserParems parems) {
		return service.info(parems);
	}
}
