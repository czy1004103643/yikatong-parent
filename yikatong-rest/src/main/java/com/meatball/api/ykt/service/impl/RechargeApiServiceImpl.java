/**
 * Project Name:meatball-rest
 * File Name:RechargeApiServiceImpl.java
 * Package Name:com.meatball.api.ykt.service.impl
 * Date:2018年3月16日上午10:26:10
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.api.ykt.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.meatball.utils.HttpClient;
import groovy.util.logging.Log4j2;
import org.springframework.stereotype.Service;

import com.meatball.api.ykt.dao.AccountMapper;
import com.meatball.api.ykt.dao.ComsumeRecordMapper;
import com.meatball.api.ykt.dao.RechargeRecordMapper;
import com.meatball.api.ykt.dao.RefundRecordMapper;
import com.meatball.api.ykt.enums.DealTypeEnum;
import com.meatball.api.ykt.model.Account;
import com.meatball.api.ykt.model.ComsumeRecord;
import com.meatball.api.ykt.model.RechargeRecord;
import com.meatball.api.ykt.model.RefundRecord;
import com.meatball.api.ykt.parems.AccountCreateParams;
import com.meatball.api.ykt.parems.AccountInfoParams;
import com.meatball.api.ykt.parems.AccountModifyParams;
import com.meatball.api.ykt.parems.AmountInfoParams;
import com.meatball.api.ykt.parems.CancelAmountInfoParams;
import com.meatball.api.ykt.parems.CancelAmountParams;
import com.meatball.api.ykt.parems.ComsumeAmountParams;
import com.meatball.api.ykt.parems.MobileParams;
import com.meatball.api.ykt.parems.PayOrderParams;
import com.meatball.api.ykt.parems.RechargeAmountParams;
import com.meatball.api.ykt.parems.RefundAmountInfoParams;
import com.meatball.api.ykt.parems.RefundAmountParams;
import com.meatball.api.ykt.service.OrderService;
import com.meatball.api.ykt.service.OperationLogService;
import com.meatball.api.ykt.service.RechargeApiService;
import com.meatball.component.ErrorLog;
import com.meatball.utils.ArithUtil;
import com.meatball.utils.DateUtil;
import com.meatball.vo.ResultMsg;

/**   
 * @Title: RechargeApiServiceImpl.java 
 * @Package com.meatball.api.ykt.service.impl 
 * @Description: TODO(充值类接口实现类) 
 * @author jw 
 * @date 2018年3月16日 上午10:26:10 
 * @version V1.0   
 */
@Service
public class RechargeApiServiceImpl implements RechargeApiService {
    /**
     * @Title: setRchargeReturnInfo
     * @Description: TODO(组装充值返回信息)
     * @param params
     * @param account
     * @param info
     * @param msg
     * @param wxCode
     * @param wxOrder
     * @param zfbCode
     * @param zfbOrder
     * @return void    返回类型
     */
    private void setRchargeReturnInfo(RechargeAmountParams params, Account account, AmountInfoParams info, String msg, String wxCode, String wxOrder, String zfbCode, String zfbOrder, RechargeRecord bankRecord) {
        info.setBalance(String.valueOf(account.getdBalance()));
        info.setCreateDate(DateUtil.getTime(new Date()));
        info.setName(account.getvName());
        info.setNumber(params.getNumber());
        info.setType(params.getType());
        info.setResultCode(0);
        info.setResultMsg(msg);
        info.setWxCode(wxCode);
        info.setWxOrder(wxOrder);
        info.setZfbCode(zfbCode);
        info.setZfbOrder(zfbOrder);
        info.setBalanceOrder(bankRecord.getbId());
    }


	@Resource
	private AccountMapper accountMapper;

	@Resource
	private RechargeRecordMapper rechargeRecordMapper;

	@Resource
	private OperationLogService operationLogService;

	@Resource
	private ComsumeRecordMapper comsumeRecordMapper;

	@Resource
	private RefundRecordMapper refundRecordMapper;

	@Resource
	private OrderService oderService;

