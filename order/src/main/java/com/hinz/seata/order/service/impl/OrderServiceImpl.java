package com.hinz.seata.order.service.impl;

import com.hinz.commonapi.entities.CommonResult;
import com.hinz.seata.order.dao.OrderDao;
import com.hinz.seata.order.entity.Order;
import com.hinz.seata.order.service.AccountService;
import com.hinz.seata.order.service.OrderService;

import com.hinz.seata.order.service.StorageService;
import io.seata.core.context.RootContext;
import io.seata.core.exception.TransactionException;
import io.seata.spring.annotation.GlobalTransactional;
import io.seata.tm.api.GlobalTransactionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @auther quanhz
 * @create 2020-02-26 15:20
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService
{
    @Resource
    private OrderDao orderDao;
    @Resource
    private StorageService storageService;
    @Resource
    private AccountService accountService;

    /**
     * 创建订单->调用库存服务扣减库存->调用账户服务扣减账户余额->修改订单状态
     * 简单说：下订单->扣库存->减余额->改状态
     */
    @Override
    @GlobalTransactional(name = "hinzzz-create-order",rollbackFor = Exception.class)
    public void create()
    {
        log.info("----->开始新建订单");
        //1 新建订单
        Order order = Order.builder()
                .userId(1L)
                .count(10)
                .money(new BigDecimal(100))
                .productId(1L)
                .status(0).build();
        orderDao.create(order);

        //2 扣减库存
        log.info("----->订单微服务开始调用库存，做扣减Count");
        storageService.decrease(order.getProductId(),order.getCount());
        log.info("----->订单微服务开始调用库存，做扣减end");

        int a = 10 /0 ;

        //3 扣减账户
        log.info("----->订单微服务开始调用账户，做扣减Money");
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("----->订单微服务开始调用账户，做扣减end");

        //4 修改订单状态，从零到1,1代表已经完成
        log.info("----->修改订单状态开始");
        orderDao.update(order.getId(),1);
        log.info("----->修改订单状态结束");

        log.info("----->下订单结束了");

    }

    @GlobalTransactional(name = "hinzzz-create-order",rollbackFor = Exception.class)
    @Override
    public CommonResult createNormalOrder() throws TransactionException {
        log.info("order-service txID:{}", RootContext.getXID());
        log.info("----->开始新建订单");
        //1 新建订单
        Order order = Order.builder()
                .userId(1L)
                .count(10)
                .money(new BigDecimal(100))
                .productId(1L)
                .status(0).build();
        orderDao.create(order);

        //2 扣减库存
        log.info("----->订单微服务开始调用库存，做扣减Count");
        storageService.decrease(order.getProductId(),order.getCount());
        log.info("----->订单微服务开始调用库存，做扣减end");

        //3 扣减账户
        log.info("----->订单微服务开始调用账户，做扣减Money");
        CommonResult accountResult = accountService.decrease(order.getUserId(), order.getMoney());

        try {
            if(501==accountResult.getCode()){
                throw new RuntimeException(accountResult.get("msg").toString());
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            GlobalTransactionContext.reload(RootContext.getXID()).rollback();
            return CommonResult.error(accountResult.get("msg").toString());
        }
        log.info("----->订单微服务开始调用账户，做扣减end");

        //4 修改订单状态，从零到1,1代表已经完成
        log.info("----->修改订单状态开始");
        orderDao.update(order.getId(),1);
        log.info("----->修改订单状态结束");

        log.info("----->下订单结束了");
        return CommonResult.ok();
    }

    @Transactional(rollbackFor = Exception.class)
    public void createTraditionalOrder()
    {
        log.info("----->开始新建订单");
        //1 新建订单
        Order order = Order.builder()
                .userId(1L)
                .count(10)
                .money(new BigDecimal(100))
                .productId(1L)
                .status(0).build();
        orderDao.create(order);

        //2 扣减库存
        log.info("----->订单微服务开始调用库存，做扣减Count");
        storageService.decrease(order.getProductId(),order.getCount());
        log.info("----->订单微服务开始调用库存，做扣减end");
        int a = 10 /0 ;

        //3 扣减账户
        log.info("----->订单微服务开始调用账户，做扣减Money");
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("----->订单微服务开始调用账户，做扣减end");

        //4 修改订单状态，从零到1,1代表已经完成
        log.info("----->修改订单状态开始");
        orderDao.update(order.getId(),1);
        log.info("----->修改订单状态结束");

        log.info("----->下订单结束了");

    }
}
