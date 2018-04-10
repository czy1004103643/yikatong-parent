/**
 * Project Name:meatball-core
 * File Name:MeatballWebAppConfigurer.java
 * Package Name:com.meatball.component
 * Date:2018年3月5日上午10:22:17
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.Interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**   
 * @Title: MeatballWebAppConfigurer.java 
 * @Package com.meatball.component 
 * @Description: TODO(注册自定义拦截器) 
 * @author 張翔宇  
 * @date 2018年3月5日 上午10:22:17 
 * @version V1.0   
 */
@Configuration
public class MeatballWebAppConfigurer extends WebMvcConfigurerAdapter {
	
	/**
	 * 注册自定义拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new MeatballApiInterceptor()).addPathPatterns("/api/**").excludePathPatterns("/api/login/*");
		super.addInterceptors(registry);
	}
}