	/**
	 * 系统建卡操作
	 */
	@Override
	@ErrorLog("系统建卡操作")
	public ResultMsg getCreateCardResult(AccountCreateParams params) throws Exception {
		Account account = null;
		//身份证号
		String idcard = null;
		//就诊卡号
		String cardNumber = null;
		//社保卡号
		String sinCard = null;
		//居民健康卡号
		String healthCard = null;
		AccountInfoParams infoParams = new AccountInfoParams();
		ResultMsg msg = new ResultMsg(200, infoParams);
		/*1、先查询账户是否存在*/
		switch (params.getType()) {
		case 1://身份证号
			idcard = params.getNumber();
			account = accountMapper.selectAccountByIdcard(idcard);
			createCard(params, account, idcard, cardNumber, sinCard, healthCard, infoParams);
			break;
		case 2://就诊卡号
			cardNumber = params.getNumber();
			account = accountMapper.selectAccountByPatientidcard(cardNumber);
			createCard(params, account, idcard, cardNumber, sinCard, healthCard, infoParams);
			break;
		case 3://社保卡号
			sinCard = params.getNumber();
			account = accountMapper.selectAccountBySincard(sinCard);
			createCard(params, account, idcard, cardNumber, sinCard, healthCard, infoParams);
			break;
		case 4://居民健康卡号
			healthCard = params.getNumber();
			account = accountMapper.selectAccountByHealthcard(healthCard);
			createCard(params, account, idcard, cardNumber, sinCard, healthCard, infoParams);
			break;
		default:
			infoParams.setResultCode(1);
			infoParams.setResultMsg("请传入正确的卡类别");
			break;
		}

		return msg;
	}

	/**
	 * 充值操作
	 */
	@Override
	@ErrorLog("充值操作")
	public ResultMsg getRechargeResult(RechargeAmountParams params) {
		Account account = null;
		//身份证号
		String idcard = null;
		//就诊卡号
		String cardNumber = null;
		//社保卡号
		String sinCard = null;
		//居民健康卡号
		String healthCard = null;
		AmountInfoParams info = new AmountInfoParams();
		ResultMsg msg = new ResultMsg(200, info);
		/*1、先查询账户是否存在，如果存在则查询账户信息*/
		switch (params.getType()) {
		case 1://身份证号
			idcard = params.getNumber();
			account = accountMapper.selectAccountByIdcard(idcard);
			break;
		case 2://就诊卡号
			cardNumber = params.getNumber();
			account = accountMapper.selectAccountByPatientidcard(cardNumber);
			break;
		case 3://社保卡号
			sinCard = params.getNumber();
			account = accountMapper.selectAccountBySincard(sinCard);
			break;
		case 4://居民健康卡号
			healthCard = params.getNumber();
			account = accountMapper.selectAccountByHealthcard(healthCard);
			break;
		default:
			info.setResultCode(1);
			info.setResultMsg("请传入正确的卡类别");
			break;
		}

		if(null != account) {
			/*2、判断哪种支付方式，并掉用其充值方法*/
			switch (params.getPayType()) {
			case 1://现金支付
				recharge(params, account, info, "现金");
				break;
			case 2://银行卡支付
				recharge(params, account, info, "银行卡");
				break;
			case 3://移动支付
				//组装移动支付方法传入的参数
				MobileParams mobileParams= new MobileParams();
				setMoblieParams(mobileParams,account.getbId(), 1,params.getMachineId(),params.getOperator(),params.getBalance(),params.getDealType());
				//调用移动支付
				ResultMsg rinfo =  oderService.mobileOrder(mobileParams);
				if(null != rinfo && rinfo.getCode() == 200 && null != (PayOrderParams) rinfo.getData()) {
					PayOrderParams por =  (PayOrderParams) rinfo.getData();
					/*3、生成返回结果信息*/
					setRchargeReturnInfo(params, account, info,"移动支付成功预下单",por.getWxCode(),por.getWxOrder(),por.getZfbCode(),por.getZfbOrder(), null);
					//插入操作日志
					operationLogService.insertOperationLog(params.getMachineId(), "移动支付充值操作", account.getbId(),
							account.getvName(), Double.parseDouble(params.getBalance()));
				} else {
					info.setResultCode(1);
					info.setResultMsg("移动支付故障");
				}
				break;
            case 4: // 医佳云
                recharge(params, account, info, "医佳云");
                break;
			default:
				info.setResultCode(1);
				info.setResultMsg("请传入正确的支付方式");
				break;
			}

		} else {
			//账户不存在直接返回结果
			info.setResultCode(1);
			info.setResultMsg("账户不存在");
		}
		return msg;
	}

