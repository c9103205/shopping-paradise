package com.shop.DAO;

import java.util.List;

import com.shop.model.AccountBean;

public interface IAccountDAO {
    boolean isUserExisted(AccountBean account);
    void addAccount(AccountBean account);
    void getAccount(AccountBean account); 
    void updateAccount(AccountBean account);
    void updateAccount(int uid, int privilege);
    List<AccountBean> getAccount();
    void getAccountById(AccountBean account,String uid);
}
