package com.star95.study.spring;

import com.star95.study.spring.po.UserBalancePO;
import com.star95.study.spring.service.IsolationUserbalanceService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author hemingzhun
 * @date 2019/1/24
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class IsolationTest {

    ThreadPoolExecutor executor = null;

    @Before
    public void init() {
        executor = new ThreadPoolExecutor(4, 4, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(16));
    }

    @Autowired
    IsolationUserbalanceService isolationUserbalanceService;

    @Test
    public void readUncommittedTest() throws Exception {
        CountDownLatch latch = new CountDownLatch(2);
        executor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    isolationUserbalanceService.readUncommittedTest();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            }
        });
        executor.submit(new Runnable() {
            @Override
            public void run() {
                isolationUserbalanceService.getReadUncommittedAllList();
                latch.countDown();
            }
        });
        latch.await();
        System.out.println("事务结束后再次读取");
        List<UserBalancePO> beforeList = isolationUserbalanceService.getAllList();
        for (UserBalancePO po : beforeList) {
            System.out.println(po);
        }
        executor.shutdown();
    }

    @Test
    public void readCommittedTest() throws Exception {
        CountDownLatch latch = new CountDownLatch(2);
        executor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    isolationUserbalanceService.readCommittedTest();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            }
        });
        executor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    isolationUserbalanceService.getReadCommittedAllList();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            }
        });
        latch.await();
        System.out.println("事务结束后再次读取");
        List<UserBalancePO> beforeList = isolationUserbalanceService.getAllList();
        for (UserBalancePO po : beforeList) {
            System.out.println(po);
        }
        executor.shutdown();
    }

    @Test
    public void repeatableReadTest() throws Exception {
        CountDownLatch latch = new CountDownLatch(2);
        executor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    isolationUserbalanceService.repeatableReadTest();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            }
        });
        executor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    isolationUserbalanceService.getRepeatableAllList();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            }
        });
        latch.await();
        System.out.println("事务结束后再次读取");
        List<UserBalancePO> beforeList = isolationUserbalanceService.getAllList();
        for (UserBalancePO po : beforeList) {
            System.out.println(po);
        }
        executor.shutdown();
    }

    @Test
    public void serializableTest() throws Exception {
        CountDownLatch latch = new CountDownLatch(2);
        executor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    isolationUserbalanceService.serializableTest();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            }
        });
        executor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    isolationUserbalanceService.getSerializableAllList();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            }
        });
        latch.await();
        System.out.println("事务结束后再次读取");
        List<UserBalancePO> beforeList = isolationUserbalanceService.getAllList();
        for (UserBalancePO po : beforeList) {
            System.out.println(po);
        }
        executor.shutdown();
    }
}