	/** 
	 * @Title: recharge 
	 * @Description: TODO(充值流程) 
	 * @param params
	 * @param account
	 * @param info
	 * @param aa
	 * @return void    返回类型 
	 */
	private void recharge(RechargeAmountParams params, Account account, AmountInfoParams info, String aa) {
		//修改账户余额
		account.setdBalance(ArithUtil.add(account.getdBalance(), Double.parseDouble(params.getBalance())));
		accountMapper.updateByPrimaryKeySelective(account);
		//插入充值记录信息
		RechargeRecord bankRecord = new RechargeRecord();
		setRechargeRecordValues(params.getPayType(), aa, params, account, bankRecord);
		rechargeRecordMapper.insertSelective(bankRecord);
		//组装返回信息
		setRchargeReturnInfo(params, account, info,"充值成功"+params.getBalance()+"元",null,null,null,null, bankRecord);
		//插入操作日志
		operationLogService.insertOperationLog(params.getMachineId(), aa+"充值操作", account.getbId(),
				account.getvName(), Double.parseDouble(params.getBalance()));
	}

	/**
	 * 消费操作
	 */
	@Override
	@ErrorLog("消费操作")
	public ResultMsg getComsumeResult(ComsumeAmountParams params) {
		Account account = null;
		//身份证号
		String idcard = null;
		//就诊卡号
		String cardNumber = null;
		//社保卡号
		String sinCard = null;
		//居民健康卡号
		String healthCard = null;
		AmountInfoParams info = new AmountInfoParams();
		ResultMsg msg = new ResultMsg(200, info);
		/*1、先查询账户是否存在，如果存在则查询账户信息*/
		switch (params.getType()) {
		case 1://身份证号
			idcard = params.getNumber();
			account = accountMapper.selectAccountByIdcard(idcard);
			break;
		case 2://就诊卡号
			cardNumber = params.getNumber();
			account = accountMapper.selectAccountByPatientidcard(cardNumber);
			break;
		case 3://社保卡号
			sinCard = params.getNumber();
			account = accountMapper.selectAccountBySincard(sinCard);
			break;
		case 4://居民健康卡号
			healthCard = params.getNumber();
			account = accountMapper.selectAccountByHealthcard(healthCard);
			break;
		default:
			info.setResultCode(1);
			info.setResultMsg("请传入正确的卡类别");
			break;
		}
		if(null != account) {
			/*2、判断哪种支付方式，并使用其充值方法*/
			switch (params.getPayType()) {
			case 1://现金支付
				comsume(params, account, "现金", info);
				break;
			case 2://银行卡支付
				comsume(params, account, "银行卡", info); 
				break;
			case 3://移动支付
				//组装移动支付方法传入的参数
				MobileParams mobileParams= new MobileParams();
				setMoblieParams(mobileParams,account.getbId(), 2,params.getMachineId(),params.getOperator(),params.getBalance(),params.getDealType());
				//调用移动支付
				ResultMsg rinfo =  oderService.mobileOrder(mobileParams);
				if(null != rinfo && rinfo.getCode() == 200 && null != (PayOrderParams) rinfo.getData()) {
					PayOrderParams por =  (PayOrderParams) rinfo.getData();
					/*3、生成返回结果信息*/
					returnInfo(params, account, info,"移动支付成功预下单",por.getWxCode(),por.getWxOrder(),por.getZfbCode(),por.getZfbOrder());
					//插入操作日志
					operationLogService.insertOperationLog(params.getMachineId(), "移动支付消费操作", account.getbId(),
							account.getvName(), Double.parseDouble(params.getBalance()));
				} else {
					info.setResultCode(1);
					info.setResultMsg("移动支付故障");
				}
				
				break;
			case 4://余额支付
				double num = ArithUtil.sub(account.getdBalance(), Double.parseDouble(params.getBalance()));
				//判断账户余额是否满足消费金额条件，如果满足则修改账户余额
				if(num >= 0) {
					account.setdBalance(num);
					accountMapper.updateByPrimaryKeySelective(account);
					//插入消费记录信息
					ComsumeRecord yueRecord = new ComsumeRecord(); 
					setComsumeRecordValues(5,"余额",params, account, yueRecord);
					yueRecord.setbId(new Date().getTime());
					comsumeRecordMapper.insertSelective(yueRecord);
					info.setBalanceOrder(yueRecord.getbId());
				} else {
				//如果不满足则返回提示信息
					info.setResultCode(1);
					info.setResultMsg("账户余额不足");
					return msg;
				}
				returnInfo(params, account, info,"消费成功"+params.getBalance()+"元",null,null,null,null);
				break;
			default:
				info.setResultCode(1);
				info.setResultMsg("请传入正确的支付方式");
				break;
			}
			
		} else {
			//账户不存在直接返回结果
			info.setResultCode(1);
			info.setResultMsg("账户不存在");
		}
		return msg;
	}

