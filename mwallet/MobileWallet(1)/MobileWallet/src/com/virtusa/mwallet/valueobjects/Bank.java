/**
 * 
 */
package com.virtusa.mwallet.valueobjects;

/**
 * @author nishanthk
 *
 */
public class Bank {
	private String MMID;
	private String bankName;
	private float balance;
	/**
	 * @return the mMID
	 */
	public String getMMID() {
		return MMID;
	}
	/**
	 * @param mMID the mMID to set
	 */
	public void setMMID(String mMID) {
		MMID = mMID;
	}
	/**
	 * @return the bankName
	 */
	public String getBankName() {
		return bankName;
	}
	/**
	 * @param bankName the bankName to set
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	/**
	 * @return the balance
	 */
	public float getBalance() {
		return balance;
	}
	/**
	 * @param balance the balance to set
	 */
	public void setBalance(float balance) {
		this.balance = balance;
	}
}
