package com.hinz.seata.storage.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface StorageDao {

    //扣减库存

    @Update("update t_storage set used= used + #{count}")
    void decrease(@Param("productId") Long productId, @Param("count") Integer count);
}
