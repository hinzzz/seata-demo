package com.hinz.seata.storage.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @auther quanhz
 * @create 2019-12-11 16:57
 */
@Configuration
@MapperScan({"com.hinz.seata.storage.dao"})
public class MyBatisConfig {
}
