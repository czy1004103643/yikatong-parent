/**
 * Project Name:meatball-core
 * File Name:DealTypeEnum.java
 * Package Name:com.meatball.utils
 * Date:2018年3月19日下午5:08:08
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.utils;
/**   
 * @Title: DealTypeEnum.java 
 * @Package com.meatball.utils 
 * @Description: TODO(交易类别) 
 * @author jw 
 * @date 2018年3月19日 下午5:08:08 
 * @version V1.0   
 */
public enum DealTypeEnum {
	GHF("挂号费", 1),
	MZF("门诊(处方)费", 2),
	ZYF("住院费", 3),
	YE("余额", 4);
	private final String key;
	private final int value;

	private DealTypeEnum(String key, int value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return this.key;
	}

	public int getValue() {
		return this.value;
	}
}
