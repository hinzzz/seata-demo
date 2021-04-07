package com.hinz.seata.order.controller;


import com.hinz.commonapi.entities.CommonResult;
import com.hinz.seata.order.service.OrderService;
import io.seata.core.exception.TransactionException;
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
    public CommonResult create()
    {
        orderService.create();
        return CommonResult.ok("订单创建成功");
    }

    @GetMapping("/order/createNormalOrder")
    public CommonResult createNormalOrder()
    {
        try {
            return orderService.createNormalOrder();
        } catch (TransactionException e) {
            e.printStackTrace();
        }
        return CommonResult.error("订单创建失败");
    }


    @GetMapping("/order/createTraditionalOrder")
    public CommonResult createTraditionalOrder()
    {
        orderService.createTraditionalOrder();
        return CommonResult.ok("订单创建成功");
    }
}
