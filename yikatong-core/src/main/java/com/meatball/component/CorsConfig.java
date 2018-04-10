/**
 * Project Name:meatball-core
 * File Name:CorsConfig.java
 * Package Name:com.meatball.component
 * Date:2017年10月16日下午3:45:25
 * Copyright (c) 2017, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**   
 * @Title: CorsConfig.java 
 * @Package com.meatball.component 
 * @Description: TODO(跨域请求支持) 
 * @author 張翔宇  
 * @date 2017年10月16日 下午3:45:25 
 * @version V1.0   
 */
@Configuration
public class CorsConfig {
	private Logger log = LoggerFactory.getLogger(CorsConfig.class);
	/**
	 * @Title: buildConfig 
	 * @Description: TODO(配置跨域参数) 
	 * @return CorsConfiguration    返回类型
	 */
	private CorsConfiguration buildConfig() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		// 允许任何域名使用
        corsConfiguration.addAllowedOrigin("*"); 
        // 允许任何头
        corsConfiguration.addAllowedHeader("*");
        // 允许任何方法（post、get等）
        corsConfiguration.addAllowedMethod("*");
        return corsConfiguration;
	}
	
	/**
	 * @Title: corsFilter 
	 * @Description: TODO(注册跨域请求) 
	 * @return CorsFilter    返回类型
	 */
	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		// 设置允许跨域访问目录
        source.registerCorsConfiguration("/api/**", buildConfig());
        log.info("Registered cross-domain support success. (注册跨域支持成功。)");
        return new CorsFilter(source);
	}
}
