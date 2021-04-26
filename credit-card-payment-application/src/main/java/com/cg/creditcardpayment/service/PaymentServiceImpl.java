package com.cg.creditcardpayment.service;
/**
* <h1>PaymentServiceImpl</h1>
* TransactionServiceImpl is a program where all the methods in 	TransactionServiceImpl are implemented
* <p>
* 
*
* @author  P Pranava Charitra
* @version 1.0
* @since   2021-03-31 
*/
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.creditcardpayment.dao.IAccountRepository;
import com.cg.creditcardpayment.dao.ICreditCardRepository;
import com.cg.creditcardpayment.dao.IPaymentRepository;
import com.cg.creditcardpayment.dao.IStatementRepository;
import com.cg.creditcardpayment.entity.AccountEntity;
import com.cg.creditcardpayment.entity.CreditCardEntity;
import com.cg.creditcardpayment.entity.StatementEntity;
import com.cg.creditcardpayment.exception.AccountException;
import com.cg.creditcardpayment.exception.CreditCardException;
import com.cg.creditcardpayment.exception.PaymentException;
import com.cg.creditcardpayment.exception.StatementException;
import com.cg.creditcardpayment.model.AccountModel;
import com.cg.creditcardpayment.model.PaymentModel;
import com.cg.creditcardpayment.model.StatementModel;


@Service
public class PaymentServiceImpl implements IPaymentService {
	/**
	 * This a local variable: {@link #paymentRepo} defines the object of IPaymentRepository
	 */
	@Autowired
	private IPaymentRepository paymentRepo;
	/**
	 * This a local variable: {@link #creditCardRepo} defines the object of ICreditCardRepository
	 */
	@Autowired
	private ICreditCardRepository creditCardRepo;
	/**
	 * This a local variable: {@link #accountRepo} defines the object of IAccountRepository
	 */
	@Autowired
	private IAccountRepository accountRepo;
	
	/**
	 * This a local variable: {@link #statementRepo} defines the object of IStatementRepository
	 * @HasGetter
	 * @HasSetter
	 */
	@Autowired
	private IStatementRepository statementRepo;
	
	/**
	 * This a local variable: {@link #paymentRepo} defines the object of IPaymentRepository
	 */
	@Autowired
	private IPaymentService paymentService;
	
	/**
	 * This a local variable: {@link #parser} defines the object of EMparse
	 */
	@Autowired
	private EMParse parser;
	
	/**
	 * Default Constructor
	 */
	public PaymentServiceImpl() {
		
	}

	/**
	 * Parameterized Constructor
	 * @param paymentRepo 		the onject of IPaymentRepository
	 */
	public PaymentServiceImpl(IPaymentRepository paymentRepo) {
		super();
		this.paymentRepo = paymentRepo;
		this.parser = new EMParse();
	}


	/**
	 * This method is used to add the new payment
	 * @param payment which contains the new payment details
	 * @param userId which contains paymentId of the payment
	 * @return PaymentModel which is added 
	 * @throws PaymentException when exception occurs
	 */
	@Override
	public PaymentModel add(PaymentModel payment) throws PaymentException {
		if(payment !=null) {
			if(paymentRepo.existsById(payment.getPaymentId())) {
				throw new PaymentException("Payment "+payment.getPaymentId()+" is already Exists");
			}else {
				payment=parser.parse(paymentRepo.save(parser.parse(payment)));
			}
		}
		return payment;
	}

	/**
	 * This method is used to update the payment
	 * @param payment which contains the updated payment details
	 * @return PaymentModel which is updated 
	 * @throws PaymentException when exception occurs
	 */
	@Override
	public PaymentModel save(PaymentModel payment) throws PaymentException {
		if(payment==null) {
			throw new PaymentException("Payment can not be Null");
		}
		return parser.parse(paymentRepo.save(parser.parse(payment)));
	}

