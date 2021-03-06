package com.cg.creditcardpayment.api;
/**
* <h1>TransactionRestController</h1>
* The TransactionRestController program takes care of mapping the url's 
* to the functions which are specific to the Transaction0
* <p>
* 
*
* @author  Gajula Naveen
* @version 1.0
* @since   2021-03-31 
*/
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.creditcardpayment.exception.CreditCardException;
import com.cg.creditcardpayment.exception.TransactionException;
import com.cg.creditcardpayment.model.TransactionModel;
import com.cg.creditcardpayment.service.ITransactionService;

@RestController
@RequestMapping("/transactions")
@CrossOrigin
public class TransactionRestController {
	/**
	 * This a local variable: {@link #transactionService} is the object of ITransacionService which is used to access the functions in IPaymentService 
	 */
	@Autowired
	private ITransactionService transactionService;
	
	/**
	 * This method retrieve all the transactions from the findAll method in ITransactionService
	 * @return ResponseEntity which contains all the Transactions
	 */
	@GetMapping("/getAllTransactions")
	public ResponseEntity<List<TransactionModel>> findAll() {
		return ResponseEntity.ok(transactionService.findAll());
	}
	
	/**
	 * This method calls findbyId in ITransactionService to fetch the details of the paticular transaction
	 * @param transactionId for which the details should be retrieved
	 * @return response which is ResponseEntity with HTTP status code FOUND
	 * @throws TransactionException
	 */
	@GetMapping("/getTransaction/{transactionId}")
	public ResponseEntity<TransactionModel> findById(@PathVariable("transactionId") Long transactionId) throws TransactionException{
		ResponseEntity<TransactionModel> response=null;
		if(!(transactionService.existsById(transactionId)) || transactionId==null) {
			response=new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			response=new ResponseEntity<>(transactionService.findById(transactionId),HttpStatus.FOUND);
		}
		return response;
	}
	/**
	 * This method sends the new transaction details to the add method in ITransactionService
	 * @param transaction which contains transaction details
	 * @return ResponseEntity with HTTP status which contain the newly added transaction details
	 * @throws TransactionException when an exception occurs
	 */
	@PostMapping("/addTransaction")
	public ResponseEntity<TransactionModel> add(@RequestBody TransactionModel transaction) throws TransactionException {
		transaction=transactionService.add(transaction);
		return ResponseEntity.ok(transaction);
	}
	/**
	 * This method is used to update the transaction by sending the updated transaction details to save method in ITransactionService
	 * @param transaction which contains all the updated transaction details
	 * @return ResponseEntity with HTTP status code OK
	 * @throws TransactionException when exception occurs
	 */
	@PutMapping("/updateTransaction")
	public ResponseEntity<TransactionModel> updateUser(@RequestBody TransactionModel transaction) throws TransactionException{
		transaction =transactionService.save(transaction);
		return ResponseEntity.ok(transaction);
	}
	
	/**
	 * This method is used to make transaction using cardNumber 
	 * @param cardNumber through which the transaction should be made
	 * @param amount of the transaction
	 * @param description of the transactions
	 * @return ResponseEntity with HTTP status OK
	 * @throws CreditCardException when an exception occurs
	 */
	
	@GetMapping("/transact/{cardNumber}/{amount}/{description}")
	public ResponseEntity<TransactionModel> transact(@PathVariable("cardNumber") String cardNumber,@PathVariable("amount") Double amount,@PathVariable("description") String description) throws CreditCardException {
		return ResponseEntity.ok(transactionService.transaction(cardNumber, amount, description));
	}
	
	/**
	 * This method called when the user wants to list his transaction history
	 * @param cardNumber to which he wants to see his transaction history
	 * @return ResponseEntity with HTTP status code OK
	 * @throws CreditCardException when an exception occurs
	 */
	@GetMapping("/transactionHistory/{cardNumber}")
	public ResponseEntity<List<TransactionModel>> transactionHistory(@PathVariable("cardNumber") String cardNumber) throws CreditCardException {
		return ResponseEntity.ok(transactionService.transactionHistory(cardNumber));
	}
}
