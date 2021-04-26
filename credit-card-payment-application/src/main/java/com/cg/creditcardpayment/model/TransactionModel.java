package com.cg.creditcardpayment.model;
/**
* <h1>Transaction Model</h1>
* The Transaction Model program implements an application such that
* the user can make transaction from the limit present in the credit card and send the details to entity with help of Parser
* and perform some Validations.
* <p>
* 
*
* @author  Gajula Naveen
* @version 1.0
* @since   2021-03-31 
*/
import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TransactionModel {
	/**
	* This a local variable: {@link #transactionId} defines the unique id of the transaction 
	* @HasGetter
	* @HasSetter
	*/
	private Long transactionId;
	
	/**
	* This a local variable: {@link #cardNumber} defines the card number to which the transaction belongs to 
	* @HasGetter
	* @HasSetter
	*/
	@NotNull(message="CardNumber Cannot to be Null")
	@NotEmpty(message="CardNumber cannot be Empty")
	private String cardNumber;
	
	/**
	* This a local variable: {@link #transactionDate} defines date of the transaction done 
	* @HasGetter
	* @HasSetter
	*/
	@NotNull(message="date Cannot to be Null")
	@NotEmpty(message="date cannot be Empty")
	private LocalDate transactionDate;
	
	/**
	* This a local variable: {@link #transactionTime} defines the time of the transaction done 
	* @HasGetter
	* @HasSetter
	*/
	@NotNull(message="time Cannot to be Null")
	@NotEmpty(message="time cannot be Empty")
	private LocalTime transactionTime;
	
	/**
	* This a local variable: {@link #amount} defines the amount of transaction made
	* @HasGetter
	* @HasSetter
	*/
	@NotNull(message="amount Cannot to be Null")
	@NotEmpty(message="amount cannot be Empty")
	private Double amount;

	/**
	* This a local variable: {@link #status} defines the status of the transaction
	* @HasGetter
	* @HasSetter
	*/
	@NotNull(message="status Cannot to be Null")
	@NotEmpty(message="status cannot be Empty")
	private TransactionStatus status;
	
	/**
	* This a local variable: {@link #description} defines the description of the transaction
	* @HasGetter
	* @HasSetter
	*/
	@NotNull(message="description Cannot to be Null")
	@NotEmpty(message="description cannot be Empty")
	private String description;
	
	/**
	 * Default Constructor
	 */
	public TransactionModel() {
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
	public TransactionModel(Long transactionId,
			@NotNull(message = "CardNumber Cannot to be Null") @NotEmpty(message = "CardNumber cannot be Empty") String cardNumber,
			@NotNull(message = "amount Cannot to be Null") @NotEmpty(message = "amount cannot be Empty") Double amount,
			@NotNull(message="date Cannot to be Null") @NotEmpty(message="date cannot be Empty") LocalDate transactionDate,
			@NotNull(message="time Cannot to be Null") @NotEmpty(message="time cannot be Empty") LocalTime transactionTime,
			@NotNull(message = "status Cannot to be Null") @NotEmpty(message = "status cannot be Empty") TransactionStatus status,
			@NotNull(message = "description Cannot to be Null") @NotEmpty(message = "description cannot be Empty") String description) {
		super();
		this.transactionId = transactionId;
		this.cardNumber = cardNumber;
		this.transactionDate = transactionDate;
		this.transactionTime = transactionTime;
		this.amount = amount;
		this.status = status;
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
	 * @return cardNumber as String
	 */
	public String getCardNumber() {
		return cardNumber;
	}

	/**
	 * 
	 * @param cardNumber to set the credit card number on which the transaction has made
	 */
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((cardNumber == null) ? 0 : cardNumber.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((transactionDate == null) ? 0 : transactionDate.hashCode());
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
		TransactionModel other = (TransactionModel) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (cardNumber == null) {
			if (other.cardNumber != null)
				return false;
		} else if (!cardNumber.equals(other.cardNumber))
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
		if (transactionDate == null) {
			if (other.transactionDate != null)
				return false;
		} else if (!transactionDate.equals(other.transactionDate))
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
				"TransactionModel [transactionId=%s, cardNumber=%s, transactionDate=%s, transactionTime=%s, amount=%s, status=%s, description=%s]",
				transactionId, cardNumber, transactionDate, transactionTime, amount, status, description);
	}
	
	
}
