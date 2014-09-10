package com.virtusa.mwallet.business.components.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import com.virtusa.mwallet.business.components.interfaces.ToppingComponentInterface;
import com.virtusa.mwallet.business.util.DAOFactoryUtil;
import com.virtusa.mwallet.constants.StatusConstants;
import com.virtusa.mwallet.constants.TransactionConstants;
import com.virtusa.mwallet.persistence.component.interfaces.WalletDAOInterface;
import com.virtusa.mwallet.persistence.factories.WalletDAOFactory;
import com.virtusa.mwallet.valueobjects.TransactionStatus;
import com.virtusa.mwallet.valueobjects.ETransaction;
import com.virtusa.mwallet.valueobjects.TransactionDetails;
import com.virtusa.mwallet.valueobjects.Wallet;

public class ToppingComponent implements ToppingComponentInterface {
	public WalletDAOFactory walletDAOFactory = (WalletDAOFactory) DAOFactoryUtil
			.getFactory("walletDAOFactory");
	private WalletDAOInterface walletDAOComponent = walletDAOFactory
			.getWalletDAOComponent();
	ResourceBundle rb = ResourceBundle.getBundle("status");

	@Override
	public TransactionStatus topUp(String walletID, String MMID, String ipin, float amount)

	{
		
		TransactionStatus transactionStatus = new TransactionStatus();
		try
		{
		Wallet wallet = walletDAOComponent.readWallet(walletID, MMID);

		if (wallet != null) {
			if (wallet.getiPIN().equals(ipin)) {
				wallet.setBalance(wallet.getBalance() + amount);
				String description = "Amount " + amount
						+ "is credited to your wallet from Bank with MMID"
						+ MMID;
				TransactionDetails transactionDetails = new TransactionDetails(
						MMID, walletID, TransactionConstants.TOPUP, description);

				Set<TransactionDetails> transDetails =  new HashSet<TransactionDetails>();
				transDetails.add(transactionDetails);
				ETransaction transaction = new ETransaction(amount, new Date(),
						transDetails);

				if (walletDAOComponent.updateWallet(wallet, transaction)) {
					transactionStatus.setStatusCode(StatusConstants.TOPUP_SUCCESS_CODE);
					transactionStatus.setStatusMessage(rb.getString(Integer
							.toString(StatusConstants.TOPUP_SUCCESS_CODE)));
					transactionStatus.setMMID(MMID);
					transactionStatus.setWalletID(walletID);
					Wallet walletUpdated = walletDAOComponent.readWallet(
							walletID, MMID);
					transactionStatus.setBalance(walletUpdated.getBalance());
					
					// Here we need to get Trans Id from DB
					transactionStatus.setTransactionID(12345);
				} else {
					transactionStatus.setStatusCode(StatusConstants.TOPUP_FAILURE_CODE);
					transactionStatus.setStatusMessage(rb.getString(Integer
							.toString(StatusConstants.TOPUP_FAILURE_CODE)));
				}
			} else {
				transactionStatus.setStatusCode(StatusConstants.INVALID_IPIN_CODE);
				transactionStatus.setStatusMessage(rb.getString(Integer
						.toString(StatusConstants.INVALID_IPIN_CODE)));
			}
		} else {
			transactionStatus.setStatusCode(StatusConstants.CHECK_CREDENTIALS_CODE);
			transactionStatus.setStatusMessage(rb.getString(Integer
					.toString(StatusConstants.CHECK_CREDENTIALS_CODE)));

		}
		}
		catch (Exception e) {
			transactionStatus.setStatusCode(StatusConstants.EXCEPTION_STATUS_CODE);
			transactionStatus.setStatusMessage(rb.getString(Integer
					.toString(StatusConstants.EXCEPTION_STATUS_CODE)));
		}
		return transactionStatus;
	}

	@Override
	public TransactionStatus topDown(String walletID, String MMID, String ipin,
			float amount) {
		TransactionStatus transactionStatus = new TransactionStatus();

		try {
			Wallet wallet = walletDAOComponent.readWallet(walletID, MMID);
			if (wallet != null) {
				if (wallet.getiPIN().equals(ipin)) {
					if (wallet.getBalance() > amount) {
						wallet.setBalance(wallet.getBalance() - amount);
						String description = "Amount:"
								+ amount
								+ "is debited from your wallet to Bank with MMID:"
								+ MMID;
						TransactionDetails transactionDetails = new TransactionDetails(
								MMID, walletID, TransactionConstants.TOPDOWN,
								description);

						Set<TransactionDetails> transDetails = new HashSet<TransactionDetails>();
						transDetails.add(transactionDetails);

						ETransaction transaction = new ETransaction(amount,
								new Date(), transDetails);

						if (walletDAOComponent
								.updateWallet(wallet, transaction)) {
							transactionStatus.setStatusCode(StatusConstants.TOPDOWN_SUCCESS_CODE);
							transactionStatus.setStatusMessage(rb.getString(Integer
									.toString(StatusConstants.TOPDOWN_SUCCESS_CODE)));
							transactionStatus.setMMID(MMID);
							transactionStatus.setWalletID(walletID);
							Wallet walletUpdated = walletDAOComponent
									.readWallet(walletID, MMID);
							transactionStatus.setBalance(walletUpdated.getBalance());
							transactionStatus.setTransactionID(1245);
						} else {
							transactionStatus.setStatusCode(StatusConstants.TOPDOWN_FAILURE_CODE);
							transactionStatus.setStatusMessage(rb.getString(Integer
									.toString(StatusConstants.TOPDOWN_FAILURE_CODE)));
						}
					} else {
						transactionStatus.setStatusCode(StatusConstants.INSUFFICIENT_BALANCE_CODE);
						transactionStatus.setStatusMessage(rb.getString(Integer
								.toString(StatusConstants.INSUFFICIENT_BALANCE_CODE)));
					}

				} else {
					transactionStatus.setStatusCode(StatusConstants.INVALID_IPIN_CODE);
					transactionStatus.setStatusMessage(rb.getString(Integer
							.toString(StatusConstants.INVALID_IPIN_CODE)));
				}
			} else {
				transactionStatus.setStatusCode(StatusConstants.CHECK_CREDENTIALS_CODE);
				transactionStatus.setStatusMessage(rb.getString(Integer
						.toString(StatusConstants.CHECK_CREDENTIALS_CODE)));

			}
		} catch (Exception e) {
			transactionStatus.setStatusCode(StatusConstants.EXCEPTION_STATUS_CODE);
			transactionStatus.setStatusMessage(rb.getString(Integer
					.toString(StatusConstants.EXCEPTION_STATUS_CODE)));
		}
		return transactionStatus;

	}
}
