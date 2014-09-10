package com.virtusa.mwallet.valueobjects;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class Statement {
	private long TransactionID;
	private Date date;
	private float amount;
	private String MMID, walletID,type;
	public long getTransactionID() {
		return TransactionID;
	}
	public void setTransactionID(long transactionID) {
		TransactionID = transactionID;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public String getMMID() {
		return MMID;
	}
	public void setMMID(String mMID) {
		MMID = mMID;
	}
	public String getWalletID() {
		return walletID;
	}
	public void setWalletID(String walletID) {
		this.walletID = walletID;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	

}
