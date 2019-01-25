package com.star95.study.spring.service.impl;

import com.star95.study.spring.dao.UserBalanceDao;
import com.star95.study.spring.po.UserBalancePO;
import com.star95.study.spring.service.UserBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author hemingzhun
 * @date 2019/1/24
 */

@Component("transactionWithSharedTxProxy")
public class TransactionWithSharedTxProxyImpl implements UserBalanceService {

    @Autowired()
    private UserBalanceDao userBalanceDao;

    @Override
    public List<UserBalancePO> getAllList() {
        return userBalanceDao.getAllList();
    }

    @Override
    public void transferAmount(int fromUserId, int toUserId, double amount, boolean needException) {
        System.out.println("所有Bean共享同一个代理事务-转账开始");
        System.out.println(String.format("用户ID：%s 给用户ID：%s 转账金额：%s，是否模拟异常：%s", fromUserId, toUserId, amount, needException));
        userBalanceDao.deductionAmount(fromUserId, amount);
        if (needException) {
            throw new RuntimeException("所有Bean共享同一个代理事务-转账异常");
        }
        userBalanceDao.rechargeAmount(toUserId, amount);
        System.out.println("所有Bean共享同一个代理事务-转账成功！");
    }
}
