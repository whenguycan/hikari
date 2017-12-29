package com.lepus.hikari.framework.build.json;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author wangchenyu
 * @date 2017-12-23
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Json {

	Class<?> type();
	String include() default "";
	String exclude() default "";
	
}
