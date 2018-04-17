/**
 * Project Name:meatball-rest
 * File Name:OrderApiForWeixinServiceImpl.java
 * Package Name:com.meatball.api.ykt.service.impl
 * Date:2018年3月19日上午9:56:42
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.api.ykt.service.impl;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.meatball.api.ykt.dao.AccountMapper;
import com.meatball.api.ykt.dao.ComsumeRecordMapper;
import com.meatball.api.ykt.dao.PayCallbackRecordMapper;
import com.meatball.api.ykt.dao.RechargeRecordMapper;
import com.meatball.api.ykt.enums.DealTypeEnum;
import com.meatball.api.ykt.model.Account;
import com.meatball.api.ykt.model.ComsumeRecord;
import com.meatball.api.ykt.model.PayCallbackRecord;
import com.meatball.api.ykt.model.RechargeRecord;
import com.meatball.api.ykt.parems.PayOrderParams;
import com.meatball.api.ykt.parems.PayResultParams;
import com.meatball.api.ykt.parems.WxCallBackParams;
import com.meatball.api.ykt.parems.WxOrderParams;
import com.meatball.api.ykt.service.OperationLogService;
import com.meatball.api.ykt.service.OrderApiForWeixinService;
import com.meatball.utils.ArithUtil;
import com.meatball.utils.pay.weixin.WXPayUtil;
import com.meatball.utils.pay.weixin.WXTradeService;
import com.meatball.vo.ResultMsg;

/**   
 * @Title: OrderApiForWeixinServiceImpl.java 
 * @Package com.meatball.api.ykt.service.impl 
 * @Description: TODO(微信下单接口实现类) 
 * @author jw 
 * @date 2018年3月19日 上午9:56:42 
 * @version V1.0   
 */
@Service
public class OrderApiForWeixinServiceImpl implements OrderApiForWeixinService {

	@Resource
	private AccountMapper accountMapper;
	
	@Resource
	private RechargeRecordMapper rechargeRecordMapper;
	
	@Resource
	private ComsumeRecordMapper comsumeRecordMapper;
	
	@Resource
	private WXTradeService wxTradeService;
	
	@Resource
	private OperationLogService operationLogService;
	
	@Resource
	private PayCallbackRecordMapper payCallbackRecordMapper;
	
