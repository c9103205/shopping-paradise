package com.shop.service;

import java.util.ArrayList;
import java.util.List;

import com.shop.DAO.IAccountDAO;
import com.shop.model.AccountBean;

public class UserService {
	IAccountDAO iAccountDAO;

	public UserService(IAccountDAO userDAO) {
		this.iAccountDAO = userDAO;
	}

	public boolean signup(AccountBean account) {
		boolean isSuccessed = false;
		if (!iAccountDAO.isUserExisted(account)) {
			iAccountDAO.addAccount(account);
			isSuccessed = true;
		}
		return isSuccessed;
	}

	public boolean login(AccountBean account) {
		boolean isSuccessed = false;
		iAccountDAO.getAccount(account);
		if(!(account.getName() == null)){
			isSuccessed = true;
		}
		return isSuccessed;
	}
	
	public void update(AccountBean account) {
		iAccountDAO.updateAccount(account);
	}
	
	public void update(int uid,int privilege) {
		iAccountDAO.updateAccount(uid, privilege);
	}
	
	public List<AccountBean> getAll(){
		return iAccountDAO.getAccount();	
	}
	
	public void getByUid(AccountBean account,String uid) {
		iAccountDAO.getAccountById(account, uid);
	}
}
