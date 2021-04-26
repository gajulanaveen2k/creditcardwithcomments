package com.cg.creditcardpayment.api;
/**
* <h1>PaymentRestController</h1>
* The PaymentRestController program takes care of mapping the url's 
* to the functions which are specific to the Payment
* <p>
* 
*
* @author  P Pranava Charitra
* @version 1.0
* @since   2021-03-31 
*/
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.creditcardpayment.exception.AccountException;
import com.cg.creditcardpayment.exception.CreditCardException;
import com.cg.creditcardpayment.exception.PaymentException;
import com.cg.creditcardpayment.exception.StatementException;
import com.cg.creditcardpayment.model.PaymentModel;
import com.cg.creditcardpayment.model.StatementModel;
import com.cg.creditcardpayment.service.IPaymentService;

@RestController
@RequestMapping("/payments")
@CrossOrigin
public class PaymentRestController {
	/**
	 * This a local variable: {@link #paymentService} is the object of IPaymentService which is used to access the functions in IPaymentService 
	 */
	@Autowired
	private IPaymentService paymentService;
	
	/**
	 * This method retrieve all the payments from the findAll method in IPaymentService
	 * @return ResponseEntity which contains all the payments
	 */
	@GetMapping("/getAllPayments")
	public ResponseEntity<List<PaymentModel>> findAll() {
		return ResponseEntity.ok(paymentService.findAll());
	}
	/**
	 * This method retrieves the payment details of the given paymentId
	 * @param paymentId to find the payment details
	 * @return ResponseEntity, with HTTP status OK, which contains the payment details of the given paymentId 
	 * @throws PaymentException when there is an exception
	 */
	@GetMapping("/getPayment/{paymentId}")
	public ResponseEntity<PaymentModel> findById(@PathVariable("paymentId") Long paymentId) throws PaymentException{
		return ResponseEntity.ok(paymentService.findById(paymentId));
	}
	/**
	 * This method sends the new payment details to the add method in IPaymentService
	 * @param payment which contains payment details
	 * @return ResponseEntity with HTTP status which contain the newly added payment details
	 * @throws PaymentException when an exception occurs
	 */
	@PostMapping("/addPayment")
	public ResponseEntity<PaymentModel> add(@RequestBody PaymentModel payment) throws PaymentException {
		payment=paymentService.add(payment);
		return ResponseEntity.ok(payment);
	}
	
	/**
	 * This method is called when the customer wants to list pending bills
	 * @param cardNumber to which the customer wants see the pending the bill
	 * @return ResponseEntity with status code ok and details of pending bills
	 * @throws CreditCardException when an exception occurs
	 */
	@GetMapping("/pendingBills/{cardNumber}")
	public ResponseEntity<List<StatementModel>> getPendingStatements(@PathVariable("cardNumber") String cardNumber) throws CreditCardException{
		return ResponseEntity.ok(paymentService.pendingBills(cardNumber));
	}
	/**
	 * This method is called when the customer wants to pay the pending bills using UPI
	 * @param cardNumber to which the customer wants to pay the bill
	 * @return ResponseEntity with status code ok and details of pending bills
	 * @throws CreditCardException when an exception occurs
	 */
	@PostMapping("/pendingBills/payUsingUPI/{statementId}")
	public ResponseEntity<PaymentModel> paybill(@RequestBody PaymentModel pay,@PathVariable("statementId") Long statementId) throws PaymentException, CreditCardException, StatementException{
		return ResponseEntity.ok(paymentService.payBill(pay,statementId));
	}
	/**
	 * This method is called when the customer wants to pay the bill using UPI
	 * @param pay it is a payment model
	 * @param cardNumber to which the customer wants to pay the bill
	 * @return ResponseEntity with status code ok and details of pending bills
	 * @throws CreditCardException when an exception related to credit card occurs
	 * @throws PaymentException when exception related to payment occurs
	 * @throws StatementException when exception related to statement occurs
	 */
	@PostMapping("/pay/payUsingUPI/{cardNumber}")
	public ResponseEntity<PaymentModel> paybill(@RequestBody PaymentModel pay,@PathVariable("cardNumber") String cardNumber) throws PaymentException, CreditCardException, StatementException{
		return ResponseEntity.ok(paymentService.payForCreditCard(pay,cardNumber));
	}
	
	/**
	 * This method is called when the customer wants pay the bills from his account
	 * @param pay is the object of payment model
	 * @param statementId to which statement the customer wants to pay the bill
	 * @param accountNumber from which he wants to pay the bill
	 * @return ReaponseEntity with HTTP status code OK 
	 * @throws PaymentException when Payment related exception occurs
	 * @throws CreditCardException when credit card related exception occurs
	 * @throws StatementException when Statement related exception occurs
	 * @throws AccountException when Account related exception occurs
	 */
	@PostMapping("/pendingBills/payUsingAccount/{statementId}/{accountNumber}")
	public ResponseEntity<PaymentModel> paybillUsingAccount(@RequestBody PaymentModel pay,@PathVariable("statementId") Long statementId,@PathVariable("accountNumber") String accountNumber) throws PaymentException, CreditCardException, StatementException, AccountException{
		return ResponseEntity.ok(paymentService.payBill(pay,statementId,accountNumber));
	}
	
	/**
	 * This method called when the user wants to list his payment history
	 * @param cardNumber to which he wants to see his payment history
	 * @return ResponseEntity with HTTP status code OK
	 * @throws CreditCardException when an exception occurs
	 */
	@GetMapping("/paymentHistory/{cardNumber}")
	public ResponseEntity<List<PaymentModel>> paymentHistory(@PathVariable("cardNumber") String cardNumber) throws CreditCardException{
		return ResponseEntity.ok(paymentService.paymentHistory(cardNumber));
	}
	
}
