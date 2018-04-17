package com.meatball.api.ykt.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * @Title: Account.java 
 * @Package com.meatball.api.ykt.model 
 * @Description: TODO(账户信息model) 
 * @author jw  
 * @date 2018年3月16日 上午11:05:04 
 * @version V1.0
 */
public class Account {
	
    private Long bId;
    //身份证
    private String vIdcard;
    //就诊卡
    private String vPatientidcard;
    //社保卡
    private String vSincard;
    //居民健康卡
    private String vHealthcard;
    //账户余额
    private double dBalance;
    //支付密码
    private String vPaymentcode;
    //指纹
    private String vFingerprint;
    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") 
    private Date tCreatedate;
    //姓名
    private String vName;
    //电话
    private String vTel;
    //性别
    private Integer iGender;
    //住址
    private String vAddress;
    //生日
    @JsonFormat(pattern = "yyyy-MM-dd") 
    private Date dBirthday;

    public Long getbId() {
        return bId;
    }

    public void setbId(Long bId) {
        this.bId = bId;
    }

    public String getvIdcard() {
        return vIdcard;
    }

    public void setvIdcard(String vIdcard) {
        this.vIdcard = vIdcard == null ? null : vIdcard.trim();
    }

    public String getvPatientidcard() {
        return vPatientidcard;
    }

    public void setvPatientidcard(String vPatientidcard) {
        this.vPatientidcard = vPatientidcard == null ? null : vPatientidcard.trim();
    }

    public String getvSincard() {
        return vSincard;
    }

    public void setvSincard(String vSincard) {
        this.vSincard = vSincard == null ? null : vSincard.trim();
    }

    public String getvHealthcard() {
        return vHealthcard;
    }

    public void setvHealthcard(String vHealthcard) {
        this.vHealthcard = vHealthcard == null ? null : vHealthcard.trim();
    }


    public double getdBalance() {
		return dBalance;
	}

	public void setdBalance(double dBalance) {
		this.dBalance = dBalance;
	}

	public String getvPaymentcode() {
        return vPaymentcode;
    }

    public void setvPaymentcode(String vPaymentcode) {
        this.vPaymentcode = vPaymentcode == null ? null : vPaymentcode.trim();
    }

    public String getvFingerprint() {
        return vFingerprint;
    }

    public void setvFingerprint(String vFingerprint) {
        this.vFingerprint = vFingerprint == null ? null : vFingerprint.trim();
    }

    public Date gettCreatedate() {
        return tCreatedate;
    }

    public void settCreatedate(Date tCreatedate) {
        this.tCreatedate = tCreatedate;
    }

    public String getvName() {
        return vName;
    }

    public void setvName(String vName) {
        this.vName = vName == null ? null : vName.trim();
    }

    public String getvTel() {
        return vTel;
    }

    public void setvTel(String vTel) {
        this.vTel = vTel == null ? null : vTel.trim();
    }

    public Integer getiGender() {
        return iGender;
    }

    public void setiGender(Integer iGender) {
        this.iGender = iGender;
    }

    public String getvAddress() {
        return vAddress;
    }

    public void setvAddress(String vAddress) {
        this.vAddress = vAddress == null ? null : vAddress.trim();
    }

    public Date getdBirthday() {
        return dBirthday;
    }

    public void setdBirthday(Date dBirthday) {
        this.dBirthday = dBirthday;
    }
}