package com.meatball.component;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: SystemServiceLog 
 * @Description: TODO(自定义注解 异常日志 拦截service) 
 * @author 張翔宇
 * @date 2017年4月17日 下午3:06:29 
 *
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ErrorLog {
	String value()  default "";  
}
