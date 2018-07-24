package com.shop.DAO;

import java.util.List;

import com.shop.model.MarqueeBean;

public interface IMarqueeDAO {
	List<MarqueeBean> getAll();
	MarqueeBean getById(int id);
	void add(String context);
	void remove(int id);
	void selected(int id);
}
