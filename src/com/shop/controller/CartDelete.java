package com.shop.controller;

import java.io.IOException;
import java.util.ArrayList;
import com.shop.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CartDelete
 */
@WebServlet("/CartDelete")
public class CartDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CartDelete() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<CommodityBean> list = (ArrayList<CommodityBean>) request.getSession().getAttribute("cart");
		CommodityBean target = new CommodityBean();
		for(CommodityBean cb : list) {
			if(cb.getId().equals(request.getParameter("id"))) {
				target = cb;
				break;
			}	
		}
		list.remove(target);
		response.sendRedirect("cart.jsp");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
