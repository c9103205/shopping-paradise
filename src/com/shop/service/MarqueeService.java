package com.shop.service;

import java.util.List;

import com.shop.DAO.IMarqueeDAO;
import com.shop.model.MarqueeBean;

public class MarqueeService {
	IMarqueeDAO mdao;

	public MarqueeService(IMarqueeDAO mdao) {
		this.mdao = mdao;
	}

	public List<MarqueeBean> getAll() {
		return mdao.getAll();
	}
	
	
	
	public void add(String context) {
		mdao.add(context);
	}

	public void remove(int id) {
		mdao.remove(id);
	}
	
	public MarqueeBean selected(int id) {
		mdao.selected(id);
		return mdao.getById(id);
	}

}
