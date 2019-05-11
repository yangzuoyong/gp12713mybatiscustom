package com.gp12713.mybatis.v2.session;

import com.gp12713.mybatis.v2.executor.Executor;

/**
 * MeBatis的API，提供给应用层使用
 */
public class DefaultSqlSession {
    private Configuration configuration;
    private Executor executor;
    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
        // 根据全局配置决定是否使用缓存装饰
        this.executor = configuration.newExecutor();

    }
    public Configuration getConfiguration(){
        return configuration;
    }
    public <T>T getMapper(Class<T> clazz){
        return configuration.getMapper(clazz,this);
    }
    public <T>T selectOne(String statement,Object[]paramenter,Class pojo){
        String sql = getConfiguration().getMappedStatement(statement);
        return executor.query(sql,paramenter,pojo);
    }
}
