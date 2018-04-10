/**
 * Project Name:meatball-core
 * File Name:AlipayResRedder.java
 * Package Name:com.meatball.utils
 * Date:2018年3月25日下午2:22:35
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**   
 * @Title: AlipayResRedder.java 
 * @Package com.meatball.utils 
 * @Description: TODO(解析支付宝支付返回结果) 
 * @author 張翔宇  
 * @date 2018年3月25日 下午2:22:35 
 * @version V1.0   
 */
public class AlipayResRedder {
	private static final Logger log = LoggerFactory.getLogger(AlipayResRedder.class);
	/**
	 * @Title: read 
	 * @Description: TODO(读取返回结果) 
	 * @param request
	 * @return Map<String,String>    返回类型
	 */
	public static Map<String, String> read(HttpServletRequest request) {
		Map<String, String> maps = new HashMap<String, String>();
		Map<String, String[]> result = request.getParameterMap();
		for (Iterator<String> iter = result.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) result.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            maps.put(name, valueStr);
        }
		log.info(JSON.toJSONString(maps));
		return maps;
	}
}
