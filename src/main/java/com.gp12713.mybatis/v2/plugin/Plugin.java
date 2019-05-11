package com.gp12713.mybatis.v2.plugin;

import com.gp12713.mybatis.v2.annotation.Intercepts;
import com.gp12713.mybatis.v2.interceptor.MyPlugin;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Plugin implements InvocationHandler {
    private Object target;
    private Interceptor interceptor;

    public Plugin(Object target, Interceptor interceptor) {
        this.target = target;
        this.interceptor = interceptor;
    }

    /**
     * 对被代理对象进行代理，返回代理类
     * @param target
     * @param interceptor
     * @return
     */
    public static Object wrap(Object target, Interceptor interceptor) {
        Class clazz = target.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),new Plugin(target,interceptor));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //自定义的插件上有@Intercepts注解，指定了拦截的方法
        if(interceptor.getClass().isAnnotationPresent(Intercepts.class)){
            //如果是被拦截的方法，则进入自定义拦截器的逻辑
            if (method.getName().equals(interceptor.getClass().getAnnotation(Intercepts.class).value())) {
                return interceptor.intercept(new Invocation(target,method,args));
            }
        }
        //非被拦截方法，执行原逻辑
        return method.invoke(target,method,args);
    }
}
