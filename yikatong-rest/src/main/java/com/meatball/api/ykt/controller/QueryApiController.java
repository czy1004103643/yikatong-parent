package com.meatball.api.ykt.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meatball.api.ykt.parems.AccountInfoParams;
import com.meatball.api.ykt.parems.AccountQueryParams;
import com.meatball.api.ykt.parems.ConsumeRecordParams;
import com.meatball.api.ykt.parems.RechargeRecordParams;
import com.meatball.api.ykt.parems.RefundAmountInfoParams;
import com.meatball.api.ykt.parems.RefundRecordParams;
import com.meatball.api.ykt.service.AccountInfoApiService;
import com.meatball.component.OperateLog;
import com.meatball.vo.ResultMsg;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @Title: QueryApiController.java 
 * @Package com.meatball.api.ykt.controller 
 * @Description: TODO(查询类接口) 
 * @author jw 
 * @date 2018年3月13日 上午11:16:15 
 * @version V1.0
 */
@Api(tags = "查询接口")
@RestController
@RequestMapping("/api/ykt/query")
public class QueryApiController {
	
	@Resource
	private AccountInfoApiService accountInfoApiService;
	
	@ApiOperation(value = "账户信息查询", notes = "返回：账户基本信息（包含虚拟卡号在内的基本信息）")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "权限验证成功", response = AccountInfoParams.class), 
		@ApiResponse(code = 201, message = "请求成功并且服务器创建了新的资源", response = Void.class), 
		@ApiResponse(code = 401, message = "用户名或密码错", response = Void.class), 
		@ApiResponse(code = 403, message = "权限认证失败", response = Void.class),
		@ApiResponse(code = 404, message = "请求的资源不存在", response = Void.class)
	})
	@PostMapping("/queryAccountInfo")
	@OperateLog("账户信息查询")
	public ResultMsg queryAccountInfo(@RequestBody AccountQueryParams params) {
		return accountInfoApiService.getAccountInfoBy(params);
	}
	
	@ApiOperation(value = "余额查询", notes = "返回：账户余额")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "权限验证成功", response = RefundAmountInfoParams.class), 
		@ApiResponse(code = 201, message = "请求成功并且服务器创建了新的资源", response = Void.class), 
		@ApiResponse(code = 401, message = "用户名或密码错", response = Void.class), 
		@ApiResponse(code = 403, message = "权限认证失败", response = Void.class),
		@ApiResponse(code = 404, message = "请求的资源不存在", response = Void.class)
	})
	@PostMapping("/queryBalance")
	@OperateLog("余额查询")
	public ResultMsg queryBalance(@RequestBody AccountQueryParams params) {
		return accountInfoApiService.getAccountBalanceBy(params);
	}
	
	@ApiOperation(value = "充值记录查询", notes = "返回：充值记录列表（包含充值方式、金额、时间等基本信息）")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "权限验证成功", response = RechargeRecordParams.class), 
		@ApiResponse(code = 201, message = "请求成功并且服务器创建了新的资源", response = Void.class), 
		@ApiResponse(code = 401, message = "用户名或密码错", response = Void.class), 
		@ApiResponse(code = 403, message = "权限认证失败", response = Void.class),
		@ApiResponse(code = 404, message = "请求的资源不存在", response = Void.class)
	})
	@PostMapping("/queryRecharge")
	@OperateLog("充值记录查询")
	public ResultMsg queryRecharge(@RequestBody AccountQueryParams params) {
		return accountInfoApiService.getRechargeRecordBy(params);
	}
	
	@ApiOperation(value = "消费记录查询", notes = "返回：消费记录列表（包含金额、时间等基本信息）")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "权限验证成功", response = ConsumeRecordParams.class), 
		@ApiResponse(code = 201, message = "请求成功并且服务器创建了新的资源", response = Void.class), 
		@ApiResponse(code = 401, message = "用户名或密码错", response = Void.class), 
		@ApiResponse(code = 403, message = "权限认证失败", response = Void.class),
		@ApiResponse(code = 404, message = "请求的资源不存在", response = Void.class)
	})
	@PostMapping("/queryConsume")
	@OperateLog("消费记录查询")
	public ResultMsg queryConsume(@RequestBody AccountQueryParams params) {
		return accountInfoApiService.getConsumeRecordBy(params);
	}
	
	@ApiOperation(value = "退款记录查询", notes = "返回：退款记录列表（包含金额、时间等基本信息）")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "权限验证成功", response = RefundRecordParams.class), 
		@ApiResponse(code = 201, message = "请求成功并且服务器创建了新的资源", response = Void.class), 
		@ApiResponse(code = 401, message = "用户名或密码错", response = Void.class), 
		@ApiResponse(code = 403, message = "权限认证失败", response = Void.class),
		@ApiResponse(code = 404, message = "请求的资源不存在", response = Void.class)
	})
	@PostMapping("/queryRefund")
	@OperateLog("退款记录查询")
	public ResultMsg queryRefund(@RequestBody AccountQueryParams params) {
		return accountInfoApiService.getRefundRecordBy(params);
	}
}
