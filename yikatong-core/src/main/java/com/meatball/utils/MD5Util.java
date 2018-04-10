/**
 * Project Name:meatball-core
 * File Name:MD5Util.java
 * Package Name:com.meatball.utils
 * Date:2017年10月10日下午12:03:30
 * Copyright (c) 2017, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.utils;

import java.util.Random;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**   
 * @Title: MD5Util.java 
 * @Package com.meatball.utils 
 * @Description: TODO(带盐加密MD5) 
 * @author 張翔宇  
 * @date 2017年10月10日 下午12:03:30 
 * @version V1.0   
 */
public class MD5Util {
	// 加盐参数
    public final static String hashAlgorithmName = "MD5";

    // 循环次数
    public final static int hashIterations = 1024;

    /**
     * @Title: md5 
     * @Description: TODO(shiro密码加密工具类) 
     * @param credentials 密码
     * @param saltSource	密码盐
     * @return String    返回类型
     */
    public static String md5(String credentials, String saltSource) {
        ByteSource salt = new Md5Hash(saltSource);
        return new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations).toString();
    }
    
    /**
     * @Title: getRandomString 
     * @Description: TODO(获取一个随机字符串，用于生成用户盐) 
     * @param length
     * @return String    返回类型
     */
    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
