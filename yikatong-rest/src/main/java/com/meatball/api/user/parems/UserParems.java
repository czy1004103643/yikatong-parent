/**
 * Project Name:meatball-rest
 * File Name:UserParems.java
 * Package Name:com.meatball.api.user.parems
 * Date:2018年3月4日下午3:48:58
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.api.user.parems;

import java.io.Serializable;

/**   
 * @Title: UserParems.java 
 * @Package com.meatball.api.user.parems 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 張翔宇  
 * @date 2018年3月4日 下午3:48:58 
 * @version V1.0   
 */
public class UserParems implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// 
	private String token;
	
	// 
	private String userId;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
