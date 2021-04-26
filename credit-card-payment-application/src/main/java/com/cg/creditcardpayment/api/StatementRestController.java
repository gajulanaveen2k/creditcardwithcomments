package com.cg.creditcardpayment.api;
/**
* <h1>StatementRestController</h1>
* The StatementRestController program takes care of mapping the url's 
* to the functions which are specific to the Statements
* <p>
* 
*
* @author  P Venkata Sai Reddy
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.creditcardpayment.exception.CreditCardException;
import com.cg.creditcardpayment.exception.PaymentException;
import com.cg.creditcardpayment.exception.StatementException;
import com.cg.creditcardpayment.model.StatementModel;
import com.cg.creditcardpayment.service.IStatementService;

@RestController
@RequestMapping("/statements")
@CrossOrigin
public class StatementRestController {
	/**
	 * This a local variable: {@link #statementService} is the object of IStatementService which is used to access the functions in IStatementService 
	 */
	@Autowired
	private IStatementService statementService;
	/**
	 * This method retrieve all the statements from the findAll method in IStatementService
	 * @return ResponseEntity which contains all the Statements
	 */
	@GetMapping("/getAllStatements")
	public ResponseEntity<List<StatementModel>> findAll() {
		return ResponseEntity.ok(statementService.findAll());
	}
	
	/**
	 * This method retrieves the statement details of the given statementId
	 * @paramstatementId to find the statement details
	 * @return ResponseEntity, with HTTP status OK, which contains the statement details of the given statementId 
	 * @throws PaymentException when there is an exception
	 */
	@GetMapping("/getStatement/{statementId}")
	public ResponseEntity<StatementModel> findById(@PathVariable("statementId") Long statementId) throws StatementException{
		return ResponseEntity.ok(statementService.findById(statementId));
	}
	
	/**
	 * This method sends the new statement details to the add method in IStatementService
	 * @param statements which contains statement details
	 * @return ResponseEntity with HTTP status which contain the newly added statements details
	 * @throws StatementException when an exception occurs
	 */
	@PostMapping("/addStatement")
	public ResponseEntity<StatementModel> add(@RequestBody StatementModel statement) throws StatementException {
		statement=statementService.add(statement);
		return ResponseEntity.ok(statement);
	}
	
	/**
	 * This function is called when Statement should be updated
	 * @param statement it is the updated statement model
	 * @return Response Entity with HTTP status OK
	 * @throws StatementException when the exception occurs
	 */
	@PutMapping("/updateStatement")
	public ResponseEntity<StatementModel> updateUser(@RequestBody StatementModel statement) throws StatementException{
		statement =statementService.save(statement);
		return ResponseEntity.ok(statement);
	}
	
	/**
	 * 	This function is called when the bill needs to be generated
	 * @param cardNumber to which the bill needs to be generated
	 * @return ResponseEntity with HTTP status code OK
	 * @throws CreditCardException when an exception related to credit card occurs
	 * @throws StatementException when an exception related to statement occurs
	 */
	@GetMapping("/generateBill/{cardNumber}")
	public ResponseEntity<StatementModel> getBill(@PathVariable("cardNumber") String cardNumber) throws CreditCardException, StatementException{
		return ResponseEntity.ok(statementService.getBilledStatement(cardNumber));
	}
	/**
	 * This function is called when the unbilled statement needs to be generated
	 * @param cardNumber to which the statement needs to be generated
	 * @return ResponseEntity with HTTP status code OK
	 * @throws CreditCardException when an exception occurs
	 */
	@GetMapping("/generateUnBilled/{cardNumber}")
	public ResponseEntity<StatementModel> getUnBill(@PathVariable("cardNumber") String cardNumber) throws CreditCardException{
		return ResponseEntity.ok(statementService.getUnBilledStatement(cardNumber));
	}
	
	/**
	 * This method called when the user wants to list his statement history
	 * @param cardNumber to which he wants to see his statement history
	 * @return ResponseEntity with HTTP status code OK
	 * @throws CreditCardException when an exception occurs
	 */
	@GetMapping("/statementHistory/{cardNumber}")
	public ResponseEntity<List<StatementModel>> statementHistory(@PathVariable("cardNumber") String cardNumber) throws CreditCardException {
		return ResponseEntity.ok(statementService.statementHistory(cardNumber));
	}
	
}
