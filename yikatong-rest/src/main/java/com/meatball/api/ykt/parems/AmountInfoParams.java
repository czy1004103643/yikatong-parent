/**
 * Project Name:meatball-rest
 * File Name:AmountInfoParams.java
 * Package Name:com.meatball.api.ykt.parems
 * Date:2018年3月13日下午4:16:19 
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.api.ykt.parems;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

/**   
 * @Title: AmountInfoParams.java 
 * @Package com.meatball.api.ykt.parems 
 * @Description: TODO(账户金额信息) 
 * @author jw 
 * @date 2018年3月13日 下午4:16:19 
 * @version V1.0   
 */
public class AmountInfoParams implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="交易结果(0：成功 1：失败)", example="0")
	private int resultCode;
	
	@ApiModelProperty(value="交易结果的详细描述信息", example="微信支付成功20元")
	private String resultMsg;
	
	@ApiModelProperty(value="卡类别(1：身份证   2：就诊卡   3：社保卡  4：居民健康卡)", example="1")
	private int type;
	
	@ApiModelProperty(value="号码", example="123456")
	private String number;
	
	@ApiModelProperty(value="姓名", example="张三")
	private String name;
	
	@ApiModelProperty(value="余额(带两位小数点)", example="5000.00")
	private String balance;
	
	@ApiModelProperty(value="操作时间 (yyyy-MM-dd HH:mm:ss)", example="2017-10-10 15:11:12")
	private String createDate;
	
	@ApiModelProperty(value="微信二维码(base64格式,注：移动支付时必须提供)", example="xxxxxxx")
	private String wxCode;
	
	@ApiModelProperty(value="微信订单号(注：移动支付时必须提供)", example="123456")
	private String wxOrder;
	
	@ApiModelProperty(value="支付宝二维码(base64格式,注：移动支付时必须提供)", example="xxxxxxx")
	private String zfbCode;
	
	@ApiModelProperty(value="支付宝订单号(注：移动支付时必须提供)", example="123456")
	private String zfbOrder;

	@ApiModelProperty(value = "余额订单号", example = "3343")
	private Long balanceOrder;

	public String getWxCode() {
		return wxCode;
	}

	public void setWxCode(String wxCode) {
		this.wxCode = wxCode;
	}

	public String getWxOrder() {
		return wxOrder;
	}

	public void setWxOrder(String wxOrder) {
		this.wxOrder = wxOrder;
	}

	public String getZfbCode() {
		return zfbCode;
	}

	public void setZfbCode(String zfbCode) {
		this.zfbCode = zfbCode;
	}

	public String getZfbOrder() {
		return zfbOrder;
	}

	public void setZfbOrder(String zfbOrder) {
		this.zfbOrder = zfbOrder;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
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

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Long getBalanceOrder() {
		return balanceOrder;
	}

	public void setBalanceOrder(Long balanceOrder) {
		this.balanceOrder = balanceOrder;
	}
}
