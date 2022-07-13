package controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.DAO;
import model.ArticleHelper;
import model.Order;
import model.User;

@WebServlet("/OrderController")
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public OrderController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action != null && action.equals("createOrder")) {
			// first of all we must check if user exist in session user

			HttpSession s = request.getSession();
			User loggedIn = null;
			loggedIn = (User) s.getAttribute("logged in");

			if (loggedIn != null) {
				// to be continued..
				// kada se uloguje pokupiti sesiju cart i user i pozvati dao metodu koju ce da
				// popunjuje tabelu orders zadatak za sl cas
				ArrayList<ArticleHelper> cartList = (ArrayList<ArticleHelper>) s.getAttribute("cart");
				double sum = 0;
				for (int i = 0; i < cartList.size(); i++) {
					sum = sum + (cartList.get(i).getPrice() * cartList.get(i).getCartQty());
				}
				DAO dao = new DAO();
				LocalDate date = LocalDate.now();
				LocalTime time = LocalTime.now();
				Order o = new Order(0, loggedIn.getId(), date, time, "active", sum);
				int lastOrderId = dao.insertOrder(o);
				if (lastOrderId > 0) {
					boolean created = true;
					for (ArticleHelper article : cartList) {

						String orderMsg = dao.insertArticleOrder(article.getId(), lastOrderId, article.getCartQty());
						if (orderMsg == null) {
							created = false;
						}
					}
					if (created) {
						request.setAttribute("userError", "Thank you for your order, we will contact you.");
						request.getRequestDispatcher("checkout.jsp").forward(request, response);

					}else {
						request.setAttribute("userError", "Order is not created, please contact us.");
						request.getRequestDispatcher("checkout.jsp").forward(request, response);

					}
				}
			} else {
				request.setAttribute("userError", "You must be logged in, please log in.");
				request.getRequestDispatcher("checkout.jsp").forward(request, response);
			}
		}

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
