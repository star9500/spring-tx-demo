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
