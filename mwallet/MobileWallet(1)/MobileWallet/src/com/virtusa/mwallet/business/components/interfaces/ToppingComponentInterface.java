package com.virtusa.mwallet.business.components.interfaces;

import com.virtusa.mwallet.valueobjects.TransactionStatus;

public interface ToppingComponentInterface {

	TransactionStatus topUp(String walletID, String MMID, String ipin, float amount);

	TransactionStatus topDown(String walletID, String MMID, String ipin, float amount);
}
