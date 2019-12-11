<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="com.shop.model.*,java.util.*,com.shop.service.*,com.shop.service.*"%>
<%
	List<CommodityBean> list = (ArrayList<CommodityBean>) session.getAttribute("cart");
	CommodityService commodityService = (CommodityService) application.getAttribute("commodityService");
	if (list != null) {
		for (CommodityBean ele : list) {
			CommodityBean commodity = new CommodityBean();
			commodityService.getById(commodity, ele.getId());
			if (commodity.getQuantity() < ele.getQuantity())
				ele.setQuantity(commodity.getQuantity());
		}
	}
%>
<!DOCTYPE html>
<html>
<head>

<title>購物車</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">


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
					<strong>首頁>購物車</strong>
					<marquee direction="left" height="30" scrollamount="5"
						style="color: red;"><%=marquee.getContext()%></marquee>
				</header>


				<br> <br>
				<form method="POST" action="CartUpdate">
					<div class="table-wrapper">
						<%
							if (list != null) {
						%>
						<table class="alt">
							<thead>
								<tr>
									<th></th>
									<th><strong>商品編號</strong></th>
									<th><strong>商品名稱</strong></th>
									<th><strong>縮圖</strong></th>
									<th><strong>分類</strong></th>
									<th><strong>單價</strong></th>
									<th><strong>數量</strong></th>
									<th><strong>規格</strong></th>
									<th><strong>小計</strong></th>
								</tr>
							</thead>
							<tbody>
								<%
									for (CommodityBean comm : list) {
											String commHref = "commodityUpdate.jsp?id=" + comm.getId();

											String cartDelete = "CartDelete?id=" + comm.getId();
								%>

								<tr class="main">
									<td style="vertical-align: middle;"><a
										href="javascript: deleteItem('<%=cartDelete%>')"><i
											class="fa fa-trash"></i></a></td>
									<td style="vertical-align: middle;"><%=comm.getId()%></td>
									<input type="hidden" value=<%=comm.getId()%> name="valId">
									<td style="vertical-align: middle;"><a class="logo"
										href=<%=commHref%>><strong> <%=comm.getName()%></strong></a></td>
									<%
										String imgPath = "images/" + comm.getImage();
									%>
									<td style="width: 123px; height: 124px;"><a
										href="<%=commHref%>" class="image"><img src=<%=imgPath%>
											alt="commodityIMG" height="100" /></a></td>
									<td style="vertical-align: middle;"><%=comm.getCategory()%></td>
									<td class="price" style="vertical-align: middle;"><%=comm.getPrice()%></td>
									<td class="quantity" style="vertical-align: middle;"><select
										name="valQuantity" required class=<%=comm.getId()%>>
											<%
												CommodityBean cb = new CommodityBean();
														commodityService.getById(cb, comm.getId());
														for (int i = 0; i <= cb.getQuantity(); i++) {
															if (comm.getQuantity() == 0) {
																//out.print("<option value='0' selected></option>");
															} else if (comm.getQuantity() == i)
																out.print("<option value=" + i + " selected>" + i + "</option>");
															else
																out.print("<option value=" + i + ">" + i + "</option>");
															if (i == 10)
																break;
														}
											%>
									</select> <%if (cb.getQuantity() == 0) { %> 商品已售完 <%  }  %></td>
									<td style="vertical-align: middle;"><%=comm.getSpec()%></td>
									<td class="sum" style="vertical-align: middle;"></td>
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
					<div class="total"></div>
				<%  if(list != null){
					
				
				out.print("<input type='submit' value='結帳'>");
				
				}%>
				
				</form>
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
							if (session.getAttribute("login") == null)
								out.print("<li><a href='login.jsp'>登入</a> <a href='signup.jsp'>註冊會員</a></li>");
							else
								out.print("<li><a href='logout.jsp'>登出</a> <a href='member.jsp'>會員管理</a></li>");
						%>
						<%
							int cartNum = 0;
							if (session.getAttribute("cart") == null) {
							} else {
								ArrayList<CommodityBean> cartList = (ArrayList<CommodityBean>) session.getAttribute("cart");
								cartNum = cartList.size();
							}
						%>
						<li><a href="cart.jsp">購物車(<%=cartNum%>)
						</a></li>
						<li><span class="opener">商品分類</span>
							<ul>
								<li><a href="Commodity?item=配件&account=member">配件</a></li>
								<li><a href="Commodity?item=鞋款&account=member">鞋款</a></li>
								<li><a href="Commodity?item=背包&account=member">背包</a></li>
								<li><a href="Commodity?item=帽子&account=member">帽子</a></li>
							</ul></li>
						<li><a href="Order">訂單查詢</a></li>
						<%
							if (session.getAttribute("login") != null) {
								AccountBean account = (AccountBean) session.getAttribute("login");
								if (account.getPrivilege() != 0) {
									out.print("<li><span class='opener'>後台管理</span><ul>");
									if ((account.getPrivilege() & 1) != 0)
										out.print("<li><a href='Commodity?account=admin'>商品管理</a></li>");
									if ((account.getPrivilege() & 2) != 0)
										out.print("<li><a href='OrderAdmin'>訂單管理</a></li>");
									if ((account.getPrivilege() & 4) != 0)
										out.print("<li><a href='MemberAdmin'>會員管理</a></li>");
									if ((account.getPrivilege() & 8) != 0)
										out.print("<li><a href='MarqueeAdmin?do=get'>跑馬燈內容管理</a></li>");
									out.print("</ul></li>");
								}
							}
						%>
					</ul>
				</nav>

				<!-- Footer -->
				<footer id="footer">
					<div>
						在線人數:<%=OnlineCounter.getOnline()%></div>
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
		function deleteItem(id) {
			var r = confirm("你確定要刪除這筆商品");
			if (r) {
				window.location.replace(id);
			}
		}
	</script>
	<script>
		var total = 0;
		$("tr.main").each(
				function() {

					var sum = parseInt($(this).find("td.price").text())
							* parseInt($(this).find("td.quantity").find(
									"select").val());
					$(this).find("td.sum").text(sum);
					total += sum;
					$("div.total").html("<h2>總共金額:" + total + "</h2>");
				})

		$("select").change(
				function() {
					var total = 0;
					$("tr.main").each(
							function() {
								var sum = parseInt($(this).find("td.price")
										.text())
										* parseInt($(this).find("td.quantity")
												.find("select").val());
								$(this).find("td.sum").text(sum);
								total += sum;
							});
					$("div.total").html("<h2>總共金額:" + total + "</h2>");
				});
	</script>
</body>
</html>