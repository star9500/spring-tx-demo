package com.star95.study.spring;

import com.star95.study.spring.service.PropagationUserbalanceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author hemingzhun
 * @date 2019/1/24
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class PropagationTest {

    @Autowired
    PropagationUserbalanceService propagationUserbalanceService;

    @Test
    public void normalflow() {
        propagationUserbalanceService.propagationrequiredTest();
    }
}
