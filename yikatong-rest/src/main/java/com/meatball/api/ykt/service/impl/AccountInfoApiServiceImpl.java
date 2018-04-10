/**
 * Project Name:meatball-rest
 * File Name:AccountInfoApiServiceImpl.java
 * Package Name:com.meatball.api.ykt.service.impl
 * Date:2018年3月16日上午10:00:02
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.api.ykt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.meatball.api.ykt.dao.AccountMapper;
import com.meatball.api.ykt.dao.RechargeRecordMapper;
import com.meatball.api.ykt.model.Account;
import com.meatball.api.ykt.model.RechargeRecord;
import com.meatball.api.ykt.dao.ComsumeRecordMapper;
import com.meatball.api.ykt.dao.RefundRecordMapper;
import com.meatball.api.ykt.model.ComsumeRecord;
import com.meatball.api.ykt.model.RefundRecord;
import com.meatball.api.ykt.parems.AccountInfoParams;
import com.meatball.api.ykt.parems.AccountQueryParams;
import com.meatball.api.ykt.parems.RefundAmountInfoParams;
import com.meatball.api.ykt.service.AccountInfoApiService;
import com.meatball.vo.ResultMsg;

/**   
 * @Title: AccountInfoApiServiceImpl.java 
 * @Package com.meatball.api.ykt.service.impl 
 * @Description: TODO(账户信息充值记录等查询接口实现类) 
 * @author jw 
 * @date 2018年3月16日 上午10:00:02 
 * @version V1.0   
 */
@Service
public class AccountInfoApiServiceImpl implements AccountInfoApiService {
	
