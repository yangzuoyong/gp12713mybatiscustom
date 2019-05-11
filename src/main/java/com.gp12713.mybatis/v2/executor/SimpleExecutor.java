package com.gp12713.mybatis.v2.executor;

public class SimpleExecutor implements Executor{
    @Override
    public <T> T query(String statement, Object[] parameter, Class pojo) {
        StatementHandler statementHandler = new StatementHandler();
        return statementHandler.query(statement,parameter,pojo);
    }
}
