package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Order {
	private int orderId, userId;
	private LocalDate orderDate;
	private LocalTime orderTime;
	private String status;
	private double orderSum;
	public Order() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Order(int orderId, int userId, LocalDate orderDate, LocalTime orderTime, String status, double orderSum) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.orderDate = orderDate;
		this.orderTime = orderTime;
		this.status = status;
		this.orderSum = orderSum;
	}


	public int getOrderId() {
		return orderId;
	}


	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public LocalDate getOrderDate() {
		return orderDate;
	}


	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}


	public LocalTime getOrderTime() {
		return orderTime;
	}


	public void setOrderTime(LocalTime orderTime) {
		this.orderTime = orderTime;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public double getOrderSum() {
		return orderSum;
	}


	public void setOrderSum(double orderSum) {
		this.orderSum = orderSum;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
