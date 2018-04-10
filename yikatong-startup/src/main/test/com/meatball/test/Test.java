/**
 * Project Name:meatball-startup
 * File Name:Test.java
 * Package Name:com.meatball.test
 * Date:2018年3月22日下午3:57:44
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.test;

import java.math.BigDecimal;

import com.meatball.api.ykt.enums.DealTypeEnum;

/**   
 * @Title: Test.java 
 * @Package com.meatball.test 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 張翔宇  
 * @date 2018年3月22日 下午3:57:44 
 * @version V1.0   
 */
public class Test {

	public static void main(String[] args) {
		String aaa = "456.36";
		
		Double bbb = Double.valueOf(aaa) * 100;
		System.out.println(bbb.intValue());
	}

}
