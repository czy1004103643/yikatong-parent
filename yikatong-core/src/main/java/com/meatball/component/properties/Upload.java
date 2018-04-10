/**
 * Project Name:meatball-core
 * File Name:MeatballProperties.java
 * Package Name:com.meatball.component
 * Date:2017年10月14日下午2:54:03
 * Copyright (c) 2017, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.component.properties;

import java.util.Map;

import javax.servlet.MultipartConfigElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**   
 * @Title: MeatballProperties.java 
 * @Package com.meatball.component 
 * @Description: TODO(读取自定义配置参数) 
 * @author 張翔宇  
 * @date 2017年10月14日 下午2:54:03 
 * @version V1.0   
 */
@Component
@ConfigurationProperties("upload")
public class Upload {
	private Logger log = LoggerFactory.getLogger(Upload.class);
	// 获取存放位置
	private Map<String, String> localtion;
	
	// 单个文件大小
	private String maxFileSize;
	
	// 上次总文件大小
	private String maxRequestSize;
	
	public Map<String, String> getLocaltion() {
		return localtion;
	}

	public void setLocaltion(Map<String, String> localtion) {
		this.localtion = localtion;
	}

	public String getMaxFileSize() {
		return maxFileSize;
	}

	public void setMaxFileSize(String maxFileSize) {
		this.maxFileSize = maxFileSize;
	}

	public String getMaxRequestSize() {
		return maxRequestSize;
	}

	public void setMaxRequestSize(String maxRequestSize) {
		this.maxRequestSize = maxRequestSize;
	}

	public String getBasePath() {
		String location = "";
		String os = System.getProperty("os.name");
		if(os.toLowerCase().startsWith("win")) {
			location = this.getLocaltion().get("windows");
		} else {
			location = this.getLocaltion().get("linux");
		}
		return location;
	}
	
	/**
	 * @Title: multipartConfigElement 
	 * @Description: TODO(初始化文件上传) 
	 * @return
	 * @return MultipartConfigElement    返回类型
	 */
	@Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 设置文件大小限制 ,超出设置页面会抛出异常信息，
        // 这样在文件上传的地方就需要进行异常信息的处理了;
        factory.setMaxFileSize(this.getMaxFileSize()); // KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize(this.getMaxRequestSize());
        // Sets the directory location where files will be stored.
        // factory.setLocation(this.getBasePath());
        log.info("Initialization upload parameter is successful. (初始化上传参数成功。)");
        return factory.createMultipartConfig();
    }
}
