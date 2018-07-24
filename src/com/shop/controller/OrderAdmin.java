package com.shop.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.model.AccountBean;
import com.shop.model.OrderMainBean;
import com.shop.service.OrderService;


@WebServlet("/OrderAdmin")
public class OrderAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public OrderAdmin() {
        super(); 
    }


	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		AccountBean account = (AccountBean) req.getSession().getAttribute("login");
		if((account.getPrivilege()&2)!=0) {
			OrderService orderService = (OrderService) getServletContext().getAttribute("orderService");
			List<OrderMainBean> list = orderService.getAll();
			req.getSession().setAttribute("order", list);
			resp.sendRedirect("order.jsp?account=admin");
		}else {
			//------------後臺管理萬用------------------------------------
			resp.setCharacterEncoding("utf-8");
			resp.setContentType("text/html; charset=utf-8");
			PrintWriter out = resp.getWriter();
			resp.sendRedirect("index.jsp");
			out.print("<script>alert('您沒有權限執行此操作')</script>");
			//---------------------------------------------------------
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
