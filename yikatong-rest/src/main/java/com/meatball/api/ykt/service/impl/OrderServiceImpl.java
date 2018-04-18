/**
 * Project Name:meatball-rest
 * File Name:OderServiceImpl.java
 * Package Name:com.meatball.api.ykt.service.impl
 * Date:2018年3月22日下午2:56:15
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.api.ykt.service.impl;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import com.meatball.api.ykt.parems.PayResultParams;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.meatball.api.ykt.dao.AccountMapper;
import com.meatball.api.ykt.dao.ComsumeRecordMapper;
import com.meatball.api.ykt.dao.RechargeRecordMapper;
import com.meatball.api.ykt.enums.DealTypeEnum;
import com.meatball.api.ykt.model.Account;
import com.meatball.api.ykt.model.ComsumeRecord;
import com.meatball.api.ykt.model.RechargeRecord;
import com.meatball.api.ykt.parems.MobileCallBackParams;
import com.meatball.api.ykt.parems.MobileParams;
import com.meatball.api.ykt.parems.PayOrderParams;
import com.meatball.api.ykt.service.OrderService;
import com.meatball.api.ykt.service.OperationLogService;
import com.meatball.utils.pay.AlipayTradePrecreate;
import com.meatball.utils.pay.params.AlipayParams;
import com.meatball.utils.pay.weixin.WXTradeService;
import com.meatball.vo.ResultMsg;

/**   
 * @Title: OderServiceImpl.java 
 * @Package com.meatball.api.ykt.service.impl 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 張翔宇  
 * @date 2018年3月22日 下午2:56:15 
 * @version V1.0   
 */
@Service
public class OrderServiceImpl implements OrderService {
	@Resource
	private AccountMapper accountMapper;
	@Resource
	private ComsumeRecordMapper comsumeRecordMapper;
	@Resource
	private RechargeRecordMapper rechargeRecordMapper;
	@Resource
	private AlipayTradePrecreate alipayTradePrecreate;
	@Resource
	private WXTradeService wxTradeService;
	@Resource
	private OperationLogService operationLogService;
	
	@Override
	public ResultMsg mobileOrder(MobileParams params) {
		PayOrderParams info = new PayOrderParams();
		ResultMsg msg = new ResultMsg(200, info);
		//查询用户是否存在
		Account account = accountMapper.selectByPrimaryKey(params.getUserId());
		//如果存在，则组装微信下单信息
		if(null != account) {
			//判断属于哪种订单
			switch (params.getOrderType()) {
			//充值
			case 1:
				// 微信
				//组装并生成充值订单
				RechargeRecord record = new RechargeRecord();
				setRechargeValues(params, account, record, "wx");
				rechargeRecordMapper.insertSelective(record); 
				//调用微信下单接口
				Double totalFee = Double.parseDouble(params.getBalance()) * 100;
				Map<String,String> map = wxTradeService.doUnifiedOrder("cz"+record.getbId().toString(), String.valueOf(totalFee.intValue()), record.getbId().toString());
				System.out.println("返回信息===="+map);
				//组装下单结果信息
				setResultInfos(info, record.getbId(), map);
				//插入操作日志
				operationLogService.insertOperationLog(params.getMachineId(), "微信预下单充值操作", account.getbId(), account.getvName(), Double.parseDouble(params.getBalance()));
				
				// 支付宝
				setRechargeValues(params, account, record, "zfb");
				rechargeRecordMapper.insertSelective(record);
				// 发起下单请求
				AlipayParams czalipayParams = new AlipayParams();
				BeanUtils.copyProperties(params, czalipayParams);
				czalipayParams.setOrderId("cz" + record.getbId().toString());
				AlipayTradePrecreateResponse czplaceOrderResult =  alipayTradePrecreate.placeOrder(czalipayParams);
				setResultInfos(info, record.getbId(), czplaceOrderResult);
				//插入操作日志
				operationLogService.insertOperationLog(params.getMachineId(), "支付宝预下单充值操作", account.getbId(), account.getvName(),Double.parseDouble(params.getBalance()));
				break;
			//消费
			case 2:
				// 微信
				//组装并生成消费订单
				ComsumeRecord crecord = new ComsumeRecord();
				setComsumeValues(params, account, crecord, "wx");
				comsumeRecordMapper.insertSelective(crecord); 
				//调用微信下单接口
				Double dd= Double.parseDouble(params.getBalance()) * 100;
				Map<String,String> commap = wxTradeService.doUnifiedOrder("xf"+crecord.getbId().toString(), String.valueOf(dd.longValue()), crecord.getbId().toString());
				//组装下单结果信息
				setResultInfos(info, crecord.getbId(), commap);
				//插入操作日志
				operationLogService.insertOperationLog(params.getMachineId(), "微信预下单消费操作", account.getbId(), account.getvName(),Double.parseDouble(params.getBalance()));
				
				// 支付宝
				setComsumeValues(params, account, crecord, "zfb");
				comsumeRecordMapper.insertSelective(crecord);
				// 发起下单请求
				AlipayParams alipayParams = new AlipayParams();
				BeanUtils.copyProperties(params, alipayParams);
				alipayParams.setOrderId("xf" + crecord.getbId().toString());
				AlipayTradePrecreateResponse placeOrderResult =  alipayTradePrecreate.placeOrder(alipayParams);
				setResultInfos(info, crecord.getbId(), placeOrderResult);
				//插入操作日志
				operationLogService.insertOperationLog(params.getMachineId(), "支付宝预下单消费操作", account.getbId(), account.getvName(),Double.parseDouble(params.getBalance()));
				break;
			case 3://退款
				info.setResultCode(1);
				info.setResultMsg("系统暂不支持此项业务");
				break;	
			default:
				info.setResultCode(1);
				info.setResultMsg("请传入正确的订单类型");
				break;
			}
		} else {//如果不存在，则返回提示
			info.setResultCode(1);
			info.setResultMsg("账户不存在");
		}
		return msg;
	}
	
