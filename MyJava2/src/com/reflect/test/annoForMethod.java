package com.reflect.test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value=ElementType.METHOD)
@Retention(value=RetentionPolicy.RUNTIME)
public @interface annoForMethod {

	public enum MethodType{type0,type1,type2}
	
	String value() default "";
	
	MethodType methodType() default MethodType.type0;
}
