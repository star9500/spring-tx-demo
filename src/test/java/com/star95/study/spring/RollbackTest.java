package com.star95.study.spring;

import com.star95.study.spring.service.RollBackService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
