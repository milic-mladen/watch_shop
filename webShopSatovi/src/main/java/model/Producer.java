package model;

public class Producer {
private int id;
private String name;
private String country;
private String contactPerson;
private String contact;

public Producer() {
	// TODO Auto-generated constructor stub
}
public Producer(int id, String name, String country, String contactPerson, String contact) {
	this.id=id;
	this.name=name;
	this.country=country;
	this.contactPerson=contactPerson;
	this.contact=contact;
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
public String getCountry() {
	return country;
}
public void setCountry(String country) {
	this.country = country;
}
public String getContactPerson() {
	return contactPerson;
}
public void setContactPerson(String contactPerson) {
	this.contactPerson = contactPerson;
}
public String getContact() {
	return contact;
}
public void setContact(String contact) {
	this.contact = contact;
}
@Override
	public String toString() {
		return name;
	}
}
