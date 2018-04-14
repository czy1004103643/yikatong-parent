/**
 * Project Name:meatball-core
 * File Name:WXTradeService.java
 * Package Name:com.meatball.utils.pay.weixin
 * Date:2018年3月22日下午4:22:53
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.utils.pay.weixin;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

/**   
 * @Title: WXTradeService.java 
 * @Package com.meatball.utils.pay.weixin 
 * @Description: TODO(微信交易服务) 
 * @author jw 
 * @date 2018年3月22日 下午4:22:53 
 * @version V1.0   
 */
@Service
public class WXTradeService {
	private static final Logger log = LoggerFactory.getLogger(WXTradeService.class);
	
	private WXPay wxpay;
    private WXPayConfigImpl config;

    public WXTradeService() throws Exception {
        config = WXPayConfigImpl.getInstance();
        wxpay = new WXPay(config);
    }
    
    /**
     * @Title: doUnifiedOrder 
     * @Description: TODO(下单) 
     * @param out_trade_no 商户订单号   充值订单格式 :cz+订单id 消费订单格式:xf+订单id
     * @param total_fee 金额
     * @param productId 商品编号
     * @return
     * @return Map<String,String>    返回类型
     */
    public Map<String, String> doUnifiedOrder(String out_trade_no,String total_fee,String productId){
    	Map<String, String> result = new HashMap<String, String>();
    	HashMap<String, String> data = new HashMap<>();
		data.put("body", "南充中心医院-一卡通");//商品描述
		data.put("out_trade_no", out_trade_no);//商户订单号
		data.put("fee_type", "CNY");//标价币种 人民币
		data.put("total_fee", total_fee);//金额
		data.put("spbill_create_ip", "123.12.12.123");//终端ip 微信支付API的机器IP
		data.put("notify_url", "http://zxy.jydz-yjy.com/api/ykt/order/wxback");//回调地址
//        data.put("notify_url", "http://www.jycompany.cn/api/ykt/order/wxback");//回调地址
		data.put("trade_type", "NATIVE");//交易类型 扫码支付
        data.put("device_info", "WEB");//设备号 
        data.put("product_id",  productId);//商品编号
        data.put("limit_pay", "no_credit");//指定支付方式 不支持信用卡
        // data.put("time_expire", "20170112104120");
        try {
        	log.info("微信预下单参数：" + JSON.toJSONString(data));
        	result = wxpay.unifiedOrder(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
 
}
