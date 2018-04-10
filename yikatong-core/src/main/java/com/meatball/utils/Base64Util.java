/**
 * Project Name:meatball-core
 * File Name:Base64Util.java
 * Package Name:com.meatball.utils
 * Date:2017年10月14日下午5:11:58
 * Copyright (c) 2017, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.utils;

import java.io.UnsupportedEncodingException;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**   
 * @Title: Base64Util.java 
 * @Package com.meatball.utils 
 * @Description: TODO() 
 * @author 張翔宇  
 * @date 2017年10月14日 下午5:11:58 
 * @version V1.0   
 */
public class Base64Util {
	private static final Logger logger = LoggerFactory.getLogger(Base64Util.class);

    private static final String UTF_8 = "UTF-8";

    /**
     * @Title: decodeData 
     * @Description: TODO(对给定的字符串进行base64解码操作) 
     * @param inputData
     * @return String    返回类型
     */
    public static String decodeData(String inputData) {
        try {
            if (null == inputData) {
                return null;
            }
            return new String(Base64.decodeBase64(inputData.getBytes(UTF_8)), UTF_8);
        } catch (UnsupportedEncodingException e) {
            logger.error(inputData, e);
        }

        return null;
    }

    /**
     * @Title: encodeData 
     * @Description: TODO(对给定的字符串进行base64加密操作) 
     * @param inputData
     * @return String    返回类型
     */
    public static String encodeData(String inputData) {
        try {
            if (null == inputData) {
                return null;
            }
            return new String(Base64.encodeBase64(inputData.getBytes(UTF_8)), UTF_8);
        } catch (UnsupportedEncodingException e) {
            logger.error(inputData, e);
        }

        return null;
    }

    public static void main(String[] args) {
        System.out.println(Base64Util.encodeData("A"));
    }
}
