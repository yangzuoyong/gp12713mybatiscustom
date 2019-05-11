package com.gp12713.mybatis.v2.mapper;

import com.gp12713.mybatis.v2.annotation.Entity;
import com.gp12713.mybatis.v2.annotation.Select;

@Entity(Blog.class)
public interface BlogMapper {
    /**
     * 根据主键查询文章
     * @param bid
     * @return
     */
    @Select("select * from blog where bid = ?")
    public Blog selectBlogById(Integer bid);
}
