package com.cg.creditcardpayment.model;
/**
* <h1>Account Model</h1>
* The Account Model program implements an application such that
* the user can provide Account details and send the details to entity with help of Parser
* and perform some Validations.
* <p>
* 
*
* @author  D Himavanth
* @version 1.0
* @since   2021-03-31 
*/


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AccountModel {
	/**
	 * This a local variable: {@link #accountNumber} defines the unique number for Bank account and that should not be null and empty
	 * @HasGetter
	 * @HasSetter
	 */
	@NotNull(message="Account name cannot be null")	
	@NotBlank(message="Account name cannot be blank")
	private String accountNumber;
	
	/**
	 * This a local variable: {@link #accountName} defines the name for Bank account and that should not be null and empty
	 * @HasGetter
	 * @HasSetter
	 */
	@NotNull(message="Account name cannot be null")	
	@NotBlank(message="Account name cannot be blank")
	private String accountName;
	
	/**
	 * This a local variable: {@link #accountBalance} defines the balance amount remaining in the Bank account and that should not be null
	 * @HasGetter
	 * @HasSetter
	 */
	@NotNull(message="balance cannot be null")
	private Double accountBalance;
	
	/**
	 * This a local variable: {@link #accountType} defines the type of the account like SAVINGS, CURRENT, JOINT and that should not be null 
	 * @HasGetter
	 * @HasSetter
	 */
	@NotNull(message="account type cannot be null")	
	private AccountType accountType;
	
	/**
	 * Default Constructor
	 */
	public AccountModel() {
		/* Default Constructor */
	}
	
	/**
	 * Parameterized Constructor with parameters
	 * @param accountNumber   	     	the unique Number for account
	 * @param accountName				the name of the account
	 * @param accountBalance			the amount balance in the account
	 * @param accountType				the type of the account like savings, current
	 */
	public AccountModel(String accountNumber, String accountName, Double accountBalance, AccountType accountType) {
		super();
		this.accountNumber = accountNumber;
		this.accountName = accountName;
		this.accountBalance = accountBalance;
		this.accountType = accountType;
	}
	
	/**
	 * @return  accountNumber in String
	 */
	public String getAccountNumber() {
		return accountNumber;
	}
	
	/**
	 * @param accountNumber to set which is unique and string type
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	/**
	 * 
	 * @return accountName as string
	 */
	public String getAccountName() {
		return accountName;
	}
	
	/**
	 * 
	 * @param accountName to set which is in string type
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	/**
	 * 
	 * @return accountBalance in Double type
	 */
	public Double getAccountBalance() {
		return accountBalance;
	}

	/**
	 * 
	 * @param accountBalance to set for the Account which is Double
	 */
	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
	}

	/**
	 * 
	 * @return accountType in enum object
	 */
	public AccountType getAccountType() {
		return accountType;
	}

	/**
	 * 
	 * @param accountType to set for the account which is object
	 */
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountBalance == null) ? 0 : accountBalance.hashCode());
		result = prime * result + ((accountName == null) ? 0 : accountName.hashCode());
		result = prime * result + ((accountNumber == null) ? 0 : accountNumber.hashCode());
		result = prime * result + ((accountType == null) ? 0 : accountType.hashCode());
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
		AccountModel other = (AccountModel) obj;
		if (accountBalance == null) {
			if (other.accountBalance != null)
				return false;
		} else if (!accountBalance.equals(other.accountBalance))
			return false;
		if (accountName == null) {
			if (other.accountName != null)
				return false;
		} else if (!accountName.equals(other.accountName))
			return false;
		if (accountNumber == null) {
			if (other.accountNumber != null)
				return false;
		} else if (!accountNumber.equals(other.accountNumber))
			return false;
		if (accountType == null) {
			if (other.accountType != null)
				return false;
		} else if (!accountType.equals(other.accountType))
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
		return String.format("AccountModel [accountNumber=%s, accountName=%s, accountBalance=%s, accountType=%s]",
				accountNumber, accountName, accountBalance, accountType);
	}

	
}
