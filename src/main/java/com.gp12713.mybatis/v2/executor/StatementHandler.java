package com.gp12713.mybatis.v2.executor;

import com.gp12713.mybatis.v2.parameter.ParameterHandler;
import com.gp12713.mybatis.v2.session.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 封装JDBC Statement，用于操作数据库
 */
public class StatementHandler {
    private ResultSetHandler resultSetHandler = new ResultSetHandler();
    public <T> T query(String statement, Object[] parameter, Class pojo) {
        Connection conn = null;
        PreparedStatement pstm = null;
        Object result = null;
        try {
            conn = getConnection();
            pstm = conn.prepareStatement(statement);
            ParameterHandler parameterHandler = new ParameterHandler(pstm);
            parameterHandler.setParameters(parameter);
            pstm.execute();
            result = resultSetHandler.handle(pstm.getResultSet(),pojo);
            try{
                result=resultSetHandler.handle(pstm.getResultSet(),pojo);
            }catch (Exception e){
                e.printStackTrace();
            }
            return (T)result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(conn!=null){
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // 只在try里面return会报错
        return null;
    }

    /**
     * 获取连接
     * @return
     */
    private Connection getConnection() {
        String driver = Configuration.properties.getString("jdbc.driver");
        String url = Configuration.properties.getString("jdbc.url");
        String userName=Configuration.properties.getString("jdbc.username");
        String password = Configuration.properties.getString("jdbc.password");
        Connection conn = null;

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url,userName,password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
