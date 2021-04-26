package com.cg.creditcardpayment.entity;
/**
* <h1>CreditCardEntity</h1>
* The CreditCardEntity program implements an application such that
* the data of the creditcards is sent to the database
* <p>
* 
*
* @author  Abhilash Reddy Gone
* @version 1.0
* @since   2021-03-31 
*/
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.cg.creditcardpayment.model.CardName;
import com.cg.creditcardpayment.model.CardType;


@Entity
@Table(name="creditcards")
public class CreditCardEntity {
	/**
	 * This a local variable: {@link #cardNumber} defines the unique Number for Credit Card
	 * @HasGetter
	 * @HasSetter
	 */
	@Id
	@Column(name="card_number")
	private String cardNumber;
	
	/**
	 * This a local variable: {@link #cardName} defines the name of the Credit Card
	 * @HasGetter
	 * @HasSetter 
	 */
	@Column(name="card_name", nullable=false)
	@Enumerated(EnumType.STRING)
	private CardName cardName;

	/**
	 * This a local variable: {@link #cardType} defines the type of the Credit Card
	 * @HasGetter
	 * @HasSetter 
	 */
	@Column(name="card_type",nullable=false)
	@Enumerated(EnumType.STRING)
	private CardType cardType;
	
	/**
	 * This a local variable: {@link #expiryDate} defines the expire date of the credit card 
	 * @HasGetter
	 * @HasSetter
	 */	
	@Column(name="expiry_date", nullable=false)
    private LocalDate expiryDate;
	/**
	 *This a local variable: {@link #bankName} defines the bank name of the credit card 
	 * @HasGetter
	 * @HasSetter
	 */
	@Column(name="bank_name", nullable=false)
	private String bankName;
	
	/**
	 * This a local variable: {@link #cvv} defines the cvv present on the credit card and it should not be null
	 * @HasGetter
	 * @HasSetter
	 */
	@Column(name="cvv", nullable=false)
    private Integer cvv;
	
	/**
	 * This a local variable: {@link #creditLimit} defines the maximum limit that can be used by the user
	 * @HasGetter
	 * @HasSetter
	 */
	@Column(name="credit_limit",nullable=false)
	private Double creditLimit;
	
