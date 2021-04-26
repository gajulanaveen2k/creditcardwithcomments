package com.cg.creditcardpayment.service;
/**
* <h1>AccountServiceImpl</h1>
* IAccountService is a program where all the methods in IAccountService are implemented
* <p>
* 
*
* @author  D Himavanth
* @version 1.0
* @since   2021-03-31 
*/
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.creditcardpayment.dao.IAccountRepository;
import com.cg.creditcardpayment.dao.ICustomerRepository;
import com.cg.creditcardpayment.entity.AccountEntity;
import com.cg.creditcardpayment.entity.CustomerEntity;
import com.cg.creditcardpayment.exception.AccountException;
import com.cg.creditcardpayment.exception.CustomerException;
import com.cg.creditcardpayment.model.AccountModel;


@Service
public class AccountServiceImpl implements IAccountService {
	
	String constant="Account ";
	
	/**
	 * This a local variable: {@link #accountRepo} defines the object of IAccountRepository
	 * @HasGetter
	 * @HasSetter
	 */
	@Autowired
	private IAccountRepository accountRepo;
	
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
	 * Default constructor
	 */
	public AccountServiceImpl() {
		
	}

	/**
	 * Parameterized Constructor
	 * @param accountRepo		the IAccountRepository object
	 * @param customerRepo		the ICustomerRepository object
	 */
	public AccountServiceImpl(IAccountRepository accountRepo, ICustomerRepository customerRepo) {
		super();
		this.accountRepo = accountRepo;
		this.customerRepo = customerRepo;
		this.parser=new EMParse();
	}


	/**
	 * @return accountRepo as IAccountRepository
	 */
	public IAccountRepository getAccountRepo() {
		return accountRepo;
	}

	/**
	 * 
	 * @param accountRepo which is IAccount repository
	 */
	public void setAccountRepo(IAccountRepository accountRepo) {
		this.accountRepo = accountRepo;
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
	 * This method is used to add the new account
	 * @param account which contains the new account details
	 * @return account which is AccountModel which is added 
	 * @throws AccountException when exception occurs
	 */
	@Override
	public AccountModel add(AccountModel account) throws AccountException {
		if(account !=null) {
			if(accountRepo.existsById(account.getAccountNumber())) {
				throw new AccountException(constant+account.getAccountNumber()+" is already Exists");
			}else {
				account=parser.parse(accountRepo.save(parser.parse(account)));
			}
		}
		return account;
	}

	/**
	 * This method is used to update the old account
	 * @param account which contains the updated account details
	 * @return account AccountModel which is updated 
	 * @throws AccountException when exception occurs
	 */
	@Override
	public AccountModel save(AccountModel account) throws AccountException {
		if(account==null) {
			throw new AccountException("Account should not be null");
		}
		return parser.parse(accountRepo.save(parser.parse(account)));
	}

	/**
	 * This method deletes the account by its account number
	 * @param accountNumber which should be deleted
	 * @throws AccountException when exception occurs
	 */
	@Override
	public void deleteById(String accountNumber) throws AccountException {
		if(accountNumber==null) {
			throw new AccountException("Account Number should not be null");
		}else if(!accountRepo.existsById(accountNumber)) {
			throw new AccountException(constant+accountNumber+" Does not Exists");
		}
		accountRepo.deleteById(accountNumber);
	}

	/**
	 *  This method search the account by its account number
	 * @param accountNumber to be searched
	 * @return AccountModel when the account is found
	 * @throws AccountException when the exception occurs
	 */
	@Override
	public AccountModel findById(String accountNumber) throws AccountException {
		if(accountNumber==null) {
			throw new AccountException("Account Number should not be null");
		}else if(!this.existsById(accountNumber)) {
			throw new AccountException("Account Does not Exists");
		}
		return parser.parse(accountRepo.findById(accountNumber).orElse(null));
	}

	/**
	 * This method list all the accounts
	 * @return List<AccountModel> which contains all the account details
	 */
	@Override
	public List<AccountModel> findAll() {
		return accountRepo.findAll().stream().map(parser::parse).collect(Collectors.toList());
	}

	/**
	 * This method confirms whether the given number is there in the data base
	 * @param accountNumber which should be searched
	 * @return boolean whether the given number exists or nut
	 * @throws AccountException when exception occurs
	 */
	@Override
	public boolean existsById(String accountNumber) throws AccountException {
		if(accountNumber==null) {
			throw new AccountException("Account Number can not be Null");
		}
		return accountRepo.existsById(accountNumber);
	}

	/**
	 * This method add account by its customer
	 * @param account which should be added
	 * @param customerId to which the account should be added
	 * @return AccountModel which is added to the given cutomer
	 * @throws AccountException when the account related exception occurs
	 * @throws CustomerException when the customer related exception occurs
	 */
	@Override
	public AccountModel addByCustomer(AccountModel account, String customerId) throws AccountException, CustomerException {
		if(customerId==null) {
			throw new CustomerException("Customer Id can not be null");
		}
		CustomerEntity customer=customerRepo.findById(customerId).orElse(null);
		if(customer==null) {
			throw new CustomerException("Customer does not exists");
		}
		Set<AccountModel> accounts=customer.getAccounts().stream().map(parser::parse).collect(Collectors.toSet());
		if(accounts.contains(account)) {
			throw new AccountException(constant+account.getAccountNumber()+" is already Exists");
		}else {
			account=parser.parse(accountRepo.save(parser.parse(account)));
			customer.getAccounts().add(parser.parse(account));
			customer.setAccounts(customer.getAccounts());
			customerRepo.save(customer);
			
		}
		return account;
	}
	
	/**
	 * This method finds all the accounts by the customerId
	 * @param customerId to which the accounts should be searched
	 * @return Set<AccountModel> which contain all the accounts of the customer
	 * @throws CustomerException when the exception occurs
	 */
	@Override
	public Set<AccountModel> findAllByCustomerId(String customerId) throws CustomerException {
		CustomerEntity customer=customerRepo.findById(customerId).orElse(null);
		if(customerId==null) {
			throw new CustomerException("Customer Id should not be null");
		}else if(customer==null) {
			throw new CustomerException("No Customer Exists");
		}else if(customer.getAccounts().isEmpty()) {
			throw new CustomerException("No Accounts Exists");
		}
		return customer.getAccounts().stream().map(parser::parse).collect(Collectors.toSet());
	}

	/**
	 * This method deletes the account by customerId
	 * @param customerId to delete the account
	 * @param accountNumber to delete the account
	 * @throws AccountException when account related exception occurs
	 * @throws CustomerException when customer related exception occurs
	 */
	@Override
	public void deleteAccountByCustomer(String customerId, String accountNumber) throws AccountException, CustomerException {
		CustomerEntity customer=customerRepo.findById(customerId).orElse(null);
		if(customerId==null) {
			throw new CustomerException("Customer Id can not be null");
		}else if(customer==null) {
			throw new CustomerException("No Customer Exists");
		}else if(customer.getAccounts().isEmpty()) {
			throw new CustomerException("No Accounts Exists");
		}
		AccountEntity account = accountRepo.findById(accountNumber).orElse(null);
		if(account==null) {
			throw new AccountException("Account doesnot exist to delete");
		}
		customer.getAccounts().remove(account);
	}
}
