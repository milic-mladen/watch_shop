package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

import model.ArticleHelper;
import model.Category;
import model.Order;
import model.Producer;
import model.Product;
import model.User;

public class DAO {

	DbManager db = new DbManager();

	// DEFINICIJU SVIH SQL UPITA KOJE KORISTIMO NAD BAZOM
	private static String SELECTUSERBYUSERNAMEANDPASSWORD = "SELECT * FROM users WHERE username=? AND password=? ";
	private static String ADDUSER = "INSERT INTO users VALUES(?,?,?,?,?,?,?,?,?)";
	private static String SELECTALLUSERS = "SELECT * FROM users";
	private static String DELETEUSER = "DELETE FROM users WHERE user_id=?";
	private static String GETUSERBYID = "SELECT * FROM users WHERE user_id=?";
	private static String UPDATEUSER = "UPDATE users SET first_name=?, last_name=?,phone=?, adress=?, email=?, username=?, password=?, admin=? 	"
			+ " WHERE user_id=?";
	private static String SELECTALLARTICLES = "SELECT * FROM articles";
	private static String SELECTALLPRODUCERS = "SELECT * FROM producers";
	private static String SELECTALLCATEGORIES = "SELECT * FROM categories";
	private static String DELETEPRODUCT = "DELETE FROM articles WHERE article_id=?";
	private static String GETPRODUCTBYID = "SELECT * FROM articles WHERE article_id=?";
	private static String GETPRODUCERBYID = "SELECT * FROM producers WHERE producer_id=?";
	private static String GETCATEGORYBYID = "SELECT * FROM categories WHERE category_id=?";
	private static String UPDATEPRODUCT = "UPDATE articles SET article_name=?, article_desc=?,article_price=?, article_stock_num=?, article_color=?, article_image=?, producer_id=?"
			+ " WHERE article_id=?";
	private static String UPDATEPRODUCER = "UPDATE producers SET producer_name=?, producer_country=?, producer_contact_person=?, producer_contact=?"
			+ " WHERE producer_id=? ";
	private static String UPDATECATEGORY = "UPDATE categories SET category_name=? WHERE category_id=?";
	private static String DELETECATEGORY = "DELETE FROM categories WHERE category_id=?";
	private static String DELETEPRODUCER = "DELETE FROM producers WHERE producer_id=?";
	private static String SELECTALLARTICLESJOIN = "SELECT a.article_id, a.article_name, a.article_desc, a.article_price, "
			+ " a.article_stock_num, a.article_color, a.article_image, categories.category_name " + " FROM articles a "
			+ "JOIN articles_categories ON a.article_id = articles_categories.article_id "
			+ " JOIN categories on articles_categories.category_id = categories.category_id";
	private static String GETARTICLEBYID = "SELECT * FROM articles WHERE article_id=?";
	private static String INSERTORDER = "INSERT INTO orders VALUES(null,?,?,?,?,?)";
	private static String INSERTARTICLEORDER = "INSERT INTO articles_orders VALUES(null,?,?,?)";

	// DEFINICIJA METODA - SVAKI GORE DEFINISAN UPIT CE IMATI SVOJU METODU
	public User logIn(String username, String password) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet result = null;

		User u = null;

		try {

			conn = db.getConnection();

			pstm = conn.prepareStatement(SELECTUSERBYUSERNAMEANDPASSWORD);
			pstm.setString(1, username);
			pstm.setString(2, password);
			pstm.execute();

			result = pstm.getResultSet();

			if (result.next()) {
				u = new User();

				u.setId(result.getInt("user_id"));
				u.setFirst_name(result.getString("first_name"));
				u.setLast_name(result.getString("last_name"));
				u.setPhone(result.getString("phone"));
				u.setAdress(result.getString("adress"));
				u.setEmail(result.getString("email"));
				u.setUsername(result.getString("username"));
				u.setPassword(result.getString("password"));
				u.setAdmin(result.getBoolean("admin"));

			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		return u;

	}

	public boolean addUser(User user) {
		Connection conn = null;
		PreparedStatement pstm = null;

		try {

			conn = db.getConnection();

			pstm = conn.prepareStatement(ADDUSER);
			pstm.setInt(1, nextAvailableId());
			pstm.setString(2, user.getFirst_name());
			pstm.setString(3, user.getLast_name());
			pstm.setString(4, user.getPhone());
			pstm.setString(5, user.getAdress());
			pstm.setString(6, user.getEmail());
			pstm.setString(7, user.getUsername());
			pstm.setString(8, user.getPassword());
			pstm.setBoolean(9, user.isAdmin());
			pstm.executeUpdate();

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;

		}
	}

	public boolean userEmailExists(User user) {
		Connection conn = null;
		PreparedStatement pstm = null;

		try {

			conn = db.getConnection();

			pstm = conn.prepareStatement(SELECTALLUSERS);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				if (rs.getString("email").equals(user.getEmail())) {
					return true;
				}
			}

			return false;

		} catch (Exception e) {
			e.printStackTrace();
			return false;

		}
	}

