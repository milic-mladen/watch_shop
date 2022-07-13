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
import model.User;

@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserController() {
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		if (action != null && action.equals("logOut")) {

			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect("index.jsp");

			// session.removeAttribute("logged in");

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// logovanje
		String action = request.getParameter("action");
		// System.out.println(action);

		if (action != null && action.equals("LogIn")) {

			String username = request.getParameter("username");
			String password = request.getParameter("password");

			if (username != null && username.length() > 0 && password != null && password.length() > 0) {

				DAO dao = new DAO();

				User loggedUser = dao.logIn(username, password);
				// System.out.println(loggedUser);

				if (loggedUser != null) {
					HttpSession session = request.getSession();
					session.setAttribute("logged in", loggedUser);

					// System.out.println("Korisnik je ulogovan");

					if (loggedUser.isAdmin()) {
						//samo ako se ulogovao administrator
						ArrayList<User>usersList = new ArrayList<User>();
						usersList = dao.getAllUsers();

	//PRIKAZ LISTE USERA ODMAH NAKON LOGOVANJA KAKO BI PODACI BILI DOSTUPNI NA STRANI ODMAH NAKON STO SE KORISNIK ULOGOVAO
						request.setAttribute("usersList", usersList);
						request.getRequestDispatcher("adminPage.jsp").forward(request, response);
						//response.sendRedirect("adminPage.jsp");
					} else {
						// ako se ulogovao kupac tj klijent
						response.sendRedirect("index.jsp");
					}

				} else {
					request.setAttribute("msgLogIn", "Error, incorrect username or password.");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}

			} else {
				request.setAttribute("msgLogIn", "Please fill all fields.");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}

			// System.out.println(username);
		} else if (action != null && action.equals("Register")) {
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String phone = request.getParameter("phone");
			String adress = request.getParameter("adress");
			String email = request.getParameter("email");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			User user = new User(firstName, lastName, phone, adress, email, username, password, false);
			if (firstName.equals("") || lastName.equals("") || phone.equals("") || adress.equals("") || email.equals("")
					|| username.equals("") || password.equals("")) {
				request.setAttribute("msgRegister", "Error, you need to fill all registering details.");
				request.setAttribute("data", user);
				request.getRequestDispatcher("login.jsp").forward(request, response);

			}
		
			if (!email.contains("@")) {
				user.setEmail("");
				request.setAttribute("msgRegister", "Error, email is invalid.");
				request.setAttribute("data", user);
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}

		
			DAO dao = new DAO();
			// validacija za email i username
			if (dao.userEmailExists(user)) {
				user.setEmail("");
				request.setAttribute("msgRegister", "Error, email is already in use!");
				request.setAttribute("data", user);
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
			if (dao.userUsernameExists(user)) {
				user.setUsername("");
				request.setAttribute("data", user);
				request.setAttribute("msgRegister", "Error, username is already in use!");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}

			if (dao.addUser(user)) {
				request.setAttribute("msgRegister", "Succesfully added user!");
				request.getRequestDispatcher("login.jsp").forward(request, response);

			} else {
				System.out.println("Usao u else a user je: "+user);
				request.setAttribute("msgRegister", "Error, user already exists!!");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}

		}

	}

}