	/**
	 * This a local variable: {@link #usedLimit} defines the limit used by the customer from the credit card 
	 * @HasGetter
	 * @HasSetter
	 */
	@Column(name="used_limit")
	private Double usedLimit;
	
	
	/**
	 * This a local variable: {@link #customer} defines the customer to which the credit card belongs to.
	 * @HasGetter
	 * @HasSetter
	 */
	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="user_id")
	private CustomerEntity customer;

	/**
	 * This a local variable: {@link #transaction} defines the transactions of the credit cards.
	 * @HasGetter
	 * @HasSetter
	 */
	@OneToMany(mappedBy="creditCard",cascade=CascadeType.ALL)
	private Set<TransactionEntity> transaction;
	

	/**
	 * This a local variable: {@link #statement} defines the statements of the credit cards.
	 * @HasGetter
	 * @HasSetter
	 */
	@OneToMany(mappedBy="creditCard",cascade=CascadeType.ALL)
	private Set<StatementEntity> statement;

	/**
	 * This a local variable: {@link #payments} defines the payments of the credit cards.
	 * @HasGetter
	 * @HasSetter
	 */
	@OneToMany(mappedBy="card",cascade=CascadeType.ALL)
	private List<PaymentEntity> payments;
	
	/**
	 * Default Constructor
	 */
	public CreditCardEntity() {
		/* Default Constructor */
	}
	
	/**
	 * @return creditLimit in Double
	 */
	public Double getCreditLimit() {
		return creditLimit;
	}

	/**
	 * @param cardNumber		the unique number for credit card
	 * @param cardName			the name of the credit card
	 * @param cardType			the type of the credit card
	 * @param expiryDate		the expire date of the credit card
	 * @param bankName			the bank name for which the credit card belongs to
	 * @param cvv				the cvv of the credit card
	 * @param cardLimit			the card limit of the credit card
	 * @param usedLimit			the used limit from the credit card
	 * @param customer			the customer to which the card belongs to
	 */
	public CreditCardEntity(String cardNumber, CardName cardName, CardType cardType, LocalDate expiryDate,
			String bankName, Integer cvv, Double creditLimit, Double usedLimit, CustomerEntity customer) {
		super();
		this.cardNumber = cardNumber;
		this.cardName = cardName;
		this.cardType = cardType;
		this.expiryDate = expiryDate;
		this.bankName = bankName;
		this.cvv = cvv;
		this.creditLimit = creditLimit;
		this.usedLimit = usedLimit;
		this.customer = customer;
	}

	/**
	 * 
	 * @param cardLimit to set for the credit card which is Double type
	 */
	public void setCreditLimit(Double creditLimit) {
		this.creditLimit = creditLimit;
	}

	/**
	 * 
	 * @return usedLimit as Double
	 */
	public Double getUsedLimit() {
		return usedLimit;
	}

	/**
	 * @param usedLimit to set for used limit of the credit card which is double
	 */
	public void setUsedLimit(Double usedLimit) {
		this.usedLimit = usedLimit;
	}

	/**
	 * 
	 * @return expiryDate as LocalDate
	 */
	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	/**
	 * 
	 * @param expiryDate to set for Credit card which is Local Date
	 */
	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 * 
	 * @return customer as customerEntity
	 */
	public CustomerEntity getCustomer() {
		return customer;
	}

	/**
	 * 
	 * @param customer to which the credit card belongs to 
	 */
	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}

	/**
	 * 
	 * @return transaction as Set<TransactionEntity>
	 */
	public Set<TransactionEntity> getTransaction() {
		return transaction;
	}

	/**
	 * 
	 * @param transaction lists all the transaction on credit card
	 */
	public void setTransaction(Set<TransactionEntity> transaction) {
		this.transaction = transaction;
	}

	/**
	 * 
	 * @return statement as Set<PaymentEntity>
	 */
	public Set<StatementEntity> getStatement() {
		return statement;
	}

	/**
	 * 
	 * @param statement lists all the statements on credit card
	 */
	public void setStatement(Set<StatementEntity> statement) {
		this.statement = statement;
	}

	/**
	 * 
	 * @return payments as Set<PaymentEntity>
	 */
	public List<PaymentEntity> getPayments() {
		return payments;
	}

	/**
	 * 
	 * @param payments lists all the payments on credit card
	 */
	public void setPayments(List<PaymentEntity> payments) {
		this.payments = payments;
	}
	
	/**
	 * 
	 * @return bankName as string
	 */
	public String getBankName() {
		return bankName;
	}

	/**
	 * 
	 * @param bankName to set for CreditCard which is string
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}


	/**
	 * @return cardType as object of CardType
	 */
	public CardType getCardType() {
		return cardType;
	}

	/**
	 * @param cardType to set for Credit Card which is CardType
	 */
	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}

	/**
	 * 
	 * @return cardName as object of Enum CardName
	 */
	public CardName getCardName() {
		return cardName;
	}

	/**
	 * 
	 * @param cardName to set for Credit card which is string
	 */
	public void setCardName(CardName cardName) {
		this.cardName = cardName;
	}


	/**
	 * 
	 * @return cardNumber as string
	 */
	public String getCardNumber() {
		return cardNumber;
	}


	/**
	 * 
	 * @param cardNumber to set for the credit card which is string
	 */
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	/**
	 * 
	 * @return cvv as Integer
	 */
	public Integer getCvv() {
		return cvv;
	}

	/**
	 * 
	 * @param cvv to set for creditcard which is INteger
	 */
	public void setCvv(Integer cvv) {
		this.cvv = cvv;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bankName == null) ? 0 : bankName.hashCode());
		result = prime * result + ((cardName == null) ? 0 : cardName.hashCode());
		result = prime * result + ((cardNumber == null) ? 0 : cardNumber.hashCode());
		result = prime * result + ((cardType == null) ? 0 : cardType.hashCode());
		result = prime * result + ((cvv == null) ? 0 : cvv.hashCode());
		result = prime * result + ((expiryDate == null) ? 0 : expiryDate.hashCode());
		return result;
	}

	/**
	 * Indicates whether some other object is "equal to" this one.<br><br>
	 * The <strong>equals</strong> method for class <strong>Object</strong> implements the most discriminating possible equivalence relation on objects; 
	 * that is, for any non-null reference values x and y, this method returns <strong>true</strong> if and only if x and y refer to the same object (<strong>x == y</strong> has the value <strong>true</strong>).
	 * <br><br>Note that it is generally necessary to override the <strong>hashCode</strong> method whenever this method is overridden, 
	 * so as to maintain the general contract for the <strong>hashCode</strong> method,
	 * which states that equal objects must have equal hash codes.
	 * <br>
	 * @param obj the reference object with which to compare.
	 * 
	 * @return true if this object is the same as the obj argument; false otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CreditCardEntity other = (CreditCardEntity) obj;
		if (bankName == null) {
			if (other.bankName != null)
				return false;
		} else if (!bankName.equals(other.bankName))
			return false;
		if (cardName == null) {
			if (other.cardName != null)
				return false;
		} else if (!cardName.equals(other.cardName))
			return false;
		if (cardNumber == null) {
			if (other.cardNumber != null)
				return false;
		} else if (!cardNumber.equals(other.cardNumber))
			return false;
		if (cardType == null) {
			if (other.cardType != null)
				return false;
		} else if (!cardType.equals(other.cardType))
			return false;
		
		return true;
	}

	/**
	 * Returns a string representation of the object. In general, the toString method returns a string that "textually represents" this object. 
	 * The result should be a concise but informative representation that is easy for a person to read.
	 * 
	 * @return a string representation of the object.
	 */
	@Override
	public String toString() {
		return String.format(
				"cardNumber=%s, cardName=%s, cardType=%s, expiryDate=%s, bankName=%s, cvv=%s, customer=%s",
				cardNumber, cardName, cardType, expiryDate, bankName, cvv, customer.getName());
	}

	
    
}
