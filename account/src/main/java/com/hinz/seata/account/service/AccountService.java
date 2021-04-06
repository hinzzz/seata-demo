package com.hinz.seata.account.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hinz.commonapi.entities.CommonResult;
import com.hinz.seata.account.entity.Account;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;


public interface AccountService extends IService<Account> {

    /**
     * 扣减账户余额
     * @param userId 用户id
     * @param money 金额
     */
    CommonResult decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);
}
