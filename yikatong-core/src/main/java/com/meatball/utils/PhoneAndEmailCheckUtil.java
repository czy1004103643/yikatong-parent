/** 
 * Project Name:meatball-core 
 * File Name:PhoneFormatCheckUtil.java 
 * Package Name:com.meatball.utils 
 * Date:2017年10月7日下午1:37:02 
 * Copyright (c) 2017, zhang.xiangyu@foxmail.com All Rights Reserved. 
 */  
package com.meatball.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**   
 * @Title: PhoneFormatCheckUtil.java 
 * @Package com.meatball.utils 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 張翔宇  
 * @date 2017年10月7日 下午1:37:02 
 * @version V1.0   
 */
public class PhoneAndEmailCheckUtil {
	
	/**
	 * @Title: isPhoneLegal 
	 * @Description: TODO(大陆号码或香港号码均可 )  
	 * @param str
	 * @throws PatternSyntaxException    设定文件 
	 * @return boolean    返回类型 
	 */
    public static boolean isPhoneLegal(String str)throws PatternSyntaxException {  
        return isChinaPhoneLegal(str) || isHKPhoneLegal(str);  
    }  
  
    /**
     * @Title: isChinaPhoneLegal 
     * @Description: TODO(
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数 
     * 此方法中前三位格式有：
     * 13+任意数 
     * 15+除4的任意数 
     * 18+除1和4的任意数 
     * 17+除9的任意数 
     * 147 ) 
     * @param str
     * @throws PatternSyntaxException    设定文件 
     * @return boolean    返回类型 
     */
    private static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {  
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";  
        Pattern p = Pattern.compile(regExp);  
        Matcher m = p.matcher(str);  
        return m.matches();  
    }  
  
    /**
     * @Title: isHKPhoneLegal 
     * @Description: TODO(香港手机号码8位数，5|6|8|9开头+7位任意数 ) 
     * @param str
     * @throws PatternSyntaxException    设定文件 
     * @return boolean    返回类型 
     */
    private static boolean isHKPhoneLegal(String str)throws PatternSyntaxException {  
        String regExp = "^(5|6|8|9)\\d{7}$";  
        Pattern p = Pattern.compile(regExp);  
        Matcher m = p.matcher(str);  
        return m.matches();  
    }  
    
    /**
     * @Title: isEmaile 
     * @Description: TODO(正则表达式校验邮箱) 
     * @param emaile
     * @return boolean    返回类型
     */
	public static boolean isEmail(String emaile) {
        String RULE_EMAIL = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
        //正则表达式的模式
        Pattern p = Pattern.compile(RULE_EMAIL);
        //正则表达式的匹配器
        Matcher m = p.matcher(emaile);
        //进行正则匹配
        return m.matches();
    }
}
  