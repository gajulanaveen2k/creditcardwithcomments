package com.cg.creditcardpayment.entity;
/**
* <h1>AccountEntity</h1>
* The Account Entity program implements an application such that
* the data of the accounts is sent to the database
* <p>
* 
*
* @author  D Himavanth
* @version 1.0
* @since   2021-03-31 
*/
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.cg.creditcardpayment.model.AccountType;

@Entity
@Table(name="accounts")
public class AccountEntity {
	/**
	 * This a local variable: {@link #accountNumber} defines the unique number for Bank account
	 * @HasGetter
	 * @HasSetter
	 */
	@Id
	@Column(name="account_number")
	private String accountNumber;
	
	/**
	 * This a local variable: {@link #accountName} defines the name for Bank account 
	 * @HasGetter
	 * @HasSetter
	 */
	@Column(name="account_Name", nullable=false)
	private String accountName;
	
	/**
	 * This a local variable: {@link #accountBalance} defines the balance amount remaining in the Bank account
	 * @HasGetter
	 * @HasSetter
	 */
	@Column(name="balance", nullable=false)
	private Double accountBalance;
	
	/**
	 * This a local variable: {@link #accountType} defines the type of the account like SAVINGS, CURRENT, JOINT
	 * @HasGetter
	 * @HasSetter
	 */
	@Column(name="account_Type", nullable=false)
	@Enumerated(EnumType.STRING)
	private AccountType accountType;
	
	/**
	 * This a local variable: {@link #customers} defines the set of customer belongs to paticular account
	 */
	@ManyToMany(fetch=FetchType.LAZY,cascade= CascadeType.ALL)
	@JoinTable(name="customer_account",
	joinColumns=@JoinColumn(name="account_number"),
	inverseJoinColumns=@JoinColumn(name="user_id"))
	private Set<CustomerEntity> customers;
	
	/**
	 * Default Constructor
	 */
	public AccountEntity() {
		/* Default Constructor */
	}
	
	/**
	 * Parameterized Constructor with parameters
	 * @param accountNumber   	     	the unique Number for account
	 * @param accountName				the name of the account
	 * @param accountBalance			the amount balance in the account
	 * @param accountType				the type of the account like savings, current
	 */
	public AccountEntity(String accountNumber, String accountName, Double accountBalance, AccountType accountType) {
		super();
		this.accountNumber = accountNumber;
		this.accountName = accountName;
		this.accountBalance = accountBalance;
		this.accountType = accountType;
	}
	
	/**
	 * 
	 * @return customers as Set<CustomerEntity>
	 */
	public Set<CustomerEntity> getCustomers() {
		return customers;
	}
	/**
	 * @param customers where it contains all the customers of the account
	 */
	public void setCustomers(Set<CustomerEntity> customers) {
		this.customers = customers;
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
		result = prime * result + ((customers == null) ? 0 : customers.hashCode());
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
		AccountEntity other = (AccountEntity) obj;
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
		if (accountType != other.accountType)
			return false;
		if (customers == null) {
			if (other.customers != null)
				return false;
		} else if (!customers.equals(other.customers))
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
				"AccountEntity [accountNumber=%s, accountName=%s, accountBalance=%s, accountType=%s, customers=%s]",
				accountNumber, accountName, accountBalance, accountType, customers);
	}
	
}
