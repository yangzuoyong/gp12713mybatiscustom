package com.gp12713.mybatis.v2.session;

/**
 * 会话工厂类，用于解析配置文件，产生SqlSession
 */
public class SqlSessionFactiory {
    private Configuration configuration;

    /**
     * build方法用于初始化Configuration，解析配置文件的工作在Configuration的构造函数中
     * @return
     */
    public SqlSessionFactiory build(){
        configuration = new Configuration();
        return this;
    }

    /**
     * 获取DefaultSqlSession
     * @return
     */
    public DefaultSqlSession openSqlSession(){return new DefaultSqlSession(configuration);}
}

