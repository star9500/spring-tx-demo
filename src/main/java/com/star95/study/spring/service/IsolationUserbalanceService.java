package com.star95.study.spring.service;

import com.star95.study.spring.po.UserBalancePO;

/**
 * @author hemingzhun
 * @date 2019/1/24
 */

public interface IsolationUserbalanceService extends UserBalanceService {
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
     * 隔离级别-读未提交测试
     */
    void readUncommittedTest();

    /**
     * 获取读未提交隔离级别下的用户账户列表
     */
    void getReadUncommittedAllList();

    /**
     * 隔离级别-读提交测试
     */
    void readCommittedTest();

    /**
     * 获取读提交隔离级别下的用户账户列表
     */
    void getReadCommittedAllList();

    /**
     * 隔离级别-可重复读测试
     */
    void repeatableReadTest();

    /**
     * 获取可重复读隔离级别下的用户账户列表
     */
    void getRepeatableAllList();

    /**
     * 隔离级别-串行化测试
     */
    void serializableTest();

    /**
     * 获取串行化隔离级别下的用户账户列表
     */
    void getSerializableAllList();
}
