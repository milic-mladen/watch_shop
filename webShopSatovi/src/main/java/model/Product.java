package model;

public class Product {
	private int id;
	private String name;
	private String description;
	private double price;
	private int stockNumber;
	private String color;
	private String image;
	private int producerId;
	public Product() {
		// TODO Auto-generated constructor stub
	}
	public Product(int id, String name, String description, double price, int stockNumber, String color,
			String image, int producerId) {
		this.id=id;
		this.name=name;
		this.description=description;
		this.price=price;
		this.stockNumber=stockNumber;
		this.color=color;
		this.image=image;
		this.producerId=producerId;
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getStockNumber() {
		return stockNumber;
	}
	public void setStockNumber(int stockNumber) {
		this.stockNumber = stockNumber;
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
	public int getProducerId() {
		return producerId;
	}
	public void setProducerId(int producerId) {
		this.producerId = producerId;
	}
@Override
public String toString() {
	return name;
}
}
