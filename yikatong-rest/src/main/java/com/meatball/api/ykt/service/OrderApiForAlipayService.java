/**
 * Project Name:meatball-rest
 * File Name:OrderApiService.java
 * Package Name:com.meatball.api.ykt.service
 * Date:2018年3月15日下午3:44:38
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.api.ykt.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meatball.api.ykt.parems.AliOrderParams;
import com.meatball.vo.ResultMsg;

/**   
 * @Title: OrderApiService.java 
 * @Package com.meatball.api.ykt.service 
 * @Description: TODO(支付宝下单) 
 * @author 張翔宇  
 * @date 2018年3月15日 下午3:44:38 
 * @version V1.0   
 */
public interface OrderApiForAlipayService {
	
	/**
	 * @Title: aliOrder 
	 * @Description: TODO(支付宝下单) 
	 * @param params
	 * @return
	 * @return ResultMsg    返回类型
	 */
	public ResultMsg aliOrder(AliOrderParams params);
	
	/**
	 * @Title: zfbback 
	 * @Description: TODO(支付宝回调) 
	 * @param request
	 * @param response
	 * @return void    返回类型
	 */
	public void zfbback(HttpServletRequest request, HttpServletResponse response);
}
