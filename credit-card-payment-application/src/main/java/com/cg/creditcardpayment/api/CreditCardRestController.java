package com.cg.creditcardpayment.api;
/**
* <h1>CreditCardRestController</h1>
* The CreditCardRestController program takes care of mapping the url's 
* to the functions which are specific to the CreditCard
* <p>
* 
*
* @author  Abhilash Reddy Gone
* @version 1.0
* @since   2021-03-31 
*/
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.creditcardpayment.exception.CreditCardException;
import com.cg.creditcardpayment.exception.CustomerException;
import com.cg.creditcardpayment.model.CreditCardModel;
import com.cg.creditcardpayment.service.ICreditCardService;

@RestController
@RequestMapping("/creditcards")
@CrossOrigin
public class CreditCardRestController {
	
	/**
	 * This a local variable: {@link #creditCardService} is the object of ICreditCardService which is used to access the functions in ICreditCardService 
	 */
	@Autowired
	private ICreditCardService creditCardService;
	
	/**
	 * This method retrieve all the creditCards from the findAll method in ICreditCardService
	 * @return ResponseEntity which contains all the creditCards
	 */
	@GetMapping("/getAllCreditCards")
	public ResponseEntity<List<CreditCardModel>> findAll() {
		return ResponseEntity.ok(creditCardService.findAll());
	}
	
	/**
	 * This method retrieve the creditcard details of the given creditcard number
	 * @param cardNumber to find the Credit Card
	 * @return ResponseEntity, with HTTP status found, which contains the credit card details of the given credit card number
	 * @throws CreditCardException When an exception occurs
	 */
	@GetMapping("/getCreditCard/{cardNumber}")
	public ResponseEntity<CreditCardModel> findById(@PathVariable("cardNumber") String cardNumber) throws CreditCardException{
		ResponseEntity<CreditCardModel> response=null;
		if(!(creditCardService.existsById(cardNumber)) || cardNumber==null) {
			response=new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			response=new ResponseEntity<>(creditCardService.findById(cardNumber),HttpStatus.FOUND);
		}
		return response;
	}
	
	/**
	 * This method sends the new credit card details to the add method in ICreditCardService
	 * @param account which contains all the credit card details
	 * @return response is a ResponseEntity with HTTP status which contain the newly added credit card details
	 * @throws CreditCardException when an exception occurs
	 */
	@PostMapping("/addCreditCard")
	public ResponseEntity<CreditCardModel> add(@RequestBody @Valid CreditCardModel creditCard, BindingResult result) throws CreditCardException {
		ResponseEntity<CreditCardModel> response=null;
		if(result.hasErrors()) {
			throw new CreditCardException(GlobalExceptionHandler.messageFrom(result));
		}
		if(creditCard==null) {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			creditCard=creditCardService.add(creditCard);
			response= new ResponseEntity<>(creditCard, HttpStatus.CREATED);
		}
		return response;
	}
	
	/**
	 * This method calls the deleteById in ICreditCardService if the credit card Number exists
	 * @param cardNumber is taken to delete the account
	 * @return ResponseEntity with HTTP status 
	 * @throws CreditCardException when the exception occurs
	 */
	@DeleteMapping("/deleteCreditCard/{cardNumber}")
	public ResponseEntity<String> deleteCreditCard(@PathVariable("cardNumber") String cardNumber) throws CreditCardException {
		ResponseEntity<String> response=null;
		CreditCardModel creditCard=creditCardService.findById(cardNumber);
		if(creditCard==null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			creditCardService.deleteById(cardNumber);
			response = new ResponseEntity<>("Credit Card is Deleted",HttpStatus.OK);
		}
		return response;
	}
	
	/**
	 * This is a method calls save method in ICreditCardService
	 * @param creditCard contains all the updated credit card details 
	 * @return response which is ResponseEntity with status code OK or NO_CONTENT if the account is not found
	 * @throws CreditCardException when exception occurs
	 */
	@PutMapping("/updateCreditCard")
	public ResponseEntity<CreditCardModel> updateCreditCard(@RequestBody @Valid CreditCardModel creditCard, BindingResult result) throws CreditCardException{
		ResponseEntity<CreditCardModel> response=null;
		if(result.hasErrors()) {
			throw new CreditCardException(GlobalExceptionHandler.messageFrom(result));
		}
		if(creditCard==null) {
			response=new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			creditCard =creditCardService.save(creditCard);
			response =new ResponseEntity<>(creditCard,HttpStatus.OK);
		}
		
		return response;
	}
	
	/**
	 * This method calls addToCustomer in ICreditCardService
	 * @param creditCard contains credit card details to be added
	 * @param customerId is id of the customer to which the credit card should be added
	 * @return response which is ResponseEntity with response code CREATED or NO_CONTENT
	 * @throws CreditCardException when credit card related Exception occurs
	 * @throws CustomerException when customer related Exception occurs
	 */
	@PostMapping("/addCreditCard/{customerId}")
	public ResponseEntity<CreditCardModel> addCreditCardToCustomer(@RequestBody @Valid CreditCardModel creditCard,BindingResult result,@PathVariable("customerId") String customerId) throws CreditCardException, CustomerException {
		ResponseEntity<CreditCardModel> response=null;
		if(result.hasErrors()) {
			throw new CreditCardException(GlobalExceptionHandler.messageFrom(result));
		}
		if(creditCard==null) {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			creditCard=creditCardService.addToCustomer(creditCard,customerId);
			response= new ResponseEntity<>(creditCard, HttpStatus.CREATED);
		}
		return response;
	}
	
	/**
	 * This method calls findByCustomerId in ICreditCardService
	 * @param customerId is the unique id of the customer to which all the credits should be retrieved 
	 * @return response which is response entity containing all the credit cards of the given customer id
	 * @throws CreditCardException when credit card related exception occurs
	 * @throws CustomerException when customer related exception occurs
	 */
	@GetMapping("/getAllCreditCards/{customerId}")
	public ResponseEntity<Set<CreditCardModel>> getAllCreditCardsOfCustomer(@PathVariable("customerId") String customerId) throws CreditCardException, CustomerException{
		ResponseEntity<Set<CreditCardModel>> response=null;
		if(customerId==null) {
			response=new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			response=new ResponseEntity<>(creditCardService.findByCustomerId(customerId),HttpStatus.FOUND);
		}
		return response;
	}
	
	/**
	 * This method which calls deleteCustomerCreditCard in the ICreditCardService
	 * @param customerId is the id of the customer
	 * @param cardNumber is the card number which should be deleted
	 * @return response which is ResponseEntity with HTTP status code OK
	 * @throws CreditCardException when credit card related exception occurs
	 * @throws CustomerException when customer related exception occurs
	 */
	@DeleteMapping("/deleteCreditCard/{customerId}/{cardNumber}")
	public ResponseEntity<String> deleteCustomerCreditCard(@PathVariable("customerId") String customerId,@PathVariable("cardNumber") String cardNumber) throws CreditCardException, CustomerException {
		ResponseEntity<String> response=null;
		CreditCardModel creditCard=creditCardService.findById(cardNumber);
		if(creditCard==null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			creditCardService.deleteCreditCardOfCustomer(customerId,cardNumber);
			response = new ResponseEntity<>("Credit Card is Deleted",HttpStatus.OK);
		}
		return response;
	}
	
	
}
