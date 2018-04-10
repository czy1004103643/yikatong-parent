/**
 * Project Name:meatball-rest
 * File Name:WxOrderParams.java
 * Package Name:com.meatball.api.ykt.parems
 * Date:2018年3月13日下午4:23:09
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.api.ykt.parems;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

/**   
 * @Title: WxOrderParams.java 
 * @Package com.meatball.api.ykt.parems 
 * @Description: TODO(微信下单信息) 
 * @author jw 
 * @date 2018年3月13日 下午4:23:09 
 * @version V1.0   
 */
public class WxOrderParams implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="用户id", example="111111", required = true)
	private Long userId;
	
	@ApiModelProperty(value="(订单类型(1充值、2消费、3退款)", example="1", required = true)
	private Integer orderType;
	
	@ApiModelProperty(value="交易类型(1挂号费、2门诊(处方)费、3住院费)", example="1", required = true)
	private Integer dealType;
	
	@ApiModelProperty(value="金额(单位:分)", example="5000", required = true)
	private String balance;
	
	@ApiModelProperty(value="机器编号", example="1")
	private String machineId ;
	
	@ApiModelProperty(value="操作员", example="张三")
	private String operator;

	public Integer getDealType() {
		return dealType;
	}

	public void setDealType(Integer dealType) {
		this.dealType = dealType;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
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
