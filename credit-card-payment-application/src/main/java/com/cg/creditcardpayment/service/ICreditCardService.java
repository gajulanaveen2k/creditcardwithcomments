package com.cg.creditcardpayment.service;
/**
* <h1>ICreditCardService</h1>
* ICreditCardService is a interface where all the methods related to CreditCard are declared
* <p>
* 
*
* @author  Abhilash Reddy Gone
* @version 1.0
* @since   2021-03-31 
*/
import java.util.List;
import java.util.Set;

import com.cg.creditcardpayment.exception.CreditCardException;
import com.cg.creditcardpayment.exception.CustomerException;
import com.cg.creditcardpayment.model.CreditCardModel;

public interface ICreditCardService {

	/**
	 * This method confirms whether the given cardNumber is there in the data base
	 * @param cardNumber which should be searched
	 * @return boolean whether the given cardNumber exists or nut
	 * @throws CreditCardException when exception occurs
	 */
	boolean existsById(String cardNumber) throws CreditCardException;
	
	/**
	 * This method is used to add the new 
	 * @param creditCard which contains the new creditCard details
	 * @return CreditCardModel which is added 
	 * @throws CreditCardException when exception occurs
	 */
	CreditCardModel add(CreditCardModel creditCard) throws CreditCardException;
	/**
	 * This method is used to update the old creditCard
	 * @param crdeitCard which contains the updated creditCard details
	 * @return CreditCardModel which is updated 
	 * @throws CreditCardException when exception occurs
	 */
	CreditCardModel save(CreditCardModel creditCard) throws CreditCardException;
	
	/**
	 * This method deletes the creditCard by its creditCardNumber
	 * @param cardNumber which should be deleted
	 * @throws CreditCardException when exception occurs
	 */
	void deleteById(String cardNumber) throws CreditCardException;
	
	/**
	 *  This method search the CreditCard by its CreditCard number
	 * @param CreditCardNumber to be searched
	 * @return CreditCardModel when the CreditCard is found
	 * @throws CreditCardException when the exception occurs
	 */
	CreditCardModel findById(String cardNumber) throws CreditCardException;
	
	/**
	 * This method list all the CreditCards
	 * @return List<CreditCardModel> which contains all the CreditCard details
	 */
	List<CreditCardModel> findAll();
	
	/**
	 * This method finds all the CreditCards by the customerId
	 * @param customerId to which the CreditCards should be searched
	 * @return Set<CreditCardModel> which contain all the CreditCards of the customer
	 * @throws CustomerException when the exception occurs
	 */
	Set<CreditCardModel> findByCustomerId(String customerId) throws CreditCardException, CustomerException;

	/**
	 * This method add CreditCard by its customer
	 * @param creditCard which should be added
	 * @param customerId to which the CreditCard should be added
	 * @return CreditCardModel which is added to the given cutomer
	 * @throws CreditCardException when the CreditCard related exception occurs
	 * @throws CustomerException when the customer related exception occurs
	 */
	CreditCardModel addToCustomer(CreditCardModel creditCard, String customerId)throws CreditCardException, CustomerException;

	/**
	 * This method deletes the CreditCard by customerId
	 * @param customerId to delete the CreditCard
	 * @param cardNumber to delete the CreditCard
	 * @throws CreditCardException when CreditCard related exception occurs
	 * @throws CustomerException when customer related exception occurs
	 */
	void deleteCreditCardOfCustomer(String customerId, String cardNumber) throws CreditCardException, CustomerException;
}
