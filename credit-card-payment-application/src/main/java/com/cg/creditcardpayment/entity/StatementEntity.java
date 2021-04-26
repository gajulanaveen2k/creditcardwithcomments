package com.cg.creditcardpayment.entity;
/**
* <h1>	StatementEntity</h1>
* The Statement Entity program implements an application such that
* the data of the Statements is sent to the database
* <p>
* 
*
* @author  P Venkata Sai Reddy
* @version 1.0
* @since   2021-03-31 
*/
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="statements")
public class StatementEntity {	
	/**
	* This a local variable: {@link #statementId} defines the statementId of the statement
	* @HasGetter
	* @HasSetter
	*/
	@Id
	@Column(name="statement_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long statementId;
	
	/**
	* This a local variable: {@link #dueAmount} defines the due amount of the statement
	* @HasGetter
	* @HasSetter
	*/
	@Column(name="due_amount",nullable=false)
	private Double dueAmount;
	
	/**
	* This a local variable: {@link #billAmount} defines the billAmount of the statement
	* @HasGetter
	* @HasSetter
	*/
	@Column(name="bill_amount",nullable=false)
	private Double billAmount;
	
	/**
	* This a local variable: {@link #billDate} defines the bill date of the statement
	* @HasGetter
	* @HasSetter
	*/
	@Column(name="bill_date",nullable=false)
	private LocalDate billDate;
	
	/**
	* This a local variable: {@link #dueDate} defines the dueDate of the statement
	* @HasGetter
	* @HasSetter
	*/
	@Column(name="due_date",nullable=false)
	private LocalDate dueDate;
	
	/**
	* This a local variable: {@link #creditCard} defines the credit card to which the statement belongs to
	* @HasGetter
	* @HasSetter
	*/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="card_number")
	private CreditCardEntity creditCard;
	
	/**
	* This a local variable: {@link #customerName} defines the name of the custmer to which the statement belongs to
	* @HasGetter
	* @HasSetter
	*/
	@Column(name="customer_name",nullable=false)
	private String customerName;
	
	/**
	 * Default Constructor
	 */
	public StatementEntity() {
		/*Default Constructor*/
	}
	
	/**
	 * @param statementId		the unique id for the statement
	 * @param dueAmount			the due amount of the statement
	 * @param billDate			the bill date of the statement
	 * @param dueDate			the due date of the statement
	 * @param creditCard		the credit card to which the statement belongs to 
	 * @param customerName		the name of the customer to which the statement belongs to
	 */
	public StatementEntity(Long statementId, Double billAmount, Double dueAmount, LocalDate billDate, LocalDate dueDate,CreditCardEntity creditCard) {
		super();
		this.statementId=statementId;
		this.billAmount = billAmount;
		this.dueAmount=dueAmount;
		this.billDate = billDate;
		this.dueDate = dueDate;
		this.creditCard=creditCard;
		this.customerName=creditCard.getCustomer().getName();
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
	 * @return creditCard as CreditCardEntity
	 */
	public CreditCardEntity getCreditCard() {
		return creditCard;
	}

	/**
	 * 
	 * @param creditCard which is credit card to which the statement belongs to
	 */
	public void setCreditCard(CreditCardEntity creditCard) {
		this.creditCard = creditCard;
	}

	/**
	 * 
	 * @return customerName as string
	 */
	public String getCustomerName() {
		return creditCard.getCustomer().getName();
	}

	/**
	 * 
	 * @param customerName to set name of the customer for which the statement belongs to
	 */
	public void setCustomerName(String customerName) {
		if(creditCard.getCustomer().getName().equals(customerName)) {
			this.customerName=customerName;
		}else {
			this.customerName = creditCard.getCustomer().getName();
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((billAmount == null) ? 0 : billAmount.hashCode());
		result = prime * result + ((billDate == null) ? 0 : billDate.hashCode());
		result = prime * result + ((creditCard == null) ? 0 : creditCard.hashCode());
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
		StatementEntity other = (StatementEntity) obj;
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
		if (creditCard == null) {
			if (other.creditCard != null)
				return false;
		} else if (!creditCard.equals(other.creditCard))
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
		return String.format("Statement [statementId=%s, dueAmount=%s, billDate=%s, dueDate=%s,creditCard =%s customer Name=%s]",
				statementId, dueAmount, billDate, dueDate,creditCard,creditCard.getCustomer().getName());
	}
	
	
}
