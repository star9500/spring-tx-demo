package com.star95.study.spring.service;

import com.star95.study.spring.po.UserBalancePO;

/**
 * @author hemingzhun
 * @date 2019/1/24
 */

public interface PropagationUserbalanceService extends UserBalanceService {
    /**
     * 插入一条记录
     *
     * @param po
     */
    void insert(UserBalancePO po);

    /**
     * 删除记录
     *
     * @param id
     */
    void delete(int id);

    /**
     * 更新记录
     *
     * @param po
     */
    void update(UserBalancePO po);


    /**
     * 事务传播机制-required测试
     */
    void propagationrequiredTest();

    /**
     * 事务传播机制-requires_new测试
     */
    void propagationrequires_newTest();

    /**
     * 事务传播机制-supports测试
     */
    void propagationsupportsTest();

    /**
     * 事务传播机制-not_supported测试
     */
    void propagationnot_supportedTest();

    /**
     * 事务传播机制-mandatory测试
     */
    void propagationmandatoryTest();

    /**
     * 事务传播机制-nested测试
     */
    void propagationnestedTest();

    /**
     * 事务传播机制-never测试
     */
    void propagationneverTest();
}
