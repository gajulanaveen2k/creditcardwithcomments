package com.cg.creditcardpayment.model;

/**
* <h1>Statement Model</h1>
* The Statement Model program implements an application such that
* the user can view all his statements and send the details to entity with help of Parser
* and perform some Validations.
* <p>
* 
*
* @author  P Venkata Sai Reddy
* @version 1.0
* @since   2021-03-31 
*/
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class StatementModel {	
	/**
	* This a local variable: {@link #statementId} defines the statementId of the statement
	* @HasGetter
	* @HasSetter
	*/
	private Long statementId;
	
	/**
	* This a local variable: {@link #dueAmount} defines the due amount of the statement
	* @HasGetter
	* @HasSetter
	*/
	@NotNull(message="due amount cannot be null")	
	@NotBlank(message="due amount cannot be blank")
	private Double dueAmount;
	
	/**
	* This a local variable: {@link #billAmount} defines the billAmount of the statement
	* @HasGetter
	* @HasSetter
	*/
	@NotNull(message="bill amount cannot be null")	
	@NotBlank(message="bill amount cannot be blank")
	private Double billAmount;
	
	/**
	* This a local variable: {@link #billDate} defines the bill date of the statement
	* @HasGetter
	* @HasSetter
	*/
	@NotNull(message="bill date cannot be null")	
	@NotBlank(message="bill date cannot be blank")
	private LocalDate billDate;
	
	/**
	* This a local variable: {@link #dueDate} defines the dueDate of the statement
	* @HasGetter
	* @HasSetter
	*/
	
	@NotNull(message="due date cannot be null")	
	@NotBlank(message="due date cannot be blank")
	private LocalDate dueDate;

	/**
	* This a local variable: {@link #cardNumber} defines the credit card number to which the statement belongs to
	* @HasGetter
	* @HasSetter
	*/
	@NotNull(message="credit card cannot be null")	
	@NotBlank(message="credit card cannot be blank")
	private String cardNumber;

	/**
	* This a local variable: {@link #customerName} defines the name of the custmer to which the statement belongs to
	* @HasGetter
	* @HasSetter
	*/
	@NotNull(message="customer name cannot be null")	
	@NotBlank(message="customer name cannot be blank")
	private String customerName;
	
	/**
	 * Default Constructor
	 */
	public StatementModel() {
		/*Default Constructor*/
	}

	/**
	 * @param statementId		the unique id for the statement
	 * @param dueAmount			the due amount of the statement
	 * @param billDate			the bill date of the statement
	 * @param dueDate			the due date of the statement
	 * @param cardNumber		the credit card number to which the statement belongs to 
	 * @param customerName		the name of the customer to which the statement belongs to
	 */
	public StatementModel(Long statementId,
			@NotNull(message = "bill amount cannot be null") @NotBlank(message = "bill amount cannot be blank") Double billAmount,
			Double dueAmount,
			@NotNull(message = "bill date cannot be null") @NotBlank(message = "bill date cannot be blank") LocalDate billDate,
			@NotNull(message = "due date cannot be null") @NotBlank(message = "due date cannot be blank") LocalDate dueDate,
			@NotNull(message = "credit card cannot be null") @NotBlank(message = "credit card cannot be blank") String cardNumber,
			@NotNull(message="customer name cannot be null") @NotBlank(message="customer name cannot be blank") String customerName) {
		super();
		this.statementId=statementId;
		this.dueAmount = dueAmount;
		this.billDate = billDate;
		this.billAmount=billAmount;
		this.dueDate = dueDate;
		this.cardNumber = cardNumber;
		this.customerName=customerName;
	}


	/**
	 * 
	 * @return billAmount as Double
	 */
	public Double getBillAmount() {
		return billAmount;
	}



	/**
	 * 
	 * @param billAmount to set bill amount of the statement
	 */
	public void setBillAmount(Double billAmount) {
		this.billAmount = billAmount;
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
	 * @param cardNumber to set credit card number to which the statement belongs to
	 */
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}


	/**
	 * 
	 * @return statementId as Long
	 */
	public Long getStatementId() {
		return statementId;
	}
	/**
	 * 
	 * @param statementId to set id of the statement
	 */
	public void setStatementId(Long statementId) {
		this.statementId = statementId;
	}
	/**
	 * 
	 * @return dueAmount as Double
	 */
	public Double getDueAmount() {
		return dueAmount;
	}

	/**
	 * 
	 * @param dueAmount to set the due amount of the statement
	 */
	public void setDueAmount(Double dueAmount) {
		this.dueAmount = dueAmount;
	}

	/**
	 * 
	 * @return billDate as LocalDate
	 */
	public LocalDate getBillDate() {
		return billDate;
	}
	/**
	 * 
	 * @param billDate to set bill date of the statement
	 */
	public void setBillDate(LocalDate billDate) {
		this.billDate = billDate;
	}

	/**
	 * 
	 * @return dueDate as LocalDate
	 */
	public LocalDate getDueDate() {
		return dueDate;
	}

	/**
	 * 
	 * @param dueDate to set due date of the statement
	 */
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	
	/**
	 * 
	 * @return customerName as string
	 */
	public String getCustomerName() {
		return customerName;
	}


	/**
	 * 
	 * @param customerName to set name of the customer for which the statement belongs to
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((billAmount == null) ? 0 : billAmount.hashCode());
		result = prime * result + ((billDate == null) ? 0 : billDate.hashCode());
		result = prime * result + ((cardNumber == null) ? 0 : cardNumber.hashCode());
		result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
		result = prime * result + ((dueDate == null) ? 0 : dueDate.hashCode());
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
		StatementModel other = (StatementModel) obj;
		if (billAmount == null) {
			if (other.billAmount != null)
				return false;
		} else if (!billAmount.equals(other.billAmount))
			return false;
		if (billDate == null) {
			if (other.billDate != null)
				return false;
		} else if (!billDate.equals(other.billDate))
			return false;
		if (cardNumber == null) {
			if (other.cardNumber != null)
				return false;
		} else if (!cardNumber.equals(other.cardNumber))
			return false;
		if (customerName == null) {
			if (other.customerName != null)
				return false;
		} else if (!customerName.equals(other.customerName))
			return false;
		if (dueDate == null) {
			if (other.dueDate != null)
				return false;
		} else if (!dueDate.equals(other.dueDate))
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
				"StatementModel [statementId=%s, dueAmount=%s, billAmount=%s, billDate=%s, dueDate=%s, cardNumber=%s, customerName=%s]",
				statementId, dueAmount, billAmount, billDate, dueDate, cardNumber, customerName);
	}		
}
