<%@page import="model.User"%>
<%@page import="model.ArticleHelper"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>




<%

HttpSession s = request.getSession();
//nije mi najjasnije kako je ovde rekao da je nebitno kako je nazivamo a posle pozivamo preko session prom
User loggedIn = (User)session.getAttribute("logged in");

ArrayList<ArticleHelper> cartList = (ArrayList<ArticleHelper>) session.getAttribute("cart");

String userError = "";
userError = (String) request.getAttribute("userError");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Aroma Shop - Checkout</title>
<link rel="icon" href="img/Fevicon.png" type="image/png">

<link rel="stylesheet" href="vendors/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="vendors/fontawesome/css/all.min.css">
<link rel="stylesheet" href="vendors/themify-icons/themify-icons.css">
<link rel="stylesheet" href="vendors/linericon/style.css">
<link rel="stylesheet"
	href="vendors/owl-carousel/owl.theme.default.min.css">
<link rel="stylesheet" href="vendors/owl-carousel/owl.carousel.min.css">
<link rel="stylesheet" href="vendors/nice-select/nice-select.css">
<link rel="stylesheet" href="vendors/nouislider/nouislider.min.css">

<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<!--================ Start Header Menu Area =================-->
	<header class="header_area">
		<div class="main_menu">
			<nav class="navbar navbar-expand-lg navbar-light">
				<div class="container">
					<a class="navbar-brand logo_h" href="index.jsp"><img
						src="img/logo.png" alt=""></a>
					<button class="navbar-toggler" type="button" data-toggle="collapse"
						data-target="#navbarSupportedContent"
						aria-controls="navbarSupportedContent" aria-expanded="false"
						aria-label="Toggle navigation">
						<span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<div class="collapse navbar-collapse offset"
						id="navbarSupportedContent">
						<ul class="nav navbar-nav menu_nav ml-auto mr-auto">
							<li class="nav-item"><a class="nav-link" href="index.jsp">Home</a></li>
							<li class="nav-item active submenu dropdown"><a href="#"
								class="nav-link dropdown-toggle" data-toggle="dropdown"
								role="button" aria-haspopup="true" aria-expanded="false">Shop</a>
								<ul class="dropdown-menu">
									<li class="nav-item"><a class="nav-link"
										href="category.html">Shop Category</a></li>
									
									<li class="nav-item"><a class="nav-link"
										href="checkout.jsp">Product Checkout</a></li>
									<li class="nav-item"><a class="nav-link"
										href="confirmation.html">Confirmation</a></li>
									<li class="nav-item"><a class="nav-link" href="cart.html">Shopping
											Cart</a></li>
								</ul></li>
							<li class="nav-item submenu dropdown"><a href="#"
								class="nav-link dropdown-toggle" data-toggle="dropdown"
								role="button" aria-haspopup="true" aria-expanded="false">Blog</a>
								<ul class="dropdown-menu">
									<li class="nav-item"><a class="nav-link" href="blog.html">Blog</a></li>
									<li class="nav-item"><a class="nav-link"
										href="single-blog.html">Blog Details</a></li>
								</ul></li>
						
							<li class="nav-item"><a class="nav-link" href="contact.html">Contact</a></li>
						</ul>

						<ul class="nav-shop">
						<li class="nav-item">
							  <%              
                 if(loggedIn != null){       
              %>
              <h4>Welcome: <%= loggedIn.getFirst_name() +" "+loggedIn.getLast_name() %></h4>
                         
               <% } %>
               </li>
                  <a href="checkout.jsp">
							<li class="nav-item"><button>
									 <%  if(cartList!=null){%>
              <li class="nav-item"><button><i class="ti-shopping-cart"></i><span class="nav-shop__circle"><%=cartList.size()%></span></button> </li>
              <% } else{%>
              <li class="nav-item"><button><i class="ti-shopping-cart"></i><span class="nav-shop__circle"><%=0%></span></button> </li>
              <% }%>
              </a>
								</button></li>
							<li class="nav-item"><a class="button button-header"
								href="register.jsp">Register</a></li>
						</ul>
					</div>
				</div>
			</nav>
		</div>
	</header>
	<!--================ End Header Menu Area =================-->

	<!-- ================ start banner area ================= -->

	<!-- ================ end banner area ================= -->


	<!--================Checkout Area =================-->
	<section class="checkout_area section-margin--small"
		style="margin-top: 20px;">
		<div class="container">
			<div class="returning_customer">
				<%
				if (userError != null) {
				%>
				<p style="color: red;"><%=userError%></p>
				<%
				}
				%>
				
				
				
				
				
				
				<form class="row contact_form" action="UserController" method="post"
					novalidate="novalidate">
					<div class="col-md-6 form-group p_star">
						<input type="text" class="form-control" name="username"
									placeholder="Username" onfocus="this.placeholder = ''"
									onblur="this.placeholder = 'Username'">
					</div>
					<div class="col-md-6 form-group p_star">
						<input type="password" class="form-control" name="password"
									placeholder="Password" onfocus="this.placeholder = ''"
									onblur="this.placeholder = 'Password'">
					</div>
					<div class="col-md-12 form-group">
							<button type="submit" name="action" value="LogIn"
									class="button button-login w-40">Log In</button>
					</div>
				</form>
			</div>

			<div class="billing_details">
				<div class="row">

					<div class="col-lg-8">
						<div class="order_box">
							<h2>Your Order</h2>
							<%
							if (cartList != null) {
							%>
							<ul class="list">
								<li><a href="#"><h4>
											Product <span>Total</span>
										</h4></a></li>
								<%
								double totalPrice = 0;
								for (int i = 0; i < cartList.size(); i++) {
								%>
								<li><a href="#"><%=cartList.get(i).getName()%><span
										class="middle">x <%=cartList.get(i).getCartQty()%></span> <span
										class="last">$<%=cartList.get(i).getPrice()%> &euro;
									</span></a></li>

								<%
								totalPrice = totalPrice + (cartList.get(i).getPrice() * cartList.get(i).getCartQty());

								}
								%>
							</ul>
							<ul class="list list_2">
								<li><a href="#">Shipping <span>Flat rate: 0.00
											&euro;</span></a></li>
								<li><a href="#">Total <span>$<%=totalPrice%>
											&euro;
									</span></a></li>
							</ul>


							<div class="text-center">
								<a class="button button-login "
									href="OrderController?action=createOrder">Proceed to
									checkout</a>
							</div>
							<%
							} else {
							%>
							<h3 styl="color:red;">Your cart is empty</h3>
							<%
							}
							%>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!--================End Checkout Area =================-->



	<!--================ Start footer Area  =================-->
	<footer>
		<div class="footer-area footer-only">
			<div class="container">
				<div class="row section_gap">
					<div class="col-lg-3 col-md-6 col-sm-6">
						<div class="single-footer-widget tp_widgets ">
							<h4 class="footer_title large_title">Our Mission</h4>
							<p>So seed seed green that winged cattle in. Gathering thing
								made fly you're no divided deep moved us lan Gathering thing us
								land years living.</p>
							<p>So seed seed green that winged cattle in. Gathering thing
								made fly you're no divided deep moved</p>
						</div>
					</div>
					<div class="offset-lg-1 col-lg-2 col-md-6 col-sm-6">
						<div class="single-footer-widget tp_widgets">
							<h4 class="footer_title">Quick Links</h4>
							<ul class="list">
								<li><a href="#">Home</a></li>
								<li><a href="#">Shop</a></li>
								<li><a href="#">Blog</a></li>
								<li><a href="#">Product</a></li>
								<li><a href="#">Brand</a></li>
								<li><a href="#">Contact</a></li>
							</ul>
						</div>
					</div>
					<div class="col-lg-2 col-md-6 col-sm-6">
						<div class="single-footer-widget instafeed">
							<h4 class="footer_title">Gallery</h4>
							<ul class="list instafeed d-flex flex-wrap">
								<li><img src="img/gallery/r1.jpg" alt=""></li>
								<li><img src="img/gallery/r2.jpg" alt=""></li>
								<li><img src="img/gallery/r3.jpg" alt=""></li>
								<li><img src="img/gallery/r5.jpg" alt=""></li>
								<li><img src="img/gallery/r7.jpg" alt=""></li>
								<li><img src="img/gallery/r8.jpg" alt=""></li>
							</ul>
						</div>
					</div>
					<div class="offset-lg-1 col-lg-3 col-md-6 col-sm-6">
						<div class="single-footer-widget tp_widgets">
							<h4 class="footer_title">Contact Us</h4>
							<div class="ml-40">
								<p class="sm-head">
									<span class="fa fa-location-arrow"></span> Head Office
								</p>
								<p>123, Main Street, Your City</p>

								<p class="sm-head">
									<span class="fa fa-phone"></span> Phone Number
								</p>
								<p>
									+123 456 7890 <br> +123 456 7890
								</p>

								<p class="sm-head">
									<span class="fa fa-envelope"></span> Email
								</p>
								<p>
									free@infoexample.com <br> www.infoexample.com
								</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="footer-bottom">
			<div class="container">
				<div class="row d-flex">
					<p class="col-lg-12 footer-text text-center">
						<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
						Copyright &copy;
						<script>
							document.write(new Date().getFullYear());
						</script>
						All rights reserved | This template is made with <i
							class="fa fa-heart" aria-hidden="true"></i> by <a
							href="https://colorlib.com" target="_blank">Colorlib</a>
						<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
					</p>
				</div>
			</div>
		</div>
	</footer>
	<!--================ End footer Area  =================-->



	<script src="vendors/jquery/jquery-3.2.1.min.js"></script>
	<script src="vendors/bootstrap/bootstrap.bundle.min.js"></script>
	<script src="vendors/skrollr.min.js"></script>
	<script src="vendors/owl-carousel/owl.carousel.min.js"></script>
	<script src="vendors/nice-select/jquery.nice-select.min.js"></script>
	<script src="vendors/jquery.ajaxchimp.min.js"></script>
	<script src="vendors/mail-script.js"></script>
	<script src="js/main.js"></script>
</body>
</html>