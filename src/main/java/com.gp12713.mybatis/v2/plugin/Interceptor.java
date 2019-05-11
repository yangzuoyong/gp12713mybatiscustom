package com.gp12713.mybatis.v2.plugin;

/**
 * 拦截器接口，所有自定义拦截器必须实现此接口
 */
public interface Interceptor {
    /**
     *插件的核心逻辑实现
     * @param invocation
     * @return
     * @throws Throwable
     */
    public Object intercept(Invocation invocation)throws Throwable;

    /**
     * 对被拦截对象进行代理
     * @param target
     * @return
     */
    public Object plugin(Object target);
}

