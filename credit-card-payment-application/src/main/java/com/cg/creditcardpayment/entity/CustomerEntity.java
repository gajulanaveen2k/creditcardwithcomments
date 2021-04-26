package com.cg.creditcardpayment.entity;
/**
* <h1>CustomerEntity</h1>
* The Customer Entity program implements an application such that
* the data of the customer is sent to the database
* <p>
* 
*
* @author  P Venkata Sai Reddy
* @version 1.0
* @since   2021-03-31 
*/
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.cg.creditcardpayment.model.AddressModel;


@Entity
@Table(name="customers")
public class CustomerEntity {
	/**
	 * This a local variable: {@link #userId} defines the unique Id for Customer
	 */
	@Id
	@Column(name="user_id")
	private String userId;
	
	/**
	* This a local variable: {@link #userName} defines the user name of Customer
	* @HasGetter
	* @HasSetter
	*/
	@Column(name="user_name",nullable=false)
	private String userName;
	
	/**
	 * This a local variable: {@link #email} defines the user Email of the Customer
	 * @HasGetter
	 * @HasSetter
	 */	
	@Column(name="email",nullable=false)
	private String email;
	
	/**
	 * This a local variable: {@link #contactNo} defines the user mobile number of the Customer 
	 * @HasGetter
	 * @HasSetter
	 */	
	@Column(name="contact_number",nullable=false)
	private String contactNo;
	
	/**
	 * This a local variable: {@link #dob} defines the user Date of Birth of the Customer which should not be Null
	 * @HasGetter
	 * @HasSetter
	 */	
	@Column(name="date_of_birth",nullable=false)
	private  LocalDate dob;
	
	/**
	 * This a local variable: {@link #user} defines the login details of the Customer
	 * @HasGetter
	 * @HasSetter
	 */	
	@OneToOne(cascade=CascadeType.ALL, mappedBy="user")
	private LoginEntity user;
	
	/**
	 * This a local variable: {@link #address} defines the user Address of the Customer which should not be Null
	 * @HasGetter
	 * @HasSetter
	 */	
	@Embedded
	private AddressModel address;
	

	/**
	 * This a local variable: {@link #creditCard} defines the credit cards of the Customer 
	 * @HasGetter
	 * @HasSetter
	 */	
	@OneToMany(mappedBy="customer",cascade=CascadeType.ALL)
	private Set<CreditCardEntity> creditCard;

	/**
	 * This a local variable: {@link #accounts} defines the accounts of the Customer 
	 * @HasGetter
	 * @HasSetter
	 */
	@ManyToMany(fetch=FetchType.LAZY,cascade= CascadeType.ALL)
	@JoinTable(name="customer_account",
	joinColumns=@JoinColumn(name="user_id"),
	inverseJoinColumns=@JoinColumn(name="account_number"))
	private Set<AccountEntity> accounts;
	
	/**
	 * Default Constructor
	 */
	public CustomerEntity() {
		/* Default Constructor*/
	}
	
	/**
	 * Parameterized Constructor with parameters
	 * @param userId      the unique Id for Customer
	 * @param userName    the name of the Customer
	 * @param email       the email of the Customer
	 * @param contactNo   the contact number of Customer 
	 * @param dob		  the date of birth of Customer
	 * @param address     the Address of the Customer
	 */
	public CustomerEntity(String userId, String userName, String email, String contactNo, LocalDate dob, AddressModel address) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.contactNo = contactNo;
		this.dob = dob;
		this.address = address;
	}
	
	/**
	 * 
	 * @return accounts as Set<AccountEntity>
	 */
	public Set<AccountEntity> getAccounts() {
		return accounts;
	}
	
	/**
	 * 
	 * @param accounts which belongs to the customers
	 */
	public void setAccounts(Set<AccountEntity> accounts) {
		this.accounts = accounts;
	}

	/**
	 * 
	 * @return creditCard as Set<CreditCardEntity>
	 */
	public Set<CreditCardEntity> getCreditCard() {
		return creditCard;
	}
	
	/**
	 * 
	 * @param creditCard which belongs to the customers
	 */
	public void setCreditCard(Set<CreditCardEntity> creditCard) {
		this.creditCard = creditCard;
	}
	/**
	 * @return userId in String  
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId to set which is unique and string type
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/** 
	 * @return userName in String 	 
	 */
	public String getName() {
		return userName;
	}
	/**
	 * @param userName to set for the User which is string type
	 */
	public void setName(String userName) {
		this.userName = userName;
	}
	/** 
	 * @return email in String 	 
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email to set for the User which is string type
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/** 
	 * @return contactNo in String 	 
	 */
	public String getContactNo() {
		return contactNo;
	}
	/**
	 * @param contactNo to set for the User which is string type
	 */
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	/** 
	 * @return dob in LocalDate 	 
	 */
	public LocalDate getDob() {
		return dob;
	}
	/**
	 * @param dob to set for the User which is LocalDate type
	 */
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	/** 
	 * @return address in object 	 
	 */
	public AddressModel getAddress() {
		return address;
	}
	/**
	 * @param address to set for the User which is an object
	 */
	public void setAddress(AddressModel address) {
		this.address = address;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((contactNo == null) ? 0 : contactNo.hashCode());
		result = prime * result + ((dob == null) ? 0 : dob.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}
	
	/**
	 * Indicates whether some other object is "equal to" this one.<br><br>
	 * The <strong>equals</strong> method for class <strong>Object</strong> implements the most discriminating possible equivalence relation on objects; 
	 * that is, for any non-null reference values x and y, this method returns <strong>true</strong> if and only if x and y refer to the same object (<strong>x == y</strong> has the value <strong>true</strong>).
	 * <br><br>Note that it is generally necessary to override the <strong>hashCode</strong> method whenever this method is overridden, 
	 * so as to maintain the general contract for the <strong>hashCode</strong> method,
	 * which states that equal objects must have equal hash codes.
	 * <br>
	 * @param obj the reference object with which to compare.
	 * 
	 * @return true if this object is the same as the obj argument; false otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerEntity other = (CustomerEntity) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (contactNo == null) {
			if (other.contactNo != null)
				return false;
		} else if (!contactNo.equals(other.contactNo))
			return false;
		if (dob == null) {
			if (other.dob != null)
				return false;
		} else if (!dob.equals(other.dob))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	
	/**
	 * Returns a string representation of the object. In general, the toString method returns a string that "textually represents" this object. 
	 * The result should be a concise but informative representation that is easy for a person to read.
	 * 
	 * @return a string representation of the object.
	 */
	@Override
	public String toString() {
		return String.format("userId=%s, userName=%s, email=%s, contactNo=%s, dob=%s, address=%s", userId, userName,
				email, contactNo, dob, address);
	}

	
}
