<%@page import="model.Producer"%>
<%@page import="model.Product"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.User"%>
<%

HttpSession sessionUser = request.getSession();
User loggedIn = null;
loggedIn = (User) sessionUser.getAttribute("logged in");

//kupimo product koji nam stize iz kontrolera
Producer p = (Producer) request.getAttribute("producer");
HashMap<String, String>producerErrors=null;
producerErrors=(HashMap<String, String>)request.getAttribute("producerErrors");
if (p != null) {
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Aroma Shop - Login</title>
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
			<nav class="navbar navbar-expand-lg navbar-dark">
				<div class="container">
					<h5>Admin page</h5>
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
							<li class="nav-item active"><a class="nav-link"
								href="AdminController?action=showAllUsers">Users</a></li>
							<li class="nav-item submenu dropdown"><a href="#"
								class="nav-link dropdown-toggle" data-toggle="dropdown"
								role="button" aria-haspopup="true" aria-expanded="false">
									Orders</a></li>
							<li class="nav-item submenu dropdown"><a
								href="AdminController?action=showAllProducts"
								class="nav-link dropdown-toggle" data-toggle="dropdown"
								role="button" aria-haspopup="true" aria-expanded="false">Products</a></li>
							<li class="nav-item submenu dropdown"><a href="#"
								class="nav-link dropdown-toggle" data-toggle="dropdown"
								role="button" aria-haspopup="true" aria-expanded="false">Categories</a>
							</li>
							<li class="nav-item"><a class="nav-link"
								href="AdminController?action=showAllProducers">Producers</a></li>
						</ul>

						<ul class="nav-shop">
							<li class="nav-item"><button>
									<i class="ti-shopping-cart"></i><span class="nav-shop__circle">0</span>
								</button></li>
							<li class="nav-item">
								<%
								if (loggedIn != null) {
								%>
								<h4>
									<%=loggedIn.getFirst_name() + " " + loggedIn.getLast_name()%></h4> <%
 }
 %>
							</li>

							<%
							if (loggedIn != null) {
							%>
							<li class="nav-item"><a class="button button-header"
								href="UserController?action=logOut">Log out</a></li>
							<%
							}
							%>




						</ul>
					</div>
				</div>
			</nav>
		</div>
	</header>
	<!--================ End Header Menu Area =================-->


	<!--================Login Box Area =================-->
	<section class="login_box_area">
		<div class="container">
			<div class="row">

				<div class="col-lg-12">
					<div class="login_form_inner"">
						<h3>Edit producer</h3>
						<div class="hover">
					
							<form class="row login_form" action="AdminController"
								id="contactForm" method="post">
								<div class="col-md-12 form-group">
									<input type="text" class="form-control" name="name"
										placeholder="First name" onfocus="this.placeholder = ''"
										onblur="this.placeholder = 'Name'"
										value="<%=p.getName()%>">
								</div>
								<div class="col-md-12 form-group">
									<input type="text" class="form-control" name="country"
										placeholder="Last name" onfocus="this.placeholder = ''"
										onblur="this.placeholder = 'Description'"
										value="<%=p.getCountry()%>">
								</div>
								<div class="col-md-12 form-group">
									<input type="text" class="form-control" name="contactPerson"
										placeholder="contactPerson" onfocus="this.placeholder = ''"
										onblur="this.placeholder = 'contactPerson'" value="<%=p.getContactPerson()%>">
								</div>
								<div class="col-md-12 form-group">
									<input type="text" class="form-control" name="contact"
										placeholder="contact" onfocus="this.placeholder = ''"
										onblur="this.placeholder = 'contact'"
										value="<%=p.getContact()%>">
								</div>
								

								
								<input type="hidden" name="producerId" value="<%=p.getId()%>">

								<div class="col-md-12 form-group">
									<button type="submit" name="action" value="editProducer"
										class="button button-login w-100">EDIT PRODUCER</button>
								</div>

							</form>
						</div>
					</div>
				</div>
			</div>
	</section>
	<!--================End Login Box Area =================-->




	<script src="vendors/jquery/jquery-3.2.1.min.js"></script>
	<script src="vendors/bootstrap/bootstrap.bundle.min.js"></script>
	<script src="vendors/skrollr.min.js"></script>
	<script src="vendors/owl-carousel/owl.carousel.min.js"></script>
	<script src="vendors/nice-select/jquery.nice-select.min.js"></script>
	<script src="vendors/jquery.ajaxchimp.min.js"></script>
	<script src="vendors/mail-script.js"></script>
	<script src="js/main.js"></script>

	<%
	}
	%>
</body>
</html>