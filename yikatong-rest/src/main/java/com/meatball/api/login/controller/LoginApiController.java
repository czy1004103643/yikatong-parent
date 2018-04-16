/** 
 * Project Name:meatball-admin 
 * File Name:LoginController.java 
 * Package Name:com.meatball.login.controller 
 * Date:2017年10月6日上午12:28:08 
 * Copyright (c) 2017, zhang.xiangyu@foxmail.com All Rights Reserved. 
 */  
package com.meatball.api.login.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meatball.api.login.parems.LoginParams;
import com.meatball.api.login.service.LoginApiService;
import com.meatball.component.OperateLog;
import com.meatball.system.user.model.SysUser;
import com.meatball.vo.ResultMsg;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

/**   
 * @Title: LoginController.java 
 * @Package com.meatball.login.controller 
 * @Description: TODO(登陆) 
 * @author 張翔宇  
 * @date 2017年10月6日 上午12:28:08 
 * @version V1.0   
 */
@Api(tags = "权限认证")
@RestController
@RequestMapping("/api/login")
public class LoginApiController {
	@Resource
	private LoginApiService service;
	
	/**
	 * @Title: validate 
	 * @Description: TODO(验证用户，并返回token) 
	 * @param user
	 * @RequestBody 必须指定参数获取位置，否则无法获取到参数
	 * @return boolean    返回类型
	 */
	@ApiOperation(value = "获取授权，并返回权限及菜单信息", notes = "验证用户名与密码是否正确")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "权限验证成功", response = SysUser.class), 
		@ApiResponse(code = 201, message = "请求成功并且服务器创建了新的资源", response = Void.class), 
		@ApiResponse(code = 401, message = "用户名或密码错", response = Void.class), 
		@ApiResponse(code = 403, message = "权限认证失败", response = Void.class),
		@ApiResponse(code = 404, message = "请求的资源不存在", response = Void.class)
	})
	@PostMapping("/validate")
	@OperateLog("用户登陆")
	public ResultMsg validate(@RequestBody LoginParams params) {
		return service.validate(params);
	}
}
  