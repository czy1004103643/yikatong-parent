/**
 * Project Name:meatball-core
 * File Name:SecretProperties.java
 * Package Name:com.meatball.component
 * Date:2017年10月16日下午5:33:18
 * Copyright (c) 2017, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.component.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**   
 * @Title: SecretProperties.java 
 * @Package com.meatball.component 
 * @Description: TODO(密钥参数配置) 
 * @author 張翔宇  
 * @date 2017年10月16日 下午5:33:18 
 * @version V1.0   
 */
@Component
@ConfigurationProperties("secret")
public class Secret {
	// 密钥
	private String key;
	
	private String issuer;
	
	private String subject;
	
	private Integer ttlMillis;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Integer getTtlMillis() {
		return ttlMillis;
	}

	public void setTtlMillis(Integer ttlMillis) {
		this.ttlMillis = ttlMillis;
	}
}
