package model;

public class User {

	
	private int id;
	private String first_name, last_name, phone, adress,email,username,password;
	private boolean admin;
	
	
	
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}



	public User(String first_name, String last_name, String phone, String adress, String email, String username,
			String password, boolean admin, int id) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.phone = phone;
		this.adress = adress;
		this.email = email;
		this.username = username;
		this.password = password;
		this.admin = admin;
		this.id=id;
	}
	public User(String first_name, String last_name, String phone, String adress, String email, String username,
			String password, boolean admin) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.phone = phone;
		this.adress = adress;
		this.email = email;
		this.username = username;
		this.password = password;
		this.admin = admin;
	}


	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getFirst_name() {
		return first_name;
	}




	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}




	public String getLast_name() {
		return last_name;
	}




	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}




	public String getPhone() {
		return phone;
	}




	public void setPhone(String phone) {
		this.phone = phone;
	}




	public String getAdress() {
		return adress;
	}




	public void setAdress(String adress) {
		this.adress = adress;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public String getUsername() {
		return username;
	}




	public void setUsername(String username) {
		this.username = username;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	public boolean isAdmin() {
		return admin;
	}




	public void setAdmin(boolean admin) {
		this.admin = admin;
	}




	@Override
	public String toString() {
		return "User [first_name=" + first_name + ", last_name=" + last_name + ", phone=" + phone + ", adress=" + adress
				+ ", email=" + email + ", username=" + username + ", password=" + password + ", admin=" + admin + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
}
