package com.hinz.seata.order.controller;


import com.hinz.commonapi.entities.CommonResult;
import com.hinz.seata.order.entities.Order;
import com.hinz.seata.order.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @auther quanhz
 * @create 2020-02-26 15:24
 */
@RestController
public class OrderController
{
    @Resource
    private OrderService orderService;


    @GetMapping("/order/create")
    public CommonResult create(Order order)
    {
        orderService.create(order);
        return CommonResult.ok("订单创建成功");
    }


    @GetMapping("/order/createTraditionalOrder")
    public CommonResult createTraditionalOrder(Order order)
    {
        orderService.createTraditionalOrder(order);
        return CommonResult.ok("订单创建成功");
    }
}
