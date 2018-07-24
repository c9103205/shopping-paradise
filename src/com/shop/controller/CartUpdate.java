package com.shop.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.model.CommodityBean;

/**
 * Servlet implementation class CartUpdate
 */
@WebServlet("/CartUpdate")
public class CartUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
//cart.jsp 提交到此表單
    public CartUpdate() {
    	
        super();
        //啟動這個服務一定要用super。
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String quantity[] = req.getParameterValues("valQuantity");
		ArrayList<CommodityBean> list = (ArrayList<CommodityBean>) req.getSession().getAttribute("cart");
		int i=0;
		for(CommodityBean cb : list) {
			cb.setQuantity(Integer.parseInt(quantity[i]));
			i++;
		}
		if(req.getSession().getAttribute("login")==null)
			resp.sendRedirect("login.jsp");
		else
			resp.sendRedirect("orderCheck.jsp");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
