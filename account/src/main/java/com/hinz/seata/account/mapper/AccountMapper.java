package com.hinz.seata.account.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hinz.seata.account.entity.Account;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;

public interface AccountMapper extends BaseMapper<Account> {

    /**
     * 扣减账户余额
     */
    @Update("update t_account set used = used + #{money} where id = #{id}")
    void decrease(@Param("id") Long id, @Param("money") BigDecimal money);
}
