package com.star95.study.spring;

import com.star95.study.spring.service.ReadOnlyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * spring事务是否只读测试<br/>
 * 测试用例：readOnlyTest<br/>
 * 如果设置为只读即（readOnlyService.transferAmount方法注解加readOnly = true）则更新抛异常<br/>
 *
 * @author hemingzhun
 * @date 2019/1/24
 */
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
