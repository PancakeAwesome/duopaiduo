/** 
 * Copyright 2014 jrzmq All Rights Reserved.
 * 项目名：jrzmq-core
 * 包名：com.jrzmq.core.web.annotation
 * 文件名：RequestJson.java 
 * 日期：2014-11-25 10:21
 */
package com.jrzmq.core.web.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * springmvc json的request请求转对象的注解.
 * @author wusimai
 *
 */
@Target({java.lang.annotation.ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestJson {
    /**
     * 编码方式，默认base64
     * @return
     */
    String encoder() default "base64";
}
