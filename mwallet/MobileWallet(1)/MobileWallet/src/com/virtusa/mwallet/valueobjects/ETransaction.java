package com.virtusa.mwallet.valueobjects;

// Generated Sep 3, 2009 8:47:06 PM by Hibernate Tools 3.2.4.GA

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * This class contains ETransaction details.
 */
public class ETransaction implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @param amount
	 * @param date
	 * @param transactionDetails
	 */
	public ETransaction(float amount, Date date,
			Set<TransactionDetails> transactionDetails) {
		super();
		this.amount = amount;
		this.date = date;
		this.transactionDetails = transactionDetails;
	}
	public ETransaction() {
		// TODO Auto-generated constructor stub
	}
	private long transactionId;
	private float amount;
    private Date date;
	private Set<TransactionDetails> transactionDetails = new HashSet<TransactionDetails>(0);
	/**
	 * @return the transactionId
	 */
	public long getTransactionId() {
		return transactionId;
	}
	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}
	/**
	 * @return the amount
	 */
	public float getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(float amount) {
		this.amount = amount;
	}
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * @return the transactionDetails
	 */
	public Set<TransactionDetails> getTransactionDetails() {
		return transactionDetails;
	}
	/**
	 * @param transactionDetails the transactionDetails to set
	 */
	public void setTransactionDetails(Set<TransactionDetails> transactionDetails) {
		this.transactionDetails = transactionDetails;
	}
	
}