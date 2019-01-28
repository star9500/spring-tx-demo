package com.star95.study.spring;

import com.star95.study.spring.po.UserBalancePO;
import com.star95.study.spring.service.UserBalanceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * 此类测试事务配置的5种方式<br/>
 * 1、每个Bean都有一个代理（基于TransactionProxyFactoryBean代理），使用@Qualifier("transactionWithSingleTxProxyBean")<br/>
 * 2、所有Bean共享一个代理基类（基于TransactionProxyFactoryBean代理），使用@Qualifier("transactionWithSharedTxProxyBean")<br/>
 * 3、使用拦截器，使用@Qualifier("withInterceptorService")<br/>
 * 4、使用tx标签配置的拦截器(AOP)，使用@Qualifier("transactionWithAop")<br/>
 * 5、全注解(方法或类加@Transactional)，使用@Qualifier("transactionWithAnnotation")<br/>
 * 另外一种不使用任何事务管理的测试，使用@Qualifier("userBalanceNoTransaction")<br/>
 * 可根据以上配置分别测试正常流程（normalflow测试方式）和异常流程（exceptionflow方法）<br/>
 *
 * @author hemingzhun
 * @date 2019/1/24
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class UserBalanceTransactionTest {

    @Autowired
//    @Qualifier("userBalanceNoTransaction")
//    @Qualifier("transactionWithSingleTxProxyBean")
//    @Qualifier("transactionWithSharedTxProxyBean")
//    @Qualifier("withInterceptorService")
//    @Qualifier("transactionWithAop")
    @Qualifier("transactionWithAnnotation")
    UserBalanceService userBalanceService;

    @Test
    public void normalflow() {
        System.out.println("===================转账前用户账户信息===================");
        List<UserBalancePO> beforeList = userBalanceService.getAllList();
        for (UserBalancePO ub : beforeList) {
            System.out.println(ub);
        }
        userBalanceService.transferAmount(2, 1, 50, false);
        List<UserBalancePO> afterList = userBalanceService.getAllList();
        System.out.println("===================转账后用户账户信息===================");
        for (UserBalancePO ub : afterList) {
            System.out.println(ub);
        }
    }

    @Test
    public void exceptionflow() {
        System.out.println("===================转账前用户账户信息===================");
        List<UserBalancePO> beforeList = userBalanceService.getAllList();
        for (UserBalancePO ub : beforeList) {
            System.out.println(ub);
        }
        try {
            userBalanceService.transferAmount(1, 2, 50, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<UserBalancePO> afterList = userBalanceService.getAllList();
        System.out.println("===================转账后用户账户信息===================");
        for (UserBalancePO ub : afterList) {
            System.out.println(ub);
        }
    }
}
