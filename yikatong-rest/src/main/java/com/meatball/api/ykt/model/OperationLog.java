package com.meatball.api.ykt.model;

import java.util.Date;
/**
 * @Title: OperationLog.java 
 * @Package com.meatball.api.ykt.model 
 * @Description: TODO(操作日志Model) 
 * @author jw  
 * @date 2018年3月16日 下午3:03:52 
 * @version V1.0
 */
public class OperationLog {
    private Long bId;
    //操作机器编号
    private String vMachineid;
    //操作内容
    private String vContent;
    //操作时间
    private Date tTime;
    //影响用户编号
    private Long bAccountid;
    //影响用户名称
    private String vCaaountname;
    //影响金额
    private double dBalance;
    //操作ip
    private String vIp;

    public Long getbId() {
        return bId;
    }

    public void setbId(Long bId) {
        this.bId = bId;
    }

    public String getvMachineid() {
        return vMachineid;
    }

    public void setvMachineid(String vMachineid) {
        this.vMachineid = vMachineid == null ? null : vMachineid.trim();
    }

    public String getvContent() {
        return vContent;
    }

    public void setvContent(String vContent) {
        this.vContent = vContent == null ? null : vContent.trim();
    }

    public Date gettTime() {
        return tTime;
    }

    public void settTime(Date tTime) {
        this.tTime = tTime;
    }

    public Long getbAccountid() {
        return bAccountid;
    }

    public void setbAccountid(Long bAccountid) {
        this.bAccountid = bAccountid;
    }

    public String getvCaaountname() {
        return vCaaountname;
    }

    public void setvCaaountname(String vCaaountname) {
        this.vCaaountname = vCaaountname == null ? null : vCaaountname.trim();
    }

   

    public double getdBalance() {
		return dBalance;
	}

	public void setdBalance(double dBalance) {
		this.dBalance = dBalance;
	}

	public String getvIp() {
        return vIp;
    }

    public void setvIp(String vIp) {
        this.vIp = vIp == null ? null : vIp.trim();
    }
}