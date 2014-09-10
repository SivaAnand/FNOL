package com.virtusa.mwallet.valueobjects;

import java.util.Date;

public class TempTransaction {
	
	private String payerEWalletID,payerMMID,receiverEWalletID,receiverMMID;
	private long tempTransactionID,IMEI;	
	private int approvalCode;
	float amount;
	Date date;
	
	public long getTempTransactionID() {
		return tempTransactionID;
	}

	public void setTempTransactionID(long tempTransactionID) {
		this.tempTransactionID = tempTransactionID;
	}

	public TempTransaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getTempTransactionId() {
		return tempTransactionID;
	}

	public void setTempTransactionId(long tempTransactionID) {
		this.tempTransactionID = tempTransactionID;
	}
	
	
	
	

	public TempTransaction(String payerEWalletID, String payerMMID,
			String receiverEWalletID, String receiverMMID, long iMEI,
			int approvalCode, float amount, Date date) {
		super();
		this.payerEWalletID = payerEWalletID;
		this.payerMMID = payerMMID;
		this.receiverEWalletID = receiverEWalletID;
		this.receiverMMID = receiverMMID;
		IMEI = iMEI;
		this.approvalCode = approvalCode;
		this.amount = amount;
		this.date = date;
	}

	public String getPayerEWalletID() {
		return payerEWalletID;
	}
	
	public void setPayerEWalletID(String payerEWalletID) {
		this.payerEWalletID = payerEWalletID;
	}
	public String getPayerMMID() {
		return payerMMID;
	}
	public void setPayerMMID(String payerMMID) {
		this.payerMMID = payerMMID;
	}
	public String getReceiverEWalletID() {
		return receiverEWalletID;
	}
	public void setReceiverEWalletID(String receiverEWalletID) {
		this.receiverEWalletID = receiverEWalletID;
	}
	public String getReceiverMMID() {
		return receiverMMID;
	}
	public void setReceiverMMID(String receiverMMID) {
		this.receiverMMID = receiverMMID;
	}
	public long getIMEI() {
		return IMEI;
	}
	public void setIMEI(long IMEI) {
		this.IMEI = IMEI;
	}
	public int getApprovalCode() {
		return approvalCode;
	}
	public void setApprovalCode(int approvalCode) {
		this.approvalCode = approvalCode;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	
}
