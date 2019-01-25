package com.star95.study.spring.service;

import com.star95.study.spring.po.UserBalancePO;

import java.util.List;

/**
 * @author hemingzhun
 * @date 2019/1/23
 */

public interface UserBalanceService {

    /**
     * 获取所有用户账户信息<br/>
     * <b>注意：数据量大的情况下必须分页，这里仅做测试</b>
     *
     * @return 用户账户信息集合List
     */
    List<UserBalancePO> getAllList();

    /**
     * 用户之间的转账
     *
     * @param fromUserId    转出方
     * @param toUserId      转入方
     * @param amount        金额
     * @param needException 方便测试，加这个参数是否抛异常
     */
    void transferAmount(int fromUserId, int toUserId, double amount, boolean needException);

}
