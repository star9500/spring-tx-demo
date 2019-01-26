package com.star95.study.spring.service.impl;

import com.star95.study.spring.dao.UserBalanceDao;
import com.star95.study.spring.po.UserBalancePO;
import com.star95.study.spring.service.IsolationUserbalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author hemingzhun
 * @date 2019/1/24
 */
@Component
public class IsolationUserbalanceServiceImpl implements IsolationUserbalanceService {

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

    @Transactional(isolation = Isolation.READ_UNCOMMITTED, rollbackFor = RuntimeException.class)
    @Override
    public void readUncommittedTest() {
        readUncommitted_insert(new UserBalancePO("uncommitted", 1000));
        sleep(10 * 1000);
        System.out.println(1 / 0);
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED, rollbackFor = RuntimeException.class)
    void readUncommitted_insert(UserBalancePO po) {
        insert(po);
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED, rollbackFor = RuntimeException.class)
    @Override
    public void getReadUncommittedAllList() {
        try {
            int i = 0;
            while (i++ < 3) {
                System.out.println(Thread.currentThread().getName() + "获取用户账户信息列表start=========");
                Thread.sleep(5000);
                List<UserBalancePO> beforeList = getAllList();
                for (UserBalancePO po : beforeList) {
                    System.out.println(po);
                }
                System.out.println(Thread.currentThread().getName() + "获取用户账户信息列表end=========");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = RuntimeException.class)
    @Override
    public void readCommittedTest() {
        readcommitted_insert(new UserBalancePO("committed", 2000));
        sleep(10 * 1000);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = RuntimeException.class)
    void readcommitted_insert(UserBalancePO po) {
        insert(po);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = RuntimeException.class)
    @Override
    public void getReadCommittedAllList() {
        try {
            int i = 0;
            while (i++ < 3) {
                System.out.println(Thread.currentThread().getName() + "获取用户账户信息列表start=========");
                Thread.sleep(5000);
                List<UserBalancePO> beforeList = getAllList();
                for (UserBalancePO po : beforeList) {
                    System.out.println(po);
                }
                System.out.println(Thread.currentThread().getName() + "获取用户账户信息列表end=========");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = RuntimeException.class)
    @Override
    public void repeatableReadTest() {
        repeatable_insert(new UserBalancePO("repeatable", 3000));
        sleep(10 * 1000);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = RuntimeException.class)
    void repeatable_insert(UserBalancePO po) {
        insert(po);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = RuntimeException.class)
    public void getRepeatableAllList() {
        try {
            int i = 0;
            while (i++ < 3) {
                System.out.println(Thread.currentThread().getName() + "获取用户账户信息列表start=========");
                Thread.sleep(5000);
                List<UserBalancePO> beforeList = getAllList();
                for (UserBalancePO po : beforeList) {
                    System.out.println(po);
                }
                System.out.println(Thread.currentThread().getName() + "获取用户账户信息列表end=========");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = RuntimeException.class)
    @Override
    public void serializableTest() {
        serializable_insert(new UserBalancePO("serializable", 4000));
        sleep(10 * 1000);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = RuntimeException.class)
    void serializable_insert(UserBalancePO po) {
        insert(po);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = RuntimeException.class)
    public void getSerializableAllList() {
        try {
            int i = 0;
            while (i++ < 3) {
                System.out.println(Thread.currentThread().getName() + "获取用户账户信息列表start=========");
                Thread.sleep(5000);
                List<UserBalancePO> beforeList = getAllList();
                for (UserBalancePO po : beforeList) {
                    System.out.println(po);
                }
                System.out.println(Thread.currentThread().getName() + "获取用户账户信息列表end=========");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<UserBalancePO> getAllList() {
        return userBalanceDao.getAllList();
    }

    @Override
    public void transferAmount(int fromUserId, int toUserId, double amount, boolean needException) {

    }

    public void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
