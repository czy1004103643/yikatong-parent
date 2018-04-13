/**
 * Project Name:meatball-core
 * File Name:PayProperties.java
 * Package Name:com.meatball.utils.pay.properties
 * Date:2018年3月19日上午9:10:33
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.utils.pay.properties;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**   
 * @Title: PayProperties.java 
 * @Package com.meatball.utils.pay.properties 
 * @Description: TODO(支付宝支付参数) 
 * @author 張翔宇  
 * @date 2018年3月19日 上午9:10:33 
 * @version V1.0   
 */
@Component
@ConfigurationProperties(prefix = "alipay") 
@PropertySource("classpath:alipay.yml")
public class AlipayPayProperties implements Serializable {
	private static final long serialVersionUID = 1L;

	// 支付宝网关名
	@Value("${open_api_domain}")
	private String openApiDomain;
	
	//
	@Value("${mcloud_api_domain}")
	private String mcloudApiDomain;
	
	// 
	@Value("${pid}")
	private String pid;
	
	//
	@Value("${appid}")
	private String appid;
	
	// RSA私钥
	@Value("${private_key}")
	private String privateKey;
	
	// RSA公钥
	@Value("${public_key}")
	private String publicKey;
	
	// SHA256withRsa对应支付宝公钥
	@Value("${alipay_public_key}")
	private String alipayPublicKey;
	
	// 签名类型: RSA->SHA1withRsa,RSA2->SHA256withRsa
	@Value("${sign_type}")
	private String signType;
	
	// 当面付最大查询次数和查询间隔（毫秒）
	@Value("${max_query_retry}")
	private String maxQueryRetry;
	@Value("${query_duration}")
	private String queryDuration;
	
	// 当面付最大撤销次数和撤销间隔（毫秒）
	@Value("${max_cancel_retry}")
	private String maxCancelRetry;
	@Value("${cancel_duration}")
	private String cancelDuration;
	
	// 交易保障线程第一次调度延迟和调度间隔（秒）
	@Value("${heartbeat_delay}")
	private String heartbeatDelay;
	@Value("${heartbeat_duration}")
	private String heartbeatDuration;
	
	@Value("${notify_url}")
	private String notifyUrl;
	
	/** 
	 * @return openApiDomain 
	 */
	public String getOpenApiDomain() {
		return openApiDomain;
	}
	/** 
	 * @param openApiDomain 要设置的 openApiDomain 
	 */
	public void setOpenApiDomain(String openApiDomain) {
		this.openApiDomain = openApiDomain;
	}
	/** 
	 * @return mcloudApiDomain 
	 */
	public String getMcloudApiDomain() {
		return mcloudApiDomain;
	}
	/** 
	 * @param mcloudApiDomain 要设置的 mcloudApiDomain 
	 */
	public void setMcloudApiDomain(String mcloudApiDomain) {
		this.mcloudApiDomain = mcloudApiDomain;
	}
	/** 
	 * @return pid 
	 */
	public String getPid() {
		return pid;
	}
	/** 
	 * @param pid 要设置的 pid 
	 */
	public void setPid(String pid) {
		this.pid = pid;
	}
	/** 
	 * @return appid 
	 */
	public String getAppid() {
		return appid;
	}
	/** 
	 * @param appid 要设置的 appid 
	 */
	public void setAppid(String appid) {
		this.appid = appid;
	}
	/** 
	 * @return privateKey 
	 */
	public String getPrivateKey() {
		return privateKey;
	}
	/** 
	 * @param privateKey 要设置的 privateKey 
	 */
	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
	/** 
	 * @return publicKey 
	 */
	public String getPublicKey() {
		return publicKey;
	}
	/** 
	 * @param publicKey 要设置的 publicKey 
	 */
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
	/** 
	 * @return alipayPublicKey 
	 */
	public String getAlipayPublicKey() {
		return alipayPublicKey;
	}
	/** 
	 * @param alipayPublicKey 要设置的 alipayPublicKey 
	 */
	public void setAlipayPublicKey(String alipayPublicKey) {
		this.alipayPublicKey = alipayPublicKey;
	}
	/** 
	 * @return signType 
	 */
	public String getSignType() {
		return signType;
	}
	/** 
	 * @param signType 要设置的 signType 
	 */
	public void setSignType(String signType) {
		this.signType = signType;
	}
	/** 
	 * @return maxQueryRetry 
	 */
	public String getMaxQueryRetry() {
		return maxQueryRetry;
	}
	/** 
	 * @param maxQueryRetry 要设置的 maxQueryRetry 
	 */
	public void setMaxQueryRetry(String maxQueryRetry) {
		this.maxQueryRetry = maxQueryRetry;
	}
	/** 
	 * @return queryDuration 
	 */
	public String getQueryDuration() {
		return queryDuration;
	}
	/** 
	 * @param queryDuration 要设置的 queryDuration 
	 */
	public void setQueryDuration(String queryDuration) {
		this.queryDuration = queryDuration;
	}
	/** 
	 * @return maxCancelRetry 
	 */
	public String getMaxCancelRetry() {
		return maxCancelRetry;
	}
	/** 
	 * @param maxCancelRetry 要设置的 maxCancelRetry 
	 */
	public void setMaxCancelRetry(String maxCancelRetry) {
		this.maxCancelRetry = maxCancelRetry;
	}
	/** 
	 * @return cancelDuration 
	 */
	public String getCancelDuration() {
		return cancelDuration;
	}
	/** 
	 * @param cancelDuration 要设置的 cancelDuration 
	 */
	public void setCancelDuration(String cancelDuration) {
		this.cancelDuration = cancelDuration;
	}
	/** 
	 * @return heartbeatDelay 
	 */
	public String getHeartbeatDelay() {
		return heartbeatDelay;
	}
	/** 
	 * @param heartbeatDelay 要设置的 heartbeatDelay 
	 */
	public void setHeartbeatDelay(String heartbeatDelay) {
		this.heartbeatDelay = heartbeatDelay;
	}
	/** 
	 * @return heartbeatDuration 
	 */
	public String getHeartbeatDuration() {
		return heartbeatDuration;
	}
	/** 
	 * @param heartbeatDuration 要设置的 heartbeatDuration 
	 */
	public void setHeartbeatDuration(String heartbeatDuration) {
		this.heartbeatDuration = heartbeatDuration;
	}
	/** 
	 * @return notifyUrl 
	 */
	public String getNotifyUrl() {
		return notifyUrl;
	}
	/** 
	 * @param notifyUrl 要设置的 notifyUrl 
	 */
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
}
