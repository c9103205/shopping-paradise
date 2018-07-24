package com.shop.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.SmartUpload;
import com.shop.model.CommodityBean;
import com.shop.service.CommodityService;

@WebServlet("/CommodityDelete")
public class CommodityDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CommodityDelete() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CommodityService commodityService = (CommodityService) getServletContext().getAttribute("commodityService");
		String id = req.getParameter("id");
		commodityService.delete(id);
		resp.sendRedirect("Commodity");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
