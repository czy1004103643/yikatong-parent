package com.meatball.api.ykt.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * @Title: RechargeRecord.java 
 * @Package com.meatball.api.ykt.model 
 * @Description: TODO(充值记录Model) 
 * @author jw  
 * @date 2018年3月16日 下午3:04:28 
 * @version V1.0
 */
public class RechargeRecord {
    private Long bId;
    //支付方式(1现金2银行卡3微信4支付宝)
    private Integer iPaytype;
    //支付方式名称
    private String vPayname;
    //充值支付编号(平台交易流水号)
    private String vOrderid;
    //账户编号
    private Long bAccountid;
    //账户名称
    private String vAccountname;
    //交易类别(1挂号费、2门诊(处方)费、3住院费)
    private Integer iDealtype;
    //交易类别名称
    private String vDealname;
    //充值金额
    private double dBalance;
    //充值状态(0成功 1失败 8撤销9待支付)
    private Integer iDealstatus;
    //充值时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") 
    private Date tDealtime;
    //机器编号
    private String vMachineid;
    //操作员
    private String vOperator;

    public Long getbId() {
        return bId;
    }

    public void setbId(Long bId) {
        this.bId = bId;
    }

    public Integer getiPaytype() {
        return iPaytype;
    }

    public void setiPaytype(Integer iPaytype) {
        this.iPaytype = iPaytype;
    }

    public String getvPayname() {
        return vPayname;
    }

    public void setvPayname(String vPayname) {
        this.vPayname = vPayname == null ? null : vPayname.trim();
    }

    public String getvOrderid() {
        return vOrderid;
    }

    public void setvOrderid(String vOrderid) {
        this.vOrderid = vOrderid == null ? null : vOrderid.trim();
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

    public Integer getiDealtype() {
        return iDealtype;
    }

    public void setiDealtype(Integer iDealtype) {
        this.iDealtype = iDealtype;
    }

    public String getvDealname() {
        return vDealname;
    }

    public void setvDealname(String vDealname) {
        this.vDealname = vDealname == null ? null : vDealname.trim();
    }

    

    public double getdBalance() {
		return dBalance;
	}

	public void setdBalance(double dBalance) {
		this.dBalance = dBalance;
	}

	public Integer getiDealstatus() {
        return iDealstatus;
    }

    public void setiDealstatus(Integer iDealstatus) {
        this.iDealstatus = iDealstatus;
    }

    public Date gettDealtime() {
        return tDealtime;
    }

    public void settDealtime(Date tDealtime) {
        this.tDealtime = tDealtime;
    }

    public String getvMachineid() {
        return vMachineid;
    }

    public void setvMachineid(String vMachineid) {
        this.vMachineid = vMachineid == null ? null : vMachineid.trim();
    }

    public String getvOperator() {
        return vOperator;
    }

    public void setvOperator(String vOperator) {
        this.vOperator = vOperator == null ? null : vOperator.trim();
    }
}