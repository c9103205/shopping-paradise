package com.shop.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.model.AccountBean;
import com.shop.model.OrderMainBean;
import com.shop.service.OrderService;


@WebServlet("/Order")
public class Order extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public Order() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getSession().getAttribute("login")!=null) {
			AccountBean account = (AccountBean) req.getSession().getAttribute("login");
			OrderService orderService = (OrderService) getServletContext().getAttribute("orderService");
			List<OrderMainBean> list = orderService.get(account);
			req.getSession().setAttribute("order", list);
			resp.sendRedirect("order.jsp");
		}else {
			resp.sendRedirect("login.jsp");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
