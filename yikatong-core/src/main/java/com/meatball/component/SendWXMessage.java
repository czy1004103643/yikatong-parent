package com.meatball.component;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: SendWXMessage 
 * @Description: TODO(微信消息发送) 
 * @author 張翔宇
 * @date 2017年4月18日 下午5:02:58 
 *
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SendWXMessage {
}
