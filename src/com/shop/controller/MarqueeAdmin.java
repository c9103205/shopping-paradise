package com.shop.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.model.MarqueeBean;
import com.shop.service.MarqueeService;

@WebServlet("/MarqueeAdmin")
public class MarqueeAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MarqueeAdmin() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MarqueeService ms = (MarqueeService) req.getServletContext().getAttribute("marqueeService");
		if ("get".equals(req.getParameter("do"))) {
			ArrayList<MarqueeBean> list = (ArrayList<MarqueeBean>) ms.getAll();
			req.getSession().setAttribute("marquee", list);
			resp.sendRedirect("marqueeAdmin.jsp");
		}
		else if("remove".equals(req.getParameter("do"))) {
			ms.remove(Integer.parseInt(req.getParameter("id")));
			resp.sendRedirect("MarqueeAdmin?do=get");
		}
		else if("add".equals(req.getParameter("do"))) {
			ms.add(req.getParameter("valContext"));
			resp.sendRedirect("MarqueeAdmin?do=get");
		}
		else if("selected".equals(req.getParameter("do"))) {
			req.getServletContext().setAttribute("showMarquee", ms.selected(Integer.parseInt(req.getParameter("id"))));
			resp.sendRedirect("MarqueeAdmin?do=get");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
