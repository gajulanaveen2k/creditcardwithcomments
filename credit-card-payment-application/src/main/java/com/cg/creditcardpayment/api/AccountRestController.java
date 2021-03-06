package com.cg.creditcardpayment.api;
/**
* <h1>AccountRestController</h1>
* The AccountRestController program takes care of mapping the url's 
* to the functions which are specific to the Account
* <p>
* 
*
* @author  D Himavanth
* @version 1.0
* @since   2021-03-31 
*/

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;
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

import com.cg.creditcardpayment.exception.AccountException;
import com.cg.creditcardpayment.exception.CustomerException;
import com.cg.creditcardpayment.model.AccountModel;
import com.cg.creditcardpayment.service.IAccountService;

@RestController
@RequestMapping("/home/customer/accounts")
@CrossOrigin
public class AccountRestController {

	/**
	 * This a local variable: {@link #accountService} is the object of IAccountService which is used to access the functions in IAccountService 
	 */
	@Autowired
	private IAccountService accountService;
	
	/**
	 * This method retrieve all the details of accounts from the findAll method in IAccountService
	 * @return ResponseEntity which contains all the details of accounts
	 */
	@GetMapping("/allAccounts")
	public ResponseEntity<List<AccountModel>> findAll() {
		return ResponseEntity.ok(accountService.findAll());
	}
	
	/**
	 * This method retrieve the account details of the given account number
	 * @param accountNumber to find the account details
	 * @return ResponseEntity, with HTTP status found, which contains the account details of the given account number 
	 * @throws AccountException when there is an exception
	 */
	@GetMapping("/{accountnumber}")
	public ResponseEntity<AccountModel> findByAccountNumber(@PathVariable("accountnumber") String accountNumber) throws AccountException{
		ResponseEntity<AccountModel> response=null;
		response=new ResponseEntity<>(accountService.findById(accountNumber),HttpStatus.FOUND);
		return response;
	}
	/**
	 * This method sends the new account details to the add method in IAccountService
	 * @param account which contains all the account details
	 * @return response is a ResponseEntity with HTTP status which contain the newly added account details
	 * @throws AccountException when an exception occurs
	 */
	@PostMapping("/add")
	@Transactional
	public ResponseEntity<String> addAccount(@RequestBody @Valid AccountModel account, BindingResult result) throws AccountException {
		ResponseEntity<String> response=null;
		if(result.hasErrors()) {
			throw new AccountException(GlobalExceptionHandler.messageFrom(result));
		}
		if(account==null) {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			accountService.add(account);
			
			response= new ResponseEntity<>("Account is Added",HttpStatus.CREATED);

		}
		return response;
	}
	
	/**
	 * This method calls the deleteById in IAccountService if the Account Number exists
	 * @param accountNumber is taken to delete the account
	 * @return ResponseEntity with HTTP status 
	 * @throws AccountException when the exception occurs
	 */
	
	@DeleteMapping("/{accountnumber}")
	@Transactional
	public ResponseEntity<String> deleteAccount(@PathVariable("accountnumber") String accountNumber) throws AccountException {
		ResponseEntity<String> response=null;
		AccountModel account=accountService.findById(accountNumber);
		if(account==null) {
			response = new ResponseEntity<>("Account Doesnot Exists",HttpStatus.NOT_FOUND);
		}else {
			accountService.deleteById(accountNumber);
			response=new ResponseEntity<>("Account Deleted",HttpStatus.OK);
		}
		return response;
	}

	/**
	 * This method which calls deleteAccountByCustomer in the IAccountService 
	 * @param customerId is the id of the customer
	 * @param accountNumber is the account number which should be deleted
	 * @return ResponseEntity with HTTP status code OK
	 * @throws AccountException When account related exception occurs
	 * @throws CustomerException when customer related exception occurs
	 */
	@DeleteMapping("/{customerId}/{accountnumber}")
	@Transactional
	public ResponseEntity<String> deleteAccountByCustomer(@PathVariable("customerId") String customerId,@PathVariable("accountnumber") String accountNumber) throws AccountException, CustomerException {
		ResponseEntity<String> response=null;
		AccountModel account=accountService.findById(accountNumber);
		if(account==null) {
			response = new ResponseEntity<>("Account Doesnot Exists",HttpStatus.NOT_FOUND);
		}else {
			accountService.deleteAccountByCustomer(customerId,accountNumber);
			response=new ResponseEntity<>("Account Deleted",HttpStatus.OK);
		}
		return response;
	}

	/**
	 * This is a method calls save method in IAccountService
	 * @param account contains all the updated account details 
	 * @return response which is ResponseEntity with status code OK or NO_CONTENT if the account is not found
	 * @throws AccountException when exception occurs
	 */
	@PutMapping("/update")
	@Transactional
	public ResponseEntity<AccountModel> updateAccount(@RequestBody @Valid AccountModel account,BindingResult result) throws AccountException{
		ResponseEntity<AccountModel> response=null;
		if(result.hasErrors()) {
			throw new AccountException(GlobalExceptionHandler.messageFrom(result));
		}
		if(account==null) {
			response=new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			account =accountService.save(account);
			response =new ResponseEntity<>(account,HttpStatus.OK);
		}
		
		return response;
	}
	
	/**
	 * This method calls addByCustomer in IAccountService
	 * @param account contains account details that to be added
	 * @param customerId is id of the customer to which the account should be added
	 * @return response which is ResponseEntity with response code CREATED or NO_CONTENT
	 * @throws AccountException when account related Exception occurs
	 * @throws CustomerException when customer related Exception occurs
	 */
	@PostMapping("/add/{customerId}")
	public ResponseEntity<AccountModel> addAccountToCustomer(@RequestBody @Valid AccountModel account,BindingResult result,@PathVariable("customerId") String customerId) throws AccountException, CustomerException {
		ResponseEntity<AccountModel> response=null;
		if(result.hasErrors()) {
			throw new AccountException(GlobalExceptionHandler.messageFrom(result));
		}
		if(account==null) {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			account=accountService.addByCustomer(account,customerId);
			response= new ResponseEntity<>(account, HttpStatus.CREATED);
		}
		return response;
	}
	
	/**
	 * This method calls findAllByCustomerId in IAccountService
	 * @param customerId is the unique id of the customer to which all the accounts should be retrieved
	 * @return response which contain all the accounts of the given customer id and HTTP status code OK
	 * @throws CustomerException when exception occurs
	 */
	@GetMapping("/allAccounts/{customerId}")
	public ResponseEntity<Set<AccountModel>> getAllAccountOfCustomer(@PathVariable("customerId") String customerId) throws CustomerException{
		ResponseEntity<Set<AccountModel>> response=null;
		if(customerId==null) {
			response=new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			response=new ResponseEntity<>(accountService.findAllByCustomerId(customerId),HttpStatus.OK);
		}
		return response;
	}
	
}
