/**
 * Project Name:meatball-rest
 * File Name:OderService.java
 * Package Name:com.meatball.api.ykt.service
 * Date:2018年3月22日下午2:55:58
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.api.ykt.service;

import com.meatball.api.ykt.parems.MobileCallBackParams;
import com.meatball.api.ykt.parems.MobileParams;
import com.meatball.vo.ResultMsg;

/**   
 * @Title: OderService.java 
 * @Package com.meatball.api.ykt.service 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 張翔宇  
 * @date 2018年3月22日 下午2:55:58 
 * @version V1.0   
 */
public interface OrderService {
	/**
	 * @Title: mobileOrder 
	 * @Description: TODO(移动下单，支付宝与微信统一调用) 
	 * @param params
	 * @return ResultMsg    返回类型
	 */
	public ResultMsg mobileOrder(MobileParams params);
	
	/**
	 * @Title: mobileCallBack 
	 * @Description: TODO(支付结果查询) 
	 * @param params
	 * @return ResultMsg    返回类型
	 */
	public ResultMsg mobileCallBack(MobileCallBackParams params);
}
