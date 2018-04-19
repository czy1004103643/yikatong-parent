/**
 * Project Name:meatball-rest
 * File Name:AccountInfoApiServiceImpl.java
 * Package Name:com.meatball.api.ykt.service.impl
 * Date:2018年3月16日上午10:00:02
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.api.ykt.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.meatball.api.ykt.dao.AccountMapper;
import com.meatball.api.ykt.dao.ComsumeRecordMapper;
import com.meatball.api.ykt.dao.RechargeRecordMapper;
import com.meatball.api.ykt.dao.RefundRecordMapper;
import com.meatball.api.ykt.model.Account;
import com.meatball.api.ykt.model.ComsumeRecord;
import com.meatball.api.ykt.model.RechargeRecord;
import com.meatball.api.ykt.model.RefundRecord;
import com.meatball.api.ykt.parems.AccountInfoParams;
import com.meatball.api.ykt.parems.AccountQueryParams;
import com.meatball.api.ykt.parems.ConsumeRecordParams;
import com.meatball.api.ykt.parems.RechargeRecordParams;
import com.meatball.api.ykt.parems.RefundAmountInfoParams;
import com.meatball.api.ykt.parems.RefundRecordParams;
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
	
	
	SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdf1 = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
     * @Title: setAccountInfo
     * @Description: TODO(组装充值返回信息)
     * @param params
     * @param account
     * @param msg
     * @return void    返回类型
     */
	
	/**
	 * 账户返回信息
	 * @param account
	 * @param info
	 * @param msg
	 */
    private void setAccountInfo(Account account,AccountInfoParams info,String msg) {
    	info.setId(account.getbId().toString());
    	info.setResultCode(0);
    	info.setResultMsg(msg);
    	info.setName(account.getvName());
    	info.setIdcard(account.getvIdcard());
    	info.setCardNumber(account.getvPatientidcard());
    	info.setGender(account.getiGender());
    	info.setTel(account.getvTel());
    	info.setAddress(account.getvAddress());
    	info.setBirthday(sdf.format(account.getdBirthday()));
    	info.setSinCard(account.getvSincard());
    	info.setHealthCard(account.getvHealthcard());
    	info.setBalance(String.valueOf(account.getdBalance()));
    	info.setCreateDate(sdf1.format(account.gettCreatedate()));
    }
    /**
     * 余额查询信息
     * @param params
     * @param account
     * @param info
     * @param msg
     */
    private void setRefundAmountInfo(AccountQueryParams params,Account account,RefundAmountInfoParams info,String msg){
    	info.setResultCode(0);
    	info.setResultMsg(msg);
    	info.setType(params.getType());
    	info.setNumber(account.getvTel());
    	info.setName(account.getvName());
    	info.setCreateDate(sdf1.format(account.gettCreatedate()));
    	info.setBalance(String.valueOf(account.getdBalance()));
    }
    /**
     * 消费记录返回信息
     * @param comsumeRecord
     * @return
     */
    private List<ConsumeRecordParams> setConsumeRecordBy(List<ComsumeRecord> comsumeRecord){
    	if(comsumeRecord.size()>0){
    		List<ConsumeRecordParams> ls = new ArrayList<>();
    		for(int i =0;i<comsumeRecord.size();i++){
    			ConsumeRecordParams info = new ConsumeRecordParams();
    			info.setId(comsumeRecord.get(i).getbId().toString());
    			info.setiPaytype(comsumeRecord.get(i).getiPaytype());
    			info.setvPayname(comsumeRecord.get(i).getvPayname());
    			info.setvPaymentid(comsumeRecord.get(i).getvPaymentid());
    			info.setbAccountid(comsumeRecord.get(i).getbAccountid().toString());
    			info.setvAccountname(comsumeRecord.get(i).getvAccountname());
    			info.setiDealtype(comsumeRecord.get(i).getiDealtype());
    			info.setvDealname(comsumeRecord.get(i).getvDealname());
    			info.setdBalance(String.valueOf(comsumeRecord.get(i).getdBalance()));
    			info.setiDealstatus(comsumeRecord.get(i).getiDealstatus());
    			info.settDealtime(sdf1.format(comsumeRecord.get(i).gettDealtime()));
    			info.setvMachineid(comsumeRecord.get(i).getvMachineid());
    			info.setvOperator(comsumeRecord.get(i).getvOperator());
    			ls.add(info);
    		}
    		return ls;
    	}else{
    		return null;
    	}
    }
    
    /**
     * 充值记录返回信息
     * @param RechargeRecord
     * @return
     */
    private List<RechargeRecordParams> setRechargeRecord(List<RechargeRecord> RechargeRecord){
    	if(RechargeRecord.size()>0){
    		List<RechargeRecordParams> ls = new ArrayList<>();
    		for(int i =0;i<RechargeRecord.size();i++){
    			RechargeRecordParams info = new RechargeRecordParams();
    			info.setId(RechargeRecord.get(i).getbId().toString());
    			info.setiPaytype(RechargeRecord.get(i).getiPaytype());
    			info.setvPayname(RechargeRecord.get(i).getvPayname());
    			info.setvOrderid(RechargeRecord.get(i).getvOrderid());
    			info.setbAccountid(RechargeRecord.get(i).getbAccountid().toString());
    			info.setvAccountname(RechargeRecord.get(i).getvAccountname());
    			info.setiDealtype(RechargeRecord.get(i).getiDealtype());
    			info.setvDealname(RechargeRecord.get(i).getvDealname());
    			info.setdBalance(String.valueOf(RechargeRecord.get(i).getdBalance()));
    			info.setiDealstatus(RechargeRecord.get(i).getiDealstatus());
    			info.settDealtime(sdf1.format(RechargeRecord.get(i).gettDealtime()));
    			info.setvMachineid(RechargeRecord.get(i).getvMachineid());
    			info.setvOperator(RechargeRecord.get(i).getvOperator());
    			ls.add(info);
    		}
    		return ls;
    	}else{
    		return null;
    	}
    }
    
    /**
     * 退款记录返回信息
     * @param RechargeRecord
     * @return
     */
    private List<RefundRecordParams> setAccountInfo(List<RefundRecord> RefundRecord){
    	if(RefundRecord.size()>0){
    		List<RefundRecordParams> ls = new ArrayList<>();
    		for(int i =0;i<RefundRecord.size();i++){
    			RefundRecordParams info = new RefundRecordParams();
    			info.setId(RefundRecord.get(i).getbId().toString());
    			info.setiDealstatus(RefundRecord.get(i).getiDealstatus());
    			info.setiPaytype(RefundRecord.get(i).getiPaytype());
    			info.setvPayname(RefundRecord.get(i).getvPayname());
    			info.setvPaymentid(RefundRecord.get(i).getvPaymentid());
    			info.setvAccountname(RefundRecord.get(i).getvAccountname());
    			info.setbAccountid(RefundRecord.get(i).getbAccountid().toString());
    			info.setiDealtype(RefundRecord.get(i).getiDealtype());
    			info.setvDealname(RefundRecord.get(i).getvDealname());
    			info.setdBalance(String.valueOf(RefundRecord.get(i).getdBalance()));
    			info.settDealtime(sdf1.format(RefundRecord.get(i).gettDealtime()));
    			info.setvMachineid(RefundRecord.get(i).getvMachineid());
    			info.setvOperator(RefundRecord.get(i).getvOperator());
    			info.setvPic(RefundRecord.get(i).getvPic());
    			ls.add(info);
    		}
    		return ls;
    	}else{
    		return null;
    	}
    }
    
	
	/**
	 * 账户信息查询
	 */
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
				setAccountInfo(account,infoParams,"操作成功");
				break;
			case 2://就诊卡号
				cardNumber = params.getNumber();
				account = accountMapper.selectAccountByPatientidcard(cardNumber);
				setAccountInfo(account,infoParams,"操作成功");
				break;
			case 3://社保卡号
				sinCard = params.getNumber();
				account = accountMapper.selectAccountBySincard(sinCard);
				setAccountInfo(account,infoParams,"操作成功");
				break;
			case 4://居民健康卡号
				healthCard = params.getNumber();
				account = accountMapper.selectAccountByHealthcard(healthCard);
				setAccountInfo(account,infoParams,"操作成功");
				break;
			default:
				infoParams.setResultCode(1);
				infoParams.setResultMsg("请传入正确的卡类别");
				break;
			}
		return msg;
	}

	/**
	 * 余额查询
	 */
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
			setRefundAmountInfo(params,account,info,"操作成功");
			break;
		case 2://就诊卡号
			cardNumber = params.getNumber();
			account = accountMapper.selectAccountByPatientidcard(cardNumber);
			setRefundAmountInfo(params,account,info,"操作成功");
			break;
		case 3://社保卡号
			sinCard = params.getNumber();
			account = accountMapper.selectAccountBySincard(sinCard);
			setRefundAmountInfo(params,account,info,"操作成功");
			break;
		case 4://居民健康卡号
			healthCard = params.getNumber();
			account = accountMapper.selectAccountByHealthcard(healthCard);
			setRefundAmountInfo(params,account,info,"操作成功");
			break;
		default:
			info.setResultCode(1);
			info.setResultMsg("请传入正确的卡类别");
			break;
		} 
		return msg;
	}

	/**
	 * 充值记录查询
	 */
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
		List<RechargeRecordParams> setRechargeRecord = new ArrayList<>();
		
		/*1、先查询账户是否存在，如果存在则查询账户信息*/ 
		switch (params.getType()) {
		case 1://身份证号
			idcard = params.getNumber();
			account = accountMapper.selectAccountByIdcard(idcard);
			uid= account.getbId();
			rechargeRecord = rechargeRecordMapper.selectBAccountid(uid);
			setRechargeRecord = setRechargeRecord(rechargeRecord);
			break;
		case 2://就诊卡号
			cardNumber = params.getNumber();
			account = accountMapper.selectAccountByPatientidcard(cardNumber);
			uid= account.getbId();
			rechargeRecord = rechargeRecordMapper.selectBAccountid(uid);
			setRechargeRecord = setRechargeRecord(rechargeRecord);
			break;
		case 3://社保卡号
			sinCard = params.getNumber();
			account = accountMapper.selectAccountBySincard(sinCard);
			uid= account.getbId();
			rechargeRecord = rechargeRecordMapper.selectBAccountid(uid);
			setRechargeRecord = setRechargeRecord(rechargeRecord);
			break;
		case 4://居民健康卡号
			healthCard = params.getNumber();
			account = accountMapper.selectAccountByHealthcard(healthCard);
			uid= account.getbId();
			rechargeRecord = rechargeRecordMapper.selectBAccountid(uid);
			setRechargeRecord = setRechargeRecord(rechargeRecord);
			break;
		default:
			break;
		} 
		ResultMsg msg = new ResultMsg(200, setRechargeRecord);
		return msg;
		
	}

	/**
	 * 消费记录查询
	 */
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
		
		ConsumeRecordParams infoParams = new ConsumeRecordParams();
	
		List<ConsumeRecordParams> setConsumeRecordBy  = new ArrayList<>();
			/*1、先查询账户是否存在*/ 
			switch (params.getType()) {
			case 1://身份证号
				idcard = params.getNumber();
				account = accountMapper.selectAccountByIdcard(idcard);
				id = account.getbId();
				comsumeRecord = comsumeRecordMapper.selectBybAccountid(id);
				setConsumeRecordBy = setConsumeRecordBy(comsumeRecord);
				break;
			case 2://就诊卡号
				cardNumber = params.getNumber();
				account = accountMapper.selectAccountByPatientidcard(cardNumber);
				id = account.getbId();
				comsumeRecord = comsumeRecordMapper.selectBybAccountid(id);
				setConsumeRecordBy = setConsumeRecordBy(comsumeRecord);
				break;
			case 3://社保卡号
				sinCard = params.getNumber();
				account = accountMapper.selectAccountBySincard(sinCard);
				id = account.getbId();
				comsumeRecord = comsumeRecordMapper.selectBybAccountid(id);
				setConsumeRecordBy = setConsumeRecordBy(comsumeRecord);
				break;
			case 4://居民健康卡号
				healthCard = params.getNumber();
				account = accountMapper.selectAccountByHealthcard(healthCard);
				id = account.getbId();
				comsumeRecord = comsumeRecordMapper.selectBybAccountid(id);
				setConsumeRecordBy = setConsumeRecordBy(comsumeRecord);
				break;
			default:
				break;
			}
			ResultMsg msg = new ResultMsg(200, setConsumeRecordBy);
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
		List<RefundRecordParams> setAccountInfo  = new ArrayList<>();
		
			/*1、先查询账户是否存在*/ 
			switch (params.getType()) {
			case 1://身份证号
				idcard = params.getNumber();
				
				account = accountMapper.selectAccountByIdcard(idcard);
				id = account.getbId();
				refundRecord = refundRecordMapper.selectBybAccountid(id);
				setAccountInfo = setAccountInfo(refundRecord);
				break;
			case 2://就诊卡号
				cardNumber = params.getNumber();
				account = accountMapper.selectAccountByPatientidcard(cardNumber);
				id = account.getbId();
				refundRecord = refundRecordMapper.selectBybAccountid(id);
				setAccountInfo = setAccountInfo(refundRecord);
				break;
			case 3://社保卡号
				sinCard = params.getNumber();
				account = accountMapper.selectAccountBySincard(sinCard);
				id = account.getbId();
				refundRecord = refundRecordMapper.selectBybAccountid(id);
				setAccountInfo = setAccountInfo(refundRecord);
				break;
			case 4://居民健康卡号
				healthCard = params.getNumber();
				account = accountMapper.selectAccountByHealthcard(healthCard);
				id = account.getbId();
				refundRecord = refundRecordMapper.selectBybAccountid(id);
				setAccountInfo = setAccountInfo(refundRecord);
				break;
			default:
				break;
			}
			ResultMsg msg = new ResultMsg(200, setAccountInfo);
		return msg;
	}

}
