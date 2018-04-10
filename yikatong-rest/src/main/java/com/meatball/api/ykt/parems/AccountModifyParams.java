/**
 * Project Name:meatball-rest
 * File Name:AccountModifyParams.java
 * Package Name:com.meatball.api.ykt.parems
 * Date:2018年3月21日上午9:46:58
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.api.ykt.parems;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**   
 * @Title: AccountModifyParams.java 
 * @Package com.meatball.api.ykt.parems 
 * @Description: TODO(账户修改参数类) 
 * @author jw 
 * @date 2018年3月21日 上午9:46:58 
 * @version V1.0   
 */
public class AccountModifyParams implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="用户id", example="123456", required = true)
	private String userId;
	
	@ApiModelProperty(value="姓名", example="张三", required = true)
	private String name;

	@ApiModelProperty(value="身份证号", example="512341199010101111")
	private String idcard;
	
	@ApiModelProperty(value="就诊卡号", example="1555555555")
	private String cardNumber;
	
	@ApiModelProperty(value="性别（1 男 2女）", example="1")
	private int gender;
	
	@ApiModelProperty(value="电话", example="13500000000")
	private String tel;
	
	@ApiModelProperty(value="住址", example="四川省成都市xx区xx街道")
	private String address;
	
	@ApiModelProperty(value="出生日期 (YYYY-MM-DD)", example="1990-10-10")
	private Date birthday;
	
	@ApiModelProperty(value="社保卡号", example="1555555555")
	private String sinCard;
	
	@ApiModelProperty(value="居民健康卡号", example="1555555555")
	private String healthCard;
	
	@ApiModelProperty(value="账户余额(带两位小数点)", example="5000.00")
	private String balance;

	@ApiModelProperty(value="支付密码", example="xxxxxx")
    private String vPaymentcode;
 
	@ApiModelProperty(value="指纹", example="xxxxxx")
    private String vFingerprint;

	public String getvPaymentcode() {
		return vPaymentcode;
	}

	public void setvPaymentcode(String vPaymentcode) {
		this.vPaymentcode = vPaymentcode;
	}

	public String getvFingerprint() {
		return vFingerprint;
	}

	public void setvFingerprint(String vFingerprint) {
		this.vFingerprint = vFingerprint;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard == null ? null : idcard.trim();
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber == null ? null : cardNumber.trim();
	}

	public String getSinCard() {
		return sinCard;
	}

	public void setSinCard(String sinCard) {
		this.sinCard = sinCard == null ? null : sinCard.trim();
	}

	public String getHealthCard() {
		return healthCard;
	}

	public void setHealthCard(String healthCard) {
		this.healthCard = healthCard == null ? null : healthCard.trim();
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel == null ? null : tel.trim();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}


}
