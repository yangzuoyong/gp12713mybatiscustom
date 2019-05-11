package com.gp12713.mybatis.v2.interceptor;

import com.gp12713.mybatis.v2.annotation.Intercepts;
import com.gp12713.mybatis.v2.plugin.Interceptor;
import com.gp12713.mybatis.v2.plugin.Invocation;
import com.gp12713.mybatis.v2.plugin.Plugin;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 自定义插件
 */
@Intercepts("query")
@Slf4j
public class MyPlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        String statement =(String)invocation.getArgs()[0];
        Object[] parameter =(Object[])invocation.getArgs()[1];
        Class pojo = (Class)invocation.getArgs()[2];
        log.info("插件输出：SQL：["+statement+"]");
        log.info("插件输出：Parameters："+ Arrays.toString(parameter));
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }
}
