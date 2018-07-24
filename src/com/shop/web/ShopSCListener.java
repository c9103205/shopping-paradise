package com.shop.web;

import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import com.shop.DAO.AccountDAO;
import com.shop.DAO.CommodityDAO;
import com.shop.DAO.MarqueeDAO;
import com.shop.DAO.OrderDAO;
import com.shop.model.MarqueeBean;
import com.shop.service.CommodityService;
import com.shop.service.MarqueeService;
import com.shop.service.OrderService;
import com.shop.service.UserService;

@WebListener
public class ShopSCListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce) {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			DataSource dataSource = (DataSource) envContext.lookup("jdbc/shop");
			ServletContext context = sce.getServletContext();
			context.setAttribute("dataSource", dataSource);
			context.setAttribute("userService", new UserService(new AccountDAO(dataSource)));
			context.setAttribute("commodityService", new CommodityService(new CommodityDAO(dataSource)));
			context.setAttribute("orderService", new OrderService(new OrderDAO(dataSource)));
			context.setAttribute("marqueeService", new MarqueeService(new MarqueeDAO(dataSource)));
			//以下為了讓index剛讀取就可以拿到跑馬燈的資訊
			MarqueeService ms = (MarqueeService)context.getAttribute("marqueeService");
			ArrayList<MarqueeBean> list = (ArrayList<MarqueeBean>) ms.getAll();
			if(list!=null)
				for(MarqueeBean marquee:list) {
					if(marquee.getSelected()==1) {
						context.setAttribute("showMarquee", marquee);
						break;
					}
				}
		} catch (NamingException ex) {
			throw new RuntimeException(ex);
		}

	}

	public void contextDestroyed(ServletContextEvent sce) {

	}
}
