package com.cg.creditcardpayment.service;
/**
* <h1>CustomerServiceImpl</h1>
* ICustomerService is a program where all the methods in ICustomerService are implemented
* <p>
* 
*
* @author  P Venkata Sai Reddy
* @version 1.0
* @since   2021-03-31 
*/
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.creditcardpayment.dao.ICustomerRepository;
import com.cg.creditcardpayment.entity.CustomerEntity;
import com.cg.creditcardpayment.exception.AccountException;
import com.cg.creditcardpayment.exception.CustomerException;
import com.cg.creditcardpayment.model.AccountModel;
import com.cg.creditcardpayment.model.CustomerModel;


@Service
public class CustomerServiceImpl implements ICustomerService {
	
	/**
	 * This a local variable: {@link #customerRepo} defines the object of ICustomerRepository
	 * @HasGetter
	 * @HasSetter
	 */
	@Autowired
	private ICustomerRepository customerRepo;
	
	/**
	 * This a local variable: {@link #parser} defines the object of EMparse
	 * @HasGetter
	 * @HasSetter
	 */
	@Autowired
	private EMParse parser;
	
	/**
	 * Parameterized Constructor
	 * @param customerRepo 		the ICustomerRepository
	 */
	public CustomerServiceImpl(ICustomerRepository customerRepo) {
		super();
		this.customerRepo = customerRepo;
		this.parser = new EMParse();
	}

	/**
	 * @return customerRepo as ICustomerRepository
	 */
	public ICustomerRepository getCustomerRepo() {
		return customerRepo;
	}

	/**
	 * 
	 * @param customerRepo which is ICustomerRepository
	 */
	public void setCustomerRepo(ICustomerRepository customerRepo) {
		this.customerRepo = customerRepo;
	}

	/**
	 * 
	 * @return parser as EMparse
	 */
	public EMParse getParser() {
		return parser;
	}

	/**
	 * 
	 * @param parser which is object of EMParse
	 */
	public void setParser(EMParse parser) {
		this.parser = parser;
	}

	String constant=" is Already Exists";
	String constant1="Customer ";

	/**
	 * This method is used to add the new customer
	 * @param customer which contains the new customer details
	 * @return CustomerModel which is added 
	 * @throws CustomerException when exception occurs
	 */
	@Override
	@Transactional
	public CustomerModel addCustomer(CustomerModel customer,String userId) throws CustomerException {
		if(customer !=null) {
			if(customerRepo.existsById(userId)) {
				throw new CustomerException(constant1+userId+constant);
			}else if (customerRepo.existsByContactNo(customer.getContactNo())) {
				throw new CustomerException("Customer with number "+customer.getContactNo()+constant);
			}else if (customerRepo.existsByEmail(customer.getEmail())) {
				throw new CustomerException("Customer with email "+customer.getEmail()+constant);
			}else {
				customer.setUserId(userId);
				customer=parser.parse(customerRepo.save(parser.parse(customer)));
			}
		}
		return customer;
	}

	/**
	 * This method is used to update the old customer
	 * @param customer which contains the updated customer details
	 * @return CustomerModel which is updated 
	 * @throws CustomerException when exception occurs
	 */
	@Override
	@Transactional
	public CustomerModel updateCustomer(CustomerModel customer) throws CustomerException {
		if(customer ==null) {
			throw new CustomerException("Customer details cannot be null");
		}
		return parser.parse(customerRepo.save(parser.parse(customer)));
	}

	/**
	 * This method deletes the customer by its customerId
	 * @param customerId which should be deleted
	 * @throws CustomerException when exception occurs
	 */
	@Override
	@Transactional
	public void deleteById(String userId) throws CustomerException {
		if(userId==null) {
			throw new CustomerException("CustomerId can not be null");
		}else if(!customerRepo.existsById(userId)) {
			throw new CustomerException(constant1+userId+" is not Exists");
		}
		customerRepo.deleteById(userId);
	}
	/**
	 * This method search the customer by its customer number
	 * @param customerId to be searched
	 * @return CustomerModel when the customer is found
	 * @throws CustomerException when the exception occurs
	 */
	@Override
	public CustomerModel findById(String userId) throws CustomerException {
		if(userId==null) {
			throw new CustomerException("CustomerId can not be null");
		}else if(!customerRepo.existsById(userId)) {
			throw new CustomerException(constant1+userId+" is not Exists");
		}
		return parser.parse(customerRepo.findById(userId).orElse(null));
	}
	/**
	 * This method list all the customers
	 * @return List<CustomerModel> which contains all the customer details
	 */
	@Override
	public List<CustomerModel> findAll() {
		return customerRepo.findAll().stream().map(parser::parse).collect(Collectors.toList());
	}
	/**
	 * This method checks customer by contact number
	 * @param contactNo to be checked
	 * @return boolean to check the contact number exists or not
	 * @throws CustomerException when an exception occurs
	 */
	@Override
	public boolean existsByContactNo(String contactNo) throws CustomerException {
		if(contactNo==null) {
			throw new CustomerException("contact No can not be null");
		}
		return customerRepo.existsByContactNo(contactNo);
	}
	/**
	 * This method checks customer by email
	 * @param email to be checked
	 * @return boolean to check the email exists or not
	 * @throws CustomerException when an exception occurs
	 */
	@Override
	public boolean existsByEmail(String email) throws CustomerException {
		if(email==null) {
			throw new CustomerException("email can not be null");
		}
		return customerRepo.existsByEmail(email);
	}
	/**
	 * This method checks customer by userId
	 * @param UserId to be checked
	 * @return boolean to check the userId exists or not
	 * @throws CustomerException when an exception occurs
	 */
	@Override
	public boolean existsById(String userId) throws CustomerException {
		if(userId==null) {
			throw new CustomerException("Id can not be null");
		}
		return customerRepo.existsById(userId);
	}

	/**
	 * This method adds account to the customer
	 * @param account consists of the account details to be added
	 * @param customerId to which the account should be added
	 * @return boolean to check whether the account is added or not
	 * @throws AccountException when account related exception occur
	 * @throws CustomerException when the customer related exception occurs
	 */
	@Override
	@Transactional
	public boolean addAccount(AccountModel account,String customerId) throws AccountException, CustomerException{
		CustomerEntity customer=customerRepo.findById(customerId).orElse(null);
		boolean isAdded=false;
		if(account==null) {
			throw new AccountException("Account can not be null");
		}
		if(customerId==null) {
			throw new CustomerException("customer Id is null");
		}else if(customer == null){
			throw new CustomerException("Customer Id in null");
		}else {
			customer.getAccounts().add(parser.parse(account));
			customer.setAccounts(customer.getAccounts());
			customerRepo.save(customer);
			isAdded=true;
		}
		return isAdded;
	}

	/**
	 * This method gets account which belongs to customerId
	 * @param customerId to fetch the account
	 * @return List<AccountModel> which contains the list of the accounts 
	 * @throws AccountException when the exception occurs
	 */
	@Override
	public List<AccountModel> getAccounts(String customerId) throws AccountException {
		CustomerEntity customer=customerRepo.findById(customerId).orElse(null);
		if(customer ==null) {
			throw new AccountException("No customer Exists");
		}
		return customer.getAccounts().stream().map(parser::parse).collect(Collectors.toList());
	}
}
