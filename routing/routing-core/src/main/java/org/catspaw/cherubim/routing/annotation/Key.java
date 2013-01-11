package org.catspaw.cherubim.routing.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.catspaw.cherubim.routing.AccessStrategy;

@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Key {

	/**
	 * 路由应用范围
	 * 如果指定应用范围，则这个路由只对指定的类（dao或service）有效
	 * @return
	 */
	Class<?>[] targetClass() default Object.class;

	String[] target() default "";

	/**
	 * 属性名
	 * 表示此注解作用于标注对象的属性（标注到方法上时表示方法所属对象的属性）
	 * @return
	 */
	String property() default "";

	/**
	 * key的类别
	 * @return
	 */
	String kind() default "";

	String kindProperty() default "";

	/**
	 * 路由的类别
	 * @return
	 */
	String type() default "";

	AccessStrategy accessStrategy() default AccessStrategy.DEFAULT;
}
