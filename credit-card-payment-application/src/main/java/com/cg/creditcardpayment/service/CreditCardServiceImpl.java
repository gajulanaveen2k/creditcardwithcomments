package com.cg.creditcardpayment.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.creditcardpayment.dao.ICreditCardRepository;
import com.cg.creditcardpayment.dao.ICustomerRepository;
import com.cg.creditcardpayment.entity.CreditCardEntity;
import com.cg.creditcardpayment.entity.CustomerEntity;
import com.cg.creditcardpayment.exception.CreditCardException;
import com.cg.creditcardpayment.exception.CustomerException;
import com.cg.creditcardpayment.model.CreditCardModel;


@Service
public class CreditCardServiceImpl implements ICreditCardService {
	
	/**
	 * This a local variable: {@link #creditCardRepo} defines the object of ICreditCardRepository
	 * @HasGetter
	 * @HasSetter
	 */
	@Autowired
	private ICreditCardRepository creditCardRepo;
	
	/**
	 * This a local variable: {@link #customerRepo} defines the object of ICustomerRepository
	 * @HasGetter
	 * @HasSetter
	 */
	@Autowired
	private ICustomerRepository customerRepo;

	/**
	 * This a local variable: {@link #parser} defines the object of EMParse
	 * @HasGetter
	 * @HasSetter
	 */
	@Autowired
	private EMParse parser;
	/**
	 * default constructor
	 */
	public CreditCardServiceImpl() {
		
	}

	/**
	 * Parameterized Constructor
	 * @param creditCardRepo		the ICreditCardRepository object
	 * @param customerRepo			the ICustomerRepository object
	 */
	public CreditCardServiceImpl(ICreditCardRepository creditCardRepo,ICustomerRepository customerRepo) {
		super();
		this.creditCardRepo = creditCardRepo;
		this.customerRepo=customerRepo;
		this.parser = new EMParse();
	}
	/**
	 * @return creditCardRepo as ICreditCardRepository
	 */
	public ICreditCardRepository getCreditCardRepo() {
		return creditCardRepo;
	}

