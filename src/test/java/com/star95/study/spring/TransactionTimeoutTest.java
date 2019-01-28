package com.star95.study.spring;

import com.star95.study.spring.service.TxTimeoutService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * spring事务超时机制<br/>
 * @Transactional注解配置了timeout属性和去掉timeout属性事务的超时是否回滚
 *
 * @author hemingzhun
 * @date 2019/1/24
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class TransactionTimeoutTest {


    @Autowired
    private TxTimeoutService txTimeoutService;

    @Test
    public void txTimeoutTest() {
        txTimeoutService.timeoutTransaction();
    }
}
