package com.cg.creditcardpayment.model;
/**
* <h1>Login Model</h1>
* The Login Model program implements an application such that
* the user can login using the username and password and send the details to entity with help of Parser
* and perform some Validations.
* <p>
* 
*
* @author  Shambhavi
* @version 1.0
* @since   2021-03-31 
*/

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class LoginModel {
	/**
	 * This a local variable: {@link #userId} userId of the user
	 * @HasGetter
	 * @HasSetter
	 */
	@NotNull(message="user id cannot be null")	
	@NotBlank(message="user id cannot be blank")
	@Pattern(regexp="^[A-Za-z][A-Za-z0-9]{3,20}$")
	private String userId;
	
	/**
	 * This a local variable: {@link #password} password of the user
	 * @HasGetter
	 * @HasSetter
	 */
	@NotNull(message="password cannot be null")	
	@NotBlank(message="password cannot be blank")
	@Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&()])(?=\\S+$).{8,30}$",message="Password should contain atleast one Capital, Lower, Numeric and special charecters with min length of 8")
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	
	/**
	 * This a local variable: {@link #role} role of the user
	 * @HasGetter
	 * @HasSetter
	 */
	private String role;
	
	 /**
	 * Default Constructor
	 */
	public LoginModel() {
		/* Default Constructor*/
	}
	
	/**
	 * 
	 * @param userId		the unique id of the user
	 * @param password		the password to login 
	 * @param role			the role of the user
	 */
	public LoginModel(
			@NotNull(message = "user id cannot be null") @NotBlank(message = "user id cannot be blank") @Pattern(regexp="^[A-Za-z][A-Za-z0-9]{3,20}$") String userId,
			@NotNull(message = "password cannot be null") @NotBlank(message = "password cannot be blank") @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&()])(?=\\S+$).{8,30}$") String password,
			String role) {
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
		LoginModel other = (LoginModel) obj;
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
		return String.format("User [userId=%s, password=%s, role=%s]", userId, password, role);
	}
	
	
}