	/**
	 * 微信下单支付操作
	 */
	@Override
	public ResultMsg getWeixinOrderResult(WxOrderParams params) {
		PayOrderParams info = new PayOrderParams();
		ResultMsg msg = new ResultMsg(200, info);
		//查询用户是否存在
		Account account = accountMapper.selectByPrimaryKey(params.getUserId());
		if(null != account) {//如果存在，则组装微信下单信息
			switch (params.getOrderType()) {//判断属于哪种订单
			case 1://充值
				//组装并生成充值订单
				RechargeRecord record = new RechargeRecord();
				setRechargeValues(params, account, record);
				rechargeRecordMapper.insertSelective(record); 
				//调用微信下单接口
				Map<String,String> map = wxTradeService.doUnifiedOrder("cz"+record.getbId().toString(), params.getBalance(), record.getbId().toString());
				System.out.println("返回信息===="+map);
				//组装下单结果信息
				setResultInfos(info, record.getbId(), map);
				//插入操作日志
				operationLogService.insertOperationLog(params.getMachineId(), "微信预下单充值操作", account.getbId(), account.getvName(), ArithUtil.div(Double.parseDouble(params.getBalance()), 100));
				break;
			case 2://消费
				//组装并生成消费订单
				ComsumeRecord crecord = new ComsumeRecord();
				setComsumeValues(params, account, crecord);
				comsumeRecordMapper.insertSelective(crecord); 
				//调用微信下单接口
				Map<String,String> commap = wxTradeService.doUnifiedOrder("xf"+crecord.getbId().toString(), params.getBalance(), crecord.getbId().toString());
				//组装下单结果信息
				setResultInfos(info, crecord.getbId(), commap);
				//插入操作日志
				operationLogService.insertOperationLog(params.getMachineId(), "微信预下单消费操作", account.getbId(), account.getvName(),ArithUtil.div(Double.parseDouble(params.getBalance()), 100));
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
			info.setResultMsg("微信预下单成功");
			info.setWxCode(commap.get("code_url"));
			info.setWxOrder(id.toString());
		} else {
		//预下单失败
			info.setResultCode(1);
			info.setResultMsg(commap.get("return_msg"));
		}
	}

	/** 
	 * @Title: setRechargeValues 
	 * @Description: TODO(充值记录属性赋值) 
	 * @param params
	 * @param account
	 * @param record
	 * @return void    返回类型 
	 */
	private void setRechargeValues(WxOrderParams params, Account account, RechargeRecord record) {
		record.setbAccountid(account.getbId());
		record.setvAccountname(account.getvName());
		record.setdBalance(Double.parseDouble(params.getBalance()));
		record.setiDealstatus(9);
		record.setiDealtype(params.getDealType());
		record.settDealtime(new Date());
		record.setvDealname(DealTypeEnum.lookup(params.getDealType()).toString());  
		record.setvMachineid(params.getMachineId());
		record.setvOperator(params.getOperator());
		record.setvOrderid(null);
		record.setvPayname("微信");
		record.setiPaytype(3);
	}

	/** 
	 * @Title: setComsumeValues 
	 * @Description: TODO(消费记录属性赋值) 
	 * @param params
	 * @param account
	 * @param crecord
	 * @return void    返回类型 
	 */
	private void setComsumeValues(WxOrderParams params, Account account, ComsumeRecord crecord) {
		crecord.setbAccountid(account.getbId());
		crecord.setvAccountname(account.getvName());
		crecord.setdBalance((Double.parseDouble(params.getBalance())));
		crecord.setiDealstatus(9);
		crecord.setiDealtype(params.getDealType());
		crecord.settDealtime(new Date());
		crecord.setvDealname(DealTypeEnum.lookup(params.getDealType()).toString());  
		crecord.setvMachineid(params.getMachineId());
		crecord.setvOperator(params.getOperator());
		crecord.setvPaymentid(null);
		crecord.setvPayname("微信");
		crecord.setiPaytype(3);
	}

	/**
	 * 微信下单回调结果
	 */
	@Override
	public ResultMsg getWeixinOrderBackResult(WxCallBackParams params) {
		PayResultParams info = new PayResultParams();
		ResultMsg msg = new ResultMsg(200, info);
		//判断订单类型
		switch (params.getOrderType()) {
		case 1://充值
			RechargeRecord record = rechargeRecordMapper.selectSuccessRechargeRecord(Long.parseLong(params.getNumber()));
			if(null != record ) {
				//账户余额操作
				Account account = accountMapper.selectByPrimaryKey(record.getbAccountid());
				account.setdBalance(account.getdBalance()+record.getdBalance());
				accountMapper.updateByPrimaryKeySelective(account);
				setInfoMsg(info, 0, "微信成功支付"+record.getdBalance()+"元",params.getOrderType(),params.getNumber());
			} else {
				setInfoMsg(info, 1, "无此订单",params.getOrderType(),params.getNumber());
			}
			break;
		case 2://消费
			ComsumeRecord crecord = comsumeRecordMapper.selectSuccessComsumeRecord(Long.parseLong(params.getNumber()));
			if(null != crecord) {
				setInfoMsg(info, 0, "微信成功支付"+crecord.getdBalance()+"元",params.getOrderType(),params.getNumber());
			} else {
				setInfoMsg(info, 1, "无此订单",params.getOrderType(),params.getNumber());
			}
			break;
		case 3://退款
			setInfoMsg(info, 1, "系统暂不支持此项业务",params.getOrderType(),params.getNumber());
			break;	
		default:
			setInfoMsg(info, 1, "请传入正确的订单类型",params.getOrderType(),params.getNumber());
			break;
		} 
		return msg;
	}
	
	/**
	 * @Title: setInfoMsg 
	 * @Description: TODO(组装微信回调信息) 
	 * @param info
	 * @param m
	 * @param str
	 * @param orderType 
	 * @param number 
	 * @return void    返回类型
	 */
	private void setInfoMsg(PayResultParams info, int m, String str, int orderType, String number) {
		info.setResultCode(m);
		info.setResultMsg(str);
		info.setOrderType(orderType);
		info.setNumber(number);
	}

	/**
	 * 微信回调结果操作
	 * @throws Exception 
	 */
	@Override
	public void getWxBackMsg(HttpServletRequest request, HttpServletResponse resposnse) throws Exception {
		//解析转换微信回调返回信息 
		InputStream inputStream;  
        StringBuffer strXML = new StringBuffer();  
        inputStream = request.getInputStream();  
        String s;  
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));  
        while ((s = in.readLine()) != null) {  
        	strXML.append(s);  
        }  
        in.close();  
        inputStream.close();  
        Map<String, String> map = WXPayUtil.xmlToMap(strXML.toString());
        
