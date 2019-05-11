package com.gp12713.mybatis.v1.mapper;

import lombok.extern.slf4j.Slf4j;

import java.util.logging.Logger;
@Slf4j
public class Test {
    public static void main(String[] args) {
        String input = "%d %s %d";
        Object[] objects = new Object[]{1,"2",3};
        String result = String.format(input,objects);
        log.info("result:"+result);
    }
}
