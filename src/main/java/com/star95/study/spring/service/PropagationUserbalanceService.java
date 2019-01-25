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

}
