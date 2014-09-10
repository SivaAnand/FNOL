package com.virtusa.mwallet.valueobjects;

// Generated Sep 3, 2009 8:47:06 PM by Hibernate Tools 3.2.4.GA

/**
 * This class contains student's TransactionDetails number
 * 			details.
 */
public class TransactionDetails implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long  transactionDetailsId;
	private String MMID;
	private String eWalletID;
	private String type;
	private String description;
	/**
	 * @return the transactionDetailsId
	 */
	public long getTransactionDetailsId() {
		return transactionDetailsId;
	}
	/**
	 * @param transactionDetailsId the transactionDetailsId to set
	 */
	public void setTransactionDetailsId(long transactionDetailsId) {
		this.transactionDetailsId = transactionDetailsId;
	}
	/**
	 * @return the mMID
	 */
	public String getMMID() {
		return MMID;
	}
	/**
	 * @param mMID
	 * @param eWalletID
	 * @param type
	 * @param description
	 */
	public TransactionDetails(String mMID, String eWalletID, String type,
			String description) {
		super();
		MMID = mMID;
		this.eWalletID = eWalletID;
		this.type = type;
		this.description = description;
	}
	/**
	 * @param mMID the mMID to set
	 */
	public void setMMID(String mMID) {
		MMID = mMID;
	}
	/**
	 * @return the eWalletID
	 */
	public String geteWalletID() {
		return eWalletID;
	}
	/**
	 * @param eWalletID the eWalletID to set
	 */
	public void seteWalletID(String eWalletID) {
		this.eWalletID = eWalletID;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
}