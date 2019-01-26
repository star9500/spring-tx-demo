package com.star95.study.spring.service.impl;

import com.star95.study.spring.dao.UserBalanceDao;
import com.star95.study.spring.po.UserBalancePO;
import com.star95.study.spring.service.TxTimeoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TxTimeoutServiceImpl implements TxTimeoutService {

    @Autowired()
    private UserBalanceDao userBalanceDao;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class, timeout = 3)
    @Override
    public void timeoutTransaction() {
        userBalanceDao.insert(new UserBalancePO("timeout1", 1000));
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        userBalanceDao.insert(new UserBalancePO("timeout2", 2000));
    }
}
