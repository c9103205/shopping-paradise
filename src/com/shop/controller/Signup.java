package com.shop.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.model.AccountBean;
import com.shop.service.UserService;

@WebServlet("/Signup")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Signup() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config); // 沒有這行ServletContect會被遺棄QQ
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
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");
		PrintWriter out = resp.getWriter();
		
		if (userService.signup(account)) {
			req.getRequestDispatcher("/login.jsp").include(req,resp);
			out.print("<script>alert('註冊成功!按下確定返回登入頁面')</script>");
			
		} else {
			req.getRequestDispatcher("/signup.jsp").include(req,resp);
			out.print("<script>alert('註冊失敗(帳號已被使用)，按下確定後返回註冊頁面')</script>");
		}

	}

}
