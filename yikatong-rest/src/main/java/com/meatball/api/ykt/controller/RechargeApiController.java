/**
 * Project Name:meatball-rest
 * File Name:RechargeApiController.java
 * Package Name:com.meatball.api.ykt.controller
 * Date:2018年3月13日上午11:49:28
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.api.ykt.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meatball.api.ykt.parems.AccountCreateParams;
import com.meatball.api.ykt.parems.AccountInfoParams;
import com.meatball.api.ykt.parems.AccountModifyParams;
import com.meatball.api.ykt.parems.AmountInfoParams;
import com.meatball.api.ykt.parems.CancelAmountInfoParams;
import com.meatball.api.ykt.parems.CancelAmountParams;
import com.meatball.api.ykt.parems.ComsumeAmountParams;
import com.meatball.api.ykt.parems.RechargeAmountParams;
import com.meatball.api.ykt.parems.RefundAmountInfoParams;
import com.meatball.api.ykt.parems.RefundAmountParams;
import com.meatball.api.ykt.service.RechargeApiService;
import com.meatball.component.OperateLog;
import com.meatball.vo.ResultMsg;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**   
 * @Title: RechargeApiController.java 
 * @Package com.meatball.api.ykt.controller 
 * @Description: TODO(充值类接口) 
 * @author jw 
 * @date 2018年3月13日 上午11:49:28 
 * @version V1.0   
 */
@Api(tags = "充值接口")
@RestController
@RequestMapping("/api/ykt/recharge")
public class RechargeApiController {
	@Resource
	private RechargeApiService rechargeApiService;
	
	@ApiOperation(value = "系统建卡", notes = "返回：是否成功和账户基本信息（包含虚拟卡号在内的基本信息）")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "权限验证成功", response = AccountInfoParams.class), 
		@ApiResponse(code = 201, message = "请求成功并且服务器创建了新的资源", response = Void.class), 
		@ApiResponse(code = 401, message = "用户名或密码错", response = Void.class), 
		@ApiResponse(code = 403, message = "权限认证失败", response = Void.class),
		@ApiResponse(code = 404, message = "请求的资源不存在", response = Void.class)
	})
	@PostMapping("/createCard")
	@OperateLog("系统建卡")
	public ResultMsg createCard(@RequestBody AccountCreateParams params) {
		return rechargeApiService.getCreateCardResult(params);
	}
	
	@ApiOperation(value = "修改账户", notes = "返回：是否成功和账户基本信息（包含虚拟卡号在内的基本信息）")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "权限验证成功", response = AccountInfoParams.class), 
		@ApiResponse(code = 201, message = "请求成功并且服务器创建了新的资源", response = Void.class), 
		@ApiResponse(code = 401, message = "用户名或密码错", response = Void.class), 
		@ApiResponse(code = 403, message = "权限认证失败", response = Void.class),
		@ApiResponse(code = 404, message = "请求的资源不存在", response = Void.class)
	})
	@PostMapping("/modifyCard")
	@OperateLog("修改账户")
	public ResultMsg modifyCard(@RequestBody AccountModifyParams params) {
		return rechargeApiService.getModifyCardResult(params);
	}
	
	
	@ApiOperation(value = "充值", notes = "返回：是否充值成功和余额等信息（如果是移动支付，则返回二维码支付信息）")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "权限验证成功", response = AmountInfoParams.class), 
		@ApiResponse(code = 201, message = "请求成功并且服务器创建了新的资源", response = Void.class), 
		@ApiResponse(code = 401, message = "用户名或密码错", response = Void.class), 
		@ApiResponse(code = 403, message = "权限认证失败", response = Void.class),
		@ApiResponse(code = 404, message = "请求的资源不存在", response = Void.class)
	})
	@PostMapping("/recharge")
	@OperateLog("充值")
	public ResultMsg recharge(@RequestBody RechargeAmountParams params) {
		return rechargeApiService.getRechargeResult(params);
	}
	
	
	@ApiOperation(value = "消费", notes = "返回：是否成功和余额等信息（如果是移动支付，则返回二维码支付信息）")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "权限验证成功", response = AmountInfoParams.class), 
		@ApiResponse(code = 201, message = "请求成功并且服务器创建了新的资源", response = Void.class), 
		@ApiResponse(code = 401, message = "用户名或密码错", response = Void.class), 
		@ApiResponse(code = 403, message = "权限认证失败", response = Void.class),
		@ApiResponse(code = 404, message = "请求的资源不存在", response = Void.class)
	})
	@PostMapping("/comsume")
	@OperateLog("消费")
	public ResultMsg comsume(@RequestBody ComsumeAmountParams params) {
		return rechargeApiService.getComsumeResult(params);
	}
	
	@ApiOperation(value = "退款", notes = "返回：是否成功和余额等信息（如不成功返回失败原因）")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "权限验证成功", response = RefundAmountInfoParams.class), 
		@ApiResponse(code = 201, message = "请求成功并且服务器创建了新的资源", response = Void.class), 
		@ApiResponse(code = 401, message = "用户名或密码错", response = Void.class), 
		@ApiResponse(code = 403, message = "权限认证失败", response = Void.class),
		@ApiResponse(code = 404, message = "请求的资源不存在", response = Void.class)
	})
	@PostMapping("/refund")
	@OperateLog("退款")
	public ResultMsg refund(@RequestBody RefundAmountParams params) {
		return rechargeApiService.getRefundResult(params);
	}
	
	@ApiOperation(value = "撤销", notes = "返回：是否成功和余额等信息（如不成功返回失败原因）")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "权限验证成功", response = CancelAmountInfoParams.class), 
		@ApiResponse(code = 201, message = "请求成功并且服务器创建了新的资源", response = Void.class), 
		@ApiResponse(code = 401, message = "用户名或密码错", response = Void.class), 
		@ApiResponse(code = 403, message = "权限认证失败", response = Void.class),
		@ApiResponse(code = 404, message = "请求的资源不存在", response = Void.class)
	})
	@PostMapping("/cancel")
	@OperateLog("撤销")
	public ResultMsg cancel(@RequestBody CancelAmountParams params) {
		return rechargeApiService.getCancelResult(params);
	}
	
	

}
