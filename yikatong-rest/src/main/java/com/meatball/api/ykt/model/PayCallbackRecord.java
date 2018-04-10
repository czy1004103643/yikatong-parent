package com.meatball.api.ykt.model;

import java.util.Date;
/**
 * @Title: PayCallbackRecord.java 
 * @Package com.meatball.api.ykt.model 
 * @Description: TODO(支付平台回调记录Model) 
 * @author jw  
 * @date 2018年3月16日 下午3:04:10 
 * @version V1.0
 */
public class PayCallbackRecord {
    private Long bId;
    //回调时间
    private Date tTime;
    //订单编号(充值消费退款记录编号)
    private String vOrderid;
    //支付平台
    private String vPayplatform;
    //交易流水号
    private String vDealno;
    //下单时间
    private Date tOrdertime;
    //支付时间
    private Date tPaytime;
    //交易金额
    private double dBalance;
    //交易类别(1挂号费、2门诊(处方)费、3住院费)
    private Integer iDealtype2;
    //交易类别名称
    private String vDealname;
    //交易结果
    private String vResult;
    //账户编号
    private Long bAccountid;
    //账户名称
    private String vAccountname;
    //银行类别
    private String vBank;
    //商户编号
    private String vMerchantid;

    public Long getbId() {
        return bId;
    }

    public void setbId(Long bId) {
        this.bId = bId;
    }

    public Date gettTime() {
        return tTime;
    }

    public void settTime(Date tTime) {
        this.tTime = tTime;
    }

    public String getvOrderid() {
        return vOrderid;
    }

    public void setvOrderid(String vOrderid) {
        this.vOrderid = vOrderid == null ? null : vOrderid.trim();
    }

    public String getvPayplatform() {
        return vPayplatform;
    }

    public void setvPayplatform(String vPayplatform) {
        this.vPayplatform = vPayplatform == null ? null : vPayplatform.trim();
    }

    public String getvDealno() {
        return vDealno;
    }

    public void setvDealno(String vDealno) {
        this.vDealno = vDealno == null ? null : vDealno.trim();
    }

    public Date gettOrdertime() {
        return tOrdertime;
    }

    public void settOrdertime(Date tOrdertime) {
        this.tOrdertime = tOrdertime;
    }

    public Date gettPaytime() {
        return tPaytime;
    }

    public void settPaytime(Date tPaytime) {
        this.tPaytime = tPaytime;
    }

   

    public double getdBalance() {
		return dBalance;
	}

	public void setdBalance(double dBalance) {
		this.dBalance = dBalance;
	}

	public Integer getiDealtype2() {
        return iDealtype2;
    }

    public void setiDealtype2(Integer iDealtype2) {
        this.iDealtype2 = iDealtype2;
    }

    public String getvDealname() {
        return vDealname;
    }

    public void setvDealname(String vDealname) {
        this.vDealname = vDealname == null ? null : vDealname.trim();
    }

    public String getvResult() {
        return vResult;
    }

    public void setvResult(String vResult) {
        this.vResult = vResult == null ? null : vResult.trim();
    }

    public Long getbAccountid() {
        return bAccountid;
    }

    public void setbAccountid(Long bAccountid) {
        this.bAccountid = bAccountid;
    }

    public String getvAccountname() {
        return vAccountname;
    }

    public void setvAccountname(String vAccountname) {
        this.vAccountname = vAccountname == null ? null : vAccountname.trim();
    }

    public String getvBank() {
        return vBank;
    }

    public void setvBank(String vBank) {
        this.vBank = vBank == null ? null : vBank.trim();
    }

    public String getvMerchantid() {
        return vMerchantid;
    }

    public void setvMerchantid(String vMerchantid) {
        this.vMerchantid = vMerchantid == null ? null : vMerchantid.trim();
    }
}