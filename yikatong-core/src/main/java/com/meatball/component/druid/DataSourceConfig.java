/**
 * Project Name:meatball-core
 * File Name:DataSourceConfig.java
 * Package Name:com.meatball.component.druid
 * Date:2018年1月13日下午12:39:50
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.component.druid;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

/**
 * @Title: DataSourceConfig.java
 * @Package com.meatball.component.druid
 * @Description: TODO(数据库监控)
 * @author 張翔宇
 * @date 2018年1月13日 下午12:39:50
 * @version V1.0
 */
@Configuration
public class DataSourceConfig {
	// JDBC url
	private static String dbUrl;

	// 数据库登陆账号
	private static String username;

	// 数据库登陆密码
	private static String password;

	// 数据库驱动名称
	private static String driverClassName;

	// 初始大小
	private static int initialSize;

	// 最小
	private static int minIdle;
	
	// 最大连接数
	private static int maxActive;

	// 连接等待超时时间
	private static int maxWait;

	/**
	 * @Title: druidServletRegistrationBean 
	 * @Description: TODO(注册DruidServlet) 
	 * @return ServletRegistrationBean    返回类型
	 */
	@Bean
	public ServletRegistrationBean druidServletRegistrationBean() {
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
		servletRegistrationBean.setServlet(new StatViewServlet());
		servletRegistrationBean.addUrlMappings("/druid/*");
		return servletRegistrationBean;
	}

	/**
	 * @Title: druidFilterRegistrationBean 
	 * @Description: TODO(注册DruidFilter拦截) 
	 * @return
	 * @return FilterRegistrationBean    返回类型
	 */
	@Bean
	public FilterRegistrationBean druidFilterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new WebStatFilter());
		Map<String, String> initParams = new HashMap<String, String>();
		// 设置忽略请求
		initParams.put("exclusions", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");
		filterRegistrationBean.setInitParameters(initParams);
		filterRegistrationBean.addUrlPatterns("/*");
		return filterRegistrationBean;
	}

	/**
	 * @Title: dataSource 
	 * @Description: TODO(配置DataSource) 
	 * @throws SQLException
	 * @return DataSource    返回类型
	 */
	@Bean(initMethod = "init", destroyMethod = "close")
	@Primary
	public DataSource dataSource() throws SQLException {
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setUsername(username);
		druidDataSource.setPassword(password);
		druidDataSource.setUrl(dbUrl);
		druidDataSource.setFilters("stat,wall");
		druidDataSource.setInitialSize(initialSize);
		druidDataSource.setMinIdle(minIdle);
		druidDataSource.setMaxActive(maxActive);
		druidDataSource.setMaxWait(maxWait);
		druidDataSource.setUseGlobalDataSourceStat(true);
		druidDataSource.setDriverClassName(driverClassName);
		return druidDataSource;
	}

	@Value("${spring.datasource.url}")
	public void setDbUrl(String dbUrl) {
		DataSourceConfig.dbUrl = dbUrl;
	}

	@Value("${spring.datasource.username}")
	public void setUsername(String username) {
		DataSourceConfig.username = username;
	}

	@Value("${spring.datasource.password}")
	public void setPassword(String password) {
		DataSourceConfig.password = password;
	}

	@Value("${spring.datasource.driver-class-name}")
	public void setDriverClassName(String driverClassName) {
		DataSourceConfig.driverClassName = driverClassName;
	}

	@Value(value = "${spring.datasource.initialSize}")
	public void setInitialSize(int initialSize) {
		DataSourceConfig.initialSize = initialSize;
	}

	@Value(value = "${spring.datasource.minIdle}")
	public void setMinIdle(int minIdle) {
		DataSourceConfig.minIdle = minIdle;
	}

	@Value(value = "${spring.datasource.maxActive}")
	public void setMaxActive(int maxActive) {
		DataSourceConfig.maxActive = maxActive;
	}

	@Value(value = "${spring.datasource.maxWait}")
	public void setMaxWait(int maxWait) {
		DataSourceConfig.maxWait = maxWait;
	}
}
