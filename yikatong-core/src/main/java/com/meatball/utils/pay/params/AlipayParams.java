/**
 * Project Name:meatball-rest
 * File Name:AliOrderParams.java
 * Package Name:com.meatball.api.ykt.parems
 * Date:2018年3月13日下午4:24:27
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.utils.pay.params;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

/**   
 * @Title: AliOrderParams.java 
 * @Package com.meatball.api.ykt.parems 
 * @Description: TODO(支付宝下单信息) 
 * @author jw 
 * @date 2018年3月13日 下午4:24:27 
 * @version V1.0   
 */
public class AlipayParams implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// 订单号，此处ID为我们消费表或者充值表的主键
	private String orderId;
	
	@ApiModelProperty(value="用户id", example="111111", required = true)
	private Integer userId;
	
	@ApiModelProperty(value="订单类型(1挂号2门诊3住院)", example="1", required = true)
	private Integer dealType ;
	
	@ApiModelProperty(value="金额(带两位小数点)", example="5000.00", required = true)
	private String balance;
	
	@ApiModelProperty(value="机器编号", example="1")
	private String machineId ;
	
	@ApiModelProperty(value="操作员", example="张三")
	private String operator;
	
	private Integer orderType;

	/** 
	 * @return orderId 
	 */
	public String getOrderId() {
		return orderId;
	}

	/** 
	 * @param orderId 要设置的 orderId 
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	/** 
	 * @return dealType 
	 */
	public Integer getDealType() {
		return dealType;
	}

	/** 
	 * @param dealType 要设置的 dealType 
	 */
	public void setDealType(Integer dealType) {
		this.dealType = dealType;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getMachineId() {
		return machineId;
	}

	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	
}
