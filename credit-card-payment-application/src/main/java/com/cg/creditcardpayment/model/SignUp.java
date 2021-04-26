package com.cg.creditcardpayment.model;
/**
* <h1>SignUp</h1>
* The SignUp program implements an application such that
* the new user can signup with the key and userid given.
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

public class SignUp {
	/**
	 * This a local variable: {@link #userId} defines the unique Id for Customer
	 * @HasGetter
	 * @HasSetter
	 */
	@NotNull(message="user id cannot be null")	
	@NotBlank(message="user id cannot be blank")
	@Pattern(regexp="^[A-Za-z][A-Za-z0-9]{3,20}$")
	private String userId;
	
	/**
	 * This a local variable: {@link #key} defines the key given to the customer
	 * @HasGetter
	 * @HasSetter
	 */
	@NotNull(message="Key cannot be null")	
	@NotBlank(message="Key cannot be blank")
	@Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{4,10}$")
	private String key;
	
	/**
	 * This a local variable: {@link #createPassword} defines the new password created by the customer 
	 * @HasGetter
	 * @HasSetter
	 */
	@NotNull(message="Create password cannot be null")	
	@NotBlank(message="Create password cannot be blank")
	@Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&()])(?=\\S+$).{8,30}$")
	private String createPassword;
	
	/**
	 * This a local variable: {@link #confirmPassword} defines the confirmed password given by the customer 
	 * @HasGetter
	 * @HasSetter
	 */
	@NotNull(message="confirm password cannot be null")	
	@NotBlank(message="confirm password cannot be blank")
	@Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&()])(?=\\S+$).{8,30}$")
	private String confirmPassword;
	
	/**
	 * 
	 * @return userId as String
	 */
	public String getUserId() {
		return userId;
	}
	
	/**
	 * 
	 * @param userId to set the userId of the new customer
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	/**
	 * 
	 * @return key as a String
	 */
	public String getKey() {
		return key;
	}
	
	/**
	 * 
	 * @param key to set the first time key for the user to signUp
	 */
	public void setKey(String key) {
		this.key = key;
	}
	
	/**
	 * 
	 * @return createPassword as a string
	 */
	public String getCreatePassword() {
		return createPassword;
	}
	
	/**
	 * 
	 * @param createPassword to set the created password by the user
	 */
	public void setCreatePassword(String createPassword) {
		this.createPassword = createPassword;
	}
	
	/**
	 * 
	 * @return confirmPassword as String
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}
	
	/**
	 * 
	 * @param confirmPassword to set the confirmed password by the user
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	/**
	 * Default Constructor
	 */
	public SignUp() {
		
	}
	
	/**
	 * 
	 * 
	 * Parameterized Constructor with parameters
	 * @param userId				the unique Id for the new users
	 * @param key					the key for the new user to first time signup
	 * @param createPassword		the created password by the user
	 * @param confirmPassword		the confirmed password by the user
	 */
	public SignUp(String userId, String key, String createPassword, String confirmPassword) {
		super();
		this.userId = userId;
		this.key = key;
		this.createPassword = createPassword;
		this.confirmPassword = confirmPassword;
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
	public String toString() {
		return String.format("SignUp [userId=%s, key=%s, createPassword=%s, confirmPassword=%s]", userId, key,
				createPassword, confirmPassword);
	}
	
	

}
