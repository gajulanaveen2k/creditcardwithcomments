package com.cg.creditcardpayment.service;
/**
* <h1>ICustomerService</h1>
* ICustomerService is a interface where all the methods related to Customer are declared
* <p>
* 
*
* @author  P Venkata Sai Reddy
* @version 1.0
* @since   2021-03-31 
*/
import java.util.List;

import com.cg.creditcardpayment.exception.AccountException;
import com.cg.creditcardpayment.exception.CustomerException;
import com.cg.creditcardpayment.model.AccountModel;
import com.cg.creditcardpayment.model.CustomerModel;

public interface ICustomerService {
	/**
	 * This method checks customer by contact number
	 * @param contactNo to be checked
	 * @return boolean to check the contact number exists or not
	 * @throws CustomerException when an exception occurs
	 */
	boolean existsByContactNo(String contactNo) throws CustomerException;
	
	/**
	 * This method checks customer by email
	 * @param email to be checked
	 * @return boolean to check the email exists or not
	 * @throws CustomerException when an exception occurs
	 */
	boolean existsByEmail(String email) throws CustomerException;
	/**
	 * This method checks customer by userId
	 * @param UserId to be checked
	 * @return boolean to check the userId exists or not
	 * @throws CustomerException when an exception occurs
	 */
	boolean existsById(String userId) throws CustomerException;
	
	/**
	 * This method is used to add the new customer
	 * @param customer which contains the new customer details
	 * @param userId which contains UserId of the customer
	 * @return CustomerModel which is added 
	 * @throws CustomerException when exception occurs
	 */
	CustomerModel addCustomer(CustomerModel customer,String userId) throws CustomerException;
	/**
	 * This method is used to update the old customer
	 * @param customer which contains the updated customer details
	 * @return CustomerModel which is updated 
	 * @throws CustomerException when exception occurs
	 */
	CustomerModel updateCustomer(CustomerModel customer) throws CustomerException;
	
	/**
	 * This method deletes the customer by its customerId
	 * @param customerId which should be deleted
	 * @throws CustomerException when exception occurs
	 */
	void deleteById(String customerId) throws CustomerException;
	
	/**
	 *  This method search the customer by its customer number
	 * @param customerId to be searched
	 * @return CustomerModel when the customer is found
	 * @throws CustomerException when the exception occurs
	 */
	CustomerModel findById(String customerId) throws CustomerException;
	
	/**
	 * This method list all the customers
	 * @return List<CustomerModel> which contains all the customer details
	 */
	List<CustomerModel> findAll();

	/**
	 * This method adds account to the customer
	 * @param account consists of the account details to be added
	 * @param customerId to which the account should be added
	 * @return boolean to check whether the account is added or not
	 * @throws AccountException when account related exception occur
	 * @throws CustomerException when the customer related exception occurs
	 */
	boolean addAccount(AccountModel account,String customerId) throws AccountException, CustomerException;
	/**
	 * This method gets account which belongs to customerId
	 * @param customerId to fetch the account
	 * @return List<AccountModel> which contains the list of the accounts 
	 * @throws AccountException when the exception occurs
	 */
	List<AccountModel> getAccounts(String customerId) throws AccountException;
	
	

}