	/**
	 * 
	 * @param creditCardRepo which is ICreditCardRepository
	 */
	public void setCreditCardRepo(ICreditCardRepository creditCardRepo) {
		this.creditCardRepo = creditCardRepo;
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
	 * This method is used to add the new 
	 * @param creditCard which contains the new creditCard details
	 * @return CreditCardModel which is added 
	 * @throws CreditCardException when exception occurs
	 */
	@Override
	public CreditCardModel add(CreditCardModel creditCard) throws CreditCardException {
		if(creditCard !=null) {
			if(creditCardRepo.existsById(creditCard.getCardNumber())) {
				throw new CreditCardException("CreditCard "+creditCard.getCardNumber()+" is already Exists");
			}else {
				creditCard=parser.parse(creditCardRepo.save(parser.parse(creditCard)));
			}
		}
		return creditCard;
	}

	/**
	 * This method is used to update the old creditCard
	 * @param crdeitCard which contains the updated creditCard details
	 * @return CreditCardModel which is updated 
	 * @throws CreditCardException when exception occurs
	 */
	@Override
	public CreditCardModel save(CreditCardModel creditCard) throws CreditCardException {
		if(creditCard==null) {
			throw new CreditCardException("Credit Card  should not be null");
		}else {
			return parser.parse(creditCardRepo.save(parser.parse(creditCard)));
		}
	}

	/**
	 * This method deletes the creditCard by its creditCardNumber
	 * @param cardNumber which should be deleted
	 * @throws CreditCardException when exception occurs
	 */
	@Override
	public void deleteById(String creditCardId) throws CreditCardException {
		if(creditCardId==null) {
			throw new CreditCardException("Card number should not be null");
		}else if (!creditCardRepo.existsById(creditCardId)) {
			throw new CreditCardException("Card Number"+creditCardId+" does not exists");
		}else {
			creditCardRepo.deleteById(creditCardId);
		}
	}

	/**
	 *  This method search the CreditCard by its CreditCard number
	 * @param CreditCardNumber to be searched
	 * @return CreditCardModel when the CreditCard is found
	 * @throws CreditCardException when the exception occurs
	 */
	@Override
	public CreditCardModel findById(String creditCardId) throws CreditCardException {
		if(creditCardId==null) {
			throw new CreditCardException("Card number should not be null");
		}else if (!creditCardRepo.existsById(creditCardId)) {
			throw new CreditCardException("Card Number"+creditCardId+" does not exists");
		}else {
			return parser.parse(creditCardRepo.findById(creditCardId).orElse(null));
		}
	}

	/**
	 * This method list all the CreditCards
	 * @return List<CreditCardModel> which contains all the CreditCard details
	 */
	@Override
	public List<CreditCardModel> findAll() {
		return creditCardRepo.findAll().stream().map(parser::parse).collect(Collectors.toList());
	}

	/**
	 * This method confirms whether the given cardNumber is there in the data base
	 * @param cardNumber which should be searched
	 * @return boolean whether the given cardNumber exists or nut
	 * @throws CreditCardException when exception occurs
	 */
	@Override
	public boolean existsById(String cardNumber) throws CreditCardException {
		if(cardNumber==null) {
			throw new CreditCardException("Card Number should not be null");
		}
		return creditCardRepo.existsById(cardNumber);
	}

	/**
	 * This method add CreditCard by its customer
	 * @param creditCard which should be added
	 * @param customerId to which the CreditCard should be added
	 * @return CreditCardModel which is added to the given cutomer
	 * @throws CreditCardException when the CreditCard related exception occurs
	 * @throws CustomerException when the customer related exception occurs
	 */
	@Override
	public CreditCardModel addToCustomer(CreditCardModel creditCard, String customerId) throws CreditCardException, CustomerException {
		CustomerEntity customer=customerRepo.findById(customerId).orElse(null);
		if(customerId==null) {
			throw new CustomerException("Customer Id should not null");
		}else if (customer==null) {
			throw new CustomerException("Customer does not exist");
		}
		creditCard.setCustomerId(customerId);
		Set<CreditCardModel> creditCards=customer.getCreditCard().stream().map(parser::parse).collect(Collectors.toSet());
		if(creditCards.contains(creditCard)) {
			throw new CreditCardException("CreditCard "+creditCard.getCardNumber()+" is already Exists");
		}else {
			creditCard=parser.parse(creditCardRepo.save(parser.parse(creditCard)));
			customer.getCreditCard().add(parser.parse(creditCard));
			customer.setCreditCard(customer.getCreditCard());
			customerRepo.save(customer);	
		}
		return creditCard;
	}

	/**
	 * This method finds all the CreditCards by the customerId
	 * @param customerId to which the CreditCards should be searched
	 * @return Set<CreditCardModel> which contain all the CreditCards of the customer
	 * @throws CustomerException when the exception occurs
	 */
	@Override
	public Set<CreditCardModel> findByCustomerId(String customerId) throws CreditCardException, CustomerException {
		CustomerEntity customer=customerRepo.findById(customerId).orElse(null);
		if(customerId==null) {
			throw new CustomerException("Customer Id can not be null");
		}else if(customer==null) {
			throw new CustomerException("Customer Does not Exists");
		}else if(customer.getCreditCard().isEmpty()) {
			throw new CreditCardException("No Credit Cards Exists");
		}else {
			return customer.getCreditCard().stream().map(parser::parse).collect(Collectors.toSet());
		}
	}

	/**
	 * This method deletes the CreditCard by customerId
	 * @param customerId to delete the CreditCard
	 * @param cardNumber to delete the CreditCard
	 * @throws CreditCardException when CreditCard related exception occurs
	 * @throws CustomerException when customer related exception occurs
	 */
	@Override
	public void deleteCreditCardOfCustomer(String customerId, String cardNumber) throws CreditCardException, CustomerException {
		CustomerEntity customer=customerRepo.findById(customerId).orElse(null);
		if(customerId==null) {
			throw new CustomerException("Customer Id can not be null");
		}else if(customer==null) {
			throw new CustomerException("No Customer Exists");
		}else if(customer.getCreditCard().isEmpty()) {
			throw new CustomerException("No Credit Card Exists");
		}
		CreditCardEntity card =creditCardRepo.findById(cardNumber).orElse(null);
		if(card==null) {
			throw new CreditCardException("CreditCard doesnot exist to delete");
		}
		if(!customer.getCreditCard().contains(card)) {
			throw new CustomerException("Credit Card Not Exists");
		}
		customer.getCreditCard().remove(card);
		creditCardRepo.deleteById(cardNumber);
	}

}
