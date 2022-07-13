package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DAO;
import model.Category;
import model.Producer;
import model.Product;
import model.User;

/**
 * Servlet implementation class AdminController
 */
@WebServlet("/AdminController")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		if (action != null && action.equals("showAllUsers")) {

			DAO dao = new DAO();

			ArrayList<User> usersList = new ArrayList<User>();
			usersList = dao.getAllUsers();

			request.setAttribute("usersList", usersList);
			request.getRequestDispatcher("adminPage.jsp").forward(request, response);

		} else if (action != null && action.equals("showAllProducts")) {
			// logika za prikaz svih proizvoda
			// VEZBA ZA DOMACI
			DAO dao = new DAO();

			ArrayList<Product> productsList = new ArrayList<Product>();
			productsList = dao.getAllProducts();

			request.setAttribute("productsList", productsList);
			request.getRequestDispatcher("adminProducts.jsp").forward(request, response);
		} else if (action != null && action.equals("showAllProducers")) {
			// logika za prikaz svih proizvodjaca
			// VEZBA ZA DOMACI
			DAO dao = new DAO();

			ArrayList<Producer> producersList = new ArrayList<Producer>();
			producersList = dao.getAllProducers();

			request.setAttribute("producersList", producersList);
			request.getRequestDispatcher("adminProducers.jsp").forward(request, response);
		} else if (action != null && action.equals("showAllCategories")) {
			// logika za prikaz svih kategorija
			// VEZBA ZA DOMACI
			DAO dao = new DAO();

			ArrayList<Category> categoriesList = new ArrayList<Category>();
			categoriesList = dao.getAllCategories();

			request.setAttribute("categoriesList", categoriesList);
			request.getRequestDispatcher("adminCategories.jsp").forward(request, response);
		} //

		else if (action != null && action.equals("deleteUser")) {
			// brisanje korisnika na klik linka delete u tabeli
			// Koraci
			// 1.kupljenje id-a
			// 2. Validacija id-a tj provera da li je popunjen
			// 3.Pretvaranje id-a u int
			// 4.Kreiranje metode u dao klasi za brisanje korisnika i poziv te metode u
			// kontroler
			// 5. Nakon brisanja redirekcija korisnika na prikaz svih korisnika

			String userId = request.getParameter("userId");
			if (userId != null && userId != "") {
				try {
					// parsiramo string u int
					int user_Id = Integer.parseInt(userId);
					DAO dao = new DAO();

					dao.deleteUser(user_Id);

					// nakon brisanja prosledjujemo azuriranu listu

					ArrayList<User> usersList = new ArrayList<User>();
					usersList = dao.getAllUsers();

					request.setAttribute("usersList", usersList);
					request.getRequestDispatcher("adminPage.jsp").forward(request, response);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else if (action != null && action.equals("deleteProduct")) {
			String productId = request.getParameter("productId");
			if (productId != null && productId != "") {
				try {
					// parsiramo string u int
					int product_id = Integer.parseInt(productId);
					DAO dao = new DAO();

					dao.deleteProduct(product_id);

					// nakon brisanja prosledjujemo azuriranu listu

					ArrayList<Product> productsList = new ArrayList<Product>();
					productsList = dao.getAllProducts();

					request.setAttribute("productsList", productsList);
					request.getRequestDispatcher("adminProducts.jsp").forward(request, response);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else if (action != null && action.equals("deleteCategory")) {
			String categoryId = request.getParameter("categoryId");
			if (categoryId != null && categoryId != "") {
				try {
					// parsiramo string u int
					int category_id = Integer.parseInt(categoryId);
					DAO dao = new DAO();

					dao.deleteCategory(category_id);

					// nakon brisanja prosledjujemo azuriranu listu

					ArrayList<Category> categoriesList = new ArrayList<Category>();
					categoriesList = dao.getAllCategories();

					request.setAttribute("categoriesList", categoriesList);
					request.getRequestDispatcher("adminCategories.jsp").forward(request, response);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else if (action != null && action.equals("deleteProducer")) {
			String producerId = request.getParameter("producerId");
			if (producerId != null && producerId != "") {
				try {
					// parsiramo string u int
					int producer_id = Integer.parseInt(producerId);
					DAO dao = new DAO();

					dao.deleteProducer(producer_id);

					// nakon brisanja prosledjujemo azuriranu listu

					ArrayList<Producer> producersList = new ArrayList<Producer>();
					producersList = dao.getAllProducers();

					request.setAttribute("producersList", producersList);
					request.getRequestDispatcher("adminProducers.jsp").forward(request, response);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else if (action != null && action.equals("showEditUser")) {
			// prikaz strane sa popunjenim podacima o korisniku
			// pravimo dao metodu koja po idu korisnika uzima korisnika i popunjava formu
			String userId = request.getParameter("userId");

			if (userId != null && userId != "") {

				try {
					int id = Integer.parseInt(userId);

					DAO dao = new DAO();
					User u;
					u = dao.getUserById(id);

					System.out.println(u);
					request.setAttribute("user", u);
					request.getRequestDispatcher("editUser.jsp").forward(request, response);

				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		} else if (action != null && action.equals("showEditProduct")) {
			// prikaz strane sa popunjenim podacima o korisniku
			// pravimo dao metodu koja po idu korisnika uzima korisnika i popunjava formu
			String productId = request.getParameter("productId");
			try {
				int id = Integer.parseInt(productId);

				DAO dao = new DAO();
				Product p;
				p = dao.getProductById(id);
				System.out.println("stampamo product!");
				System.out.println(p);
				request.setAttribute("product", p);
				request.getRequestDispatcher("editProduct.jsp").forward(request, response);

			} catch (Exception e) {
				// TODO: handle exception
			}
		} else if (action != null && action.equals("showEditProducer")) {
			String producertId = request.getParameter("producerId");
			try {
				int id = Integer.parseInt(producertId);

				DAO dao = new DAO();
				Producer p;
				p = dao.getProducerById(id);
				System.out.println(p);
				request.setAttribute("producer", p);
				request.getRequestDispatcher("editProducer.jsp").forward(request, response);

			} catch (Exception e) {
				// TODO: handle exception
			}
		} else if (action != null && action.equals("showEditCategory")) {
			String categoryId = request.getParameter("categoryId");
			try {
				int id = Integer.parseInt(categoryId);

				DAO dao = new DAO();
				Category c;
				c = dao.getCategoryById(id);
				System.out.println(c);
				request.setAttribute("category", c);
				request.getRequestDispatcher("editCategory.jsp").forward(request, response);

			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		if (action != null && action.equals("editUser")) {
			HashMap<String, String> userErrors = new HashMap<String, String>();

			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String phone = request.getParameter("phone");
			String adress = request.getParameter("adress");
			String email = request.getParameter("email");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String userId = request.getParameter("userId");
			if (firstName == null || firstName.length() == 0) {
				// error for first name
				userErrors.put("firstNameError", "Please enter valid first name");
			}
			if (lastName == null || lastName.length() == 0) {
				// error for last name
				userErrors.put("lastNameError", "Please enter valid last name");
			}

			if (phone == null || phone.length() == 0) {
				// error for phone
				userErrors.put("phoneError", "Please enter valid phone");
			}

			if (adress == null || adress.length() == 0) {
				// error for address
				userErrors.put("adressError", "Please enter valid adress");
			}
			if (username == null || username.length() == 0) {
				// error for username
				userErrors.put("usernameError", "Please enter valid username");
			}
			if (email == null || email.length() == 0) {
				// error for email
				userErrors.put("emailError", "Please enter valid email");
			}
			if (password == null || password.length() == 0) {
				// error for password
				userErrors.put("passwordError", "Please enter valid password");
			}
			if (!userErrors.isEmpty()) {
				User u = new User(firstName, lastName, phone, adress, email, username, password, false,
						Integer.parseInt(userId));
				request.setAttribute("user", u);
				request.setAttribute("userErrors", userErrors);
				request.getRequestDispatcher("editUser.jsp").forward(request, response);

			} else {
				try {
					int uId = Integer.parseInt(userId);

					DAO dao = new DAO();
					// uzimamo iz baze usera po id-u kako bi proverili da li je u bazi
					// sacuvan kao admin ili obican korisnik

					User u = null;
					u = dao.getUserById(uId);

					System.out.println("Izmena korisnika");
					System.out.println(u);
					User editedUser = new User(firstName, lastName, phone, adress, email, username, password,
							u.isAdmin(), uId);
					String result = dao.updateUser(editedUser);
					if (result.equals("Update success")) {
						ArrayList<User> usersList = new ArrayList<User>();
						usersList = dao.getAllUsers();

						request.setAttribute("usersList", usersList);
						request.setAttribute("updateMsg", "Update success");
						request.getRequestDispatcher("adminPage.jsp").forward(request, response);
					} else {
						userErrors.put("Error", "Update failed.");
					}
				} catch (Exception e) {
					System.out.println("Error in datda convert");
				}

			}

		} else if (action != null && action.equals("editProduct")) {

			HashMap<String, String> productErrors = new HashMap<String, String>();

			String name = request.getParameter("name");
			String description = request.getParameter("description");
			double price = Double.parseDouble(request.getParameter("price"));
			int stockNumber = Integer.parseInt(request.getParameter("stockNumber"));
			String color = request.getParameter("color");
			String image = request.getParameter("image");
			int producerId = Integer.parseInt(request.getParameter("producerId"));
			int productId = Integer.parseInt(request.getParameter("productId"));
			if (name != null && name.length() > 0) {
				if (description != null && description.length() > 0) {

					if (price > 0) {

						if (stockNumber > 0) {
							if (color != null && color.length() > 0) {
								if (image != null && image.length() > 0) {
									if (producerId > 0) {

										try {

											DAO dao = new DAO();

											Product p = new Product(productId, name, description, price, stockNumber,
													color, image, producerId);

											System.out.println("Izmena producta");
											System.out.println(p);

											String result = dao.updateProduct(p);
											if (result.equals("Update success")) {
												ArrayList<Product> productsList = new ArrayList<Product>();
												productsList = dao.getAllProducts();

												request.setAttribute("productsList", productsList);
												request.setAttribute("updateMsg", "Update success");
												request.getRequestDispatcher("adminProducts.jsp").forward(request,
														response);
											} else {
												productErrors.put("Error", "Update failed.");
											}
										} catch (Exception e) {
											System.out.println("Error in datda convert");
										}

									} else {
										System.out.println("usao u PRODUCERID");
										// error for producerId
										productErrors.put("producerIdError", "Please enter valid producerId");
									}
								} else {
									System.out.println("usao u image");
									// error for image
									productErrors.put("imageError", "Please enter valid image");
								}

							} else {
								System.out.println("usao u color");
								// error for color
								productErrors.put("colorError", "Please enter valid color");
							}

						} else {
							System.out.println("usao u stock");
							// error for stockNumber
							productErrors.put("stockNumberError", "Please enter valid stockNumber");
						}

					} else {
						System.out.println("usao u price");
						// error for price
						productErrors.put("priceError", "Please enter valid price");
					}

				} else {
					System.out.println("usao u descr");
					// error for description
					productErrors.put("descriptionError", "Please enter valid description");
				}
			} else {
				// error for name
				System.out.println("usao u name");
				productErrors.put("nameError", "Please enter valid name");
			}
			if (!productErrors.isEmpty()) {
				System.out.println("greska ");
				Product p = new Product(productId, name, description, price, stockNumber, color, image, producerId);
				request.setAttribute("product", p);
				request.setAttribute("productErrors", productErrors);
				request.getRequestDispatcher("editProduct.jsp").forward(request, response);

			}

		} else if (action != null && action.equals("editCategory")) {
			HashMap<String, String> categoryErrors = new HashMap<String, String>();

			String name = request.getParameter("name");
			int id = Integer.parseInt(request.getParameter("categoryId"));
			if (name != null && name.length() > 0) {
				if (id > 0) {

					try {

						DAO dao = new DAO();

						Category c = new Category(id, name);

						System.out.println("Izmena category");
						System.out.println(c);

						String result = dao.updateCategory(c);
						if (result.equals("Update success")) {
							ArrayList<Category> categoriesList = new ArrayList<Category>();
							categoriesList = dao.getAllCategories();

							request.setAttribute("categoriesList", categoriesList);
							request.setAttribute("updateMsg", "Update success");
							request.getRequestDispatcher("adminCategories.jsp").forward(request, response);
						} else {
							categoryErrors.put("Error", "Update failed.");
						}
					} catch (Exception e) {
						System.out.println("Error in datda convert");
					}

				} else {
					System.out.println("usao u PRODUCERID");
					// error for producerId
					categoryErrors.put("producerIdError", "Please enter valid producerId");
				}

			} else {
				// error for name
				System.out.println("usao u name");
				categoryErrors.put("nameError", "Please enter valid name");
			}
			if (!categoryErrors.isEmpty()) {
				System.out.println("greska ");
				Category c = new Category(id, name);
				request.setAttribute("category", c);
				request.setAttribute("categoryErrors", categoryErrors);
				request.getRequestDispatcher("editCategory.jsp").forward(request, response);

			}
		} else if (action != null && action.equals("editProducer")) {
			HashMap<String, String> producerErrors = new HashMap<String, String>();
			String name = request.getParameter("name");
			String country = request.getParameter("country");
			String contactPerson = request.getParameter("contactPerson");
			String contact = request.getParameter("contact");
			int id = Integer.parseInt(request.getParameter("producerId"));
			if (name != null && name.length() > 0) {
				if (country != null && country.length() > 0) {

					if (contactPerson != null && contactPerson.length() > 0) {

						if (contact != null && contact.length() > 0) {

							try {

								DAO dao = new DAO();

								System.out.println("Izmena producera");
								Producer p = new Producer(id, name, country, contactPerson, contact);
								String result = dao.updateProducer(p);
								if (result.equals("Update success")) {
									ArrayList<Producer> producersList = new ArrayList<Producer>();
									producersList = dao.getAllProducers();

									request.setAttribute("producersList", producersList);
									request.setAttribute("updateMsg", "Update success");
									request.getRequestDispatcher("adminProducers.jsp").forward(request, response);
								} else {
									producerErrors.put("Error", "Update failed.");
								}
							} catch (Exception e) {
								System.out.println("Error in datda convert");
							}

						} else {
							// error for contact
							producerErrors.put("contactError", "Please enter valid contact");
						}

					} else {
						// error for contactPerson
						producerErrors.put("contactPersonError", "Please enter valid contactPerson");
					}

				} else {
					// error for country
					producerErrors.put("countryError", "Please enter valid country");
				}
			} else {
				// error for name
				producerErrors.put("nameError", "Please enter valid name");
			}
			if (!producerErrors.isEmpty()) {
				Producer p = new Producer(id, name, country, contactPerson, contact);
				request.setAttribute("producer", p);
				request.setAttribute("producerErrors", producerErrors);
				request.getRequestDispatcher("editProducer.jsp").forward(request, response);

			}
		}

	}
}
