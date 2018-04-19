/**
 * Project Name:meatball-rest
 * File Name:MobileParams.java
 * Package Name:com.meatball.api.ykt.parems
 * Date:2018年3月17日下午3:59:14
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.api.ykt.parems;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

/**   
 * @Title: MobileParams.java 
 * @Package com.meatball.api.ykt.parems 
 * @Description: TODO(移动支付下单参数类) 
 * @author jw 
 * @date 2018年3月17日 下午3:59:14 
 * @version V1.0   
 */
public class MobileParams implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="用户id", example="111111", required = true)
	private Long userId;
	
	@ApiModelProperty(value="(订单类型(1充值、2消费、3退款)", example="1", required = true)
	private Integer orderType;
	
	@ApiModelProperty(value="交易类型(1挂号费、2门诊(处方)费、3住院费)", example="1", required = true)
	private Integer dealType;
	
	@ApiModelProperty(value="金额(单位:元，保留两位小数)", example="5000.11", required = true)
	private String balance;
	
	@ApiModelProperty(value="机器编号", example="1")
	private String machineId ;
	
	@ApiModelProperty(value="操作员", example="张三")
	private String operatorName;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Integer getDealType() {
		return dealType;
	}

	public void setDealType(Integer dealType) {
		this.dealType = dealType;
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

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
 

}
