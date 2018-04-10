package com.meatball;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Title: MeatballApplication.java 
 * @Package com.meatball 
 * @Description: TODO(启动类)
 * @author 張翔宇  
 * @date 2017年10月10日 下午12:17:06 
 * @version V1.0
 */
@MapperScan("**.dao")
@SpringBootApplication
//@EnableEurekaClient
public class MeatballApplication {
	private static final Logger log = LoggerFactory.getLogger(MeatballApplication.class);
	
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(MeatballApplication.class);
		application.setBannerMode(Banner.Mode.OFF);
		application.run(args);
		log.info("Service startup complete. (服务启动完成。)");
	}
}
