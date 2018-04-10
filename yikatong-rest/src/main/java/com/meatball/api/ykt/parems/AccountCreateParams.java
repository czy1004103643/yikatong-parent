/**
 * Project Name:meatball-rest
 * File Name:AccountCreateParams.java
 * Package Name:com.meatball.api.ykt.parems
 * Date:2018年3月13日下午3:25:39
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.api.ykt.parems;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**   
 * @Title: AccountCreateParams.java 
 * @Package com.meatball.api.ykt.parems 
 * @Description: TODO(系统建卡参数类) 
 * @author jw 
 * @date 2018年3月13日 下午3:25:39 
 * @version V1.0   
 */
public class AccountCreateParams implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="建卡类别(1：身份证   2：就诊卡   3：社保卡  4：居民健康卡)", example="1", required = true)
	private int type;
	
	@ApiModelProperty(value="号码", example="123456", required = true)
	private String number;
	
	@ApiModelProperty(value="姓名", example="张三", required = true)
	private String name;
	
	@ApiModelProperty(value="性别（1 男 2女）", example="1", required = true)
	private int gender;
	
	@ApiModelProperty(value="电话", example="13500000000")
	private String tel;
	
	@ApiModelProperty(value="住址", example="四川省成都市xx区xx街道")
	private String address;
	
	@ApiModelProperty(value="出生日期 (YYYY-MM-DD)", example="1990-10-10")
	private Date birthday;

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
