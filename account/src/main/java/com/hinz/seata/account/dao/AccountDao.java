package com.hinz.seata.account.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;

@Mapper
public interface AccountDao {

    /**
     * 扣减账户余额
     */
    @Update("update t_account set used = used + #{money} where user_id = #{userId}")
    void decrease(@Param("userId") Long userId, @Param("money") BigDecimal money);
}
