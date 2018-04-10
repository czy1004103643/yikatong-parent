/**
 * Project Name:meatball-startup
 * File Name:EnumTest.java
 * Package Name:com.meatball.test
 * Date:2018年3月22日下午3:56:30
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.test;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**   
 * @Title: EnumTest.java 
 * @Package com.meatball.test 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 張翔宇  
 * @date 2018年3月22日 下午3:56:30 
 * @version V1.0   
 */
public enum EnumTest {
	RESERVED(0), PING(1), PING_ACK(2), KEY_EXCHANGE(3), KEY_EXCHANGE_ACK(4), CONNECT(5), CONNECT_ACK(6), DISCONNECT(7), KEEP_ALIVE(  
            8), KEEP_ALIVE_ACK(9)   ;  
  
    private final int value;  
  
    /** 
     * enum lookup map 
     */  
    private static final Map<Integer, EnumTest> lookup = new HashMap<Integer, EnumTest>();  
  
    static {  
        for (EnumTest s : EnumSet.allOf(EnumTest.class)) {  
            lookup.put(s.getValue(), s);  
        }  
    }  
  
    EnumTest(int value) {  
        this.value = value;  
    }  
  
    public int getValue() {  
        return value;  
    }  
  
    public static EnumTest lookup(int value) {  
        return lookup.get(value);  
    }  
}
