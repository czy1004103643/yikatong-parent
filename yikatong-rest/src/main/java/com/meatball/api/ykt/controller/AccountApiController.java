/**
 * Project Name:meatball-rest
 * File Name:OrderApiController.java
 * Package Name:com.meatball.api.ykt.controller
 * Date:2018年4月16日 下午3:7:07
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
 */
package com.meatball.api.ykt.controller;

import com.meatball.api.ykt.parems.AccountCreateParams;
import com.meatball.api.ykt.parems.AccountInfoParams;
import com.meatball.api.ykt.parems.AccountModifyParams;
import com.meatball.api.ykt.service.RechargeApiService;
import com.meatball.component.OperateLog;
import com.meatball.vo.ResultMsg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Title: OrderApiController.java
 * @Package com.meatball.api.ykt.controller
 * @Description: TODO(用户接口)
 * @author jw
 * @date 2018年4月16日 下午3:7:07
 * @version V1.0
 */
@Api(tags = "用户接口")
@RestController
@RequestMapping("/api/ykt/account")
public class AccountApiController {
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
    public ResultMsg createCard(@RequestBody AccountCreateParams params) throws Exception {
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
}
