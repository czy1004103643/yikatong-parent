/**
 * Project Name:meatball-rest
 * File Name:RefundAmountParams.java
 * Package Name:com.meatball.api.ykt.parems
 * Date:2018年3月17日下午2:32:38
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.api.ykt.parems;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

/**   
 * @Title: RefundAmountParams.java 
 * @Package com.meatball.api.ykt.parems 
 * @Description: TODO(退款金额参数类) 
 * @author jw 
 * @date 2018年3月17日 下午2:32:38 
 * @version V1.0   
 */
public class RefundAmountParams implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="卡类别(1：身份证   2：就诊卡   3：社保卡  4：居民健康卡)", example="1", required = true)
	private int type;
	
	@ApiModelProperty(value="号码", example="123456", required = true)
	private String number;
	
	@ApiModelProperty(value="金额(带两位小数点)", example="5000.00", required = true)
	private String balance;
	
/*	@ApiModelProperty(value="退款方式(1线上、2线下)", example="1", required = true)
	private int payType;
	
	@ApiModelProperty(value="线上支付平台的交易流水号(注：线上退款必须提供)", example="xxxxxxxx")
	private String dealId;
	
	@ApiModelProperty(value="线下的订单号(注：线下退款必须提供)", example="xxxxxxxx")
	private String orderId;
	
	@ApiModelProperty(value="线下类别(1住院费、2账户余额)", example="1", required = true)
	private int dealType;*/
	
	@ApiModelProperty(value="身份证照片路径", example="xxxxxxxx", required = true)
	private String pic;
 	
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

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

}
