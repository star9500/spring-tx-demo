package com.star95.study.spring.service;

public interface RollBackService {
    void defaultRollback();

    void rollbackFor1() throws InterruptedException;

    void rollbackFor2() throws InterruptedException;

    void rollbackFor3() throws InterruptedException;

    void nonRollback1() throws InterruptedException;

    void nonRollback2() throws InterruptedException;
}