	/** 
	 * @Title: comsume 
	 * @Description: TODO(消费流程) 
	 * @param params
	 * @param account
	 * @param aa
	 * @return void    返回类型 
	 */
	private void comsume(ComsumeAmountParams params, Account account, String aa, AmountInfoParams info) {
		//插入消费记录信息
		ComsumeRecord bankRecord = new ComsumeRecord(); 
		setComsumeRecordValues(params.getPayType(),aa,params, account, bankRecord);
		bankRecord.setbId(new Date().getTime());
		comsumeRecordMapper.insertSelective(bankRecord);
		//生成返回信息
		returnInfo(params, account, info, "消费成功"+params.getBalance()+"元", null,null,null,null);
	}

	/** 
	 * @Title: returnInfo 
	 * @Description: TODO(组装消费返回信息) 
	 * @param params
	 * @param account
	 * @param info
	 * @param msg 
	 * @param wxCode 
	 * @param wxOrder 
	 * @param zfbCode 
	 * @param zfbOrder 
	 * @return void    返回类型 
	 */
	private void returnInfo(ComsumeAmountParams params, Account account, AmountInfoParams info, String msg, String wxCode, String wxOrder, String zfbCode, String zfbOrder) {
		/*3、生成返回结果信息*/
		info.setBalance(String.valueOf(account.getdBalance()));
		info.setCreateDate(DateUtil.getTime(new Date()));
		info.setName(account.getvName());
		info.setNumber(params.getNumber());
		info.setType(params.getType());
		info.setResultCode(0);
		info.setResultMsg(msg);
		info.setWxCode(wxCode);
		info.setWxOrder(wxOrder);
		info.setZfbCode(zfbCode);
		info.setZfbOrder(zfbOrder);

		//插入操作日志
		operationLogService.insertOperationLog(params.getMachineId(), "消费操作", account.getbId(),
				account.getvName(), Double.parseDouble(params.getBalance()));
	}

