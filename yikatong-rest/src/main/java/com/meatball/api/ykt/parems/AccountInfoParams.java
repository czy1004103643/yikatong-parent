/**
 * Project Name:meatball-rest
 * File Name:AccountInfoParams.java
 * Package Name:com.meatball.api.ykt.parems
 * Date:2018年3月13日下午2:54:23
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.api.ykt.parems;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

/**   
 * @Title: AccountInfoParams.java 
 * @Package com.meatball.api.ykt.parems 
 * @Description: TODO(账户详细信息) 
 * @author jw 
 * @date 2018年3月13日 下午2:54:23 
 * @version V1.0   
 */
public class AccountInfoParams implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="操作结果(0：成功 1：失败)", example="0")
	private int resultCode;
	
	@ApiModelProperty(value="操作结果的详细描述信息", example="操作成功")
	private String resultMsg;
	
	@ApiModelProperty(value="账户id", example="1")
	private String id;
	
	@ApiModelProperty(value="姓名", example="张三")
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
	
	@ApiModelProperty(value="出生日期 (yyyy-MM-dd)", example="1990-10-10")
	private String birthday;
	
	@ApiModelProperty(value="社保卡号", example="1555555555")
	private String sinCard;
	
	@ApiModelProperty(value="居民健康卡号", example="1555555555")
	private String healthCard;
	
	@ApiModelProperty(value="账户余额(带两位小数点)", example="5000.00")
	private String balance;
	
	@ApiModelProperty(value="创建时间 (yyyy-MM-dd HH:mm:ss)", example="2010-10-10 15:11:12")
	private String createDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
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
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getSinCard() {
		return sinCard;
	}

	public void setSinCard(String sinCard) {
		this.sinCard = sinCard;
	}

	public String getHealthCard() {
		return healthCard;
	}

	public void setHealthCard(String healthCard) {
		this.healthCard = healthCard;
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

}
