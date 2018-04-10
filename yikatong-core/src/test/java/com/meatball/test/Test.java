/** 
 * Project Name:meatball-core 
 * File Name:Test.java 
 * Package Name:com.meatball.test 
 * Date:2017年10月8日下午8:06:01 
 * Copyright (c) 2017, zhang.xiangyu@foxmail.com All Rights Reserved. 
 */  
package com.meatball.test;

import java.util.UUID;

import com.meatball.utils.MD5Util;

/**   
 * @Title: Test.java 
 * @Package com.meatball.test 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 張翔宇  
 * @date 2017年10月8日 下午8:06:01 
 * @version V1.0   
 */
public class Test {

	public static void main(String[] args) {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		System.out.println(uuid);
		
		String password = MD5Util.md5("123", "ccc");
		System.out.println(password);
	}

}
  