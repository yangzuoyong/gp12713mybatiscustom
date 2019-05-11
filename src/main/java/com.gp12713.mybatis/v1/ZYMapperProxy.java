package com.gp12713.mybatis.v1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ZYMapperProxy implements InvocationHandler {
    private ZYSqlSession sqlSession;
    public ZYMapperProxy(ZYSqlSession sqlSession) {
        this.sqlSession =sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String mapperInterface = method.getDeclaringClass().getName();
        String methodName = method.getName();
        String statementId = mapperInterface + "." + methodName;
        return sqlSession.selectOne(statementId,args[0]);
    }
}
