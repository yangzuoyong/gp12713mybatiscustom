package com.gp12713.mybatis.v1.mapper;

import com.gp12713.mybatis.v2.annotation.Entity;
import com.gp12713.mybatis.v2.annotation.Select;

@Entity(Blog.class)
public interface BlogMapper {
    @Select("select * from blog where bid = %d")
    public Blog selectBlogById(Integer bid);
}
