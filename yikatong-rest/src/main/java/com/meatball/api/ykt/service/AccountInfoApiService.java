/**
 * Project Name:meatball-rest
 * File Name:AccountInfoApiService.java
 * Package Name:com.meatball.api.ykt.service
 * Date:2018年3月16日上午9:44:10
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.api.ykt.service;

import com.meatball.api.ykt.parems.AccountQueryParams;
import com.meatball.vo.ResultMsg;

/**   
 * @Title: AccountInfoApiService.java 
 * @Package com.meatball.api.ykt.service 
 * @Description: TODO(账户信息充值记录等查询接口) 
 * @author jw 
 * @date 2018年3月16日 上午9:44:10 
 * @version V1.0   
 */
public interface AccountInfoApiService {

	/**
	 * @Title: getAccountInfoBy 
	 * @Description: TODO(获取账户信息查询结果) 
	 * @param params 账户查询参数类
	 * @return
	 * @return ResultMsg    返回账户基本信息（包含虚拟卡号在内的基本信息）
	 */
	public ResultMsg getAccountInfoBy(AccountQueryParams params);

	/**
	 * @Title: getAccountBalanceBy 
	 * @Description: TODO(获取余额查询结果) 
	 * @param params 账户查询参数类
	 * @return
	 * @return ResultMsg    返回 账户余额
	 */
	public ResultMsg getAccountBalanceBy(AccountQueryParams params);

	/**
	 * @Title: getRechargeRecordBy 
	 * @Description: TODO(获取充值记录查询结果) 
	 * @param params 账户查询参数类
	 * @return
	 * @return ResultMsg    返回 充值记录列表（包含充值方式、金额、时间等基本信息）
	 */
	public ResultMsg getRechargeRecordBy(AccountQueryParams params);

	/**
	 * @Title: getConsumeRecordBy 
	 * @Description: TODO(获取消费记录查询结果) 
	 * @param params 账户查询参数类
	 * @return
	 * @return ResultMsg    返回 消费记录列表（包含金额、时间等基本信息）
	 */
	public ResultMsg getConsumeRecordBy(AccountQueryParams params);

	/**
	 * @Title: getRefundRecordBy 
	 * @Description: TODO(获取退款记录查询结果) 
	 * @param params 账户查询参数类
	 * @return
	 * @return ResultMsg    返回 退款记录列表（包含金额、时间等基本信息）
	 */
	public ResultMsg getRefundRecordBy(AccountQueryParams params);
	
}
