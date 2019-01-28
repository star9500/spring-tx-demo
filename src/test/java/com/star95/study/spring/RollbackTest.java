package com.star95.study.spring;

import com.star95.study.spring.service.RollBackService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * spring事务回滚机制<br/>
 * 1、默认回滚机制测试用例：defaultRollback<br/>
 * 2、事务运行时异常和非异常时异常回滚测试用例：rollbackFor1、rollbackFor2、rollbackFor3<br/>
 * 3、事务运行时异常和非运行时异常不回滚测试用例：nonRollback1、nonRollback2<br/>
 *
 * @author hemingzhun
 * @date 2019/1/24
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class RollbackTest {

    @Autowired
    RollBackService rollBackService;

    @Test
    public void defaultRollback() {
        rollBackService.defaultRollback();
    }

    @Test
    public void rollbackFor1() {
        try {
            rollBackService.rollbackFor1();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void rollbackFor2() {
        try {
            rollBackService.rollbackFor2();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void rollbackFor3() {
        try {
            rollBackService.rollbackFor3();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void nonRollback1() {
        try {
            rollBackService.nonRollback1();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void nonRollback2() {
        try {
            rollBackService.nonRollback2();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
