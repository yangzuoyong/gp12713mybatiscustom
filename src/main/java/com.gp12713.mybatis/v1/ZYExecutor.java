package com.gp12713.mybatis.v1;

import com.gp12713.mybatis.v1.mapper.Blog;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;

@Slf4j
public class ZYExecutor {
    public <T> T query(String sql, Object paramater) {
        Connection conn =null;
        Statement stmt = null;
        Blog blog = new Blog();

        try {
            //1.注册JDBC驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2、打开连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gp_mybatis","root","123456");
            //3、执行查询
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format(sql,paramater));
            //4、获取结果集
            while (rs.next()){
                Integer bid = rs.getInt("bid");
                String name=rs.getString("name");
                Integer authorId = rs.getInt("autor_id");
                blog.setAuthorId(authorId);
                blog.setBid(bid);
                blog.setName(name);
            }
            System.out.println(blog);
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } finally {

            try {
                if (null!=stmt){stmt.close();}
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (null!=conn){conn.close();}
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return (T)blog;
    }
}
