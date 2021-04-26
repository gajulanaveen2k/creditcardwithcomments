package com.cg.creditcardpayment.entity;

/**
* <h1>TransactionEntity</h1>
* The Transaction Entity program implements an application such that
* the data of the transaction is sent to the database
* <p>
* 
*
* @author  Gajula Naveen
* @version 1.0
* @since   2021-03-31 
*/
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cg.creditcardpayment.model.TransactionStatus;

@Entity
@Table(name="transactions")
public class TransactionEntity {

	/**
	* This a local variable: {@link #transactionId} defines the unique id of the transaction 
	* @HasGetter
	* @HasSetter
	*/
	@Id
	@Column(name="trans_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long transactionId;
	
	/**
	* This a local variable: {@link #status} defines the status of the transaction
	* @HasGetter
	* @HasSetter
	*/
	@Enumerated(EnumType.STRING)
	@Column(name="status",nullable=false)
	private TransactionStatus status;
	
	/**
	* This a local variable: {@link #transactionDate} defines date of the transaction done 
	* @HasGetter
	* @HasSetter
	*/
	@Column(name="trans_date",nullable=false)
	private LocalDate transactionDate;
	
	/**
	* This a local variable: {@link #transactionTime} defines the time of the transaction done 
	* @HasGetter
	* @HasSetter
	*/
	@Column(name="trans_time",nullable=false)
	private LocalTime transactionTime;
	
	/**
	* This a local variable: {@link #amount} defines the amount of transaction made
	* @HasGetter
	* @HasSetter
	*/
	@Column(name="amount",nullable=false)
	private Double amount;
	
	/**
	* This a local variable: {@link #description} defines the description of the transaction
	* @HasGetter
	* @HasSetter
	*/
	@Column(name="description",nullable=false)
	private String description;
	
	/**
	* This a local variable: {@link #creditCard} defines the creditcard to which the transaction belongs to
	* @HasGetter
	* @HasSetter
	*/
	@ManyToOne( fetch = FetchType.LAZY)
	@JoinColumn(name="card_number")
	private CreditCardEntity creditCard;
	
	/**
	 * Default Constructor
	 */
	public TransactionEntity() {
		/* Default Constructor*/
	}


	/**
	 * Parameterized Constructor with parameters
	 * @param transactionId		the unique Id for the transaction
	 * @param cardNumber		the credit card on which the transaction has made
	 * @param amount			the amount of the transaction made
	 * @param transactionDate	the date of the transaction	
	 * @param transactionTime	the time of the transaction
	 * @param status			the status of the transaction
	 * @param description		the description of the transaction
	 */
	public TransactionEntity(Long transactionId, TransactionStatus status, CreditCardEntity creditCard,
			Double amount, String description) {
		super();
		this.transactionId=transactionId;
		this.status = status;
		this.transactionDate = LocalDate.now();
		this.transactionTime = LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute(), LocalTime.now().getSecond());
		this.creditCard = creditCard;
		this.amount = amount;
		this.description = description;
	}

	/**
	 * 
	 * @return transactionId as Long
	 */
	public Long getTransactionId() {
		return transactionId;
	}


	/**
	 * 
	 * @param transactionId to set the id for transaction
	 */
	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}


	/**
	 * 
	 * @return status as TransactionStatus
	 */
	public TransactionStatus getStatus() {
		return status;
	}


	/**
	 * 
	 * @param status to set the status of the transaction
	 */
	public void setStatus(TransactionStatus status) {
		this.status = status;
	}


	/**
	 * 
	 * @return transactionDate as LocalDate
	 */
	public LocalDate getTransactionDate() {
		return transactionDate;
	}


	/**
	 * 
	 * @param transactionDate to set the date of the transaction made
	 */
	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}


	/**
	 * 
	 * @return transactionTime as LocalTime
	 */
	public LocalTime getTransactionTime() {
		return transactionTime;
	}


	/**
	 * 
	 * @param transactionTime to set the time of the transaction
	 */
	public void setTransactionTime(LocalTime transactionTime) {
		this.transactionTime = transactionTime;
	}

	/**
	 * 
	 * @return amount as Double
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * 
	 * @param amount to set the amount of the transaction
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}


	/**
	 * 
	 * @return description as String
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @param description to set the description of the transaction
	 */
	public void setDescription(String description) {
		this.description = description;
	}


	/**
	 * @return creditCard as CreditCardEntity
	 */
	public CreditCardEntity getCreditCard() {
		return creditCard;
	}

	/**
	 * @param creditCard to which the transaction belongs to
	 */
	public void setCreditCard(CreditCardEntity creditCard) {
		this.creditCard = creditCard;
	}
	
	/**
	 * 
	 * @return cardNumber to which the transaction belongs to
	 */
	public String getCardNumber() {
		return creditCard.getCardNumber();
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((creditCard == null) ? 0 : creditCard.hashCode());
		result = prime * result + ((transactionDate == null) ? 0 : transactionDate.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((transactionTime == null) ? 0 : transactionTime.hashCode());
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
		TransactionEntity other = (TransactionEntity) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (creditCard == null) {
			if (other.creditCard != null)
				return false;
		} else if (!creditCard.equals(other.creditCard))
			return false;
		if (transactionDate == null) {
			if (other.transactionDate != null)
				return false;
		} else if (!transactionDate.equals(other.transactionDate))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (transactionTime == null) {
			if (other.transactionTime != null)
				return false;
		} else if (!transactionTime.equals(other.transactionTime))
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
				"TransactionEntity [transactionId=%s, status=%s, transactionDate=%s, transactionTime=%s, amount=%s, description=%s, creditCard=%s]",
				transactionId, status, transactionDate, transactionTime, amount, description, creditCard);
	}


		
	
}
