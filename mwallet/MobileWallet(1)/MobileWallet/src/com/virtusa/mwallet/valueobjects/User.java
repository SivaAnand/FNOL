package com.virtusa.mwallet.valueobjects;

public class User {
	private String userId;
	private String userName;
	private String pin;
	private String mobileNo;
	private String MMID;

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getMMID() {
		return MMID;
	}

	public void setMMID(String mMID) {
		MMID = mMID;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