	@Resource
	private RechargeRecordMapper rechargeRecordMapper;
	@Resource
	private AccountMapper accountMapper;
	@Resource
	private ComsumeRecordMapper comsumeRecordMapper;
	@Resource
	private RefundRecordMapper refundRecordMapper;
	@Override
	public ResultMsg getAccountInfoBy(AccountQueryParams params) {
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
				msg.setData(account);
				break;
			case 2://就诊卡号
				cardNumber = params.getNumber();
				account = accountMapper.selectAccountByPatientidcard(cardNumber);
				msg.setData(account);
				break;
			case 3://社保卡号
				sinCard = params.getNumber();
				account = accountMapper.selectAccountBySincard(sinCard);
				msg.setData(account);
				break;
			case 4://居民健康卡号
				healthCard = params.getNumber();
				account = accountMapper.selectAccountByHealthcard(healthCard);
				msg.setData(account);
				break;
			default:
				infoParams.setResultCode(1);
				infoParams.setResultMsg("请传入正确的卡类别");
				break;
			}
		return msg;
	}

	//余额查询
	@Override
	public ResultMsg getAccountBalanceBy(AccountQueryParams params) {
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
			msg.setData(account);
			break;
		case 2://就诊卡号
			cardNumber = params.getNumber();
			account = accountMapper.selectAccountByPatientidcard(cardNumber);
			msg.setData(account);
			break;
		case 3://社保卡号
			sinCard = params.getNumber();
			account = accountMapper.selectAccountBySincard(sinCard);
			msg.setData(account);
			break;
		case 4://居民健康卡号
			healthCard = params.getNumber();
			account = accountMapper.selectAccountByHealthcard(healthCard);
			msg.setData(account);
			break;
		default:
			info.setResultCode(1);
			info.setResultMsg("请传入正确的卡类别");
			break;
		} 
		return msg;
	}

	
	@Override
	public ResultMsg getRechargeRecordBy(AccountQueryParams params) {
		Account account = null;
		List<RechargeRecord> rechargeRecord = null;
		//身份证号
		String idcard = null;
		//就诊卡号
		String cardNumber = null;
		//社保卡号
		String sinCard = null;
		//居民健康卡号
		String healthCard = null;
		
		Long uid= null;
		RefundAmountInfoParams info = new RefundAmountInfoParams();
		ResultMsg msg = new ResultMsg(200, info);
		/*1、先查询账户是否存在，如果存在则查询账户信息*/ 
		switch (params.getType()) {
		case 1://身份证号
			idcard = params.getNumber();
			account = accountMapper.selectAccountByIdcard(idcard);
			uid= account.getbId();
			rechargeRecord = rechargeRecordMapper.selectBAccountid(uid);
			msg.setData(rechargeRecord);
			break;
		case 2://就诊卡号
			cardNumber = params.getNumber();
			account = accountMapper.selectAccountByPatientidcard(cardNumber);
			uid= account.getbId();
			rechargeRecord = rechargeRecordMapper.selectBAccountid(uid);
			msg.setData(rechargeRecord);
			break;
		case 3://社保卡号
			sinCard = params.getNumber();
			account = accountMapper.selectAccountBySincard(sinCard);
			uid= account.getbId();
			rechargeRecord = rechargeRecordMapper.selectBAccountid(uid);
			msg.setData(rechargeRecord);
			break;
		case 4://居民健康卡号
			healthCard = params.getNumber();
			account = accountMapper.selectAccountByHealthcard(healthCard);
			uid= account.getbId();
			rechargeRecord = rechargeRecordMapper.selectBAccountid(uid);
			msg.setData(rechargeRecord);
			break;
		default:
			info.setResultCode(1);
			info.setResultMsg("请传入正确的卡类别");
			break;
		} 
		return msg;
		
	}

	//充值记录查询
	@Override
	public ResultMsg getConsumeRecordBy(AccountQueryParams params) {
		Account account = null;
		List<ComsumeRecord> comsumeRecord = null;
		//身份证号
		String idcard = null;
		//就诊卡号
		String cardNumber = null;
		//社保卡号
		String sinCard = null;
		//居民健康卡号
		String healthCard = null;
		Long id = null;
		
		AccountInfoParams infoParams = new AccountInfoParams();
		ResultMsg msg = new ResultMsg(200, infoParams);
		
			/*1、先查询账户是否存在*/ 
			switch (params.getType()) {
			case 1://身份证号
				idcard = params.getNumber();
				
				account = accountMapper.selectAccountByIdcard(idcard);
				id = account.getbId();
				comsumeRecord = comsumeRecordMapper.selectBybAccountid(id);
				msg.setData(comsumeRecord);
				break;
			case 2://就诊卡号
				cardNumber = params.getNumber();
				account = accountMapper.selectAccountByPatientidcard(cardNumber);
				id = account.getbId();
				comsumeRecord = comsumeRecordMapper.selectBybAccountid(id);
				msg.setData(comsumeRecord);
				break;
			case 3://社保卡号
				sinCard = params.getNumber();
				account = accountMapper.selectAccountBySincard(sinCard);
				id = account.getbId();
				comsumeRecord = comsumeRecordMapper.selectBybAccountid(id);
				msg.setData(comsumeRecord);
				break;
			case 4://居民健康卡号
				healthCard = params.getNumber();
				account = accountMapper.selectAccountByHealthcard(healthCard);
				id = account.getbId();
				comsumeRecord = comsumeRecordMapper.selectBybAccountid(id);
				msg.setData(comsumeRecord);
				break;
			default:
				infoParams.setResultCode(1);
				infoParams.setResultMsg("请传入正确的卡类别");
				break;
			}
		return msg;
	}

	/**
	 * 退款记录查询
	 */
	@Override
	public ResultMsg getRefundRecordBy(AccountQueryParams params) {
		Account account = null;
		List<RefundRecord> refundRecord = null;
		//身份证号
		String idcard = null;
		//就诊卡号
		String cardNumber = null;
		//社保卡号
		String sinCard = null;
		//居民健康卡号
		String healthCard = null;
		Long id = null;
		
		AccountInfoParams infoParams = new AccountInfoParams();
		ResultMsg msg = new ResultMsg(200, infoParams);
		
			/*1、先查询账户是否存在*/ 
			switch (params.getType()) {
			case 1://身份证号
				idcard = params.getNumber();
				
				account = accountMapper.selectAccountByIdcard(idcard);
				id = account.getbId();
				refundRecord = refundRecordMapper.selectBybAccountid(id);
				msg.setData(refundRecord);
				break;
			case 2://就诊卡号
				cardNumber = params.getNumber();
				account = accountMapper.selectAccountByPatientidcard(cardNumber);
				id = account.getbId();
				refundRecord = refundRecordMapper.selectBybAccountid(id);
				msg.setData(refundRecord);
				break;
			case 3://社保卡号
				sinCard = params.getNumber();
				account = accountMapper.selectAccountBySincard(sinCard);
				id = account.getbId();
				refundRecord = refundRecordMapper.selectBybAccountid(id);
				msg.setData(refundRecord);
				break;
			case 4://居民健康卡号
				healthCard = params.getNumber();
				account = accountMapper.selectAccountByHealthcard(healthCard);
				id = account.getbId();
				refundRecord = refundRecordMapper.selectBybAccountid(id);
				msg.setData(refundRecord);
				break;
			default:
				infoParams.setResultCode(1);
				infoParams.setResultMsg("请传入正确的卡类别");
				break;
			}
		return msg;
	}

}
