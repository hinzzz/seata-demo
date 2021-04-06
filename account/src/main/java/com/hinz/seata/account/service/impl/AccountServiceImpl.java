package com.hinz.seata.account.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hinz.commonapi.entities.CommonResult;
import com.hinz.seata.account.mapper.AccountMapper;
import com.hinz.seata.account.entity.Account;
import com.hinz.seata.account.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 账户业务实现类
 * Created by quanhz on 2019/11/11.
 */
@Service
@Slf4j
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    @Resource
    AccountMapper accountDao;

    /**
     * 扣减账户余额
     */
    @Override
    public CommonResult decrease(Long userId, BigDecimal money) {
        log.info("------->account-service中扣减账户余额开始");
        Account account = accountDao.selectById(1);
        if(money.add(account.getUsed()).compareTo(account.getTotal()) > 0){
            return CommonResult.error(501,"余额不足");
        }
        accountDao.decrease(userId, money);
        log.info("------->account-service中扣减账户余额结束");
        return  CommonResult.ok();
    }
}
