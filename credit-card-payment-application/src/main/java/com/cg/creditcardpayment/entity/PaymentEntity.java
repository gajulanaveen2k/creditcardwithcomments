package com.cg.creditcardpayment.entity;

/**
* <h1>PaymentEntity</h1>
* The Payment Entity program implements an application such that
* the data of the payment is sent to the database
* <p>
* 
*
* @author  P Pranava Charitra
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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cg.creditcardpayment.model.PaymentMethod;

@Entity
@Table(name="payments")
public class PaymentEntity {
	/**
	 * This a local variable: {@link #paymentId} defines the unique Id for each Payment
	 * @HasGetter
	 * @HasSetter
	 */
	@Id
	@Column(name="payment_id")
	private Long paymentId;
	
	/**
	 * This a local variable: {@link #method} defines the payment method to make payment
	 * @HasGetter
	 * @HasSetter
	 */
	@Enumerated(EnumType.STRING)
	@Column(name="payment_method",nullable=false)
	private PaymentMethod method;

	/**
	 * This a local variable: {@link #paidDate} defines the payment date
	 * @HasGetter
	 * @HasSetter
	 */
	@Column(name="paid_date")
	private LocalDate paidDate;

	/**
	 * This a local variable: {@link #paidTime} defines the payment time
	 * @HasGetter
	 * @HasSetter
	 */
	@Column(name="paid_time")
	private LocalTime paidTime;
	/**
	 * This a local variable: {@link #amount} defines the unique Id for Customer
	 * @HasGetter
	 * @HasSetter
	 */
	@Column(name="amount",nullable=false)
	private Double amount;
	
	/**
	 * This a local variable: {@link #card} defines the card to which the payment has to be made
	 * @HasGetter
	 * @HasSetter
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="card_number")
	private CreditCardEntity card;

	/**
	 * Default Constructor
	 */
	public PaymentEntity() {
		/*Default Constructor*/
	}

	/**
	 * Parameterized Constructor with parameters
	 * @param paymentId  		the unique id for Payment
	 * @param method			the method for payment
	 * @param amount			the amount paid
	 * @param cardNumber		the card for which the payment is made
	 */
	public PaymentEntity(Long paymentId, PaymentMethod method,LocalDate paidDate,LocalTime paidTime, Double amount,CreditCardEntity card) {
		super();
		this.paymentId=paymentId;
		this.method = method;
		this.paidDate=paidDate;
		this.paidTime=paidTime;
		this.amount = amount;
		this.card = card;
	}

	/**
	 * 
	 * @return paidDate in LocalDate
	 */
	public LocalDate getPaidDate() {
		return paidDate;
	}

	/**
	 * 
	 * @param paidDate to set the date of the payment made
	 */
	public void setPaidDate(LocalDate paidDate) {
		this.paidDate = paidDate;
	}

	/**
	 * 
	 * @return paidTime in LocalTime
	 */
	public LocalTime getPaidTime() {
		return paidTime;
	}

	/**
	 * 
	 * @param paidTime to set the time of the payment made
	 */
	public void setPaidTime(LocalTime paidTime) {
		this.paidTime = paidTime;
	}

	/**
	 * 
	 * @return paymentId as Long
	 */
	public Long getPaymentId() {
		return paymentId;
	}


	/**
	 * 
	 * @return method as PaymentMethod
	 */
	public PaymentMethod getMethod() {
		return method;
	}

	/**
	 * 
	 * @param method set set the payment method to payment
	 */
	public void setMethod(PaymentMethod method) {
		this.method = method;
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
	 * @param amount to set the amount for payment done
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	/**
	 * 
	 * @return paymentId as Long
	 */
	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	/**
	 * 
	 * @return card as CreditCardEntity
	 */
	public CreditCardEntity getCard() {
		return card;
	}

	/**
	 * 
	 * @param card where the payment is made
	 */
	public void setCard(CreditCardEntity card) {
		this.card = card;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((card == null) ? 0 : card.hashCode());
		result = prime * result + ((method == null) ? 0 : method.hashCode());
		result = prime * result + ((paymentId == null) ? 0 : paymentId.hashCode());
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
		PaymentEntity other = (PaymentEntity) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (card == null) {
			if (other.card != null)
				return false;
		} else if (!card.equals(other.card))
			return false;
		if (method == null) {
			if (other.method != null)
				return false;
		} else if (!method.equals(other.method))
			return false;
		if (paymentId == null) {
			if (other.paymentId != null)
				return false;
		} else if (!paymentId.equals(other.paymentId))
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
		return String.format("PaymentEntity [paymentId=%s, method=%s, amount=%s, card=%s]", paymentId, method, amount,
				card.getCardNumber());
	}
	
	
	
	
}
