package com.gp12713.mybatis.v1;

import com.gp12713.mybatis.v1.mapper.BlogMapper;

public class ZYSqlSession {
    private ZYConfiguration configuration;
    private ZYExecutor executor;
    public ZYSqlSession(ZYConfiguration configuration, ZYExecutor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }
    public <T> T selectOne(String statementId,Object paramater){
        String sql = ZYConfiguration.sqlMappings.getString(statementId);
        if(null != sql && !"".equals(sql)){
            return executor.query(sql, paramater );
        }
        return null;
    }
    public <T> T getMapper(Class clazz) {
        return configuration.getMapper(clazz,this);
    }
}
