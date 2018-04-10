/**
 * Project Name:meatball-rest
 * File Name:AliCallBackParams.java
 * Package Name:com.meatball.api.ykt.parems
 * Date:2018年3月13日下午4:26:48
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.api.ykt.parems;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

/**   
 * @Title: AliCallBackParams.java 
 * @Package com.meatball.api.ykt.parems 
 * @Description: TODO(支付宝回调下单信息) 
 * @author jw 
 * @date 2018年3月13日 下午4:26:48 
 * @version V1.0   
 */
public class AliCallBackParams implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="订单号", example="a123456", required = true)
	private String number;
	
	@ApiModelProperty(value="订单类别(1充值、2消费、3退款)", example="1", required = true)
	private int orderType;

	public int getOrderType() {
		return orderType;
	}

	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
}
