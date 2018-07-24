<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="com.shop.model.*,java.util.*"%>
<%
	CommodityBean cb = new CommodityBean();
%>
<!DOCTYPE html>
<html>
<head>
<title>新增商品</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
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
					<strong>首頁>商品管理>新增商品</strong>
					<marquee direction="left" height="30" scrollamount="5" style="color:red;"><%=marquee.getContext() %></marquee>
				</header>


				<br> <br>
				<h3>新增商品</h3>
				<!-- Form -->
				<form method="post" action="CommodityAdd" enctype="multipart/form-data">
                    <!--   enctype="multipart/form-data" 不對字符編碼 -->
					<div class="row gtr-uniform">
						<div class="col-12 col-12-xsmall">
							<input type="text" id="name" name="valName" placeholder="商品名稱"
								required />
						</div>
						<div class="col-12 col-12-xsmall">
							<select name="valCategory" id="">
								<option value="商品分類">商品分類</option>
								<option value="配件">配件</option>
								<option value="鞋款">鞋款</option>
								<option value="背包">背包</option>
								<option value="帽子">帽子</option>
							</select>
						</div>
						<div class="col-12 col-12-xsmall">
							<input type="text" id="" name="valPrice"
								placeholder="商品單價" required />
						</div>
						<div class="col-12 col-12-xsmall">
							<input type="text" id="" name="valQuantity" placeholder="商品數量"
								required />
						</div>
						<div class="col-12 col-12-xsmall">
							<div class="col-12">
								<textarea name="valDetail" id=""
									placeholder="新增描述" rows="6"></textarea>
							</div>
						</div>
						<div class="col-12 col-12-xsmall">
							<input type="text" id="" name="valSpec" placeholder="商品規格(顏色,大小等)"
								required />
						</div>
						<div class="col-12 col-12-xsmall">
							<input type="file" id="" name="valImage" required />
						</div>
					</div>
					<br>
					<div class="col-12">
						<ul class="actions">
							<li><input type="submit" value="確認新增" class="primary" /></li>
							<li><input type="reset" value="清空" /></li>
						</ul>
					</div>
				</form>
				<!-- Break -->
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
					<header class="major">						<h2>Menu</h2>
					</header>
					<ul>
						<li><a href="index.jsp">回首頁</a></li>
						<%
							if(session.getAttribute("login")==null)
								out.print("<li><a href='login.jsp'>登入</a> <a href='signup.jsp'>註冊會員</a></li>");
							else
								out.print("<li><a href='logout.jsp'>登出</a> <a href='member.jsp'>會員管理</a></li>");
						// 如果沒有登入，印出下列東西。
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
	</body>
</html>