	/**
	 * 退款操作
	 */
	@Override
	@ErrorLog("退款操作")
	public ResultMsg getRefundResult(RefundAmountParams params) {
		Account account = null;
		//身份证号
		String idcard = null;
		//就诊卡号
		String cardNumber = null;
		//社保卡号
		String sinCard = null;
		//居民健康卡号
		String healthCard = null;
		RefundAmountInfoParams info = new RefundAmountInfoParams();
		ResultMsg msg = new ResultMsg(200, info);
		/*1、先查询账户是否存在，如果存在则查询账户信息*/ 
		switch (params.getType()) {
		case 1://身份证号
			idcard = params.getNumber();
			account = accountMapper.selectAccountByIdcard(idcard);
			break;
		case 2://就诊卡号
			cardNumber = params.getNumber();
			account = accountMapper.selectAccountByPatientidcard(cardNumber);
			break;
		case 3://社保卡号
			sinCard = params.getNumber();
			account = accountMapper.selectAccountBySincard(sinCard);
			break;
		case 4://居民健康卡号
			healthCard = params.getNumber();
			account = accountMapper.selectAccountByHealthcard(healthCard);
			break;
		default:
			info.setResultCode(1);
			info.setResultMsg("请传入正确的卡类别");
			break;
		}
		
		if(null != account) {
			double num = ArithUtil.sub(account.getdBalance(), Double.parseDouble(params.getBalance()));
			/*2、判断账户余额是否满足退款金额条件，如果满足则修改账户余额*/
			if(num >= 0) {
				account.setdBalance(num);
				accountMapper.updateByPrimaryKeySelective(account);
				//插入退款记录信息
				RefundRecord record = new RefundRecord(); 
				setRefundRecordValues(5,"余额",params, account, record);
				refundRecordMapper.insertSelective(record); 
			} else {
			//如果不满足则返回提示信息
				info.setResultCode(1);
				info.setResultMsg("账户余额不足");
				return msg;
			}
			 
			/*3、生成返回结果信息*/
			info.setBalance(String.valueOf(account.getdBalance()));
			info.setCreateDate(DateUtil.getTime(new Date()));
			info.setName(account.getvName());
			info.setNumber(params.getNumber());
			info.setType(params.getType());
			info.setResultCode(0);
			info.setResultMsg("退款成功"+params.getBalance()+"元");
			//插入操作日志
			operationLogService.insertOperationLog(params.getMachineId(), "退款操作", account.getbId(),
					account.getvName(), Double.parseDouble(params.getBalance()));
		} else {
			//账户不存在直接返回结果
			info.setResultCode(1);
			info.setResultMsg("账户不存在");
		}
		return msg;
	}
	
	/**
	 * 撤销操作
	 */
	@Override
	@ErrorLog("撤销操作")
	public ResultMsg getCancelResult(CancelAmountParams params) {
		Account account = null;
		//身份证号
		String idcard = null;
		//就诊卡号
		String cardNumber = null;
		//社保卡号
		String sinCard = null;
		//居民健康卡号
		String healthCard = null;
		CancelAmountInfoParams info = new CancelAmountInfoParams();
		ResultMsg msg = new ResultMsg(200, info);
		/*1、先查询账户是否存在，如果存在则查询账户信息*/ 
		switch (params.getType()) {
		case 1://身份证号
			idcard = params.getNumber();
			account = accountMapper.selectAccountByIdcard(idcard);
			break;
		case 2://就诊卡号
			cardNumber = params.getNumber();
			account = accountMapper.selectAccountByPatientidcard(cardNumber);
			break;
		case 3://社保卡号
			sinCard = params.getNumber();
			account = accountMapper.selectAccountBySincard(sinCard);
			break;
		case 4://居民健康卡号
			healthCard = params.getNumber();
			account = accountMapper.selectAccountByHealthcard(healthCard);
			break;
		default:
			info.setResultCode(1);
			info.setResultMsg("请传入正确的参数");
			break;
		}
		
		if(null != account) {
			/*2、判断属于哪种撤销方式*/ 
			switch (params.getOrderType()) {
			case 1://充值
				//查询该充值订单，修改其订单状态，
				RechargeRecord rrecord = rechargeRecordMapper.selectSuccessRechargeRecord(Long.parseLong(params.getOrderNumber()));
				//判断订单是否存在,撤销金额和订单金额是否一致,订单账户和撤销账户是否一致
				if(null != rrecord && Double.parseDouble(params.getBalance()) == rrecord.getdBalance() && rrecord.getbAccountid() == account.getbId()) {
					rrecord.setiDealstatus(8);
					rechargeRecordMapper.updateByPrimaryKeySelective(rrecord);
					cancel(params, account, info,params.getOrderType());
				} else {
					info.setResultCode(1);
					info.setResultMsg("订单无效或者撤销金额与订单金额不一致");
				}
				
				break;
			case 2://消费
				//查询该消费订单，修改其订单状态
				ComsumeRecord crecord = comsumeRecordMapper.selectSuccessComsumeRecord(Long.parseLong(params.getOrderNumber()));
				//判断订单是否存在,撤销金额和订单金额是否一致,订单账户和撤销账户是否一致
				if(null != crecord && Double.parseDouble(params.getBalance()) == crecord.getdBalance() && crecord.getbAccountid() == account.getbId()) {
					crecord.setiDealstatus(8);
					comsumeRecordMapper.updateByPrimaryKeySelective(crecord);
					cancel(params, account, info,params.getOrderType());
				} else {
					info.setResultCode(1);
					info.setResultMsg("订单无效或者撤销金额与订单金额不一致");
				}
				break;
			default:
				info.setResultCode(1);
				info.setResultMsg("请传入正确的订单类别");
				break;
			}
			
		} else {
			//账户不存在直接返回结果
			info.setResultCode(1);
			info.setResultMsg("账户不存在");
		}
		return msg;
	}

