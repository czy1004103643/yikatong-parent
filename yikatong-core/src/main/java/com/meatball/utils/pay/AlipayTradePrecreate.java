/**
 * Project Name:meatball-core
 * File Name:TradePrecreate.java
 * Package Name:com.meatball.utils.alipay
 * Date:2018年3月16日上午11:14:46
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.utils.pay;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.meatball.utils.pay.params.AlipayParams;
import com.meatball.utils.pay.properties.AlipayPayProperties;

/**
 * @Title: TradePrecreate.java
 * @Package com.meatball.utils.alipay
 * @Description: TODO(支付宝支付)
 * @author 張翔宇
 * @date 2018年3月16日 上午11:14:46
 * @version V1.0
 */
@Component
public class AlipayTradePrecreate {
	private static final Logger log = LoggerFactory.getLogger(AlipayTradePrecreate.class);
	
	@Resource
	private AlipayPayProperties properties;
	
	/**
	 * @Title: PlaceOrder
	 * @Description: TODO(预下单操作)
	 * @return String 返回类型
	 * @throws AlipayApiException 
	 */
	public AlipayTradePrecreateResponse placeOrder(AlipayParams params) {
		AlipayClient alipayClient = new DefaultAlipayClient(
				properties.getOpenApiDomain(),
				properties.getAppid(),
				properties.getPrivateKey(),
				"json",
				"utf-8",
				properties.getAlipayPublicKey(),
				"RSA2");
		
		// 参数说明请参照支付宝：https://docs.open.alipay.com/api_1/alipay.trade.precreate
		AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
		// 设置回调地址
		request.setNotifyUrl(properties.getNotifyUrl());
		// 组装内容参数
		Map<String, String> orderMap = new HashMap<String, String>();
		orderMap.put("out_trade_no", params.getOrderId());
		orderMap.put("seller_id", "");
		orderMap.put("total_amount", params.getBalance());
		orderMap.put("discountable_amount", "");
		orderMap.put("subject", "南充中心医院-一卡通");
		// 支付超时设置为2分钟
		orderMap.put("timeout_express", "2m");
		if(params.getOrderType() == 1) {
			// 禁用信用卡消费
			orderMap.put("disable_pay_channels", "credit_group");
		}
		log.info("支付宝预下单请求参数：" + JSON.toJSON(orderMap));
		request.setBizContent(JSON.toJSONString(orderMap));
		
		//通过alipayClient调用API，获得对应的response类
		AlipayTradePrecreateResponse response = new AlipayTradePrecreateResponse();
		try {
			response = alipayClient.execute(request);
			 /*String basePath = "d:/Users/meatball/file/";
             String fileName = String.format("alipay%szfb-%s.png", File.separator, response.getOutTradeNo());
             String filePath = new StringBuilder(basePath).append(fileName).toString();
			 ZxingUtils.getQRCodeImge(response.getQrCode(), 256, filePath);*/
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		System.out.print(response.getBody());
		log.info("支付宝预下单返回结果：" + response.getBody());
		return response;
	}
	

	/**
	 * @Title: verifySignature 
	 * @Description: TODO(异步结果验签) 
	 * @param params 参数列表(包括待验签参数和签名值sign) key-参数名称 value-参数值
	 * @param publicKey 验签公钥
	 * @param charset 验签字符集
	 * @return boolean    返回类型
	 */
	public boolean verifySignature(Map<String, String> params) {
		boolean verify = false;
		try {
			verify = AlipaySignature.rsaCheckV1(params, properties.getAlipayPublicKey(), "utf-8", "RSA2");
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		System.out.println(verify);
		return verify;
	}
}
