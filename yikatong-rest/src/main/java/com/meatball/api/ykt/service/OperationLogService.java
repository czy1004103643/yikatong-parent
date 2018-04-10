/**
 * Project Name:meatball-rest
 * File Name:OperationLogService.java
 * Package Name:com.meatball.api.ykt.service
 * Date:2018年3月20日上午11:19:52
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.api.ykt.service;


/**   
 * @Title: OperationLogService.java 
 * @Package com.meatball.api.ykt.service 
 * @Description: TODO(操作日志接口，记录充值类接口调用日志) 
 * @author jw 
 * @date 2018年3月20日 上午11:19:52 
 * @version V1.0   
 */
public interface OperationLogService {
	
	/**
	 * @Title: insertOperationLog 
	 * @Description: TODO(插入操作日志) 
	 * @param vMachineid 操作机器编号
	 * @param vContent 操作内容
	 * @param bAccountid 影响用户编号
	 * @param vCaaountname 影响用户名称
	 * @param dBalance 影响金额
	 * @return
	 * @return boolean    返回类型
	 */
	boolean insertOperationLog(String vMachineid,String vContent,Long bAccountid,String vCaaountname,double dBalance);

}
