package com.star95.study.spring;

import com.star95.study.spring.service.ReadOnlyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class ReadOnlyTest {

    @Autowired
    private ReadOnlyService readOnlyService;

    @Test
    public void readOnlyTest() {
        readOnlyService.transferAmount(8, 9, 50, false);
    }
}
