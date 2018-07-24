package com.shop.service;

import java.util.List;

import com.shop.DAO.IOrderDAO;
import com.shop.model.AccountBean;
import com.shop.model.OrderMainBean;

public class OrderService {
	IOrderDAO iOrderDAO;

	public OrderService(IOrderDAO order) {
		this.iOrderDAO = order;
	}
	
	public void addOrder(OrderMainBean omb) {
		iOrderDAO.addOrderMain(omb);
	}
	
	public List<OrderMainBean> get(AccountBean account) {
		return iOrderDAO.getOrderMain(account);
	}
	
	public List<OrderMainBean> getAll(){
		return iOrderDAO.getAllOrderMain();
	}
	
	public void update(String id, String process) {
		iOrderDAO.updateProcess(id, process);
	}
	
}
