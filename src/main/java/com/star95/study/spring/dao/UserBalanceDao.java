package com.star95.study.spring.dao;

import com.star95.study.spring.po.UserBalancePO;

import java.util.List;

/**
 * @author hemingzhun
 * @date 2019/1/23
 */

public interface UserBalanceDao {
    /**
     * 获取所有用户账户信息<br/>
     * <b>注意：数据量大的情况下必须分页，这里仅做测试</b>
     *
     * @return 用户账户信息集合List
     */
    List<UserBalancePO> getAllList();

    /**
     * 给用户账户充值
     *
     * @param id     用户ID
     * @param amount 金额
     */
    void rechargeAmount(int id, double amount);

    /**
     * 给用户账户扣款
     *
     * @param id     用户ID
     * @param amount 金额
     */
    void deductionAmount(int id, double amount);

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

}
