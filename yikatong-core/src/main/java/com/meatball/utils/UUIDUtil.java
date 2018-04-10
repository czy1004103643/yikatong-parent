/**
 * Project Name:meatball-core
 * File Name:UUIDUtil.java
 * Package Name:com.meatball.utils
 * Date:2017年10月11日下午4:49:27
 * Copyright (c) 2017, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.utils;

import java.util.UUID;

/**   
 * @Title: UUIDUtil.java 
 * @Package com.meatball.utils 
 * @Description: TODO(生成ID) 
 * @author 張翔宇  
 * @date 2017年10月11日 下午4:49:27 
 * @version V1.0   
 */
public class UUIDUtil {
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
