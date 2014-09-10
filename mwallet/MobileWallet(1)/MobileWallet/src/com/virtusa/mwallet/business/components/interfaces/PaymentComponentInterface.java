package com.virtusa.mwallet.business.components.interfaces;

import com.virtusa.mwallet.valueobjects.TransactionStatus;
import com.virtusa.mwallet.valueobjects.P2MSecretCode;
import com.virtusa.mwallet.valueobjects.Status;

public interface PaymentComponentInterface {

	TransactionStatus payPersonToPersonMMID(String payerEWalletId, String payerMmid,
			String receiverEWallet, String receiverMmidId, String iPin,
			float amount);	
	public TransactionStatus checkBalance(String walletID, String MMID, String ipin);

	Status checkQRCodeValidity(String walletID, String MMID, String ipin);


	TransactionStatus personToMerchantQRCodePayment(long TempTransactionID,
			long IMEI, int approvalCode, String walletID, String MMID);

	P2MSecretCode personToMerchantQRCode(long IMEI, String payerEWalletID,
			String payerMMID, String receiverEWalletID, String receiverMMID,
			String ipin, float amount);
	
	
	
}
