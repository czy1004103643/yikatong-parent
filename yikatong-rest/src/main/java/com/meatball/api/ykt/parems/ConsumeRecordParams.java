/**
 * Project Name:meatball-rest
 * File Name:ConsumeRecordParams.java
 * Package Name:com.meatball.api.ykt.parems
 * Date:2018年3月13日下午4:05:33
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.api.ykt.parems;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

/**   
 * @Title: ConsumeRecordParams.java 
 * @Package com.meatball.api.ykt.parems 
 * @Description: TODO(消费记录信息) 
 * @author jw 
 * @date 2018年3月13日 下午4:05:33 
 * @version V1.0   
 */
public class ConsumeRecordParams implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="记录id", example="1")
	private String id;
	
	@ApiModelProperty(value="消费方式(1现金2银行卡3微信4支付宝5余额)", example="1")
    private Integer iPaytype;
    
	@ApiModelProperty(value="消费方式名称", example="现金")
    private String vPayname;
    
	@ApiModelProperty(value="消费支付编号(平台交易流水号)", example="11111111")
    private String vPaymentid;
  
	@ApiModelProperty(value="账户编号", example="123")
    private String bAccountid;
 
	@ApiModelProperty(value="账户名称", example="张三")
    private String vAccountname;
    
	@ApiModelProperty(value="交易类别(1挂号费、2门诊(处方)费、3住院费)", example="1")
    private Integer iDealtype;
    
	@ApiModelProperty(value="交易类别名称", example="挂号费")
    private String vDealname;
     
	@ApiModelProperty(value="消费金额(带两位小数点)", example="5000.00")
    private String dBalance;
    
	@ApiModelProperty(value="交易状态(0成功 1失败 8撤销 9待支付)", example="0")
    private Integer iDealstatus;
    
	@ApiModelProperty(value="消费时间(yyyy-MM-dd HH:mm:ss)", example="2010-10-10 15:11:12")
    private String tDealtime;
    
	@ApiModelProperty(value="机器编号", example="xxxxxx")
    private String vMachineid;
    
	@ApiModelProperty(value="操作员", example="xxxx")
    private String vOperator;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
		this.vPayname = vPayname;
	}

	public String getvPaymentid() {
		return vPaymentid;
	}

	public void setvPaymentid(String vPaymentid) {
		this.vPaymentid = vPaymentid;
	}

	public String getbAccountid() {
		return bAccountid;
	}

	public void setbAccountid(String bAccountid) {
		this.bAccountid = bAccountid;
	}

	public String getvAccountname() {
		return vAccountname;
	}

	public void setvAccountname(String vAccountname) {
		this.vAccountname = vAccountname;
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
		this.vDealname = vDealname;
	}

	public String getdBalance() {
		return dBalance;
	}

	public void setdBalance(String dBalance) {
		this.dBalance = dBalance;
	}

	public Integer getiDealstatus() {
		return iDealstatus;
	}

	public void setiDealstatus(Integer iDealstatus) {
		this.iDealstatus = iDealstatus;
	}

	public String gettDealtime() {
		return tDealtime;
	}

	public void settDealtime(String tDealtime) {
		this.tDealtime = tDealtime;
	}

	public String getvMachineid() {
		return vMachineid;
	}

	public void setvMachineid(String vMachineid) {
		this.vMachineid = vMachineid;
	}

	public String getvOperator() {
		return vOperator;
	}

	public void setvOperator(String vOperator) {
		this.vOperator = vOperator;
	}
	
}
