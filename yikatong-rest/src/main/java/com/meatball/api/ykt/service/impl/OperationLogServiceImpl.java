/**
 * Project Name:meatball-rest
 * File Name:OperationLogServiceImpl.java
 * Package Name:com.meatball.api.ykt.service.impl
 * Date:2018年3月20日上午11:22:39
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.api.ykt.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import com.meatball.api.ykt.dao.OperationLogMapper;
import com.meatball.api.ykt.model.OperationLog;
import com.meatball.api.ykt.service.OperationLogService;

/**   
 * @Title: OperationLogServiceImpl.java 
 * @Package com.meatball.api.ykt.service.impl 
 * @Description: TODO(操作日志接口实现类) 
 * @author jw 
 * @date 2018年3月20日 上午11:22:39 
 * @version V1.0   
 */
@Service
public class OperationLogServiceImpl implements OperationLogService {

	@Resource
	private OperationLogMapper operationLogMapper;
	
	@Override
	public boolean insertOperationLog(String vMachineid,String vContent,Long bAccountid,String vCaaountname,double dBalance) {
		OperationLog log = new OperationLog();
		log.setvMachineid(vMachineid);
		log.setvContent(vContent);
		log.setbAccountid(bAccountid);
		log.setvCaaountname(vCaaountname);
		log.setdBalance(dBalance);
		log.settTime(new Date());
		log.setvIp(SecurityUtils.getSubject().getSession().getHost());
		int num = operationLogMapper.insertSelective(log);
		if(num > 0) {
			return true;
		}
		return false;
	}

}
