/**
 * Project Name:meatball-rest
 * File Name:OperationalAmountParams.java
 * Package Name:com.meatball.api.ykt.parems
 * Date:2018年3月13日下午3:26:39 
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.api.ykt.parems;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**   
 * @Title: OperationalAmountParams.java 
 * @Package com.meatball.api.ykt.parems 
 * @Description: TODO(充值金额参数类) 
 * @author jw 
 * @date 2018年3月13日 下午3:26:39 
 * @version V1.0   
 */
@Data
public class RechargeAmountParams implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="证件类别(1：身份证   2：就诊卡   3：社保卡  4：居民健康卡)", example="1", required = true)
	private Integer type;
	
	@ApiModelProperty(value="证件号码", example="123456", required = true)
	private String number;
	
	@ApiModelProperty(value="金额(带两位小数点)", example="0.01", required = true)
	private String balance;
	
	@ApiModelProperty(value="支付方式(1 现金、2 银行卡、3 移动支付、4 医佳云)", example="3", required = true)
	private Integer payType;
	
	@ApiModelProperty(value="交易类别(1挂号费、2门诊(处方)费、3住院费)", example="1", required = true)
	private Integer dealType;
	
	@ApiModelProperty(value="机器编号", example="SN9527", required = true)
	private String machineId ;
	
	@ApiModelProperty(value="操作员", example="张三", required = true)
	private String operator;
}
