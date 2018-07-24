package com.shop.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.model.AccountBean;
import com.shop.service.UserService;

@WebServlet("/MemberAdmin")
public class MemberAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MemberAdmin() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AccountBean account = (AccountBean) request.getSession().getAttribute("login");
		if ((account.getPrivilege() & 4) != 0) {
			UserService userService = (UserService) getServletContext().getAttribute("userService");
			ArrayList<AccountBean> list = (ArrayList<AccountBean>) userService.getAll();
			request.getSession().setAttribute("member", list);
			response.sendRedirect("memberAdmin.jsp");
		}else{
			//------------後臺管理萬用------------------------------------
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("<script>alert('您沒有權限執行此操作')</script>");
			response.sendRedirect("index.jsp");
			
			//---------------------------------------------------------
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
