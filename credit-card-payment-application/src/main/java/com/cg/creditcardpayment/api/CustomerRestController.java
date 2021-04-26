package com.cg.creditcardpayment.api;
/**
* <h1>CustomerRestController</h1>
* The CustomerRestController program takes care of mapping the url's 
* to the functions which are specific to the Account
* <p>
* 
*
* @author  P Vnekata Sai Reddy
* @version 1.0
* @since   2021-03-31 
*/

import java.util.List;

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
import com.cg.creditcardpayment.model.CustomerModel;
import com.cg.creditcardpayment.service.ICustomerService;

@RestController
@RequestMapping("/home/customers")
@CrossOrigin
public class CustomerRestController {
	/**
	 * This a local variable: {@link #customerService} is the object of ICustomerService which is used to access the functions in IAccountService 
	 */
	@Autowired
	private ICustomerService customerService;
	
	/**
	 * This method retrieve all the details of customers from the findAll method in ICustomerService
	 * @return ResponseEntity which contains all the details of customers
	 */
	@GetMapping("/all")
	public ResponseEntity<List<CustomerModel>> findAll() {
		return ResponseEntity.ok(customerService.findAll());
	}
	
	/**
	 * This method retrieve the customer details of the given user id
	 * @param userId to find the customer details details
	 * @return ResponseEntity, with HTTP status found, which contains the customer details of the given userId 
	 * @throws CustomerException when there is an exception
	 */
	@GetMapping("/{userId}")
	public ResponseEntity<CustomerModel> findById(@PathVariable("userId") String userId) throws CustomerException{
		ResponseEntity<CustomerModel> response=null;
		if(!customerService.existsById(userId)){
			response=new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			response=new ResponseEntity<>(customerService.findById(userId),HttpStatus.FOUND);
		}
		return response;
	}
	
	/**
	 * This method sends the new customer details to the add method in ICustomersService
	 * @param customer which contains all the customer details
	 * @return response is a ResponseEntity with HTTP status which contain the newly added customer details
	 * @throws CustomerException when an exception occurs
	 */
	@PostMapping("/add/{userId}")
	public ResponseEntity<String> add(@RequestBody @Valid CustomerModel customer,BindingResult result ,@PathVariable("userId") String userId) throws CustomerException {
		ResponseEntity<String> response=null;
		if(result.hasErrors()) {
			throw new CustomerException(GlobalExceptionHandler.messageFrom(result));
		}
		if(customer==null) {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			customerService.addCustomer(customer,userId);
			response= new ResponseEntity<>("Customer is Added",HttpStatus.CREATED);
		}
		return response;
	}
	
	/**
	 * This method calls the deleteById in ICustomerService if the UserId exists
	 * @param userId is taken to delete the account
	 * @return ResponseEntity with HTTP status 
	 * @throws CustomerException when the exception occurs
	 */
	@DeleteMapping("/deleteCustomer/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable("userId") String userId) throws CustomerException {
		ResponseEntity<String> response=null;
		CustomerModel customer=customerService.findById(userId);
		if(customer==null) {
			response = new ResponseEntity<>("Customer is not Exists",HttpStatus.NOT_FOUND);
		}else {
			customerService.deleteById(customer.getUserId());
			customerService.deleteById(userId);
			response=new ResponseEntity<>("Customer is Deleted",HttpStatus.OK);
		}
		return response;
	}
	
	/**
	 * This is a method calls save method in ICustomerService
	 * @param customer contains all the updated customer details 
	 * @return response which is ResponseEntity with status code OK or NO_CONTENT if the userId is not found
	 * @throws CustomerException when exception occurs
	 */
	@PutMapping("/updateCustomer/{userId}")
	public ResponseEntity<CustomerModel> updateUser(@RequestBody CustomerModel user,@PathVariable("userId") String userId) throws CustomerException{
		
		ResponseEntity<CustomerModel> response=null;
		if(user==null) {
			response=new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			user =customerService.updateCustomer(user);
			response =new ResponseEntity<>(user,HttpStatus.OK);
		}
		
		return response;
	}
	
	/**
	 * This method calls addAccount in ICustomerService
	 * @param account contains account details that to be added
	 * @param customerId is id of the customer to which the account should be added
	 * @return response which is ResponseEntity with response code CREATED or NO_CONTENT
	 * @throws AccountException when account related Exception occurs
	 * @throws CustomerException when customer related Exception occurs
	 */
	@PostMapping("/addAccount/{customerId}")
	public ResponseEntity<String> addAccount(@RequestBody AccountModel account,@PathVariable("customerId") String customerId) throws AccountException, CustomerException{
		ResponseEntity<String> response=null;
		if(customerService.addAccount(account, customerId)) {
			response = new ResponseEntity<>("Account is Added",HttpStatus.CREATED);
		}else {
			response= new ResponseEntity<>("Account is not Added",HttpStatus.NOT_ACCEPTABLE);
		}
		return response;
	}
	
	/**
	 * This method calls getAccount in ICustomerService
	 * @param customerId is the unique id of the customer to which all the accounts should be retrieved
	 * @return response which contain all the accounts of the given customer id and HTTP status code FOUND
	 * @throws AccountException when exception occurs
	 */
	@GetMapping("/getAccounts/{customerId}")
	public ResponseEntity<List<AccountModel>> getAccounts(@PathVariable("customerId") String customerId) throws AccountException{
		ResponseEntity<List<AccountModel>> response=null;
		List<AccountModel> accounts=customerService.getAccounts(customerId);
		if(accounts==null) {
			response=new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			response=new ResponseEntity<>(customerService.getAccounts(customerId),HttpStatus.FOUND);
		}
		return response;
	}
}
