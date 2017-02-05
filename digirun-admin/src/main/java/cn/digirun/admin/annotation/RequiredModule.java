package cn.digirun.admin.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
 * @ClassName: RequiredModule 
 * @Description: 模块注解
 * @author 管东海
 *  
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiredModule {

	String name();

	Class<?> parent() default Object.class;

}
