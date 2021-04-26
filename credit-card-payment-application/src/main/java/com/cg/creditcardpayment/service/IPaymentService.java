package com.cg.creditcardpayment.service;
/**
* <h1>IPaymentService</h1>
* IPaymentService is a interface where all the methods related to Payment are declared
* <p>
* 
*
* @author  P Pranava Charitra 
* @version 1.0
* @since   2021-03-31 
*/
import java.util.List;

import com.cg.creditcardpayment.exception.AccountException;
import com.cg.creditcardpayment.exception.CreditCardException;
import com.cg.creditcardpayment.exception.PaymentException;
import com.cg.creditcardpayment.exception.StatementException;
import com.cg.creditcardpayment.model.PaymentModel;
import com.cg.creditcardpayment.model.StatementModel;

public interface IPaymentService {
	/**
	 * This method checks payment by paymentId
	 * @param UserId to be checked
	 * @return boolean to check the paymentId exists or not
	 */
	boolean existsById(Long paymentId);

	/**
	 * This method is used to add the new payment
	 * @param payment which contains the new payment details
	 * @param userId which contains paymentId of the payment
	 * @return PaymentModel which is added 
	 * @throws PaymentException when exception occurs
	 */
	PaymentModel add(PaymentModel payment) throws PaymentException;
	
	/**
	 * This method is used to update the payment
	 * @param payment which contains the updated payment details
	 * @return PaymentModel which is updated 
	 * @throws PaymentException when exception occurs
	 */
	PaymentModel save(PaymentModel payment) throws PaymentException;

	/**
	 * This method deletes the payment by its paymentId
	 * @param paymentId which should be deleted
	 * @throws PaymentException when exception occurs
	 */
	void deleteById(Long paymentId) throws PaymentException;
	
	/**
	 *  This method search the payment by its paymentId
	 * @param paytmentId to be searched
	 * @return PaymentModel when the payment is found
	 * @throws PaymentException when the exception occurs
	 */
	PaymentModel findById(Long paymentId) throws PaymentException;
	
	/**
	 * This method list all the payments
	 * @return List<PaymentModel> which contains all the payment details
	 */
	List<PaymentModel> findAll();
	/**
	 * This method is used to pay bill of the credit card
	 * @param payment which contains all the details of payment
	 * @param statementId to which the payment is done
	 * @param accountNumber from which the payment is done
	 * @return PaymentModel which contains all the payment details
	 * @throws PaymentException when payment related exception occurs
	 * @throws CreditCardException when credit card related exception occurs
	 * @throws StatementException when statement related exception occurs
	 * @throws AccountException when account related exception occurs 
	 */
	PaymentModel payBill(PaymentModel payment,Long statementId,String accountNumber)throws PaymentException, CreditCardException, StatementException, AccountException;
	
	/**
	 * This method is used to pay bill of the credit card
	 * @param payment which contains payment details
	 * @param statementId to which the payment is done
	 * @return PaymentModel which contains all the details of payment
	 * @throws PaymentException When payment related exception occurs
	 * @throws CreditCardException when creditCard related exception occurs
	 * @throws StatementException when statement related exception occurs
	 */
	PaymentModel payBill(PaymentModel payment,Long statementId) throws PaymentException, CreditCardException, StatementException;
	/**
	 * This method lists the pending bills of the credit card
	 * @param cardNumber to which the pending bills has to be fetched
	 * @return List<StatementModel> which contains all the statements of the credit cards
	 * @throws CreditCardException when exception occurs
	 */
	List<StatementModel> pendingBills(String cardNumber) throws CreditCardException;
	/**
	 * This method displays the payment history of the credit card
	 * @param cardNumber to which the payment history has to be fetched
	 * @return List<PaymentModel> which contains all the payments
	 * @throws CreditCardException when the exception occurs
	 */
	List<PaymentModel> paymentHistory (String cardNumber) throws CreditCardException;
	/**
	 *  This method is used to pay for credit card
	 * @param pay which is payment model
	 * @param cardNumber to which money has to be paid
	 * @return PaymentModel which contains the details of payment
	 * @throws PaymentException when payment related exception occurs
	 * @throws CreditCardException when credit card related exception occurs
	 * @throws StatementException when statement related exception related exception occurs
	 */
	PaymentModel payForCreditCard(PaymentModel pay, String cardNumber) throws PaymentException, CreditCardException, StatementException;
	
}