	/* (非 Javadoc) 
	 * <p>Title: mobileCallBack</p> 
	 * <p>Description: </p> 
	 * @param params
	 * @return 
	 * @see com.meatball.api.ykt.service.OderService#mobileCallBack(com.meatball.api.ykt.parems.MobileCallBackParams) 
	 */
	@Override
	public ResultMsg mobileCallBack(MobileCallBackParams params) {
		PayResultParams resultParams = new PayResultParams();
		resultParams.setResultCode(1);
		resultParams.setResultMsg("支付超时");
		switch (params.getOrderType()) {
			case 1: // 充值
				// 微信
				int time = 0;
				while (time < 120) {
					RechargeRecord wx = rechargeRecordMapper.selectByPrimaryKey(params.getWxOrder());
					if(wx.getiDealstatus() == 0) {
						resultParams.setNumber(String.valueOf(wx.getbId()));
						resultParams.setOrderType(1);
						resultParams.setPayType(1);
						resultParams.setResultCode(0);
						resultParams.setResultMsg("微信支付成功" +wx.getdBalance() + "元");
						break;
					} else {
						// 支付宝
						RechargeRecord zfb = rechargeRecordMapper.selectByPrimaryKey(params.getZfbOrder());
						if(zfb.getiDealstatus() == 0) {
							resultParams.setNumber(String.valueOf(zfb.getbId()));
							resultParams.setOrderType(1);
							resultParams.setPayType(2);
							resultParams.setResultCode(0);
							resultParams.setResultMsg("支付宝支付成功" +zfb.getdBalance() + "元");
							break;
						}
					}
					try {
						Thread.sleep(1000);
						time++;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				break;
			case 2:	// 消费
				// 微信
				int timexf = 0;
				while (timexf < 120) {
					ComsumeRecord wx = comsumeRecordMapper.selectByPrimaryKey(params.getWxOrder());
					if(wx.getiDealstatus() == 0) {
						resultParams.setNumber(String.valueOf(wx.getbId()));
						resultParams.setOrderType(2);
						resultParams.setPayType(1);
						resultParams.setResultCode(0);
						resultParams.setResultMsg("微信支付成功" +wx.getdBalance() + "元");
						break;
					} else {
						// 支付宝
						ComsumeRecord zfb = comsumeRecordMapper.selectByPrimaryKey(params.getZfbOrder());
						if(zfb.getiDealstatus() == 0) {
							resultParams.setNumber(String.valueOf(zfb.getbId()));
							resultParams.setOrderType(2);
							resultParams.setPayType(2);
							resultParams.setResultCode(0);
							resultParams.setResultMsg("支付宝支付成功" +zfb.getdBalance() + "元");
							break;
						}
					}
					try {
						Thread.sleep(1000);
						timexf++;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				break;
			case 3: // 退款
				resultParams.setResultCode(1);
				resultParams.setResultMsg("此功能暂未开通");
				break;
			default:
				resultParams.setResultCode(1);
				resultParams.setResultMsg("请传入正确的订单类别");
				break;
		}
		return new ResultMsg(200, resultParams);
	}

	/** 
	 * @Title: setRechargeValues 
	 * @Description: TODO(充值记录属性赋值) 
	 * @param params
	 * @param account
	 * @param record
	 * @return void    返回类型 
	 */
	private void setRechargeValues(MobileParams params, Account account, RechargeRecord record, String payWay) {
		record.setbId(new Date().getTime());
		record.setbAccountid(account.getbId());
		record.setvAccountname(account.getvName());
		record.setiDealstatus(9);
		record.setiDealtype(params.getDealType());
		record.settDealtime(new Date());
		record.setvDealname(DealTypeEnum.lookup(params.getDealType()).toString());  
		record.setvMachineid(params.getMachineId());
		record.setvOperator(params.getOperator());
		record.setvOrderid(null);
		
		switch (payWay) {
		case "wx":
			record.setdBalance(Double.parseDouble(params.getBalance()));
			record.setvPayname("微信");
			record.setiPaytype(3);
			break;
		case "zfb":
			record.setdBalance(Double.parseDouble(params.getBalance()));
			record.setvPayname("支付宝");
			record.setiPaytype(4);
			break;
		default:
			throw new RuntimeException("Invalid payment method.(无效的支付方式。)");
		}
		
	}
	
	/** 
	 * @Title: setComsumeValues 
	 * @Description: TODO(消费记录属性赋值) 
	 * @param params
	 * @param account
	 * @param crecord
	 * @return void    返回类型 
	 */
	private void setComsumeValues(MobileParams params, Account account, ComsumeRecord crecord, String payWay) {
		crecord.setbId(new Date().getTime());
		crecord.setbAccountid(account.getbId());
		crecord.setvAccountname(account.getvName());
		crecord.setiDealstatus(9);
		crecord.setiDealtype(params.getDealType());
		crecord.settDealtime(new Date());
		crecord.setvDealname(DealTypeEnum.lookup(params.getDealType()).toString());  
		crecord.setvMachineid(params.getMachineId());
		crecord.setvOperator(params.getOperator());
		crecord.setvPaymentid(null);
		switch (payWay) {
		case "wx":
			crecord.setdBalance(Double.parseDouble(params.getBalance()));
			crecord.setvPayname("微信");
			crecord.setiPaytype(3);
			break;
		case "zfb":
			crecord.setdBalance(Double.parseDouble(params.getBalance()));
			crecord.setvPayname("支付宝");
			crecord.setiPaytype(4);
			break;
		default:
			throw new RuntimeException("Invalid payment method.(无效的支付方式。)");
		}
	}
	
	/** 
	 * @Title: setResultInfos 
	 * @Description: TODO(组装下单结果信息) 
	 * @param info
	 * @param id
	 * @param commap
	 * @return void    返回类型 
	 */
	private void setResultInfos(PayOrderParams info, Long id, Map<String, String> commap) {
		//预下单成功
		if("SUCCESS".equals(commap.get("return_code"))) {
			info.setResultCode(0);
			info.setResultMsg("移动支付预下单成功");
			info.setWxCode(commap.get("code_url"));
			info.setWxOrder(id.toString());
		} else {
		//预下单失败
			info.setResultCode(1);
			info.setResultMsg(commap.get("return_msg"));
		}
	}
	
	private void setResultInfos(PayOrderParams info, Long id,  AlipayTradePrecreateResponse alipayResponse) {
		//预下单成功
		if(alipayResponse.getCode().equals("10000")) {
			info.setZfbCode(alipayResponse.getQrCode());
			info.setZfbOrder(id.toString());
		} else {
		//预下单失败
			info.setResultCode(1);
			info.setResultMsg(alipayResponse.getSubMsg());
		}
	}

}
