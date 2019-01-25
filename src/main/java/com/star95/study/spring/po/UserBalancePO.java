package com.star95.study.spring.po;

/**
 * @author hemingzhun
 * @date 2019/1/23
 */

public class UserBalancePO {
    /**
     * 用户ID
     */
    private int id;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 用户金额
     */
    private double balance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserBalancePO{");
        sb.append("id=").append(id);
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", balance=").append(balance);
        sb.append('}');
        return sb.toString();
    }
}
