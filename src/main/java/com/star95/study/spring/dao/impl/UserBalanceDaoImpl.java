package com.star95.study.spring.dao.impl;

import com.star95.study.spring.dao.UserBalanceDao;
import com.star95.study.spring.po.UserBalancePO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author hemingzhun
 * @date 2019/1/23
 */
@Repository
public class UserBalanceDaoImpl extends JdbcDaoSupport implements UserBalanceDao {

    @Resource
    public void setJdbcTemp(JdbcTemplate jb) {
        super.setJdbcTemplate(jb);
    }

    @Override
    public List<UserBalancePO> getAllList() {
        return this.getJdbcTemplate().query("select id,user_name,balance from user_balance", new RowMapper<UserBalancePO>() {
            @Override
            public UserBalancePO mapRow(ResultSet rs, int i) throws SQLException {
                UserBalancePO po = new UserBalancePO();
                po.setId(rs.getInt("id"));
                po.setUserName(rs.getString("user_name"));
                po.setBalance(rs.getDouble("balance"));
                return po;
            }
        });
    }

    @Override
    public void rechargeAmount(int id, double amount) {
        this.getJdbcTemplate().update("update user_balance set balance=balance + ? where id = ?", amount, id);
    }

    @Override
    public void deductionAmount(int id, double amount) {
        this.getJdbcTemplate().update("update user_balance set balance=balance - ? where id = ?", amount, id);
    }

    @Override
    public void insert(UserBalancePO po) {
        this.getJdbcTemplate().update("insert into user_balance(user_name,balance) values(?,?)", po.getUserName(), po.getBalance());
    }

    @Override
    public void delete(int id) {
        this.getJdbcTemplate().update("delete from user_balance where id=?", id);
    }

    @Override
    public void update(UserBalancePO po) {
        this.getJdbcTemplate().update("update user_balance set user_name=?,balance=? where id=?", po.getUserName(), po.getBalance(), po.getId());
    }
}
