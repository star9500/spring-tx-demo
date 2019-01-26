package com.star95.study.spring.service.impl;

import com.star95.study.spring.dao.UserBalanceDao;
import com.star95.study.spring.po.UserBalancePO;
import com.star95.study.spring.service.PropagationUserbalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

/**
 * @author hemingzhun
 * @date 2019/1/24
 */
@Component
public class PropagationUserbalanceServiceImpl implements PropagationUserbalanceService {

    @Autowired()
    private UserBalanceDao userBalanceDao;

    @Autowired
    private TempService1 tempService1;

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
        System.out.println("测试事务属性Propagation.REQUIRED开始！");
        insert(new UserBalancePO("zhangsan", 100));
        System.out.println(1 / 0);
        insert(new UserBalancePO("lisi", 200));
        System.out.println("测试事务属性Propagation.REQUIRED结束！");

    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    @Override
    public void propagationrequires_newTest() {
        System.out.println("测试事务属性Propagation.REQUIRES_NEW开始！");
        tempService1.insert1(new UserBalancePO("wangwu", 300));
        //同一个Service类中，spring并不重新创建新事务，如果是两不同的Service，就会创建新事务了。
        //跨Service调用方法时，都会经过org.springframework.aop.framework.CglibAopProxy.DynamicAdvisedInterceptor.intercept()方法，只有经过此处，才能对事务进行控制。
        //insert1(new UserBalancePO("wangwu",300));
        System.out.println(1 / 0);
        System.out.println("测试事务属性Propagation.REQUIRES_NEW结束！");
    }

    //@Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    @Override
    public void propagationsupportsTest() {
        System.out.println("测试事务属性Propagation.SUPPORTS开始！");
        tempService1.insert2(new UserBalancePO("zhaoliu", 400));
        //同一个Service类中，spring并不重新创建新事务，如果是两不同的Service，就会创建新事务了。
        //跨Service调用方法时，都会经过org.springframework.aop.framework.CglibAopProxy.DynamicAdvisedInterceptor.intercept()方法，只有经过此处，才能对事务进行控制。
        //insert1(new UserBalancePO("wangwu",300));
        System.out.println(1 / 0);
        System.out.println("测试事务属性Propagation.SUPPORTS结束！");
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    @Override
    public void propagationnot_supportedTest() {
        System.out.println("测试事务属性Propagation.NOT_SUPPORTED开始！");
        tempService1.insert3(new UserBalancePO("hanba", 600));
        //同一个Service类中，spring并不重新创建新事务，如果是两不同的Service，就会创建新事务了。
        //跨Service调用方法时，都会经过org.springframework.aop.framework.CglibAopProxy.DynamicAdvisedInterceptor.intercept()方法，只有经过此处，才能对事务进行控制。
        //insert1(new UserBalancePO("wangwu",300));
        System.out.println(1 / 0);
        System.out.println("测试事务属性Propagation.NOT_SUPPORTED结束！");
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    @Override
    public void propagationmandatoryTest() {
        System.out.println("测试事务属性Propagation.MANDATORY开始！");
        tempService1.insert4(new UserBalancePO("zhouqiu", 700));
        System.out.println("测试事务属性Propagation.MANDATORY结束！");
    }


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    @Override
    public void propagationnestedTest() {
        System.out.println("测试事务属性Propagation.NESTED开始！");
        tempService1.insert5(new UserBalancePO("qianshi", 800));
        //System.out.println(1 / 0);
        System.out.println("测试事务属性Propagation.NESTED结束！");
    }

    //@Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    @Override
    public void propagationneverTest() {
        System.out.println("测试事务属性Propagation.NEVER开始！");
        tempService1.insert6(new UserBalancePO("songshiyi", 900));
        System.out.println("测试事务属性Propagation.NEVER结束！");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = IOException.class)
    public void insert1(UserBalancePO po) {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + "添加用户账户信息：" + po);
        userBalanceDao.insert(po);
        System.out.println(threadName + "添加用户账户成功");
    }

}

@Component
class TempService1 {
    @Autowired()
    private UserBalanceDao userBalanceDao;

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = RuntimeException.class)
    public void insert1(UserBalancePO po) {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + "添加用户账户信息：" + po);
        userBalanceDao.insert(po);
        System.out.println(threadName + "添加用户账户成功");
    }

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = RuntimeException.class)
    public void insert2(UserBalancePO po) {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + "添加用户账户信息：" + po);
        userBalanceDao.insert(po);
        System.out.println(threadName + "添加用户账户成功");
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, rollbackFor = RuntimeException.class)
    public void insert3(UserBalancePO po) {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + "添加用户账户信息：" + po);
        userBalanceDao.insert(po);
        System.out.println(threadName + "添加用户账户成功");
    }

    @Transactional(propagation = Propagation.MANDATORY, rollbackFor = RuntimeException.class)
    public void insert4(UserBalancePO po) {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + "添加用户账户信息：" + po);
        userBalanceDao.insert(po);
        System.out.println(threadName + "添加用户账户成功");
    }

    @Transactional(propagation = Propagation.NESTED, rollbackFor = RuntimeException.class)
    public void insert5(UserBalancePO po) {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + "添加用户账户信息：" + po);
        userBalanceDao.insert(po);
        System.out.println(threadName + "添加用户账户成功");
    }

    @Transactional(propagation = Propagation.NEVER, rollbackFor = RuntimeException.class)
    public void insert6(UserBalancePO po) {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + "添加用户账户信息：" + po);
        userBalanceDao.insert(po);
        System.out.println(threadName + "添加用户账户成功");
    }
}
