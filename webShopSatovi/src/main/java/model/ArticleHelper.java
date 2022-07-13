package model;

public class ArticleHelper {
	private int id;
	private int stockNumber;
	private String name;
	private String desc;
	private String categories;
	private String color;
	private String image;
	private double price;

	private int cartQty;
	public ArticleHelper() {
		// TODO Auto-generated constructor stub
	}

	public ArticleHelper(int id, int stockNumber, String name, String desc, String categories, String color,
			String image, double price, int cartQty) {
		super();
		this.id = id;
		this.stockNumber = stockNumber;
		this.name = name;
		this.desc = desc;
		this.categories = categories;
		this.color = color;
		this.image = image;
		this.price = price;
		this.cartQty=cartQty;
	}

	public int getCartQty() {
		return cartQty;
	}

	public void setCartQty(int cartQty) {
		this.cartQty = cartQty;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStockNumber() {
		return stockNumber;
	}

	public void setStockNumber(int stockNumber) {
		this.stockNumber = stockNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getCategories() {
		return categories;
	}

	public void setCategories(String categories) {
		this.categories = categories;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ArticleHelper [id=" + id + ", stockNumber=" + stockNumber + ", name=" + name + ", desc=" + desc
				+ ", categories=" + categories + ", color=" + color + ", image=" + image + ", price=" + price + "]";
	}

	
}
