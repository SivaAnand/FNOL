package com.virtusa.mwallet.persistence.factories;

import com.virtusa.mwallet.persistence.component.interfaces.WalletDAOInterface;

public class WalletDAOFactory {

	public WalletDAOInterface walletDAOComponent;

	public WalletDAOInterface getWalletDAOComponent() {
		return walletDAOComponent;
	}

	public void setWalletDAOComponent(WalletDAOInterface walletDAOComponent) {
		this.walletDAOComponent = walletDAOComponent;
	}

}
