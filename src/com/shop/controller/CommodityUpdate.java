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

@WebServlet("/CommodityUpdate")
public class CommodityUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CommodityUpdate() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CommodityService commodityService = (CommodityService) getServletContext().getAttribute("commodityService");
		CommodityBean cb = new CommodityBean();
		SmartUpload su = new SmartUpload("UTF-8");
		boolean hasImage = false;
		su.initialize(getServletConfig(), req, resp);
		su.setAllowedFilesList("png,jpg,bmp,gif,PNG,JPG,BMP,GIF");
		try {
			su.upload();
			cb.setId(su.getRequest().getParameter("valId"));
			cb.setName(su.getRequest().getParameter("valName"));
			cb.setCategory(su.getRequest().getParameter("valCategory"));
			cb.setPrice(Integer.parseInt(su.getRequest().getParameter("valPrice")));
			cb.setQuantity(Integer.parseInt(su.getRequest().getParameter("valQuantity")));
			cb.setDetail(su.getRequest().getParameter("valDetail"));
			cb.setSpec(su.getRequest().getParameter("valSpec"));

			// su.setDeniedFilesList("exe,jsp,bat,html,,");
			System.out.println(su.getFiles().getFile(0).isMissing());
			if (!su.getFiles().getFile(0).isMissing()) {
				hasImage = true;
				Long time = new Date().getTime();
				String ext = su.getFiles().getFile(0).getFileExt();// 获取文件后缀
				String filename = time + "." + ext;
				System.out.println(filename);
				su.getFiles().getFile(0).saveAs("/images/" + filename);
				cb.setImage(filename);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		commodityService.update(cb, hasImage);
		resp.sendRedirect("Commodity?account=admin");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
