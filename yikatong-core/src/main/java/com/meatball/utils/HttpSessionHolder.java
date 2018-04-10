/** 
 * Project Name:meatball-core 
 * File Name:PhoneFormatCheckUtil.java 
 * Package Name:com.meatball.utils 
 * Date:2017年10月7日下午1:37:02 
 * Copyright (c) 2017, zhang.xiangyu@foxmail.com All Rights Reserved. 
 */ 
package com.meatball.utils;

import javax.servlet.http.HttpSession;

/**
 * @ClassName: HttpSessionHolder 
 * @Description: TODO(非Controller中获取当前session的工具类) 
 * @author 張翔宇
 * @date 2017年10月8日 下午3:38:21
 */
public class HttpSessionHolder {

    private static ThreadLocal<HttpSession> tl = new ThreadLocal<HttpSession>();

    public static void put(HttpSession s) {
        tl.set(s);
    }

    public static HttpSession get() {
        return tl.get();
    }

    public static void remove() {
        tl.remove();
    }
}
