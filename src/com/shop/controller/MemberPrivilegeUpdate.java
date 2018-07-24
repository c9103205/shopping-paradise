package com.shop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.model.AccountBean;
import com.shop.service.UserService;

@WebServlet("/MemberPrivilegeUpdate")
public class MemberPrivilegeUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MemberPrivilegeUpdate() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserService userService = (UserService) getServletContext().getAttribute("userService");
		String action = request.getParameter("action");
		if ("get".equals(action)) {
			String uid = request.getParameter("id");

			AccountBean account = new AccountBean();
			userService.getByUid(account, uid);
			request.getSession().setAttribute("privilege", account);
			response.sendRedirect("memberPrivilegeUpdate.jsp");
		} else if ("update".equals(action)) {
			String privilege[] = request.getParameterValues("privilege");
			int uid = Integer.parseInt(request.getParameter("uid"));
			int sum = 0;
			if (privilege == null)
				userService.update(uid, 0);
			else {
				for (String ele : privilege)
					sum |= Integer.parseInt(ele);
				userService.update(uid, sum);
			}
			response.sendRedirect("MemberAdmin");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
