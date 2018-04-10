/**
 * Project Name:meatball-rest
 * File Name:OrderApiForWeixinService.java
 * Package Name:com.meatball.api.ykt.service
 * Date:2018年3月19日上午9:54:54
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.api.ykt.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meatball.api.ykt.parems.WxCallBackParams;
import com.meatball.api.ykt.parems.WxOrderParams;
import com.meatball.vo.ResultMsg;

/**   
 * @Title: OrderApiForWeixinService.java 
 * @Package com.meatball.api.ykt.service 
 * @Description: TODO(微信下单接口) 
 * @author jw 
 * @date 2018年3月19日 上午9:54:54 
 * @version V1.0   
 */
public interface OrderApiForWeixinService {
	
	/**
	 * @Title: getWeixinOrderResult 
	 * @Description: TODO(获取微信下单结果) 
	 * @param params
	 * @return
	 * @return ResultMsg    返回类型
	 */
	ResultMsg getWeixinOrderResult(WxOrderParams params);

	/**
	 * @Title: getWeixinOrderBackResult 
	 * @Description: TODO(获取微信下单回调结果) 
	 * @param params
	 * @return
	 * @return ResultMsg    返回类型
	 */
	ResultMsg getWeixinOrderBackResult(WxCallBackParams params);

	/**
	 * @Title: getWxBackMsg 
	 * @Description: TODO(获取微信回调返回信息) 
	 * @param request
	 * @return
	 * @return void    返回类型
	 * @throws Exception 
	 */
	void getWxBackMsg(HttpServletRequest request, HttpServletResponse response) throws Exception;

}
