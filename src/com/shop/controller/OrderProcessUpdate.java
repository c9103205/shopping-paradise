package com.shop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.service.OrderService;


@WebServlet("/OrderProcessUpdate")
public class OrderProcessUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public OrderProcessUpdate() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrderService orderService = (OrderService) getServletContext().getAttribute("orderService");
		String process = request.getParameter("valProcess");
		String id = request.getParameter("valId");
		System.out.print(process+ "   "+ id);
		orderService.update(id, process);
		response.sendRedirect("OrderAdmin");
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
