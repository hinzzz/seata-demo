package com.hinz.seata.storeage.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @auther quanhz
 * @create 2019-12-11 16:57
 */
@Configuration
@MapperScan({"com.hinz.seata.storeage.dao"})
public class MyBatisConfig {
}
