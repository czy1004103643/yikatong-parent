/**
 * Project Name:meatball-core
 * File Name:Ads.java
 * Package Name:com.meatball.test
 * Date:2018年3月15日下午5:02:50
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayMobilePublicMultiMediaClient;
import com.alipay.api.AlipayMobilePublicMultiMediaDownloadRequest;
import com.alipay.api.AlipayMobilePublicMultiMediaDownloadResponse;

/**   
 * @Title: Ads.java 
 * @Package com.meatball.test 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 張翔宇  
 * @date 2018年3月15日 下午5:02:50 
 * @version V1.0   
 */
public class Ads {
	@SuppressWarnings("unused")
	private static String apiMethodName       = "alipay.mobile.public.multimedia.download";
    private static String media_id            = "L21pZnMvVDFNeFowWG5kYlhYYUNucHJYP3Q9YW13ZiZ4c2lnPWZlY2I5ZDM5ODZmMTBiMDFiMWQ4MjhkNTA5YTU2NDg4607";
    //公用变量
    public static String  serverUrl           = "https://openfile.alipay.com/chat/multimedia.do";
    public static String  appId               = "2013102200001786";
    public static String  partner_private_key = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKK0PXoLKnBkgtOl0kvyc9X2tUUdh/lRZr9RE1frjr2ZtAulZ+Moz9VJZFew1UZIzeK0478obY/DjHmD3GMfqJoTguVqJ2MEg+mJ8hJKWelvKLgfFBNliAw+/9O6Jah9Q3mRzCD8pABDEHY7BM54W7aLcuGpIIOa/qShO8dbXn+FAgMBAAECgYA8+nQ380taiDEIBZPFZv7G6AmT97doV3u8pDQttVjv8lUqMDm5RyhtdW4n91xXVR3ko4rfr9UwFkflmufUNp9HU9bHIVQS+HWLsPv9GypdTSNNp+nDn4JExUtAakJxZmGhCu/WjHIUzCoBCn6viernVC2L37NL1N4zrR73lSCk2QJBAPb/UOmtSx+PnA/mimqnFMMP3SX6cQmnynz9+63JlLjXD8rowRD2Z03U41Qfy+RED3yANZXCrE1V6vghYVmASYsCQQCoomZpeNxAKuUJZp+VaWi4WQeMW1KCK3aljaKLMZ57yb5Bsu+P3odyBk1AvYIPvdajAJiiikRdIDmi58dqfN0vAkEAjFX8LwjbCg+aaB5gvsA3t6ynxhBJcWb4UZQtD0zdRzhKLMuaBn05rKssjnuSaRuSgPaHe5OkOjx6yIiOuz98iQJAXIDpSMYhm5lsFiITPDScWzOLLnUR55HL/biaB1zqoODj2so7G2JoTiYiznamF9h9GuFC2TablbINq80U2NcxxQJBAMhw06Ha/U7qTjtAmr2qAuWSWvHU4ANu2h0RxYlKTpmWgO0f47jCOQhdC3T/RK7f38c7q8uPyi35eZ7S1e/PznY=";
    public static String  format              = "json";
    public static String  charset             = "GBK";
    
	public static void main(String[] args) throws AlipayApiException {
		AlipayClient alipayClient = new AlipayMobilePublicMultiMediaClient(serverUrl, appId,
	            partner_private_key, format, charset);
	        AlipayMobilePublicMultiMediaDownloadResponse response = null;
	        FileOutputStream outputStream = null;
	        try {
	            //创建文件流
	            outputStream = new FileOutputStream("D:/flask/2.jpg");
	            AlipayMobilePublicMultiMediaDownloadRequest request = new AlipayMobilePublicMultiMediaDownloadRequest();
	            request.setBizContent("{\"mediaId\":\"" + media_id + "\"}");
	            request.setOutputStream(outputStream);
	            //{"mediaId":"L21pZnMvVDFucFVGWGZKYlhYYUNucHJYP3Q9YW13ZiZ4c2lnPTEzYWYwZjE1MmU2OTVhZTRlYjRkNGVhYjhlZGU2MzIw052"}
	            System.err.println(request.getBizContent());
	            response = alipayClient.execute(request);
	        } catch (AlipayApiException e) {
	            e.printStackTrace();
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
	        //200
	        System.err.println(response.getCode());
	        //成功
	        System.err.println(response.getMsg());
	        //   {"alipay_mobile_public_multimedia_download_response":{"code":200,"msg":"成功"}}
	        System.err.println(response.getBody());
	        //{biz_content={"mediaId":"L21pZnMvVDFucFVGWGZKYlhYYUNucHJYP3Q9YW13ZiZ4c2lnPTEzYWYwZjE1MmU2OTVhZTRlYjRkNGVhYjhlZGU2MzIw052"}}
	        System.err.println(response.getParams());
	}

}
