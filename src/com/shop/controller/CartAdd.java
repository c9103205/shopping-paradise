package com.shop.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.model.CommodityBean;
import com.shop.service.CommodityService;


@WebServlet("/CartAdd")
public class CartAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public CartAdd() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<CommodityBean> list;
		CommodityBean cb = new CommodityBean();
		cb.setQuantity(Integer.parseInt(request.getParameter("valQuantity")));
		cb.setCategory(request.getParameter("valCategory"));
		cb.setDetail(request.getParameter("valDetail"));
		cb.setId(request.getParameter("valId"));
		cb.setImage(request.getParameter("valImage"));
		cb.setName(request.getParameter("valName"));
		cb.setSpec(request.getParameter("valSpec"));
		cb.setPrice(Integer.parseInt(request.getParameter("valPrice")));
		if(request.getSession().getAttribute("cart")==null)
			list = new ArrayList<CommodityBean>();
		else
			list = (ArrayList) request.getSession().getAttribute("cart");
		list.add(cb);
		request.getSession().setAttribute("cart", list);
		response.sendRedirect("itemDetail.jsp?id="+request.getParameter("valId"));

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