	/** 
	 * @Title: cancel 
	 * @Description: TODO(撤销流程) 
	 * @param params
	 * @param account
	 * @param info
	 * @return void    返回类型 
	 */
	private void cancel(CancelAmountParams params, Account account, CancelAmountInfoParams info,int type) {
		double count = 0;
		String mm = null;
		if(type == 1) {//充值订单 金额相减
			count = ArithUtil.sub(account.getdBalance(), Double.parseDouble(params.getBalance()));
			mm = "充值订单"+params.getOrderNumber()+"撤销操作";
		} else if (type == 2) {//消费订单 金额相加
			count = ArithUtil.add(account.getdBalance(), Double.parseDouble(params.getBalance()));
			mm = "消费订单"+params.getOrderNumber()+"撤销操作";
		}
		/*3、还原账户余额*/
		account.setdBalance(count);
		accountMapper.updateByPrimaryKeySelective(account);
		/*4、生成返回结果信息*/
		info.setBalance(String.valueOf(account.getdBalance()));
		info.setCreateDate(DateUtil.getTime(new Date()));
		info.setOrderNumber(params.getOrderNumber());
		info.setOrderType(params.getOrderType());
		info.setNumber(params.getNumber());
		info.setType(params.getType());
		info.setResultCode(0);
		info.setResultMsg("撤销成功"+params.getBalance()+"元");
		//插入操作日志
		operationLogService.insertOperationLog(params.getMachineId(), mm, account.getbId(), account.getvName(), Double.parseDouble(params.getBalance()));
	}
 
	/**
	 * 账户修改操作
	 */
	@Override
	@ErrorLog("账户修改操作")
	public ResultMsg getModifyCardResult(AccountModifyParams params) {
		//先查询账户是否存在 
		Account account = accountMapper.selectByPrimaryKey(Long.parseLong(params.getUserId()));
		AccountInfoParams info = new AccountInfoParams();
		ResultMsg msg = new ResultMsg(200, info);
		if(null != account) {//如果存在，则修改信息
			setModAccountValues(params, account);
			accountMapper.updateByPrimaryKeySelective(account);
			//生成返回结果信息
			setReturnAccountValues(account, info);
		} else {//如果不存在，则返回提示
			info.setResultCode(1);
			info.setResultMsg("账户不存在");
		}
		return msg;
	}
	
