package com.gp12713.mybatis.v1;

import com.gp12713.mybatis.v1.mapper.BlogMapper;

public class MyBatisBoot {
    public static void main(String[] args) {
        ZYSqlSession sqlSession = new ZYSqlSession(new ZYConfiguration(), new ZYExecutor());
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
        blogMapper.selectBlogById(1);
    }
}
