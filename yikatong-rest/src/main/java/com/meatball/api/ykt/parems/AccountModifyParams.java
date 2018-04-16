/**
 * Project Name:meatball-rest
 * File Name:AccountModifyParams.java
 * Package Name:com.meatball.api.ykt.parems
 * Date:2018年3月21日上午9:46:58
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.api.ykt.parems;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**   
 * @Title: AccountModifyParams.java 
 * @Package com.meatball.api.ykt.parems 
 * @Description: TODO(账户修改参数类) 
 * @author jw 
 * @date 2018年3月21日 上午9:46:58 
 * @version V1.0   
 */
@Data
public class AccountModifyParams implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="用户id", example="123456", required = true)
	private String userId;
	
	@ApiModelProperty(value="姓名", example="张三", required = true)
	private String name;

	@ApiModelProperty(value="身份证号", example="512341199010101111")
	private String idcard;
	
	@ApiModelProperty(value="就诊卡号", example="1555555555")
	private String cardNumber;
	
	@ApiModelProperty(value="性别（1 男 2女）", example="1")
	private int gender;
	
	@ApiModelProperty(value="电话", example="13500000000")
	private String tel;
	
	@ApiModelProperty(value="住址", example="四川省成都市xx区xx街道")
	private String address;
	
	@ApiModelProperty(value="出生日期 (YYYY-MM-DD)", example="1990-10-10")
	private Date birthday;
	
	@ApiModelProperty(value="社保卡号", example="1555555555")
	private String sinCard;
	
	@ApiModelProperty(value="居民健康卡号", example="1555555555")
	private String healthCard;
	
	@ApiModelProperty(value="支付密码", example="xxxxxx")
    private String vPaymentcode;
 
	@ApiModelProperty(value="指纹", example="xxxxxx")
    private String vFingerprint;
}
