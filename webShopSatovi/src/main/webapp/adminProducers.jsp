<%@page import="model.Producer"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%

HttpSession sessionUser = request.getSession();
User loggedIn = null;
loggedIn = (User) sessionUser.getAttribute("logged in");

ArrayList<Producer> producersList = (ArrayList<Producer>) request.getAttribute("producersList");
System.out.println(producersList);
String updateMsg = (String) request.getAttribute("updateMsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Main admin page</title>
<link rel="icon" href="img/Fevicon.png" type="image/png">
<link rel="stylesheet" href="vendors/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="vendors/fontawesome/css/all.min.css">
<link rel="stylesheet" href="vendors/themify-icons/themify-icons.css">
<link rel="stylesheet" href="vendors/nice-select/nice-select.css">
<link rel="stylesheet"
	href="vendors/owl-carousel/owl.theme.default.min.css">
<link rel="stylesheet" href="vendors/owl-carousel/owl.carousel.min.css">

<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<%
	if (loggedIn != null && loggedIn.isAdmin()) {
	%>

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
							<li class="nav-item submenu dropdown"><a href="AdminController?action=showAllCategories"
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
	<section class="login_box_area section-margin" style="margin: 20px">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="login_form_inner" style="padding: 20px">
						<!--ovde pravimo mi tabelu -->
						<%--<%
						if (updateMsg != null && updateMsg.length() > 0) {
							out.println("<span style='color:blue; font-weight:bold;'>" + updateMsg + "</span>");
						}
						%> --%>
						<table border="3" style="margin: 0 auto">
							<tr>
								<th>Name</th>
								<th>Country</th>
								<th>Contact person</th>
								<th>Contact</th>
								<th colspan="2">Action</th>

							</tr>
							<%
							for (int i = 0; i < producersList.size(); i++) {
							%>
							<tr>
								<td><%=producersList.get(i).getName()%></td>
								<td><%=producersList.get(i).getCountry()%></td>
								<td><%=producersList.get(i).getContactPerson()%></td>
								<td><%=producersList.get(i).getContact()%></td>
								<!-- znaci ne saljemo na edit nego showEditProducer pa tamo kada popunimo onda edit-->
								<td style="padding: 5px;"><a
									href="AdminController?action=showEditProducer&producerId=<%=producersList.get(i).getId()%>">Edit</a></td>
								<td style="padding: 5px;"><a
									href="AdminController?action=deleteProducer&producerId=<%=producersList.get(i).getId()%>">Delete</a></td>
							</tr>

							<%
							}
							%>
						</table>


					</div>
				</div>

			</div>
		</div>
	</section>
	<!--================End Login Box Area =================-->

	<%
	} else {
	response.sendRedirect("login.jsp");
	}
	%>
</body>
</html>