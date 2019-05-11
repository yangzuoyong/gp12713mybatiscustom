package com.gp12713.mybatis.v2.annotation;

import java.lang.annotation.*;
/**注解方法，配置SQL语句*/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Select {
    String value();
}
