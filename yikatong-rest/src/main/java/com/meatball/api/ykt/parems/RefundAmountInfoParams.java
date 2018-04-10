/**
 * Project Name:meatball-rest
 * File Name:RefundAmountInfoParams.java
 * Package Name:com.meatball.api.ykt.parems
 * Date:2018年3月19日上午10:02:32
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.api.ykt.parems;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

/**   
 * @Title: RefundAmountInfoParams.java 
 * @Package com.meatball.api.ykt.parems 
 * @Description: TODO(退款账户余额信息) 
 * @author jw 
 * @date 2018年3月19日 上午10:02:32 
 * @version V1.0   
 */
public class RefundAmountInfoParams implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="交易结果(0：成功 1：失败)", example="0")
	private int resultCode;
	
	@ApiModelProperty(value="交易结果的详细描述信息", example="退款成功20元")
	private String resultMsg;
	
	@ApiModelProperty(value="卡类别(1：身份证   2：就诊卡   3：社保卡  4：居民健康卡)", example="1")
	private int type;
	
	@ApiModelProperty(value="号码", example="123456")
	private String number;
	
	@ApiModelProperty(value="姓名", example="张三")
	private String name;
	
	@ApiModelProperty(value="余额(带两位小数点)", example="5000.00")
	private String balance;
	
	@ApiModelProperty(value="退款时间 (yyyy-MM-dd HH:mm:ss)", example="2017-10-10 15:11:12")
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

}
