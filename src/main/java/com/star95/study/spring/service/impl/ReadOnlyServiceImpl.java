package com.star95.study.spring.service.impl;

import com.star95.study.spring.dao.UserBalanceDao;
import com.star95.study.spring.po.UserBalancePO;
import com.star95.study.spring.service.ReadOnlyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ReadOnlyServiceImpl implements ReadOnlyService {

    @Autowired()
    private UserBalanceDao userBalanceDao;

    @Override
    public List<UserBalancePO> getAllList() {
        return userBalanceDao.getAllList();
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = RuntimeException.class, readOnly = true)
    @Override
    public void transferAmount(int fromUserId, int toUserId, double amount, boolean needException) {
        System.out.println(Thread.currentThread().getName() + "获取用户账户信息列表start=========");
        List<UserBalancePO> beforeList = getAllList();
        for (UserBalancePO po : beforeList) {
            System.out.println(po);
        }
        System.out.println(Thread.currentThread().getName() + "获取用户账户信息列表end=========");
        userBalanceDao.deductionAmount(fromUserId, amount);
        userBalanceDao.rechargeAmount(toUserId, amount);
    }
}
