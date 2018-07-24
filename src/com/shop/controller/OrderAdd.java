package com.shop.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.model.AccountBean;
import com.shop.model.CommodityBean;
import com.shop.model.OrderExtBean;
import com.shop.model.OrderMainBean;
import com.shop.service.CommodityService;
import com.shop.service.OrderService;


@WebServlet("/OrderAdd")  //come from 
public class OrderAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public OrderAdd() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<CommodityBean> list = (ArrayList<CommodityBean>) req.getSession().getAttribute("cart");
		AccountBean account = (AccountBean)req.getSession().getAttribute("login");
		OrderMainBean omb = new OrderMainBean();
		OrderExtBean[] oeb = new OrderExtBean[list.size()];
		System.out.println(list.size());
		OrderService orderService = (OrderService) getServletContext().getAttribute("orderService");
		CommodityService commodityService = (CommodityService) getServletContext().getAttribute("commodityService");
		//---抓當前時間
		String date;
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyyMMdd");	
		date = bartDateFormat.format(new Date());
		//
		omb.setAddr(req.getParameter("valAddr"));
		omb.setDate(date);
//		omb.setId("A"+date);			
		omb.setMemId(Integer.toString(account.getUid()));
		omb.setNote(req.getParameter("valNote"));
		omb.setProcess("出貨準備中");
		omb.setReceiver(req.getParameter("valReceiver"));
		omb.setTel(req.getParameter("valTel"));
		int i = 0;
		for(CommodityBean cb: list) {
			oeb[i] = new OrderExtBean();
			oeb[i].setBuyquantity(cb.getQuantity());
			oeb[i].setCommodityId(cb.getId());
//			oeb[i].setId("A"+date);		
			oeb[i].setPrice(cb.getPrice());			
			i++;
		}
		omb.setExt(oeb);
		orderService.addOrder(omb);
		commodityService.alterQuantity(oeb);
		req.getSession().removeAttribute("cart");
		resp.sendRedirect("Order");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
