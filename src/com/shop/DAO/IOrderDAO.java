package com.shop.DAO;

import java.util.List;

import com.shop.model.AccountBean;
import com.shop.model.OrderMainBean;

public interface IOrderDAO {
	void addOrderMain(OrderMainBean omb);
	void updateProcess(String id, String process);
	List<OrderMainBean> getOrderMain(AccountBean account);
	List<OrderMainBean> getAllOrderMain();
}
