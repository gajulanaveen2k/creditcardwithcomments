package com.cg.creditcardpayment.service;

import java.util.List;

import com.cg.creditcardpayment.exception.CreditCardException;
import com.cg.creditcardpayment.exception.TransactionException;
import com.cg.creditcardpayment.model.TransactionModel;

public interface ITransactionService {
	/**
	 * This method checks transaction by userId
	 * @param UserId to be checked
	 * @return boolean to check the userId exists or not
	 * @throws TransactionException when an exception occurs
	 */
	boolean existsById(Long transactionId) throws TransactionException;
	/**
	 * This method is used to add the new transaction
	 * @param transaction which contains the new transaction details
	 * @return TransactionModel which is added 
	 * @throws TransactionException when exception occurs
	 */
	TransactionModel add(TransactionModel transaction) throws TransactionException;
	/**
	 * This method is used to update the old transaction
	 * @param transaction which contains the updated transaction details
	 * @return TransactionModel which is updated 
	 * @throws TransactionException when exception occurs
	 */
	TransactionModel save(TransactionModel transaction) throws TransactionException;
	
	/**
	 * This method deletes the transaction by its transactionId
	 * @param transactionId which should be deleted
	 * @throws TransactionException when exception occurs
	 */
	void deleteById(Long transactionId) throws TransactionException;
	
	/**
	 *  This method search the transaction by its transaction number
	 * @param transactionId to be searched
	 * @return TransactionModel when the transaction is found
	 * @throws TransactionException when the exception occurs
	 */
	TransactionModel findById(Long transactionId) throws TransactionException;
	
	/**
	 * This method list all the transactions
	 * @return List<TransactionModel> which contains all the transaction details
	 */
	List<TransactionModel> findAll();
	
	/**
	 * This method is used to make a transactions
	 * @param cardNumber from which the transactions should be made
	 * @param amount of the transaction
	 * @param description of the transactions
	 * @return TransactionModel after the transaction is done
	 * @throws CreditCardException when the exception occurs
	 */
	TransactionModel transaction(String cardNumber,Double amount,String description) throws CreditCardException;

	/**
	 * This method is used to get transactionHistory
	 * @param cardNumber to get transaction history
	 * @return List<TransationModel> which contains all the transaction of the cardNumber
	 * @throws CreditCardException when exception occurs
	 */
	List<TransactionModel> transactionHistory(String cardNumber) throws CreditCardException;
}