	public boolean userUsernameExists(User user) {
		Connection conn = null;
		PreparedStatement pstm = null;

		try {

			conn = db.getConnection();

			pstm = conn.prepareStatement(SELECTALLUSERS);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				if (rs.getString("username").equals(user.getUsername())) {
					return true;
				}
			}

			return false;

		} catch (Exception e) {
			e.printStackTrace();
			return false;

		}
	}

	public int nextAvailableId() {
		Connection conn = null;
		PreparedStatement pstm = null;
		int brojac = 0;
		try {

			conn = db.getConnection();

			pstm = conn.prepareStatement(SELECTALLUSERS);
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				brojac = rs.getInt("user_id");
				System.out.println(brojac);
			}

			return ++brojac;

		} catch (Exception e) {
			e.printStackTrace();
			return brojac++;

		}
	}

	public ArrayList<User> getAllUsers() {
		Connection conn = null;
		PreparedStatement pstm = null;
		ArrayList<User> usersList = new ArrayList<User>();
		try {

			conn = db.getConnection();

			pstm = conn.prepareStatement(SELECTALLUSERS);
			ResultSet result = pstm.executeQuery();

			while (result.next()) {
				User u = new User();
				u.setId(result.getInt("user_id"));
				u.setFirst_name(result.getString("first_name"));
				u.setLast_name(result.getString("last_name"));
				u.setPhone(result.getString("phone"));
				u.setAdress(result.getString("adress"));
				u.setEmail(result.getString("email"));
				u.setUsername(result.getString("username"));
				u.setPassword(result.getString("password"));
				u.setAdmin(result.getBoolean("admin"));

				usersList.add(u);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
		return usersList;
	}

	public void deleteUser(int id) {
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = db.getConnection();
			pstm = conn.prepareStatement(DELETEUSER);

			pstm.setInt(1, id);
			pstm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(conn);
			db.closePrepareStmt(pstm);
		}

	}

	public User getUserById(int id) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet result = null;
		User u = null;
		try {
			conn = db.getConnection();
			pstm = conn.prepareStatement(GETUSERBYID);

			pstm.setInt(1, id);
			pstm.execute();

			result = pstm.getResultSet();

			if (result.next()) {
				u = new User();
				u.setId(result.getInt("user_id"));
				u.setFirst_name(result.getString("first_name"));
				u.setLast_name(result.getString("last_name"));
				u.setPhone(result.getString("phone"));
				u.setAdress(result.getString("adress"));
				u.setEmail(result.getString("email"));
				u.setUsername(result.getString("username"));
				u.setPassword(result.getString("password"));
				u.setAdmin(result.getBoolean("admin"));
			}
			return u;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			db.closeConnection(conn);
			db.closePrepareStmt(pstm);
		}

	}

	public String updateUser(User u) {
		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = db.getConnection();
			pstm = conn.prepareStatement(UPDATEUSER);

			pstm.setString(1, u.getFirst_name());
			pstm.setString(2, u.getLast_name());
			pstm.setString(3, u.getPhone());
			pstm.setString(4, u.getAdress());
			pstm.setString(5, u.getEmail());
			pstm.setString(6, u.getUsername());
			pstm.setString(7, u.getPassword());
			pstm.setBoolean(8, u.isAdmin());
			pstm.setInt(9, u.getId());

			pstm.execute();
			return "Update success";
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		} finally {
			db.closeConnection(conn);
			db.closePrepareStmt(pstm);
		}
	}

	public ArrayList<Product> getAllProducts() {

		Connection conn = null;
		PreparedStatement pstm = null;
		ArrayList<Product> productsList = new ArrayList<Product>();
		try {

			conn = db.getConnection();

			pstm = conn.prepareStatement(SELECTALLARTICLES);
			ResultSet result = pstm.executeQuery();

			while (result.next()) {
				Product p = new Product();
				p.setId(result.getInt("article_id"));
				p.setName(result.getString("article_name"));
				p.setDescription(result.getString("article_desc"));
				p.setPrice(result.getDouble("article_price"));
				p.setStockNumber(result.getInt("article_stock_num"));
				p.setColor(result.getString("article_color"));
				p.setImage(result.getString("article_image"));
				p.setProducerId(result.getInt("producer_id"));

				productsList.add(p);
			}
			return productsList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		} finally {
			db.closeConnection(conn);
			db.closePrepareStmt(pstm);
		}
	}

	public ArrayList<Producer> getAllProducers() {
		Connection conn = null;
		PreparedStatement pstm = null;
		ArrayList<Producer> producersList = new ArrayList<Producer>();
		try {

			conn = db.getConnection();

			pstm = conn.prepareStatement(SELECTALLPRODUCERS);
			ResultSet result = pstm.executeQuery();

			while (result.next()) {
				Producer p = new Producer();
				p.setId(result.getInt("producer_id"));
				p.setName(result.getString("producer_name"));
				p.setCountry(result.getString("producer_country"));
				p.setContactPerson(result.getString("producer_contact_person"));
				p.setContact(result.getString("producer_contact"));

				producersList.add(p);
			}
			return producersList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		} finally {
			db.closeConnection(conn);
			db.closePrepareStmt(pstm);
		}
	}

	public ArrayList<Category> getAllCategories() {
		Connection conn = null;
		PreparedStatement pstm = null;
		ArrayList<Category> categoriesList = new ArrayList<Category>();
		try {

			conn = db.getConnection();

			pstm = conn.prepareStatement(SELECTALLCATEGORIES);
			ResultSet result = pstm.executeQuery();

			while (result.next()) {
				Category c = new Category();
				c.setId(result.getInt("category_id"));
				c.setName(result.getString("category_name"));

				categoriesList.add(c);
			}
			return categoriesList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		} finally {
			db.closeConnection(conn);
			db.closePrepareStmt(pstm);
		}
	}

	public void deleteProduct(int id) {
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = db.getConnection();
			pstm = conn.prepareStatement(DELETEPRODUCT);

			pstm.setInt(1, id);
			pstm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(conn);
			db.closePrepareStmt(pstm);
		}

	}

	public Product getProductById(int id) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet result = null;
		Product p = null;
		try {
			conn = db.getConnection();
			pstm = conn.prepareStatement(GETPRODUCTBYID);

			pstm.setInt(1, id);
			pstm.execute();

			result = pstm.getResultSet();

			if (result.next()) {
				p = new Product();
				p.setId(result.getInt("article_id"));
				p.setName(result.getString("article_name"));
				p.setDescription(result.getString("article_desc"));
				p.setPrice(result.getDouble("article_price"));
				p.setStockNumber(result.getInt("article_stock_num"));
				p.setColor(result.getString("article_color"));
				p.setImage(result.getString("article_image"));
				p.setProducerId(result.getInt("producer_id"));
			}
			return p;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			db.closeConnection(conn);
			db.closePrepareStmt(pstm);
		}

	}

	public String updateProduct(Product p) {
		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = db.getConnection();
			pstm = conn.prepareStatement(UPDATEPRODUCT);

			pstm.setString(1, p.getName());
			pstm.setString(2, p.getDescription());
			pstm.setDouble(3, p.getPrice());
			pstm.setInt(4, p.getStockNumber());
			pstm.setString(5, p.getColor());
			pstm.setString(6, p.getImage());
			pstm.setInt(7, p.getProducerId());
			pstm.setInt(8, p.getId());

			pstm.execute();
			return "Update success";
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		} finally {
			db.closeConnection(conn);
			db.closePrepareStmt(pstm);
		}
	}

	public void deleteCategory(int id) {
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = db.getConnection();
			pstm = conn.prepareStatement(DELETECATEGORY);

			pstm.setInt(1, id);
			pstm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(conn);
			db.closePrepareStmt(pstm);
		}

	}

	public void deleteProducer(int id) {
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = db.getConnection();
			pstm = conn.prepareStatement(DELETEPRODUCER);

			pstm.setInt(1, id);
			pstm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(conn);
			db.closePrepareStmt(pstm);
		}

	}

	public Producer getProducerById(int id) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet result = null;
		Producer p = null;
		try {
			conn = db.getConnection();
			pstm = conn.prepareStatement(GETPRODUCERBYID);

			pstm.setInt(1, id);
			pstm.execute();

			result = pstm.getResultSet();

			if (result.next()) {
				p = new Producer();
				p.setId(result.getInt("producer_id"));
				p.setName(result.getString("producer_name"));
				p.setCountry(result.getString("producer_country"));
				p.setContactPerson(result.getString("producer_contact_person"));
				p.setContact(result.getString("producer_contact"));
			}
			return p;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			db.closeConnection(conn);
			db.closePrepareStmt(pstm);
		}
	}

	public Category getCategoryById(int id) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet result = null;
		Category c = null;
		try {
			conn = db.getConnection();
			pstm = conn.prepareStatement(GETCATEGORYBYID);

			pstm.setInt(1, id);
			pstm.execute();

			result = pstm.getResultSet();

			if (result.next()) {
				c = new Category();
				c.setId(result.getInt("category_id"));
				c.setName(result.getString("category_name"));
			}
			return c;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			db.closeConnection(conn);
			db.closePrepareStmt(pstm);
		}
	}

	public String updateProducer(Producer p) {
		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = db.getConnection();
			pstm = conn.prepareStatement(UPDATEPRODUCER);

			pstm.setString(1, p.getName());
			pstm.setString(2, p.getCountry());
			pstm.setString(3, p.getContactPerson());
			pstm.setString(4, p.getContact());
			pstm.setInt(5, p.getId());

			pstm.execute();
			return "Update success";
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		} finally {
			db.closeConnection(conn);
			db.closePrepareStmt(pstm);
		}
	}

	public String updateCategory(Category c) {
		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = db.getConnection();
			pstm = conn.prepareStatement(UPDATECATEGORY);

			pstm.setString(1, c.getName());
			pstm.setInt(2, c.getId());

			pstm.execute();
			return "Update success";
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		} finally {
			db.closeConnection(conn);
			db.closePrepareStmt(pstm);
		}
	}

	public ArrayList<ArticleHelper> getAllArticles() {
		Connection conn = null;
		PreparedStatement pstm = null;
		ArrayList<ArticleHelper> articlesList = new ArrayList<ArticleHelper>();
		try {

			conn = db.getConnection();

			pstm = conn.prepareStatement(SELECTALLARTICLESJOIN);
			ResultSet result = pstm.executeQuery();
			System.out.println("ovde je usao");
			while (result.next()) {
				ArticleHelper a = new ArticleHelper();
				a.setId(result.getInt("article_id"));
				a.setName(result.getString("article_name"));
				a.setDesc(result.getString("article_desc"));
				a.setPrice(result.getDouble("article_price"));
				a.setStockNumber(result.getInt("article_stock_num"));
				a.setColor(result.getString("article_color"));
				a.setImage(result.getString("article_image"));
				a.setCategories(result.getString("category_name"));
				articlesList.add(a);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
		return articlesList;
	}

	public ArticleHelper getArticleById(int id) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet result = null;
		ArticleHelper a = null;
		try {
			conn = db.getConnection();
			pstm = conn.prepareStatement(GETARTICLEBYID);

			pstm.setInt(1, id);
			pstm.execute();

			result = pstm.getResultSet();

			if (result.next()) {
				a = new ArticleHelper();
				a.setId(result.getInt("article_id"));
				a.setName(result.getString("article_name"));
				a.setDesc(result.getString("article_desc"));
				a.setPrice(result.getDouble("article_price"));
				a.setStockNumber(result.getInt("article_stock_num"));
				a.setColor(result.getString("article_color"));
				a.setImage(result.getString("article_image"));
				// a.setCategories(result.getString("category_name"));nemamo naziv kategorije
				// jer nemamo join sql upit za sada
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			db.closeConnection(conn);
			db.closePrepareStmt(pstm);
		}
		return a;

	}

	public int insertOrder(Order o) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet result = null;
		int lastInsertedId = 0;
		try {

			conn = db.getConnection();
			pstm = conn.prepareStatement(INSERTORDER, Statement.RETURN_GENERATED_KEYS);

			pstm.setDate(1, java.sql.Date.valueOf(o.getOrderDate()));
			pstm.setTime(2, java.sql.Time.valueOf(o.getOrderTime()));
			pstm.setString(3, o.getStatus());
			pstm.setDouble(4, o.getOrderSum());
			pstm.setInt(5, o.getUserId());
			pstm.executeUpdate();
			result = pstm.getGeneratedKeys();
			while (result.next()) {
				lastInsertedId = result.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			db.closeConnection(conn);
			db.closePrepareStmt(pstm);
		}
		return lastInsertedId;
	}

	public String insertArticleOrder(int articleId, int orderId, int qty) {
		Connection conn = null;
		PreparedStatement pstm = null;
		try {

			conn = db.getConnection();
			pstm = conn.prepareStatement(INSERTARTICLEORDER);

			pstm.setInt(1, articleId);
			pstm.setInt(2, orderId);
			pstm.setInt(3, qty);
			
			pstm.executeUpdate();
			
			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			db.closeConnection(conn);
			db.closePrepareStmt(pstm);
		}
		
	}
}
