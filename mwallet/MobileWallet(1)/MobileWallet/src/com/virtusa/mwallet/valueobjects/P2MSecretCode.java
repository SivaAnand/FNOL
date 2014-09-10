package com.virtusa.mwallet.valueobjects;

public class P2MSecretCode {

	private Long TempTransactionID;
	private Long IMEI;
	private int approvalCode;
	private String message;
	
	public Long getTempTransactionID() {
		return TempTransactionID;
	}
	public void setTempTransactionID(Long tempTransactionID) {
		TempTransactionID = tempTransactionID;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Long getIMEI() {
		return IMEI;
	}
	public void setIMEI(Long IMEI) {
		this.IMEI = IMEI;
	}
	public int getApprovalCode() {
		return approvalCode;
	}
	public void setApprovalCode(int approvalCode) {
		this.approvalCode = approvalCode;
	}
	
	
}
