/**
 * Project Name:meatball-core
 * File Name:SpringUtil.java
 * Package Name:com.meatball.component
 * Date:2018年2月7日下午4:24:22
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**   
 * @Title: SpringUtil.java 
 * @Package com.meatball.component 
 * @Description: TODO(普通类获取springbean) 
 * @author 張翔宇  
 * @date 2018年2月7日 下午4:24:22 
 * @version V1.0   
 */
@Component
public class SpringUtil implements ApplicationContextAware {
	Logger log = LoggerFactory.getLogger(SpringUtil.class);
	private static ApplicationContext applicationContext = null;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if(SpringUtil.applicationContext == null) {
			SpringUtil.applicationContext = applicationContext;
		}
		log.info("Common class get SpringBean is loads successfully. (普通类获取SpringBean模块加载成功。)");
	}
	
	/**
	 * @Title: getApplicationContext 
	 * @Description: TODO(获取applicationContext) 
	 * @return ApplicationContext    返回类型
	 */
    public static ApplicationContext getApplicationContext() {
       return applicationContext;
    }
    
    /**
     * @Title: getBean 
     * @Description: TODO(通过name获取 Bean.) 
     * @param name
     * @return Object    返回类型
     */
    public static Object getBean(String name){
       return getApplicationContext().getBean(name);
    }
    
    /**
     * @Title: getBean 
     * @Description: TODO(通过class获取Bean.) 
     * @param clazz
     * @return T    返回类型
     */
    public static <T> T getBean(Class<T> clazz){
       return getApplicationContext().getBean(clazz);
    }
    
    /**
     * @Title: getBean 
     * @Description: TODO(通过name,以及Clazz返回指定的Bean) 
     * @param name
     * @param clazz
     * @return T    返回类型
     */
    public static <T> T getBean(String name,Class<T> clazz){
       return getApplicationContext().getBean(name, clazz);
    }
}
