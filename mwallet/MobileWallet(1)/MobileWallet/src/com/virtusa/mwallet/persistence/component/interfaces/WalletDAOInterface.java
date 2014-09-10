package com.virtusa.mwallet.persistence.component.interfaces;

import com.virtusa.mwallet.valueobjects.ETransaction;
import com.virtusa.mwallet.valueobjects.TempTransaction;
import com.virtusa.mwallet.valueobjects.Wallet;

public interface WalletDAOInterface {


	public boolean createWallet(Wallet wallet) throws Exception;
	public Wallet readWallet(String walletID, String MMID) throws Exception ;
	public boolean updateWallet(Wallet wallet) throws Exception;
	public boolean deleteWallet(String walletId, String MMID) throws Exception;
	public boolean updateWallets(Wallet wallet1, Wallet wallet2) throws Exception;
	boolean updateWallet(Wallet wallet, ETransaction eTransaction) throws Exception;
	boolean updateWallet(Wallet walletPayee, Wallet walletRecepient,
			ETransaction eTransaction) throws Exception;
	public void createTempTransaction(TempTransaction transaction);
	Wallet readWallet(String MMID) throws Exception;

	
}
