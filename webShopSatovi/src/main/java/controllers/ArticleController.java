package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.DAO;
import model.ArticleHelper;

/**
 * Servlet implementation class ArticleController
 */
@WebServlet("/ArticleController")
public class ArticleController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ArticleController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action != null && action.equals("showArticleData")) {
			String articleId = request.getParameter("id_article");

			try {

				int idArt = Integer.parseInt(articleId);
				DAO dao = new DAO();
				ArticleHelper article = dao.getArticleById(idArt);
				request.setAttribute("article", article);
				request.getRequestDispatcher("singleProduct.jsp").forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (action != null && action.equals("formAddToCart")) {
			// add to cart from article details page
			String articleId = request.getParameter("articleId");
			String qty = request.getParameter("qty");
			try {

				int idArt = Integer.parseInt(articleId);
				int q = Integer.parseInt(qty);
				DAO dao = new DAO();
				ArticleHelper article = dao.getArticleById(idArt);
				if (q < 1) {
					// default value for quantity if form value is invalid
					q = 1;
				}
				article.setCartQty(q);

				ArrayList<ArticleHelper> cartList = new ArrayList<ArticleHelper>();
				cartList.add(article);

				HttpSession sessionCart = request.getSession();
				ArrayList<ArticleHelper> existingCartList = null;
				existingCartList = (ArrayList<ArticleHelper>) sessionCart.getAttribute("cart");
				if (existingCartList != null && existingCartList.size() > 0) {
					// if cart is not empty
					existingCartList.add(article);
					sessionCart.setAttribute("cart", existingCartList);
				} else {
					// if cart is empty
					sessionCart.setAttribute("cart", cartList);

				}
				response.sendRedirect("checkout.jsp");
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (action != null && action.equals("linkAddToCart")) {
			// add to cart from index page when we click on cart icon

			String articleId = request.getParameter("articleId");
			try {
				int idArt = Integer.parseInt(articleId);
				int q = 1;// default value for quantity if we add article from index page

				DAO dao = new DAO();
				ArticleHelper article = dao.getArticleById(idArt);
				article.setCartQty(q);

				ArrayList<ArticleHelper> cartList = new ArrayList<ArticleHelper>();
				cartList.add(article);
				
				HttpSession sessionCart = request.getSession();
				ArrayList<ArticleHelper> existingCartList = null;
				existingCartList = (ArrayList<ArticleHelper>) sessionCart.getAttribute("cart");
				if (existingCartList != null && existingCartList.size() > 0) {
					// if cart is not empty
					existingCartList.add(article);
					sessionCart.setAttribute("cart", existingCartList);
				} else {
					// if cart is empty
					sessionCart.setAttribute("cart", cartList);

				}
				response.sendRedirect("checkout.jsp");

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
