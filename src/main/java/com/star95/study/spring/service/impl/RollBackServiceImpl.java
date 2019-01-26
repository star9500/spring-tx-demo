package com.star95.study.spring.service.impl;

import com.star95.study.spring.dao.UserBalanceDao;
import com.star95.study.spring.po.UserBalancePO;
import com.star95.study.spring.service.RollBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class RollBackServiceImpl implements RollBackService {

    @Autowired
    UserBalanceDao userBalanceDao;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    @Override
    public void defaultRollback() {
        userBalanceDao.insert(new UserBalancePO("defaultRollback", 1));
        int[] num = new int[3];
        System.out.println(num[4]);
        //throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = ClassNotFoundException.class)
    @Override
    public void rollbackFor1() throws InterruptedException {
        userBalanceDao.insert(new UserBalancePO("rollbackFor1", 1));
        throw new InterruptedException();
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = InterruptedException.class)
    @Override
    public void rollbackFor2() throws InterruptedException {
        userBalanceDao.insert(new UserBalancePO("rollbackFor2", 1));
        throw new InterruptedException();
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackForClassName = "java.lang.IOException")
    @Override
    public void rollbackFor3() throws InterruptedException {
        userBalanceDao.insert(new UserBalancePO("rollbackFor3", 1));
        throw new InterruptedException();
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, noRollbackFor = InterruptedException.class)
    @Override
    public void nonRollback1() throws InterruptedException {
        userBalanceDao.insert(new UserBalancePO("nonRollback1", 1));
        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, noRollbackForClassName = "java.io.RuntimeException")
    @Override
    public void nonRollback2() throws InterruptedException {
        userBalanceDao.insert(new UserBalancePO("nonRollback2", 1));
        throw new InterruptedException();
    }
}
