<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="com.shop.model.*,java.util.*"%>
<%
	List<OrderMainBean> list = (ArrayList<OrderMainBean>) session.getAttribute("order");
%>

<!DOCTYPE html>
<html>
<head>
<title>訂單查詢</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/css/main.css" />
</head>
<body class="is-preload">

	<!-- Wrapper -->
	<div id="wrapper">

		<!-- Main -->
		<div id="main">
			<div class="inner">

				<!-- Header -->
				<%
					MarqueeBean marquee = (MarqueeBean) application.getAttribute("showMarquee");
				%>
				<header id="header">
					<strong>首頁>訂單查詢</strong>
					<marquee direction="left" height="30" scrollamount="5" style="color:red;"><%=marquee.getContext() %></marquee>
				</header>
				<br>
				<div class="table-wrapper">
					<%
						if (list != null) {
					%>
					<table class="alt">
						<thead>
							<tr>
								<th class="sort"><a class="logo" href="javascript: void(0)"><strong>訂單編號</strong></a></th>
								<th><strong>收件人</strong></th>
								<th class="sort"><a class="logo" href="javascript: void(0)"><strong>下單日期</strong></a></th>
								<th class="sort"><a class="logo" href="javascript: void(0)"><strong>出貨狀態</strong></a></th>					
								<th><strong>備註</strong></th>
							</tr>
						</thead>
						<tbody>
							<%
								AccountBean account = (AccountBean) session.getAttribute("login");
								String ombHref;
								for (OrderMainBean omb : list) {
									if("admin".equals(request.getParameter("account")) && (account.getPrivilege()&2)!=0)
										ombHref = "orderAdmin.jsp?id=" + omb.getId();
									else
										ombHref = "orderDetail.jsp?id=" + omb.getId();
							%>
							<tr>
								<td style="vertical-align: middle;" ><a class="logo"
									href=<%=ombHref%>><strong> <%=omb.getId()%></strong></a></td>
								<td style="vertical-align: middle;" ><%=omb.getReceiver()%></td>
								<td style="vertical-align: middle;" ><%=omb.getDate()%></td>
								<td style="vertical-align: middle;" ><%=omb.getProcess()%></td>
								<td style="vertical-align: middle;" ><%=omb.getNote()%></td>
							</tr>
							<%
								}
								} else {
									out.print("目前尚無商品");
								}
							%>
						</tbody>
					</table>
				</div>
			</div>
		</div>

		<!-- Sidebar -->
		<div id="sidebar">
			<div class="inner">

				<!-- Search -->
				<section>
					<header>
						<h2>妮琪姊的購物天堂</h2>
					</header>
				</section>

				<!-- Menu -->
				<nav id="menu">
					<header class="major">
						<h2>Menu</h2>
					</header>
					<ul>
						<li><a href="index.jsp">回首頁</a></li>
						<%
							if(session.getAttribute("login")==null)
								out.print("<li><a href='login.jsp'>登入</a> <a href='signup.jsp'>註冊會員</a></li>");
							else
								out.print("<li><a href='logout.jsp'>登出</a> <a href='member.jsp'>會員管理</a></li>");
						%>
						<%
							int cartNum = 0;
							if(session.getAttribute("cart")==null){}else{
								ArrayList<CommodityBean> cartList = (ArrayList<CommodityBean>) session.getAttribute("cart");
								cartNum = cartList.size();
							}
						%>
						<li><a href="cart.jsp">購物車(<%=cartNum %>)</a></li>
						<li><span class="opener">商品分類</span>
							<ul>
								<li><a href="Commodity?item=配件&account=member">配件</a></li>
								<li><a href="Commodity?item=鞋款&account=member">鞋款</a></li>
								<li><a href="Commodity?item=背包&account=member">背包</a></li>
								<li><a href="Commodity?item=帽子&account=member">帽子</a></li>
							</ul></li>
						<li><a href="Order">訂單查詢</a></li>
						<%
						if(session.getAttribute("login")!=null){
							AccountBean account =(AccountBean) session.getAttribute("login");
							if(account.getPrivilege()!=0){
								out.print("<li><span class='opener'>後台管理</span><ul>");
								if((account.getPrivilege()&1)!=0)	
									out.print("<li><a href='Commodity?account=admin'>商品管理</a></li>");
								if((account.getPrivilege()&2)!=0)
									out.print("<li><a href='OrderAdmin'>訂單管理</a></li>");
								if((account.getPrivilege()&4)!=0)	
									out.print("<li><a href='MemberAdmin'>會員管理</a></li>");
								if((account.getPrivilege()&8)!=0)
									out.print("<li><a href='MarqueeAdmin?do=get'>跑馬燈內容管理</a></li>");
								out.print("</ul></li>");
							}
						}
						%>
					</ul>
				</nav>

				<!-- Footer -->
				<footer id="footer">
					<div>在線人數:<%=OnlineCounter.getOnline() %></div>
					<p class="copyright">&copy; Untitled. All rights reserved.</p>
				</footer>

			</div>
		</div>

	</div>

	<!-- Scripts -->
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/browser.min.js"></script>
	<script src="assets/js/breakpoints.min.js"></script>
	<script src="assets/js/util.js"></script>
	<script src="assets/js/main.js"></script>
	<script>
		$(document).on(
				'click',
				'th.sort',
				function() {
					var table = $(this).parents('table').eq(0);
					var rows = table.find('tr:gt(0)').toArray().sort(
							comparer($(this).index()));
					this.asc = !this.asc;
					if (!this.asc) {
						rows = rows.reverse();
					}
					table.children('tbody').empty().html(rows);
				});
		function comparer(index) {
			return function(a, b) {
				var valA = getCellValue(a, index), valB = getCellValue(b, index);
				return $.isNumeric(valA) && $.isNumeric(valB) ? valA - valB
						: valA.localeCompare(valB);
			};
		}
		function getCellValue(row, index) {
			return $(row).children('td').eq(index).text();
		}
	</script>
</body>
</html>