/**
 * 
 */
package com.virtusa.mwallet.valueobjects;

/**
 * @author nishanthk
 * 
 */
public class Wallet {

	private WalletPK walletPK;
	private float balance;
	private String iPIN;
	private int rechargeFrequency;

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	/**
	 * @return the walletPK
	 */
	public WalletPK getWalletPK() {
		return walletPK;
	}

	/**
	 * @param walletPK
	 *            the walletPK to set
	 */
	public void setWalletPK(WalletPK walletPK) {
		this.walletPK = walletPK;
	}

	/**
	 * @return the iPIN
	 */
	public String getiPIN() {
		return iPIN;
	}

	/**
	 * @param iPIN
	 *            the iPIN to set
	 */
	public void setiPIN(String iPIN) {
		this.iPIN = iPIN;
	}

	/**
	 * @return the rechargeFrequency
	 */
	public int getRechargeFrequency() {
		return rechargeFrequency;
	}

	/**
	 * @param rechargeFrequency
	 *            the rechargeFrequency to set
	 */
	public void setRechargeFrequency(int rechargeFrequency) {
		this.rechargeFrequency = rechargeFrequency;
	}

}