	/**
	 * This method deletes the payment by its paymentId
	 * @param paymentId which should be deleted
	 * @throws PaymentException when exception occurs
	 */
	@Override
	public void deleteById(Long paymentId) throws PaymentException {
		if(paymentId==null) {
			throw new PaymentException("payment Id can not be null");
		}else if(!paymentRepo.existsById(paymentId)) {
			throw new PaymentException("Payment Id "+paymentId+" Does not Exist");
		}
		paymentRepo.deleteById(paymentId);
	}

	/**
	 *  This method search the payment by its paymentId
	 * @param paytmentId to be searched
	 * @return PaymentModel when the payment is found
	 * @throws PaymentException when the exception occurs
	 */
	@Override
	public PaymentModel findById(Long paymentId) throws PaymentException {
		if(paymentId==null) {
			throw new PaymentException("payment Id can not be null");
		}else if(!paymentRepo.existsById(paymentId)) {
			throw new PaymentException("Payment Id "+paymentId+" Does not Exist");
		}
		return parser.parse(paymentRepo.findById(paymentId).orElse(null));
	}

	/**
	 * This method list all the payments
	 * @return List<PaymentModel> which contains all the payment details
	 */
	@Override
	public List<PaymentModel> findAll() {
		return paymentRepo.findAll().stream().map(parser::parse).collect(Collectors.toList());
	}

	/**
	 * This method checks payment by paymentId
	 * @param UserId to be checked
	 * @return boolean to check the paymentId exists or not
	 */
	@Override
	public boolean existsById(Long paymentId) {
		return paymentRepo.existsById(paymentId);
	}

	/**
	 * This method lists the pending bills of the credit card
	 * @param cardNumber to which the pending bills has to be fetched
	 * @return List<StatementModel> which contains all the statements of the credit cards
	 * @throws CreditCardException when exception occurs
	 */
	@Override
	public List<StatementModel> pendingBills(String cardNumber) throws CreditCardException {
		CreditCardEntity card=creditCardRepo.findById(cardNumber).orElse(null);
		if(cardNumber==null) {
			throw new CreditCardException("Card Number can not be null");
		}else if(card==null) {
			throw new CreditCardException("Card does not Exists");
		}		
		Set<StatementEntity> statements =card.getStatement();			
	
		List<StatementModel> pendingStatements=statements.stream().filter(state->state.getDueAmount()>=0.0001).distinct().map(parser::parse).collect(Collectors.toList());
		
		pendingStatements.sort((st1,st2)->st1.getStatementId().compareTo(st2.getStatementId()));
		
		return pendingStatements;
	}

	/**
	 * This method is used to pay bill of the credit card
	 * @param payment which contains all the details of payment
	 * @param statementId to which the payment is done
	 * @param accountNumber from which the payment is done
	 * @return PaymentModel which contains all the payment details
	 * @throws PaymentException when payment related exception occurs
	 * @throws CreditCardException when credit card related exception occurs
	 * @throws StatementException when statement related exception occurs
	 * @throws AccountException when account related exception occurs 
	 */
	@Override
	public PaymentModel payBill(PaymentModel pay, Long statementId,String accountNumber) throws PaymentException, CreditCardException, StatementException, AccountException {
		
		CreditCardEntity card=creditCardRepo.findById(pay.getCardNumber()).orElse(null);
		
		if(card==null) {
			throw new CreditCardException("Credit card does not Exists");
		}
		
		StatementModel statement=card.getStatement().stream().filter(state->state.getStatementId().equals(statementId)).map(parser::parse).findFirst().orElse(null);
		if(statementId==null) {
			throw new StatementException("StatementId can not be Null");
		}else if(statement==null) {
			throw new StatementException("Statement Does not ");
		}
		PaymentModel payment=new PaymentModel();
		payment.setCardNumber(pay.getCardNumber());
		payment.setMethod(pay.getMethod());
		
		if(accountNumber==null) {
			throw new AccountException("account number can not be null");
		}
		AccountEntity acc=accountRepo.findById(accountNumber).orElse(null);
		if(acc==null) {
			throw new AccountException("Account Does not Exists");
		}
		Double accountBalance=acc.getAccountBalance();
		
		Double amount=pay.getAmount();
		AccountModel account=parser.parse(accountRepo.findById(accountNumber).orElse(null));
		account.setAccountBalance(accountBalance-amount);
		payment.setAmount(amount);
		card.setUsedLimit(card.getUsedLimit()-amount);
		if(statement.getDueAmount()-amount>=0.0) {
			statement.setDueAmount(statement.getDueAmount()-amount);
		}else {
			statement.setDueAmount(0.0);
		}
		accountRepo.save(parser.parse(account));
		payment.setPaidDate(LocalDate.now());
		payment.setPaidTime(LocalTime.now());
		payment.setPaymentId(pay.getPaymentId());
		statementRepo.save(parser.parse(statement));
		paymentService.add(payment);
		return payment;
	}


