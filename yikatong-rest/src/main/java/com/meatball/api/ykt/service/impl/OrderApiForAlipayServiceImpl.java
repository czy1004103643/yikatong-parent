/**
 * Project Name:meatball-rest
 * File Name:OrderApiServiceImpl.java
 * Package Name:com.meatball.api.ykt.service.impl
 * Date:2018年3月15日下午3:44:59
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.api.ykt.service.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.meatball.api.ykt.dao.AccountMapper;
import com.meatball.api.ykt.dao.ComsumeRecordMapper;
import com.meatball.api.ykt.dao.PayCallbackRecordMapper;
import com.meatball.api.ykt.dao.RechargeRecordMapper;
import com.meatball.api.ykt.enums.DealTypeEnum;
import com.meatball.api.ykt.model.Account;
import com.meatball.api.ykt.model.ComsumeRecord;
import com.meatball.api.ykt.model.PayCallbackRecord;
import com.meatball.api.ykt.model.RechargeRecord;
import com.meatball.api.ykt.parems.AliOrderParams;
import com.meatball.api.ykt.parems.PayOrderParams;
import com.meatball.api.ykt.service.OrderApiForAlipayService;
import com.meatball.utils.AlipayResRedder;
import com.meatball.utils.DateUtil;
import com.meatball.utils.QRCodeToBase64;
import com.meatball.utils.pay.AlipayTradePrecreate;
import com.meatball.utils.pay.params.AlipayParams;
import com.meatball.vo.ResultMsg;

/**   
 * @Title: OrderApiServiceImpl.java 
 * @Package com.meatball.api.ykt.service.impl 
 * @Description: TODO(支付宝下单) 
 * @author 張翔宇  
 * @date 2018年3月15日 下午3:44:59 
 * @version V1.0   
 */
@Service
public class OrderApiForAlipayServiceImpl implements OrderApiForAlipayService {
	@Resource
	private AlipayTradePrecreate alipayTradePrecreate; 
	@Resource
	private ComsumeRecordMapper comsumeRecordMapper;
	@Resource
	private PayCallbackRecordMapper payCallbackRecordMapper;
	@Resource
	private RechargeRecordMapper rechargeRecordMapper;
	@Resource
	private AccountMapper accountMapper;

	// 测试
	@Override
	public ResultMsg aliOrder(AliOrderParams params) {
		// 发起预下单操作
		AlipayParams aliParams = new AlipayParams();
		BeanUtils.copyProperties(params, aliParams);
		AlipayTradePrecreateResponse payResult = alipayTradePrecreate.placeOrder(aliParams);
		
		PayOrderParams result = new PayOrderParams();
		if(payResult.getCode().equals("10000")) {
			//
			result.setZfbCode(QRCodeToBase64.qrCodeToBase64(payResult.getQrCode()));
			result.setZfbOrder(payResult.getOutTradeNo());
		}
		return new ResultMsg(200, "预下单成功", result);
	}

	@Override
	public void zfbback(HttpServletRequest request, HttpServletResponse response) {
		//获取支付宝POST过来反馈信息 
        Map<String, String> maps = AlipayResRedder.read(request);
        // 验签结果
        boolean verify  = alipayTradePrecreate.verifySignature(maps);
        if(maps.get("trade_status").equals("TRADE_SUCCESS") && verify) {
        	// 获取订单号
        	String outTradeNo = maps.get("out_trade_no");
        	// 获取订单类型
        	String orderType = outTradeNo.substring(0, 2);
        	switch (orderType) {
			case "cz":
				// 查询出充值订单
				RechargeRecord rechargeRecord = rechargeRecordMapper.selectByPrimaryKey(Long.valueOf(outTradeNo.substring(2, outTradeNo.length())));
				// 设置交易流水号
				rechargeRecord.setvOrderid(maps.get("trade_no"));
				ComsumeRecord comsumeRecord = new ComsumeRecord();
				comsumeRecord.setvPaymentid(rechargeRecord.getvOrderid());
				BeanUtils.copyProperties(rechargeRecord, comsumeRecord);
				// 插入回调表数据
				this.insertPayCallbackRecord(maps, comsumeRecord);
				// 更新交易状态
				rechargeRecord.setiDealstatus(0);
				// 更新信息
				rechargeRecordMapper.updateByPrimaryKeySelective(rechargeRecord);
				
				// 维护账户余额
				// 查询用户信息
				Account account = accountMapper.selectByPrimaryKey(rechargeRecord.getbAccountid());
				account.setdBalance(rechargeRecord.getdBalance() + account.getdBalance());
				accountMapper.updateByPrimaryKeySelective(account);
				break;
			case "xf":
				// 查询出当前消费订单
				System.out.println(outTradeNo.substring(2, outTradeNo.length()));
				ComsumeRecord record = comsumeRecordMapper.selectByPrimaryKey(Long.valueOf(outTradeNo.substring(2, outTradeNo.length())));
				// 插入回调表数据
				this.insertPayCallbackRecord(maps, record);
				// 设置交易流水号
				record.setvPaymentid(maps.get("trade_no"));
				// 更新交易状态
				record.setiDealstatus(0);
				// 更新信息
				comsumeRecordMapper.updateByPrimaryKeySelective(record);
				break;
			default:
				throw new RuntimeException("订单类型不存在。");
			}
        }
        
        // 执行完成，向支付宝发送停止异步请求
        try {
			PrintWriter out = response.getWriter();
			out.println("success");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Title: insertPayCallbackRecord 
	 * @Description: TODO(保存回调信息) 
	 * @param map
	 * @return void    返回类型
	 */
	private void insertPayCallbackRecord(Map<String, String> map, ComsumeRecord comsumeRecord) {
		PayCallbackRecord record = new PayCallbackRecord();
		record.settTime(new Date());
		record.setvOrderid(map.get("out_trade_no"));
		record.setvPayplatform("支付宝");
		record.setvDealno(map.get("trade_no"));
		record.settOrdertime(DateUtil.parseTime(map.get("gmt_create")));
		record.settPaytime(DateUtil.parseTime(map.get("gmt_payment")));
		record.setdBalance(Double.parseDouble(map.get("total_amount")));
		record.setiDealtype2(comsumeRecord.getiDealtype());
		record.setvDealname(DealTypeEnum.lookup(comsumeRecord.getiDealtype()).toString());
		record.setvResult("成功");
		record.setbAccountid(comsumeRecord.getbAccountid());
		record.setvAccountname(comsumeRecord.getvAccountname());
		record.setvMerchantid(map.get("seller_id"));
		payCallbackRecordMapper.insertSelective(record); 
	}
}
