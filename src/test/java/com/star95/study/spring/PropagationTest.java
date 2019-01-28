package com.star95.study.spring;

import com.star95.study.spring.po.UserBalancePO;
import com.star95.study.spring.service.PropagationUserbalanceService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * spring事务隔离级别测试<br/>
 * 1、Propagation.REQUIRED传播行为测试用例：propagationrequiredTest<br/>
 * 2、Propagation.REQUIRES_NEW传播行为测试用例：propagationrequires_newTest<br/>
 * 3、Propagation.SUPPORTS传播行为测试用例：propagationsupportsTest<br/>
 * 4、Propagation.NOT_SUPPORTED传播行为测试用例：propagationnot_supportedTest<br/>
 * 5、Propagation.MANDATORY传播行为测试用例：propagationmandatoryTest<br/>
 * 6、Propagation.NESTED传播行为测试用例：propagationnestedTest<br/>
 * 7、Propagation.NEVER传播行为测试用例：propagationneverTest<br/>
 *
 * @author hemingzhun
 * @date 2019/1/24
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class PropagationTest {

    @Autowired
    PropagationUserbalanceService propagationUserbalanceService;

    @Before
    public void beforQuery() {
        System.out.println("beforQuery获取用户账户信息列表start=========");
        List<UserBalancePO> beforeList = propagationUserbalanceService.getAllList();
        for (UserBalancePO po : beforeList) {
            System.out.println(po);
        }
        System.out.println("beforQuery获取用户账户信息列表end=========");
    }

    @After
    public void afterQuery() {
        System.out.println("afterQuery获取用户账户信息列表start=========");
        List<UserBalancePO> beforeList = propagationUserbalanceService.getAllList();
        for (UserBalancePO po : beforeList) {
            System.out.println(po);
        }
        System.out.println("afterQuery获取用户账户信息列表end=========");
    }


    @Test
    public void propagationrequiredTest() {
        propagationUserbalanceService.propagationrequiredTest();
    }

    @Test
    public void propagationrequires_newTest() {
        propagationUserbalanceService.propagationrequires_newTest();
    }

    @Test
    public void propagationsupportsTest() {
        propagationUserbalanceService.propagationsupportsTest();
    }

    @Test
    public void propagationnot_supportedTest() {
        propagationUserbalanceService.propagationnot_supportedTest();
    }

    @Test
    public void propagationmandatoryTest() {
        propagationUserbalanceService.propagationmandatoryTest();
    }

    @Test
    public void propagationnestedTest() {
        propagationUserbalanceService.propagationnestedTest();
    }

    @Test
    public void propagationneverTest() {
        propagationUserbalanceService.propagationneverTest();
    }
}
