package com.cg.creditcardpayment.service;
/**
* <h1>ILoginService</h1>
* ILoginService is a interface where all the methods related to Login are declared
* <p>
* 
*
* @author  Shambhavi
* @version 1.0
* @since   2021-03-31 
*/
import java.util.List;

import com.cg.creditcardpayment.exception.CustomerException;
import com.cg.creditcardpayment.exception.LoginException;
import com.cg.creditcardpayment.model.ChangePassword;
import com.cg.creditcardpayment.model.SignUp;
import com.cg.creditcardpayment.model.LoginModel;

public interface ILoginService {
	/**
	 * This method checks whether the given userId exists or not
	 * @param userId to check the existence 
	 * @return boolean to identify the existence
	 */
	boolean existsById(String userId);
	
	/**
	 * This method add new login details
	 * @param user which contains all the user details
	 * @return LoginModel which is added
	 * @throws LoginException when Exception occurs
	 */
	LoginModel add(LoginModel user) throws LoginException;
	
	/**
	 * This method is used to update the Login details 
	 * @param user which contain updated details
	 * @return LoginModel which is updated
	 * @throws LoginException when exception occurs
	 */
	LoginModel save(LoginModel user) throws LoginException;
	/**
	 * This method is user for sign in
	 * @param user which contains the entered login details by user
	 * @return boolean to allow signIn or not
	 * @throws LoginException when the exception occurs
	 */
	boolean signIn(LoginModel user) throws LoginException;
	
	/**
	 * This method is used for signOut
	 * @param user which contains the login details
	 * @return boolean to allow logout
	 */
	boolean signOut(LoginModel user);
	/**
	 * This method deletes the login by its userId
	 * @param userId which should be deleted
	 * @throws CustomerException when exception occurs
	 */
	void deleteById(String userId) throws LoginException;
	
	/**
	 * This method search the user by its userId
	 * @param userId to be searched
	 * @return LoginModel when the userId is found
	 * @throws LoginException when the exception occurs
	 */
	LoginModel findById(String userId) throws LoginException;
	/**
	 * This method list all the login details
	 * @return List<LoginModel> which contains all the login details
	 */
	List<LoginModel> findAll();
	
	/**
	 * This method is used to change password
	 * @param changePassword which contains the details of change password
	 * @return boolean to check the password is changed or not
	 * @throws LoginException when the exception occurs
	 */
	boolean changePassword(ChangePassword changePassword) throws LoginException;
	
	/**
	 * This method is used of signUp
	 * @param signUp contains all the signUp details
	 * @return LoginModel which contains the login details
	 * @throws LoginException when the exception occurs
	 */ 
	LoginModel signUp(SignUp signUp) throws LoginException;
	
}
