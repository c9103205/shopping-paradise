package com.shop.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.model.AccountBean;
import com.shop.service.UserService;

@WebServlet("/MemberUpdate")
public class MemberUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MemberUpdate() {
		super();

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		AccountBean account = new AccountBean();
		UserService userService = (UserService) getServletContext().getAttribute("userService");
		account.setAccount(req.getParameter("valUser"));
		account.setAddr(req.getParameter("valAddr"));
		account.setEmail(req.getParameter("valEmail"));
		account.setName(req.getParameter("valName"));
		account.setPassword(req.getParameter("valPwd"));
		account.setTel(req.getParameter("valPhone"));
		userService.update(account);
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");
		PrintWriter out = resp.getWriter();
		req.getSession().setAttribute("login", account);
		req.getRequestDispatcher("/member.jsp").include(req, resp);
		out.print("<script>alert('修改完成!按下確定返回會員管理頁面')</script>");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
