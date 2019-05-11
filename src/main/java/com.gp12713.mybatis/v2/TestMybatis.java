package com.gp12713.mybatis.v2;

import com.gp12713.mybatis.v2.mapper.Blog;
import com.gp12713.mybatis.v2.mapper.BlogMapper;
import com.gp12713.mybatis.v2.session.DefaultSqlSession;
import com.gp12713.mybatis.v2.session.SqlSessionFactiory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestMybatis {
    public static void main(String[] args) {

        SqlSessionFactiory factiory = new SqlSessionFactiory();
        DefaultSqlSession sqlSession = factiory.build().openSqlSession();
        //获取MapperProxy代理
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        Blog blog = mapper.selectBlogById(1);
        log.info("第一次查询" + blog);
        blog = mapper.selectBlogById(1);
        log.info("第二次查询：" + blog);
    }

}
