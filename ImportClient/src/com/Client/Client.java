
package com.Client;
import java.time.LocalDate;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "Client")
@XmlAccessorType(XmlAccessType.FIELD)
public class Client {
	 @XmlElement(name = "ClientID")
	private String clientId;
	  @XmlElement(name = "FirstName")
	private String firstName;
	 @XmlElement(name = "LastName")
	private String lastName;
	  @XmlElement(name = "Gender")
	private String gender;
	 @XmlElement(name = "MartialStatus")
	private String martialStatus;
	 @XmlElement(name = "DateOfBirth")
	 @XmlJavaTypeAdapter(LocalDateAdapter.class)
	private LocalDate dob;
	 @XmlElement(name = "Address")
	private String address;
	 @XmlElement(name = "Country")
	private String Country;
	 public Client() {
		 
	 }
public Client(String clientId, String firstName, String lastName, String gender, String martialStatus, LocalDate dob,
			String address, String country) {
		super();
		this.clientId = clientId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.martialStatus = martialStatus;
		this.dob = dob;
		this.address = address;
		Country = country;
	}

public String getClientId() {
	return clientId;
}


public void setClientId(String clientId) {
	this.clientId = clientId;
}
public String getFirstName() {
	return firstName;
}


public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return lastName;
}


public void setLastName(String lastName) {
	this.lastName = lastName;
}
public String getGender() {
	return gender;
}


public void setGender(String gender) {
	this.gender = gender;
}
public String getMartialStatus() {
	return martialStatus;
}


public void setMartialStatus(String martialStatus) {
	this.martialStatus = martialStatus;
}
public LocalDate getDob() {
	return dob;
}

public void setDob(String dob) {
	this.dob = LocalDate.parse(dob);
}
public String getAddress() {
	return address;
}


public void setAddress(String address) {
	this.address = address;
}
public String getCountry() {
	return Country;
}


public void setCountry(String country) {
	Country = country;
}


}
