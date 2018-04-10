/**
 * Project Name:meatball-startup
 * File Name:AlipayTest.java
 * Package Name:com.meatball.test
 * Date:2018年3月22日下午2:35:53
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.meatball.MeatballApplication;
import com.meatball.utils.pay.AlipayTradePrecreate;

/**   
 * @Title: AlipayTest.java 
 * @Package com.meatball.test 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 張翔宇  
 * @date 2018年3月22日 下午2:35:53 
 * @version V1.0   
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MeatballApplication.class)
public class AlipayTest {
	@Resource
	private AlipayTradePrecreate alipayTradePrecreate;
	
	@Test
	public void placeOder() {
		
	}
}
