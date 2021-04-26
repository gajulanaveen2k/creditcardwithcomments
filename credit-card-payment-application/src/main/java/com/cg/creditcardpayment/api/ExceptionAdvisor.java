package com.cg.creditcardpayment.api;
/**
* <h1>ExceptionAdvisor</h1>
* The ExceptionAdvisor programs handles all the user defined exceptions
* <p>
* 
*
* @author  P Venkata Sai Reddy
* @version 1.0
* @since   2021-03-31 
*/
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cg.creditcardpayment.exception.AccountException;
import com.cg.creditcardpayment.exception.CustomerException;
import com.cg.creditcardpayment.exception.PaymentException;
import com.cg.creditcardpayment.exception.StatementException;
import com.cg.creditcardpayment.exception.TransactionException;
import com.cg.creditcardpayment.exception.LoginException;

@RestControllerAdvice
public class ExceptionAdvisor {
	/**
	 * This function handles exception related to login
	 * @param excep which is object of LoginException
	 * @return ResponseEntity with HTTP status code BAD_REQUEST
	 */
	@ExceptionHandler(LoginException.class)
	public ResponseEntity<String> handleCreditCardPaymentExceptionAction(LoginException excep) {
		return new ResponseEntity<>(excep.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * This function handles exception related to customer
	 * @param excep which is object of CustomerException
	 * @return ResponseEntity with HTTP status code BAD_REQUEST
	 */
	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<String> handleCreditCardPaymentExceptionAction(CustomerException excep) {
		return new ResponseEntity<>(excep.getMessage(),HttpStatus.BAD_REQUEST);
	}
	/**
	 * This function handles exception related to Payment
	 * @param excep which is object of PaymentException
	 * @return ResponseEntity with HTTP status code BAD_REQUEST
	 */
	@ExceptionHandler(PaymentException.class)
	public ResponseEntity<String> handleCreditCardPaymentExceptionAction(PaymentException excep) {
		return new ResponseEntity<>(excep.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * This function handles exception related to Statement
	 * @param excep which is object of StatementException
	 * @return ResponseEntity with HTTP status code BAD_REQUEST
	 */
	@ExceptionHandler(StatementException.class)
	public ResponseEntity<String> handleCreditCardPaymentExceptionAction(StatementException excep) {
		return new ResponseEntity<>(excep.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * This function handles exception related to Transaction
	 * @param excep which is object of TransactionException
	 * @return ResponseEntity with HTTP status code BAD_REQUEST
	 */
	@ExceptionHandler(TransactionException.class)
	public ResponseEntity<String> handleCreditCardPaymentExceptionAction(TransactionException excep) {
		return new ResponseEntity<>(excep.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * This function handles exception related to Account
	 * @param excep which is object of AccountException
	 * @return ResponseEntity with HTTP status code BAD_REQUEST
	 */
	@ExceptionHandler(AccountException.class)
	public ResponseEntity<String> handleCreditCardPaymentExceptionAction(AccountException excep) {
		return new ResponseEntity<>(excep.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * This function handles all the defined exception
	 * @param excep which is object of Exception
	 * @return ResponseEntity with HTTP status code INETRNAL_sERVER_ERROR
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleExceptionAction(Exception excep) {
		return new ResponseEntity<>(excep.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * This function binds all the errors 
	 * @param result which contains all the error
	 * @return all the errors in the string
	 */
	static String messageFrom(BindingResult result) {		
		return result.getAllErrors().stream()
				.map(err -> err.getObjectName() + "-"+err.getDefaultMessage())
				.collect(Collectors.toList()).toString();
	}
}
