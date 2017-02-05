package cn.digirun.admin.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
 * @ClassName: RequiredAuthority 
 * @Description: 权限注解
 * @author 管东海
 *  
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiredAuthority{

	String name();
	
	String code();
	
	String icon() default "";
}