	/**
	 * @Title: createCard 
	 * @Description: TODO(建卡流程) 
	 * @param params
	 * @param account
	 * @param idcard
	 * @param cardNumber
	 * @param sinCard
	 * @param healthCard
	 * @param infoParams
	 * @return void    返回类型
	 */
	private void createCard(AccountCreateParams params, Account account, String idcard, String cardNumber,
			String sinCard, String healthCard, AccountInfoParams infoParams) throws Exception {
		/*2、账户存在，则修改信息*/
		if(null != account) {
			account.setbId(account.getbId());
			setAddAccountValues(params, account, idcard, cardNumber, sinCard, healthCard);
			accountMapper.updateByPrimaryKeySelective(account);
		
		} else {
		/*3、账户不存在，则新增信息*/ 	
			account = new Account();
			cardNumber = String.valueOf(new Date().getTime()).substring(0, 10);
			setAddAccountValues(params, account, idcard, cardNumber, sinCard, healthCard);
			account.setdBalance((long) 0);
			account.settCreatedate(new Date());
			accountMapper.insertSelective(account);
			this.addUserToHis(params, cardNumber);
		}
		/*4、生成返回结果*/ 	
		setReturnAccountValues(account, infoParams);
	}

	private boolean addUserToHis(AccountCreateParams acc, String cardNo) throws Exception {
		Map<String, Object> params = new HashMap<>();
		params.put("api_id", "40001");
		params.put("card_no", cardNo);
		params.put("patient_name", acc.getName());
		params.put("operator_id", "YD_WX");
		params.put("birthday", DateUtil.getDay(acc.getBirthday()));
		params.put("sex", "0");
		params.put("age", "0");
		params.put("id_card_no", acc.getNumber());
		params.put("address", acc.getAddress());
		params.put("tel", acc.getTel());
		params.put("date", DateUtil.getDay());
		params.put("time", "06:27:07");
		params.put("sn", "0");
		System.out.println(JSON.toJSONString(params));
		Map<String, String> result = (Map<String, String>) JSON.parseObject(HttpClient.sendPost("http://mihp.nc120.cn/PlatformService/platform/api", "params=" + JSON.toJSONString(params))).get("result");
		if(result.get("error").equals("0")) {
			return true;
		} else {
			throw new Exception(result.get("message"));
		}
	}
	
	
	/**
	 * @Title: setReturnAccountValues 
	 * @Description: TODO(返回账户信息属性赋值) 
	 * @param account
	 * @param infoParams
	 * @return void    返回类型
	 */
	private void setReturnAccountValues(Account account, AccountInfoParams infoParams) {
		infoParams.setName(account.getvName());
		infoParams.setIdcard(account.getvIdcard());
		infoParams.setCardNumber(account.getvPatientidcard());
		infoParams.setGender(account.getiGender());
		infoParams.setTel(account.getvTel());
		infoParams.setAddress(account.getvAddress());
		infoParams.setBirthday(account.getdBirthday() == null ? DateUtil.getDay(new Date()) :  DateUtil.getDay(account.getdBirthday()));
		infoParams.setSinCard(account.getvSincard() );
		infoParams.setHealthCard(account.getvHealthcard());
		infoParams.setBalance(String.valueOf(account.getdBalance()));
		infoParams.setCreateDate(account.gettCreatedate()== null ? DateUtil.getTime(new Date()) :  DateUtil.getTime(account.gettCreatedate()));
		infoParams.setResultCode(0);
		infoParams.setResultMsg("操作成功");
	}

	/**
	 * @Title: setAccountValues 
	 * @Description: TODO(新建账户属性赋值) 
	 * @param params
	 * @param account
	 * @param idcard
	 * @param cardNumber
	 * @param sinCard
	 * @param healthCard
	 * @return void    返回类型
	 */
	private void setAddAccountValues(AccountCreateParams params, Account account, String idcard, String cardNumber,
			String sinCard, String healthCard) {
		account.setvName(params.getName());
		account.setvIdcard(idcard);
		account.setvPatientidcard(cardNumber);
		account.setvSincard(sinCard);
		account.setvHealthcard(healthCard);
		account.setiGender(params.getGender());
		account.setvTel(params.getTel());
		account.setvAddress(params.getAddress());
		account.setdBirthday(params.getBirthday() == null  ? new Date() : params.getBirthday());
	}
	
