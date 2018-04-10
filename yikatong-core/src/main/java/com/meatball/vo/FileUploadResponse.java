/**
 * Project Name:meatball-core
 * File Name:FileUploadResponseUtil.java
 * Package Name:com.meatball.utils
 * Date:2017年10月13日下午4:22:07
 * Copyright (c) 2017, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.vo;
/**   
 * @Title: FileUploadResponseUtil.java 
 * @Package com.meatball.utils 
 * @Description: TODO(格式化上传文件) 
 * @author 張翔宇  
 * @date 2017年10月13日 下午4:22:07 
 * @version V1.0   
 */
public class FileUploadResponse {
	
	// success || fail
	private String type;
	
	// 消息
	private String msg;
	
	// 文件类型
	private String contentType;
	
	// 文件名称
	private String fileName;
	
	// 上次地址
	private String url;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
