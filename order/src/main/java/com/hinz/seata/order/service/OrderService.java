package com.hinz.seata.order.service;


import com.hinz.commonapi.entities.CommonResult;
import io.seata.core.exception.TransactionException;

/**
 * @auther quanhz
 * @create 2020-02-26 15:19
 */
public interface OrderService
{
    void create();

    CommonResult createNormalOrder() throws TransactionException;

    void createTraditionalOrder();
}
