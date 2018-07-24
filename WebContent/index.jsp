<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.shop.model.*,java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<title>E-Shop</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous">
<style>
img:hover {
	cursor: pointer;
	transition: all 0.6s;
	transform: scale(1.2);
}

body {
	background-color: lightyellow;
}


</style>

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
					<a href="index.jsp" class="logo"><strong>首頁</strong></a>
					<marquee direction="left" height="30" scrollamount="5"
						style="color: red;"><%=marquee.getContext()%></marquee>
				</header>

				<!-- Banner -->
				<section id="banner">
					<div class="content">
						<header>
							<img id="hover" width="800" height="200" src="images/title.png">
						</header>
						<p><h2>慶祝結訓，老師決定請全班去杜拜 !
						</h2>
						</p>
					</div>
					<span class="image object"> <img src="images/HomePic.png"
						alt="購物圖" />
					</span>
				</section>
				<div class="container">
					<H2>精選商品</H2>
					<div class="row clearfix">
						<div class="col-md-12 column">
							<div class="row">
								<div class="col-md-4">
									<div class="thumbnail">
										<img alt="300x200" src="images/1529989339866.jpg" />
										<div class="caption">
											<h3>天炫寶寶</h3>
											<h3>$:80</h3>
											
												<a class="btn btn-primary" href="#">前往</a>
											</p>
										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="thumbnail">
										<img height="200" width="300" src="images/1529993011308.jpg" />
										<div class="caption">
											<h3>風潮時尚法式鉛筆盒</h3>
											<h3>$:100</h3>
											<a class="btn btn-primary" href="#">前往</a>
											</p>
										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="thumbnail">
										<img height="200" width="300" src="images/1530684481909.jpg" />
										<div class="caption">
											<h3>時尚大水壺</h3>
											<h3>$:200</h3>
											
												<a class="btn btn-primary" href="#">前往</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>

		<!-- Sidebar -->
		<div id="sidebar">
			<div class="inner">

				<!-- Search -->
				<section>
					<header>
						<div class="foo">
							<h2>妮琪姊的購物天堂</h2>
						</div>
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

</body>
</html>