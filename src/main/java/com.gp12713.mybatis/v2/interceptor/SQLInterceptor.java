package com.gp12713.mybatis.v2.interceptor;

import com.gp12713.mybatis.v2.annotation.Intercepts;
import com.gp12713.mybatis.v2.plugin.Interceptor;
import com.gp12713.mybatis.v2.plugin.Invocation;
import com.gp12713.mybatis.v2.plugin.Plugin;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
@Intercepts("sql")
@Slf4j
public class SQLInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        long startTime = System.currentTimeMillis();
        StatementHandler statementHandler = (StatementHandler)invocation.getTarget();
        BoundSql boundSql = statementHandler.getBoundSql();
        String sql = boundSql.getSql();
        log.info("获取SQL："+sql);
        try {
            return invocation.proceed();
        } finally {
            long endTime = System.currentTimeMillis();
            log.info("sql执行耗时 : " + (endTime - startTime) + "ms");
        }
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }
}
