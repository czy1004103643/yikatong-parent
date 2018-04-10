/**
 * Project Name:meatball-rest
 * File Name:OrderApiController.java
 * Package Name:com.meatball.api.ykt.controller
 * Date:2018年3月13日下午4:14:07
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.api.ykt.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meatball.api.ykt.parems.AliCallBackParams;
import com.meatball.api.ykt.parems.AliOrderParams;
import com.meatball.api.ykt.parems.MobileCallBackParams;
import com.meatball.api.ykt.parems.MobileParams;
import com.meatball.api.ykt.parems.PayOrderParams;
import com.meatball.api.ykt.parems.PayResultParams;
import com.meatball.api.ykt.parems.WxCallBackParams;
import com.meatball.api.ykt.parems.WxOrderParams;
import com.meatball.api.ykt.service.OrderApiForAlipayService;
import com.meatball.api.ykt.service.OrderApiForWeixinService;
import com.meatball.api.ykt.service.OrderService;
import com.meatball.component.OperateLog;
import com.meatball.vo.ResultMsg;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

/**   
 * @Title: OrderApiController.java 
 * @Package com.meatball.api.ykt.controller 
 * @Description: TODO(下单类接口) 
 * @author jw 
 * @date 2018年3月13日 下午4:14:07 
 * @version V1.0   
 */
@Api(tags = "下单接口")
@RestController
@RequestMapping("/api/ykt/order")
public class OrderApiController {
	@Resource
	private OrderApiForAlipayService alipayService;
	@Resource
	private OrderApiForWeixinService weixinService;
	@Resource
	private OrderService orderService;
	
	
	@ApiOperation(value = "微信下单", notes = "返回：扫码支付信息")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "权限验证成功", response = PayOrderParams.class), 
		@ApiResponse(code = 201, message = "请求成功并且服务器创建了新的资源", response = Void.class), 
		@ApiResponse(code = 401, message = "用户名或密码错", response = Void.class), 
		@ApiResponse(code = 403, message = "权限认证失败", response = Void.class),
		@ApiResponse(code = 404, message = "请求的资源不存在", response = Void.class)
	})
	@PostMapping("/wxOrder")
	@OperateLog("微信下单")
	@ApiIgnore
	public ResultMsg wxOrder(@RequestBody WxOrderParams params) {
		return weixinService.getWeixinOrderResult(params);
	}
	
	@ApiOperation(value = "支付宝下单", notes = "返回：扫码支付信息")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "权限验证成功", response = PayOrderParams.class), 
		@ApiResponse(code = 201, message = "请求成功并且服务器创建了新的资源", response = Void.class), 
		@ApiResponse(code = 401, message = "用户名或密码错", response = Void.class), 
		@ApiResponse(code = 403, message = "权限认证失败", response = Void.class),
		@ApiResponse(code = 404, message = "请求的资源不存在", response = Void.class)
	})
	@PostMapping("/aliOrder")
	@OperateLog("支付宝下单")
	@ApiIgnore
	public ResultMsg aliOrder(@RequestBody AliOrderParams params) {
		return alipayService.aliOrder(params);
	}
	
	@ApiOperation(value = "移动下单", notes = "返回：扫码支付信息")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "权限验证成功", response = PayOrderParams.class), 
		@ApiResponse(code = 201, message = "请求成功并且服务器创建了新的资源", response = Void.class), 
		@ApiResponse(code = 401, message = "用户名或密码错", response = Void.class), 
		@ApiResponse(code = 403, message = "权限认证失败", response = Void.class),
		@ApiResponse(code = 404, message = "请求的资源不存在", response = Void.class)
	})
	@PostMapping("/mobileOrder")
	@OperateLog("移动下单")
	public ResultMsg mobileOrder(@RequestBody MobileParams params) {
		return orderService.mobileOrder(params);
	}
	
	@ApiOperation(value = "移动支付支付结果回调", notes = "返回：扫码支付的结果")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "权限验证成功", response = PayResultParams.class), 
		@ApiResponse(code = 201, message = "请求成功并且服务器创建了新的资源", response = Void.class), 
		@ApiResponse(code = 401, message = "用户名或密码错", response = Void.class), 
		@ApiResponse(code = 403, message = "权限认证失败", response = Void.class),
		@ApiResponse(code = 404, message = "请求的资源不存在", response = Void.class)
	})
	@PostMapping("/mobileCallBack")
	@OperateLog("移动支付支付结果回调")
	public ResultMsg mobileCallBack(@RequestBody MobileCallBackParams params) {
		return orderService.mobileCallBack(params);
	}
	
	@ApiOperation(value = "微信回调", notes = "返回：扫码支付的结果")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "权限验证成功", response = PayResultParams.class), 
		@ApiResponse(code = 201, message = "请求成功并且服务器创建了新的资源", response = Void.class), 
		@ApiResponse(code = 401, message = "用户名或密码错", response = Void.class), 
		@ApiResponse(code = 403, message = "权限认证失败", response = Void.class),
		@ApiResponse(code = 404, message = "请求的资源不存在", response = Void.class)
	})                      
	@PostMapping("/wxCallBack")
	@OperateLog("微信回调")
	public ResultMsg wxCallBack(@RequestBody WxCallBackParams params) {
		return weixinService.getWeixinOrderBackResult(params);
	}
	
	@ApiOperation(value = "支付宝回调", notes = "返回：扫码支付的结果")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "权限验证成功", response = PayResultParams.class), 
		@ApiResponse(code = 201, message = "请求成功并且服务器创建了新的资源", response = Void.class), 
		@ApiResponse(code = 401, message = "用户名或密码错", response = Void.class), 
		@ApiResponse(code = 403, message = "权限认证失败", response = Void.class),
		@ApiResponse(code = 404, message = "请求的资源不存在", response = Void.class)
	})
	@PostMapping("/aliCallBack")
	@OperateLog("支付宝回调")
	public ResultMsg aliCallBack(@RequestBody AliCallBackParams params) {
		
		return new ResultMsg(200);
	}
	
	
	/**
	 * @Title: wxback 
	 * @Description: TODO(微信接口回调操作) 
	 * @param request
	 * @return
	 * @return void    返回类型
	 * @throws Exception 
	 */
	@PostMapping("/wxback")
	@OperateLog("微信接口回调")
	@ApiIgnore
	public void wxback(HttpServletRequest request, HttpServletResponse response) throws Exception {
		weixinService.getWxBackMsg(request,response);
	}
	
	/**
	 * @Title: zfbback 
	 * @Description: TODO(支付宝接口回调) 
	 * @param request
	 * @param response
	 * @throws Exception
	 * @return void    返回类型
	 */
	@PostMapping("/zfbback")
	@OperateLog("支付宝接口回调")
	@ApiIgnore
	public void zfbback(HttpServletRequest request, HttpServletResponse response) throws Exception {
		alipayService.zfbback(request, response);
	}
	
}
