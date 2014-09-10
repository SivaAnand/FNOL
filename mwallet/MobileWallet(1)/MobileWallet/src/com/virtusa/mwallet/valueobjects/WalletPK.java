/**
 * 
 */
package com.virtusa.mwallet.valueobjects;

import java.io.Serializable;

/**
 * @author nishanthk
 * 
 */
public class WalletPK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @return the eWalletID
	 */
	public String geteWalletID() {
		return eWalletID;
	}

	/**
	 * @param eWalletID
	 *            the eWalletID to set
	 */
	public void seteWalletID(String eWalletID) {
		this.eWalletID = eWalletID;
	}

	/**
	 * @return the mMID
	 */
	public String getMMID() {
		return MMID;
	}

	/**
	 * @param mMID
	 *            the mMID to set
	 */
	public void setMMID(String mMID) {
		MMID = mMID;
	}

	private String eWalletID;
	private String MMID;
}
