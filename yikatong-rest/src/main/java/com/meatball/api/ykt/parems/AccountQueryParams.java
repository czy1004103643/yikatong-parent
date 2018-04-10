/**
 * Project Name:meatball-rest
 * File Name:AccountQueryParams.java
 * Package Name:com.meatball.api.ykt.parems
 * Date:2018年3月13日上午11:23:55
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.api.ykt.parems;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

/**   
 * @Title: AccountQueryParams.java 
 * @Package com.meatball.api.ykt.parems 
 * @Description: TODO(账户查询参数类) 
 * @author jw  
 * @date 2018年3月13日 上午11:23:55 
 * @version V1.0   
 */
public class AccountQueryParams implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="查询类别(1：身份证   2：就诊卡   3：社保卡  4：居民健康卡)", example="1", required = true)
	private int type;
	
	@ApiModelProperty(value="号码", example="123456", required = true)
	private String number;

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
	
	
}
