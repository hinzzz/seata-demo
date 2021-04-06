package com.hinz.seata.order.dao;

import com.hinz.seata.order.entity.Order;
import org.apache.ibatis.annotations.*;

/**
 * @auther quanhz
 * @create 2020-02-26 15:17
 */
@Mapper
public interface OrderDao
{
    //1 新建订单
    @Insert("INSERT INTO `t_order`(`user_id`, `product_id`, `count`, `money`, `status`) " +
            "VALUES (1, 1, 10, 100, 0)")
    @SelectKey(statement = "select LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Long.class)
    void create(Order order);

    //2 修改订单状态，从零改为1
    @Update("update t_order set status=#{status} where id = #{id}")
    void update(@Param("id") Long id,@Param("status") Integer status);
}