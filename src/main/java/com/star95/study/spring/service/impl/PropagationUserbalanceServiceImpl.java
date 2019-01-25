package com.star95.study.spring.service.impl;

import com.star95.study.spring.dao.UserBalanceDao;
import com.star95.study.spring.po.UserBalancePO;
import com.star95.study.spring.service.PropagationUserbalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author hemingzhun
 * @date 2019/1/24
 */
@Component
public class PropagationUserbalanceServiceImpl implements PropagationUserbalanceService {

    @Autowired()
    private UserBalanceDao userBalanceDao;

    @Override
    public void insert(UserBalancePO po) {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + "添加用户账户信息：" + po);
        userBalanceDao.insert(po);
        System.out.println(threadName + "添加用户账户成功");
    }

    @Override
    public void delete(int id) {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + "删除用户账户信息，id=" + id);
        userBalanceDao.delete(id);
        System.out.println(threadName + "删除用户账户成功");
    }

    @Override
    public void update(UserBalancePO po) {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + "更新用户账户信息，id=" + po);
        userBalanceDao.update(po);
        System.out.println(threadName + "更新用户账户成功");
    }

    @Override
    public List<UserBalancePO> getAllList() {
        return userBalanceDao.getAllList();
    }

    @Override
    public void transferAmount(int fromUserId, int toUserId, double amount, boolean needException) {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + "转账开始");
        System.out.println(String.format("%s用户ID：%s 给用户ID：%s 转账金额：%s，是否模拟异常：%s", threadName, fromUserId, toUserId, amount, needException));
        userBalanceDao.deductionAmount(fromUserId, amount);
        if (needException) {
            throw new RuntimeException(threadName + "转账异常");
        }
        userBalanceDao.rechargeAmount(toUserId, amount);
        System.out.println(threadName + "转账成功！");
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    @Override
    public void propagationrequiredTest() {
        transferAmount(2, 1, 50, false);
    }
}
