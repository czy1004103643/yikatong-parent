/**
 * Project Name:meatball-rest
 * File Name:DealTypeEnum.java
 * Package Name:com.meatball.api.ykt.enums
 * Date:2018年3月22日下午4:00:14
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.api.ykt.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**   
 * @Title: DealTypeEnum.java 
 * @Package com.meatball.api.ykt.enums 
 * @Description: TODO(交易类型枚举) 
 * @author 張翔宇  
 * @date 2018年3月22日 下午4:00:14 
 * @version V1.0   
 */
public enum DealTypeEnum {
	挂号(1), 门诊费(2), 住院费(3), 余额(4);  
  
    private final int value;  
  
    /** 
     * enum lookup map 
     */  
    private static final Map<Integer, DealTypeEnum> lookup = new HashMap<Integer, DealTypeEnum>();  
  
    static {  
        for (DealTypeEnum s : EnumSet.allOf(DealTypeEnum.class)) {  
            lookup.put(s.getValue(), s);  
        }  
    }  
  
    DealTypeEnum(int value) {  
        this.value = value;  
    }  
  
    public int getValue() {  
        return value;  
    }  
  
    public static DealTypeEnum lookup(int value) {
        return lookup.get(value);
    } 
}
