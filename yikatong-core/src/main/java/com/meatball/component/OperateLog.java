package com.meatball.component;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: SystemControllerLog 
 * @Description: TODO(自定义注解 操作日志 拦截Controller) 
 * @author 張翔宇
 * @date 2017年4月17日 下午2:59:54 
 *
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperateLog {
	String value() default "";
}
