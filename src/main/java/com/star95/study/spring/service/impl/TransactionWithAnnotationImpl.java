package com.star95.study.spring.service.impl;

import com.star95.study.spring.dao.UserBalanceDao;
import com.star95.study.spring.po.UserBalancePO;
import com.star95.study.spring.service.UserBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author hemingzhun
 * @date 2019/1/24
 */
@Component("transactionWithAnnotation")
public class TransactionWithAnnotationImpl implements UserBalanceService {
    @Autowired()
    private UserBalanceDao userBalanceDao;

    @Override
    public List<UserBalancePO> getAllList() {
        return userBalanceDao.getAllList();
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = RuntimeException.class/*, timeout = 3*/)
    @Override
    public void transferAmount(int fromUserId, int toUserId, double amount, boolean needException) {
        System.out.println("annotation事务-转账开始");
        System.out.println(String.format("用户ID：%s 给用户ID：%s 转账金额：%s，是否模拟异常：%s", fromUserId, toUserId, amount, needException));
        userBalanceDao.deductionAmount(fromUserId, amount);
        if (needException) {
            throw new RuntimeException("annotation事务-转账异常");
        }
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        userBalanceDao.rechargeAmount(toUserId, amount);
        System.out.println("annotation-转账成功！");
    }
}
