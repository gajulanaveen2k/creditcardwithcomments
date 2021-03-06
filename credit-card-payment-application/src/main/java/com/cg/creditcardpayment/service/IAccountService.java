package com.cg.creditcardpayment.service;
/**
* <h1>IAccountService</h1>
* IAccountService is a interface where all the methods related to account are declared
* <p>
* 
*
* @author  D Himavanth
* @version 1.0
* @since   2021-03-31 
*/
import java.util.List;
import java.util.Set;

import com.cg.creditcardpayment.exception.AccountException;
import com.cg.creditcardpayment.exception.CustomerException;
import com.cg.creditcardpayment.model.AccountModel;

public interface IAccountService {
	/**
	 * This method confirms whether the given number is there in the data base
	 * @param accountNumber which should be searched
	 * @return boolean whether the given number exists or nut
	 * @throws AccountException when exception occurs
	 */
	boolean existsById(String accountNumber) throws AccountException;
	
	/**
	 * This method is used to add the new account
	 * @param account which contains the new account details
	 * @return AccountModel which is added 
	 * @throws AccountException when exception occurs
	 */
	AccountModel add(AccountModel account) throws AccountException;
	
	/**
	 * This method is used to update the old account
	 * @param account which contains the updated account details
	 * @return AccountModel which is updated 
	 * @throws AccountException when exception occurs
	 */
	AccountModel save(AccountModel account) throws AccountException;
	
	/**
	 * This method deletes the account by its account number
	 * @param accountNumber which should be deleted
	 * @throws AccountException when exception occurs
	 */
	void deleteById(String accountNumber) throws AccountException;
	
	/**
	 * This method deletes the account by customerId
	 * @param customerId to delete the account
	 * @param accountNumber to delete the account
	 * @throws AccountException when account related exception occurs
	 * @throws CustomerException when customer related exception occurs
	 */
	void deleteAccountByCustomer(String customerId, String accountNumber) throws AccountException, CustomerException;
	
	/**
	 *  This method search the account by its account number
	 * @param accountNumber to be searched
	 * @return AccountModel when the account is found
	 * @throws AccountException when the exception occurs
	 */
	AccountModel findById(String accountNumber) throws AccountException;
	
	/**
	 * This method list all the accounts
	 * @return List<AccountModel> which contains all the account details
	 */
	List<AccountModel> findAll();
	
	/**
	 * This method add account by its customer
	 * @param account which should be added
	 * @param customerId to which the account should be added
	 * @return AccountModel which is added to the given cutomer
	 * @throws AccountException when the account related exception occurs
	 * @throws CustomerException when the customer related exception occurs
	 */
	AccountModel addByCustomer(AccountModel account,String customerId) throws AccountException, CustomerException;
	
	/**
	 * This method finds all the accounts by the customerId
	 * @param customerId to which the accounts should be searched
	 * @return Set<AccountModel> which contain all the accounts of the customer
	 * @throws CustomerException when the exception occurs
	 */
	Set<AccountModel> findAllByCustomerId(String customerId) throws CustomerException;
}
