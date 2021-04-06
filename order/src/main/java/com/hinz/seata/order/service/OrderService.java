package com.hinz.seata.order.service;


/**
 * @auther quanhz
 * @create 2020-02-26 15:19
 */
public interface OrderService
{
    void create();

    void createNormalOrder();

    void createTraditionalOrder();
}
