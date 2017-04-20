package com.example.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解,为方法添加说明
 * 作用目标:方法和域,保留策略:保留到运行时
 * @author user
 *
 */
@Target({ElementType.METHOD,ElementType.FIELD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Notes {

	String author() default "";                //作者        
	String lastmodifier() default "";          //修改者
	String date() default "";                  //最后一次修改日期
	String value() default "";                 //说明
}