	/**
	 * This method is used to pay bill of the credit card
	 * @param payment which contains payment details
	 * @param statementId to which the payment is done
	 * @return PaymentModel which contains all the details of payment
	 * @throws PaymentException When payment related exception occurs
	 * @throws CreditCardException when creditCard related exception occurs
	 * @throws StatementException when statement related exception occurs
	 */
	@Override
	public PaymentModel payBill(PaymentModel pay, Long statementId) throws PaymentException, CreditCardException, StatementException{
		CreditCardEntity card=creditCardRepo.findById(pay.getCardNumber()).orElse(null);
		if(card==null) {
			throw new CreditCardException("Card does not exists");
		}
		StatementModel statement=card.getStatement().stream().filter(state->state.getStatementId().equals(statementId)).map(parser::parse).findFirst().orElse(null);
		if(statement==null) {
			throw new StatementException("Statement Does not exists");
		}
		PaymentModel payment=new PaymentModel();
		payment.setPaymentId(pay.getPaymentId());
		payment.setCardNumber(pay.getCardNumber());
		payment.setMethod(pay.getMethod());
		Double amount=pay.getAmount();
		Double dueAmount=statement.getDueAmount();
		payment.setAmount(amount);
		card.setUsedLimit(card.getUsedLimit()-amount);
		payment.setPaidDate(LocalDate.now());
		payment.setPaidTime(LocalTime.now());
		if(dueAmount-amount>=0.0) {
			statement.setDueAmount(dueAmount-amount);
		}else {
			statement.setDueAmount(0.0);
		}
		statementRepo.save(parser.parse(statement));
		paymentService.add(payment);
		return payment;
	}

	/**
	 *  This method is used to pay for credit card
	 * @param pay which is payment model
	 * @param cardNumber to which money has to be paid
	 * @return PaymentModel which contains the details of payment
	 * @throws PaymentException when payment related exception occurs
	 * @throws CreditCardException when credit card related exception occurs
	 * @throws StatementException when statement related exception related exception occurs
	 */
	@Override
	public PaymentModel payForCreditCard(PaymentModel pay, String cardNumber) throws PaymentException, CreditCardException, StatementException{
		CreditCardEntity card=creditCardRepo.findById(cardNumber).orElse(null);
		if(card==null) {
			throw new CreditCardException("Card does not exists");
		}
		PaymentModel payment=new PaymentModel();
		payment.setPaymentId(pay.getPaymentId());
		payment.setCardNumber(cardNumber);
		payment.setMethod(pay.getMethod());
		Double amount=pay.getAmount();
		payment.setAmount(amount);
		card.setUsedLimit(card.getUsedLimit()-amount);
		payment.setPaidDate(LocalDate.now());
		payment.setPaidTime(LocalTime.now());
		paymentService.add(payment);
		return payment;
	}

	/**
	 * This method displays the payment history of the credit card
	 * @param cardNumber to which the payment history has to be fetched
	 * @return List<PaymentModel> which contains all the payments
	 * @throws CreditCardException when the exception occurs
	 */
	@Override
	public List<PaymentModel> paymentHistory(String cardNumber) throws CreditCardException {
		CreditCardEntity card=creditCardRepo.findById(cardNumber).orElse(null);
		if(cardNumber==null) {
			throw new CreditCardException("Card number can not be null");
		}else if(card==null) {
			throw new CreditCardException("Credit Card does not exists");
		}
		return card.getPayments().stream().map(parser::parse).collect(Collectors.toList());
	}
}
