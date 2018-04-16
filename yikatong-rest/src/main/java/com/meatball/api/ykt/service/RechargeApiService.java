/**
 * Project Name:meatball-rest
 * File Name:RechargeApiService.java
 * Package Name:com.meatball.api.ykt.service
 * Date:2018年3月16日上午10:10:51
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.api.ykt.service;

import com.meatball.api.ykt.parems.AccountCreateParams;
import com.meatball.api.ykt.parems.AccountModifyParams;
import com.meatball.api.ykt.parems.CancelAmountParams;
import com.meatball.api.ykt.parems.ComsumeAmountParams;
import com.meatball.api.ykt.parems.RechargeAmountParams;
import com.meatball.api.ykt.parems.RefundAmountParams;
import com.meatball.vo.ResultMsg;

/**   
 * @Title: RechargeApiService.java 
 * @Package com.meatball.api.ykt.service 
 * @Description: TODO(充值类接口) 
 * @author jw 
 * @date 2018年3月16日 上午10:10:51 
 * @version V1.0   
 */
public interface RechargeApiService {
	
	/**
	 * @Title: getCreateCardResult 
	 * @Description: TODO(获取系统建卡结果) 
	 * @param params 系统建卡参数类
	 * @return
	 * @return ResultMsg    返回 账户基本信息（包含虚拟卡号在内的基本信息）
	 */
	public ResultMsg getCreateCardResult(AccountCreateParams params) throws Exception;
	
	/**
	 * @Title: getRechargeResult 
	 * @Description: TODO(获取充值操作结果) 
	 * @param params 操作金额参数类
	 * @return
	 * @return ResultMsg    返回 是否充值成功和余额等信息（如不成功返回失败原因）
	 */
	public ResultMsg getRechargeResult(RechargeAmountParams params);
	
	/**
	 * @Title: getComsumeResult 
	 * @Description: TODO(获取消费操作结果) 
	 * @param params 操作金额参数类
	 * @return
	 * @return ResultMsg    返回 是否成功和余额等信息（如不成功返回失败原因）
	 */
	public ResultMsg getComsumeResult(ComsumeAmountParams params);
	
	/**
	 * @Title: getRefundResult 
	 * @Description: TODO(获取退款操作结果) 
	 * @param params 操作金额参数类
	 * @return
	 * @return ResultMsg    返回 是否成功和余额等信息（如不成功返回失败原因）
	 */
	public ResultMsg getRefundResult(RefundAmountParams params);

	/**
	 * @Title: getCancelResult 
	 * @Description: TODO(获取撤销操作结果) 
	 * @param params
	 * @return
	 * @return ResultMsg    返回类型
	 */
	public ResultMsg getCancelResult(CancelAmountParams params);

	/**
	 * @Title: getModifyCardResult 
	 * @Description: TODO(获取账户修改信息结果) 
	 * @param params
	 * @return
	 * @return ResultMsg    返回类型
	 */
	public ResultMsg getModifyCardResult(AccountModifyParams params);
	

}
