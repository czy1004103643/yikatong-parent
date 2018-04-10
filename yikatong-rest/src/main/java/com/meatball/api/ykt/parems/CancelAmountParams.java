/**
 * Project Name:meatball-rest
 * File Name:CancelAmountParams.java
 * Package Name:com.meatball.api.ykt.parems
 * Date:2018年3月19日下午3:49:31
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.api.ykt.parems;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

/**   
 * @Title: CancelAmountParams.java 
 * @Package com.meatball.api.ykt.parems 
 * @Description: TODO(撤销参数类) 
 * @author jw 
 * @date 2018年3月19日 下午3:49:31 
 * @version V1.0   
 */
public class CancelAmountParams implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="卡类别(1：身份证   2：就诊卡   3：社保卡  4：居民健康卡)", example="1", required = true)
	private int type;
	
	@ApiModelProperty(value="号码", example="123456", required = true)
	private String number;
	
	@ApiModelProperty(value="金额(带两位小数点)", example="5000.00", required = true)
	private String balance;
	
	@ApiModelProperty(value="订单号", example="123456", required = true)
	private String orderNumber;
	
	@ApiModelProperty(value="订单类别(1充值、2消费)", example="1", required = true)
	private int orderType;
	
	@ApiModelProperty(value="机器编号", example="xxxxx", required = true)
	private String machineId ;
	
	@ApiModelProperty(value="操作员", example="张三", required = true)
	private String operator;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number == null ? null : number.trim();
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public int getOrderType() {
		return orderType;
	}

	public void setOrderType(int orderType) {
		this.orderType = orderType;
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
	
	

}
