/**
 * Project Name:meatball-rest
 * File Name:PayOrderParams.java
 * Package Name:com.meatball.api.ykt.parems
 * Date:2018年3月13日下午5:17:41
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.api.ykt.parems;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

/**   
 * @Title: PayOrderParams.java 
 * @Package com.meatball.api.ykt.parems 
 * @Description: TODO(扫码支付信息) 
 * @author jw 
 * @date 2018年3月13日 下午5:17:41 
 * @version V1.0   
 */
public class PayOrderParams implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="操作结果(0：成功 1：失败)", example="0")
	private int resultCode;
	
	@ApiModelProperty(value="操作结果的详细描述信息", example="操作成功")
	private String resultMsg;

	@ApiModelProperty(value="微信二维码", example="weixin://wxpay/bizpayurl?pr=ENaKyJK")
	private String wxCode;
	
	@ApiModelProperty(value="微信订单号", example="123456")
	private String wxOrder;
	
	@ApiModelProperty(value="支付宝二维码", example="xxxxxxx")
	private String zfbCode;
	
	@ApiModelProperty(value="支付宝订单号", example="123456")
	private String zfbOrder;

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

	 

	
}
