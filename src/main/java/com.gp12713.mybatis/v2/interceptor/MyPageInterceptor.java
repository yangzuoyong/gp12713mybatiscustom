package com.gp12713.mybatis.v2.interceptor;

import com.gp12713.mybatis.v2.annotation.Intercepts;
import com.gp12713.mybatis.v2.plugin.Interceptor;
import com.gp12713.mybatis.v2.plugin.Invocation;
import com.gp12713.mybatis.v2.plugin.Plugin;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Field;

/**
 * [逻辑分页->物理分页]插件
 */
@Intercepts("page")
@Slf4j
public class MyPageInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        log.info("[逻辑分页->物理分页]插件 begin:");
        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        BoundSql boundSql = ms.getBoundSql(args[1]);
        RowBounds rb = (RowBounds) args[2];

        if(rb == RowBounds.DEFAULT) {
            return invocation.proceed();
        }

        String sql = boundSql.getSql();
        String limit = String.format("LIMIT %d , %d" , rb.getOffset() , rb.getLimit());
        sql = sql + " " + limit;

        SqlSource sqlSource = new StaticSqlSource(ms.getConfiguration() , sql , boundSql.getParameterMappings());
        Field field = MappedStatement.class.getDeclaredField("sqlSource");
        field.setAccessible(true);
        field.set(ms , sqlSource);
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }
}
