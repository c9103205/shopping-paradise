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

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Login() {
		super();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		AccountBean account = new AccountBean();
		UserService userService = (UserService) getServletContext().getAttribute("userService");
		account.setAccount(req.getParameter("valUser"));
		account.setPassword(req.getParameter("valPwd"));
		// System.out.println(req.getAttribute("valUser"));
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");
		PrintWriter out = resp.getWriter();
		if (userService.login(account)) {
			req.getSession().setAttribute("login", account);
			System.out.println(req.getSession().getAttribute("login"));
			req.getRequestDispatcher("/index.jsp").include(req, resp);
			out.print("<script>alert('" + account.getName() + "歡迎回來!按下確定後返回首頁')</script>");
		} else {
			req.getRequestDispatcher("login.jsp").include(req, resp);
			out.print("<script>alert('登入失敗(無此帳號或密碼輸入錯誤)，按下確定後返回登入頁面')</script>");
		}
	}

}
