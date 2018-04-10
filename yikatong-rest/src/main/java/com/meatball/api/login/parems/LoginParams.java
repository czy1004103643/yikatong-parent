/**
 * Project Name:meatball-rest
 * File Name:LoginParams.java
 * Package Name:com.meatball.api.login.params
 * Date:2018年3月2日下午3:01:40
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.api.login.parems;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**   
 * @Title: LoginParams.java 
 * @Package com.meatball.api.login.params 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 張翔宇  
 * @date 2018年3月2日 下午3:01:40 
 * @version V1.0   
 */
@ApiModel
public class LoginParams implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="用户名", example="zhangsan", required = true)
	private String account;
	
	@ApiModelProperty(value="密码", example="123456", required = true)
	private String password;

	/** 
	 * @return account 
	 */
	public String getAccount() {
		return account;
	}

	/** 
	 * @param account 要设置的 account 
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/** 
	 * @return password 
	 */
	public String getPassword() {
		return password;
	}

	/** 
	 * @param password 要设置的 password 
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
