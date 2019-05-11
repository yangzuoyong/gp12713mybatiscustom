package com.gp12713.mybatis.v2.annotation;

import java.lang.annotation.*;
/**用于注解拦截器，指定拦截的方法*/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Intercepts {
    String value();
}