	/**
	 * @Title: setModAccountValues 
	 * @Description: TODO(修改账户属性赋值) 
	 * @param params
	 * @param account
	 * @return void    返回类型
	 */
	private void setModAccountValues(AccountModifyParams params, Account account){
		account.setdBirthday(params.getBirthday());
		account.setiGender(params.getGender());
		account.setvAddress(params.getAddress());
		account.setvFingerprint(params.getVFingerprint());
		account.setvHealthcard(params.getHealthCard());
		account.setvIdcard(params.getIdcard());
		account.setvName(params.getName());
		account.setvPatientidcard(params.getCardNumber());
		account.setvPaymentcode(params.getVPaymentcode());
		account.setvSincard(params.getSinCard());
		account.setvTel(params.getTel());
	}
	
	
	/**
	 * @Title: setRechargeRecordValues 
	 * @Description: TODO(充值记录属性赋值) 
	 * @param type
	 * @param typename
	 * @param params
	 * @param account
	 * @param record
	 * @return void    返回类型
	 */
	private void setRechargeRecordValues(int type,String typename,RechargeAmountParams params, Account account, RechargeRecord record) {
		record.setbId(new Date().getTime());
		record.setbAccountid(account.getbId());
		record.setvAccountname(account.getvName());
		record.setdBalance(Double.parseDouble(params.getBalance()));
		record.setiDealstatus(0);
		record.setiDealtype(params.getDealType());
		record.settDealtime(new Date());
		// record.setvDealname(DealTypeEnum.lookup(params.getDealType()).toString());
		record.setvMachineid(params.getMachineId());
		record.setvOperator(params.getOperator());
		record.setvOrderid(null);
		record.setvPayname(typename);
		record.setiPaytype(type);
	}

	/**
	 * @Title: setComsumeRecordValues 
	 * @Description: TODO(消费记录属性赋值) 
	 * @param i
	 * @param string
	 * @param params
	 * @param account
	 * @param record
	 * @return void    返回类型
	 */
	private void setComsumeRecordValues(int i, String string, ComsumeAmountParams params, Account account,
			ComsumeRecord record) {
		record.setbAccountid(account.getbId());
		record.setvAccountname(account.getvName());
		record.setdBalance(Double.parseDouble(params.getBalance()));
		record.setiDealstatus(0);
		record.setiDealtype(params.getDealType());
		record.settDealtime(new Date());
		record.setvDealname(DealTypeEnum.lookup(params.getDealType()).toString());
		 
		record.setvMachineid(params.getMachineId());
		record.setvOperator(params.getOperator());
		record.setvPaymentid(null);
		record.setvPayname(string);
		record.setiPaytype(i);
	}

	/**
	 * @Title: setRefundRecordValues 
	 * @Description: TODO(退款记录属性赋值) 
	 * @param i
	 * @param string
	 * @param params
	 * @param account
	 * @param record
	 * @return void    返回类型
	 */
	private void setRefundRecordValues(int i, String string, RefundAmountParams params, Account account,
			RefundRecord record) {
		record.setbAccountid(account.getbId());
		record.setvAccountname(account.getvName());
		record.setdBalance(Double.parseDouble(params.getBalance()));
		record.setiDealstatus(0);
		record.setiDealtype(4);
		record.settDealtime(new Date());
		record.setvDealname("余额");
		record.setvMachineid(params.getMachineId());
		record.setvOperator(params.getOperator());
		record.setvPaymentid(null);
		record.setvPayname(string);
		record.setiPaytype(i);
		record.setvPic(params.getPic());
	}

	/**
	 * @Title: setMoblieParams 
	 * @Description: TODO(移动支付参数赋值) 
	 * @param mobileParams
	 * @param userId
	 * @param m
	 * @param machineId
	 * @param operator
	 * @param balance
	 * @param dealType
	 * @return void    返回类型
	 */
	private void setMoblieParams(MobileParams mobileParams, Long userId, int m, String machineId, String operator,
			String balance, int dealType) {
		mobileParams.setMachineId(machineId);
		mobileParams.setOperator(operator);
		mobileParams.setBalance(balance);//微信金额是分
		mobileParams.setDealType(dealType);
		mobileParams.setUserId(userId);
		mobileParams.setOrderType(m);
 
	}
	
	 
	
}
