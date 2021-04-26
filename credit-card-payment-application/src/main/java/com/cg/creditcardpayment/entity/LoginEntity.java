package com.cg.creditcardpayment.entity;
/**
* <h1>LoginEntity</h1>
* The Login Entity program implements an application such that
* the login data is sent to the database
* <p>
* 
*
* @author  Shambhavi
* @version 1.0
* @since   2021-03-31 
*/
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class LoginEntity {
	
	/**
	 * This a local variable: {@link #userId} userId of the user
	 * @HasGetter
	 * @HasSetter
	 */
	@Id
	@Column(name="user_id")
	private String userId;
	
	/**
	 * This a local variable: {@link #password} password of the user
	 * @HasGetter
	 * @HasSetter
	 */
	@Column(name="password", nullable=false)
	private String password;
	
	/**
	 * This a local variable: {@link #role} role of the user
	 * @HasGetter
	 * @HasSetter
	 */
	@Column(name="role",nullable=false)
	private String role;
	
	/**
	 * This a local variable: {@link #user} consists of the customer to which the login details belongs to
	 * @HasGetter
	 * @HasSetter
	 */
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="user_id")
	private CustomerEntity user;
	
	 /**
	 * Default Constructor
	 */
	public LoginEntity() {
		/* Default Constructor*/
	}

	/**
	 * 
	 * @param userId		the unique id of the user
	 * @param password		the password to login 
	 * @param role			the role of the user
	 */
	public LoginEntity(String userId, String password, String role) {
		super();
		this.userId = userId;
		this.password = password;
		this.role = role;
	}

	/**
	 * 
	 * @return userId in String
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * 
	 * @param userId to set for the User login which is String type
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * 
	 * @return password in String
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 
	 * @param password to set for the User login which is String type
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 
	 * @return role in String 
	 */
	public String getRole() {
		return role;
	}

	/**
	 * 
	 * @param role to set for the user which is type String
	 */
	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
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
		LoginEntity other = (LoginEntity) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
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
		return String.format("userId=%s, password=%s, role=%s", userId, password, role);
	}
	
	
}
