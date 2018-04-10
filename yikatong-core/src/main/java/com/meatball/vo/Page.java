/**
 * Project Name:meatball-core
 * File Name:Page.java
 * Package Name:com.meatball.vo
 * Date:2017年10月13日下午6:01:09
 * Copyright (c) 2017, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.vo;

import java.io.Serializable;

/**   
 * @Title: Page.java 
 * @Package com.meatball.vo 
 * @Description: TODO(分页请求参数) 
 * @author 張翔宇  
 * @date 2017年10月13日 下午6:01:09 
 * @version V1.0   
 * @param <T>
 */
public class Page implements Serializable{
	private static final long serialVersionUID = 1L;

	// 开始数
	private Integer offset;
	
	// 每页展示数
	private Integer limit;
	
	// 排序方式
	private String order;
	
	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
}
