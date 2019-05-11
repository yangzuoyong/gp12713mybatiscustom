package com.gp12713.mybatis.v1.mapper;

import lombok.Data;

import java.io.Serializable;
@Data
public class Blog implements Serializable {
    //文章ID
    private Integer bid;
    //文章标题
    private String name;
    //文章作者Id
    private Integer authorId;
}
