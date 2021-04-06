package com.hinz.seata.order.service;


import com.hinz.seata.order.entities.Order;

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
