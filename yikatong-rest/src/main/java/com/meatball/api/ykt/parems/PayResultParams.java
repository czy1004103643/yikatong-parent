/**
 * Project Name:meatball-rest
 * File Name:PayResultParams.java
 * Package Name:com.meatball.api.ykt.parems
 * Date:2018年3月13日下午4:53:09
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.api.ykt.parems;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

/**   
 * @Title: PayResultParams.java 
 * @Package com.meatball.api.ykt.parems 
 * @Description: TODO(扫码支付结果信息) 
 * @author jw 
 * @date 2018年3月13日 下午4:53:09 
 * @version V1.0   
 */
public class PayResultParams implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="交易结果(0：成功 1：失败)", example="0")
	private int resultCode;
	
	@ApiModelProperty(value="支付结果的详细描述信息", example="微信支付成功20元")
	private String resultMsg;
	
	@ApiModelProperty(value="订单号", example="1123456")
	private String number;

	@ApiModelProperty(value = "支付方式(1：微信，2：支付宝)", example="1")
	private Integer payType;
	
	@ApiModelProperty(value="订单类别(1充值、2消费、3退款)", example="1")
	private int orderType;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getOrderType() {
		return orderType;
	}

	public void setOrderType(int orderType) {
		this.orderType = orderType;
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

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}
}
