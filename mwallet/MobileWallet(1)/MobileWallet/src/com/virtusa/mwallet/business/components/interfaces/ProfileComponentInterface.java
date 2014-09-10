package com.virtusa.mwallet.business.components.interfaces;

import com.virtusa.mwallet.valueobjects.Status;
import com.virtusa.mwallet.valueobjects.User;
import com.virtusa.mwallet.valueobjects.UserPIN;
import com.virtusa.mwallet.valueobjects.Wallet;

public interface ProfileComponentInterface {

	public Status login(String userId, String pin);

	public Status register(User user);

	public Status changePin(UserPIN userPin);
	public Wallet getWalletbyUserID(String userID);
	

}