        String resXml = "";// 反馈给微信服务器 
        /*1、如果支付成功，则取出订单数据，修改订单状态*/
  		if("SUCCESS".equals(map.get("return_code")) ) {
  			String type = map.get("out_trade_no").substring(0, 2);
  			String orderNumber = map.get("out_trade_no").substring(2, map.get("out_trade_no").length());
  			PayCallbackRecord callbackRecord = new PayCallbackRecord(); 
  			if(type.equals("cz")) {/*2、充值订单,修改订单状态和微信交易流水号*/
  				RechargeRecord record = rechargeRecordMapper.selectByPrimaryKey(Long.parseLong(orderNumber));
  				record.getdBalance();
  				record.setvOrderid(map.get("transaction_id"));
  				record.setiDealstatus(0);
  				rechargeRecordMapper.updateByPrimaryKeySelective(record);
  				/*3、插入支付平台回调记录*/
  				setPayCallbackRecordValues(callbackRecord, map, orderNumber, record.getbAccountid(), record.getiDealtype(),
  						record.gettDealtime(), record.getvAccountname(), record.getvDealname());
  				payCallbackRecordMapper.insertSelective(callbackRecord);
  			} else if (type.equals("xf")) {/*2、消费订单,修改订单状态和微信交易流水号*/
  				ComsumeRecord crecord = comsumeRecordMapper.selectByPrimaryKey(Long.parseLong(orderNumber));
  				crecord.setiDealstatus(0);
  				crecord.setvPaymentid(map.get("transaction_id"));
  				comsumeRecordMapper.updateByPrimaryKeySelective(crecord);
  				/*3、插入支付平台回调记录*/
  				setPayCallbackRecordValues(callbackRecord, map, orderNumber, crecord.getbAccountid(), crecord.getiDealtype(), 
  						crecord.gettDealtime(),  crecord.getvAccountname(), crecord.getvDealname());
  				payCallbackRecordMapper.insertSelective(callbackRecord);
  			}
  			/*4、通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.  */
            resXml = "<xml><return_code><![CDATA[SUCCESS]]></return_code>"  
                    + "<return_msg><![CDATA[OK]]></return_msg></xml> ";  
  		} else {
  			resXml = "<xml><return_code><![CDATA[FAIL]]></return_code>"  
                    + "<return_msg><![CDATA[报文为空]]></return_msg></xml> "; 
  		}
		 BufferedOutputStream out = new BufferedOutputStream(resposnse.getOutputStream());  
	     out.write(resXml.getBytes());  
	     out.flush();  
	     out.close(); 
	    /* String resXML= "<xml><appid><![CDATA[wx9852a1dc394adc7d]]></appid><bank_type><![CDATA[CFT]]></bank_type><cash_fee>"
					+ "<![CDATA[12]]></cash_fee><device_info><![CDATA[WEB]]></device_info><fee_type><![CDATA[CNY]]></fee_type>"
					+ "<is_subscribe><![CDATA[Y]]></is_subscribe><mch_id><![CDATA[1252626401]]></mch_id>"
					+ "<nonce_str><![CDATA[859e8b4c3b2a47c485f87d4af438a035]]></nonce_str><openid><![CDATA[ouuPRvoZoXXnAxbOhw0Bawvv9enM]]></openid>"
					+ "<out_trade_no><![CDATA[cz2811111]]></out_trade_no><result_code><![CDATA[SUCCESS]]></result_code>"
					+ "<return_code><![CDATA[SUCCESS]]></return_code><sign><![CDATA[1463B327908A54A0AD9B87CAF7DD22FBAA9695BA548FE1BA25DE7752094A4A91]]></sign>"
					+ "<time_end><![CDATA[20180323121144]]></time_end><total_fee>12</total_fee><trade_type><![CDATA[NATIVE]]></trade_type>"
					+ "<transaction_id><![CDATA[4200000052201803233903585941]]></transaction_id></xml>";*/
		 
	}
	
	/**
	 * @Title: setPayCallbackRecordValues 
	 * @Description: TODO(支付平台回调记录赋值) 
	 * @param callbackRecord
	 * @param map
	 * @param orderNumber
	 * @param accountid
	 * @param iDealtype2
	 * @param tOrdertime
	 * @param vAccountname
	 * @param vDealname
	 * @return void    返回类型
	 */
	private void setPayCallbackRecordValues(PayCallbackRecord callbackRecord,Map<String, String> map,String orderNumber,Long accountid,
				int iDealtype2,Date tOrdertime,String vAccountname,String vDealname) {
			callbackRecord.setbAccountid(accountid);
			callbackRecord.setdBalance(Double.parseDouble(map.get("total_fee"))*100);
			callbackRecord.setiDealtype2(iDealtype2);
			callbackRecord.settOrdertime(tOrdertime);
			callbackRecord.settPaytime(new Date());
			callbackRecord.settTime(new Date());
			callbackRecord.setvAccountname(vAccountname);
			callbackRecord.setvDealname(vDealname);
			callbackRecord.setvDealno(map.get("transaction_id"));
			callbackRecord.setvMerchantid(map.get("mch_id"));
			callbackRecord.setvOrderid(orderNumber);
			callbackRecord.setvPayplatform("微信");
			callbackRecord.setvResult("成功");
	}
		 

}
