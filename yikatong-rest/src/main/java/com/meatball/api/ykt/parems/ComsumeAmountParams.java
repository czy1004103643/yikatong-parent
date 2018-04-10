/**
 * Project Name:meatball-rest
 * File Name:ComsumeAmountParams.java
 * Package Name:com.meatball.api.ykt.parems
 * Date:2018年3月17日下午2:26:32
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.api.ykt.parems;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

/**   
 * @Title: ComsumeAmountParams.java 
 * @Package com.meatball.api.ykt.parems 
 * @Description: TODO(消费金额条件类) 
 * @author jw 
 * @date 2018年3月17日 下午2:26:32 
 * @version V1.0   
 */
public class ComsumeAmountParams implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="卡类别(1：身份证   2：就诊卡   3：社保卡  4：居民健康卡)", example="1", required = true)
	private int type;
	
	@ApiModelProperty(value="号码", example="123456", required = true)
	private String number;
	
	@ApiModelProperty(value="金额(带两位小数点)", example="5000.00", required = true)
	private String balance;
	
	@ApiModelProperty(value="支付方式(1现金、2银行卡、3移动支付、4余额)", example="1", required = true)
	private int payType;
	
	@ApiModelProperty(value="交易类别(1挂号费、2门诊(处方)费、3住院费)", example="1", required = true)
	private int dealType;
	
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

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public int getDealType() {
		return dealType;
	}

	public void setDealType(int dealType) {
		this.dealType = dealType;
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
