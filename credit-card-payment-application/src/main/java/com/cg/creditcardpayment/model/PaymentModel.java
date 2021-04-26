package com.cg.creditcardpayment.model;
/**
* <h1>Payment Model</h1>
* The Payment Model program implements an application such that
* the user can prform payments and send the details to entity with help of Parser
* and perform some Validations.
* <p>
* 
*
* @author  P Pranava Charitra
* @version 1.0
* @since   2021-03-31 
*/
import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class PaymentModel {
	/**
	 * This a local variable: {@link #paymentId} defines the unique Id for each Payment
	 * @HasGetter
	 * @HasSetter
	 */
	private Long paymentId;
	
	/**
	 * This a local variable: {@link #method} defines the payment method to make payment
	 * @HasGetter
	 * @HasSetter
	 */
	@NotNull(message="payment method cannot be null")
	private PaymentMethod method;
	
	/**
	 * This a local variable: {@link #amount} defines the unique Id for Customer
	 * @HasGetter
	 * @HasSetter
	 */
	@NotNull(message="amount cannot be null")
	@Min(value=1,message="ammount should not be 0")
	private Double amount;
	
	/**
	 * This a local variable: {@link #cardNumber} defines the unique number for card number
	 * @HasGetter
	 * @HasSetter
	 */
	@NotNull(message="credit card cannot be null")	
	@NotBlank(message="credit card cannot be blank")
	@Pattern(regexp = "[4-6][0-9]{15}", message="Card Number should be valid number of length 16")	
	private String cardNumber;
	
	/**
	 * This a local variable: {@link #paidDate} defines the payment date
	 * @HasGetter
	 * @HasSetter
	 */
	private LocalDate paidDate;
	
	/**
	 * This a local variable: {@link #paidTime} defines the payment time
	 * @HasGetter
	 * @HasSetter
	 */
	private LocalTime paidTime;
	
	
	
	/**
	 * Default Constructor
	 */
	public PaymentModel() {
		/*Default Constructor*/
	}

	

	/**
	 * Parameterized Constructor with parameters
	 * @param paymentId  		the unique id for Payment
	 * @param method			the method for payment
	 * @param amount			the amount paid
	 * @param cardNumber		the card number for which the payment is made
	 */
	public PaymentModel(Long paymentId,
			@NotNull(message = "payment method cannot be null") @NotBlank(message = "payment method cannot be blank") PaymentMethod method,
			@NotNull(message = "amount cannot be null") @NotBlank(message = "amount cannot be blank") Double amount,
			@NotNull(message="paid date cannot be null") @NotBlank(message="paid date cannot be blank") LocalDate paidDate,
			@NotNull(message="paid time cannot be null") @NotBlank(message="paid time cannot be blank") LocalTime paidTime,
			@NotNull(message = "credit card cannot be null") @NotBlank(message = "credit card cannot be blank") String cardNumber) {
		super();
		this.paymentId = paymentId;
		this.method = method;
		this.paidDate=paidDate;
		this.paidTime=paidTime;
		this.amount = amount;
		this.cardNumber = cardNumber;
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
	 * @param paymentId to set the paymentId of the payment
	 */
	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
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
	 * @return  cardNumber as String
	 */
	public String getCardNumber() {
		return cardNumber;
	}

	/**
	 * 
	 * @param cardNumber to set the card number for which the payment has done
	 */
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
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
		PaymentModel other = (PaymentModel) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
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
		return String.format("PaymentModel [paymentId=%s, method=%s, amount=%s, cardNumber=%s]", paymentId, method,
				amount, cardNumber);
	}
		
	

}
