package com.cg.creditcardpayment.model;

/**
* <h1>Credit Card Model</h1>
* The Credit Card Model program implements an application such that
* the Customer can Add his Credit Cards to the application and perform some validation and send the details to entity.
* 
* <p>
* 
*
* @author  Abhilash Reddy Gone
* @version 1.0
* @since   2021-03-31 
*/

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class CreditCardModel {
	/**
	 * This a local variable: {@link #cardNumber} defines the unique Number for Credit Card
	 * @HasGetter
	 * @HasSetter
	 */
	@NotNull(message="card number cannot be null")	
	@NotBlank(message="card number cannot be blank")
	@Pattern(regexp = "[0-9]{16}", message="Card Number should be valid number of length 16")
	private String cardNumber;
	
	/**
	 * This a local variable: {@link #cardName} defines the name of the Credit Card
	 * @HasGetter
	 * @HasSetter 
	 */
	@NotNull(message="card name cannot be null")
	private CardName cardName;
	
	/**
	 * This a local variable: {@link #cardType} defines the type of the Credit Card
	 * @HasGetter
	 * @HasSetter 
	 */
	@NotNull(message="card type cannot be null")
	private CardType cardType;
	
	/**
	 * This a local variable: {@link #expiryDate} defines the expire date of the credit card which should not be null
	 * @HasGetter
	 * @HasSetter
	 */	
	@NotNull(message="card expiry date cannot be null")	
	@Future(message="Expiry date cannot be in future")
    private LocalDate expiryDate;
	
	/**
	 *This a local variable: {@link #bankName} defines the bank of the credit card which should not be null
	 * @HasGetter
	 * @HasSetter
	 */
	@NotNull(message="Bank name cannot be null")	
	@NotBlank(message="Bank name cannot be blank")
	private String bankName;
	
	/**
	 * This a local variable: {@link #cvv} defines the cvv present on the credit card and it should not be null
	 * @HasGetter
	 * @HasSetter
	 */
	@NotNull(message="cvv cannot be null")
	@Min(value=100, message="CVV should have length of 3")
	@Max(value=999,message="CVV should have length of 3")
    private Integer cvv;
	
	/**
	 * This a local variable: {@link #cardLimit} defines the maximum limit that can be used by the user
	 * @HasGetter
	 * @HasSetter
	 */
	@NotNull(message="limit cannot be null")
    private Double cardLimit;
	
	/**
	 * This a local variable: {@link #usedLimit} defines the limit used by the customer from the credit card 
	 * @HasGetter
	 * @HasSetter
	 */
	@NotNull(message="Used limit cannot be null")
    private Double usedLimit;
	
	/**
	 * This a local variable: {@link #customerId} defines the customer id to which the credit card belongs to.
	 * @HasGetter
	 * @HasSetter
	 */
	@JsonProperty(access = Access.WRITE_ONLY)
    private String customerId;
	
	/**
	 * Default Constructor
	 */
	public CreditCardModel() {
		/* Default Constructor */
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
	 * @param customerId		the customer id to which the card belongs to
	 */
	public CreditCardModel(
			@NotNull(message = "card number cannot be null") @NotBlank(message = "card number cannot be blank") @Pattern(regexp = "[0-9]{16}") String cardNumber,
			@NotNull(message = "card name cannot be null") @NotBlank(message = "card name cannot be blank") CardName cardName,
			@NotNull(message = "card type cannot be null") @NotBlank(message = "card type cannot be blank") CardType cardType,
			@NotNull(message = "card expiry date cannot be null") @Future(message = "Expiry date cannot be in future") LocalDate expiryDate,
			@NotNull(message = "Bank name cannot be null") @NotBlank(message = "Bank name cannot be blank") String bankName,
			@NotNull(message = "cvv cannot be null") @NotBlank(message = "cvv cannot be blank") @Pattern(regexp = "[0-9]{3}") Integer cvv,
			@NotNull(message = "limit cannot be null") @NotBlank(message = "limit cannot be blank") Double cardLimit,
			@NotNull(message = "limit cannot be null") @NotBlank(message = "limit cannot be blank") Double usedLimit,
			@NotNull(message = "customerId cannot be null") @NotBlank(message = "customerId cannot be blank") String customerId) {
		super();
		this.cardNumber = cardNumber;
		this.cardName = cardName;
		this.cardType = cardType;
		this.expiryDate = expiryDate;
		this.bankName = bankName;
		this.cvv = cvv;
		this.cardLimit = cardLimit;
		this.usedLimit = usedLimit;
		this.customerId = customerId;
	}
	

	/**
	 * @return cardLimit in Double
	 */
	public Double getCardLimit() {
		return cardLimit;
	}

	/**
	 * 
	 * @param cardLimit to set for the credit card which is Double type
	 */
	public void setCardLimit(Double cardLimit) {
		this.cardLimit = cardLimit;
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
	 * @return customerId as String
	 */
	public String getCustomerId() {
		return customerId;
	}

	/**
	 * 
	 * @param customerId to set for the credit card which is String
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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
	 * @return cardType as object of CardType
	 */
	public CardType getCardType() {
		return cardType;
	}

	/**
	 * 
	 * @param cardType to set for Credit Card which is CardType
	 */
	public void setCardType(CardType cardType) {
		this.cardType = cardType;
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
		CreditCardModel other = (CreditCardModel) obj;
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
		if (cvv == null) {
			if (other.cvv != null)
				return false;
		} else if (!cvv.equals(other.cvv))
			return false;
		if (expiryDate == null) {
			if (other.expiryDate != null)
				return false;
		} else if (!expiryDate.equals(other.expiryDate))
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
				"CreditCardModel [cardNumber=%s, cardName=%s, cardType=%s, expiryDate=%s, bankName=%s, cvv=%s]",
				cardNumber, cardName, cardType, expiryDate, bankName, cvv);
	}

	
    
}
