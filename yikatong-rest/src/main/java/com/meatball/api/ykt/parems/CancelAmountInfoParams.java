/**
 * Project Name:meatball-rest
 * File Name:CancelAmountInfoParams.java
 * Package Name:com.meatball.api.ykt.parems
 * Date:2018年3月19日下午3:51:21
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.api.ykt.parems;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

/**   
 * @Title: CancelAmountInfoParams.java 
 * @Package com.meatball.api.ykt.parems 
 * @Description: TODO(撤销返回信息) 
 * @author jw 
 * @date 2018年3月19日 下午3:51:21 
 * @version V1.0   
 */
public class CancelAmountInfoParams implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="交易结果(0：成功 1：失败)", example="0")
	private int resultCode;
	
	@ApiModelProperty(value="交易结果的详细描述信息", example="撤销成功20元")
	private String resultMsg;
	
	@ApiModelProperty(value="卡类别(1：身份证   2：就诊卡   3：社保卡  4：居民健康卡)", example="1")
	private int type;
	
	@ApiModelProperty(value="号码", example="123456")
	private String number;
	
	@ApiModelProperty(value="余额(带两位小数点)", example="5000.00")
	private String balance;
	
	@ApiModelProperty(value="订单号", example="123456")
	private String orderNumber;
	
	@ApiModelProperty(value="订单类别(1充值、2消费)", example="1")
	private int orderType;
	
	@ApiModelProperty(value="撤销时间 (yyyy-MM-dd HH:mm:ss)", example="2017-10-10 15:11:12")
	private String createDate;

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
		this.number = number;
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

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	
	
}
