package com.virtusa.mwallet.business.components.impl;

import java.util.*;

import com.virtusa.mwallet.business.components.interfaces.PaymentComponentInterface;
import com.virtusa.mwallet.business.util.DAOFactoryUtil;
import com.virtusa.mwallet.constants.StatusConstants;
import com.virtusa.mwallet.constants.TransactionConstants;
import com.virtusa.mwallet.persistence.component.interfaces.TempTransactionDAOInterface;
import com.virtusa.mwallet.persistence.component.interfaces.WalletDAOInterface;
import com.virtusa.mwallet.persistence.factories.TempTransactionDAOFactory;
import com.virtusa.mwallet.persistence.factories.WalletDAOFactory;
import com.virtusa.mwallet.valueobjects.TransactionStatus;
import com.virtusa.mwallet.valueobjects.ETransaction;
import com.virtusa.mwallet.valueobjects.P2MSecretCode;
import com.virtusa.mwallet.valueobjects.Status;
import com.virtusa.mwallet.valueobjects.TempTransaction;
import com.virtusa.mwallet.valueobjects.TransactionDetails;
import com.virtusa.mwallet.valueobjects.Wallet;

public class PaymentComponent implements PaymentComponentInterface {

	public WalletDAOFactory walletDAOFactory = (WalletDAOFactory) DAOFactoryUtil
			.getFactory("walletDAOFactory");
	private WalletDAOInterface walletDAOComponent = walletDAOFactory
			.getWalletDAOComponent();
	public TempTransactionDAOFactory tempTransactionDAOFactory = (TempTransactionDAOFactory) DAOFactoryUtil
	.getFactory("tempTransactionDAOFactory");
	private TempTransactionDAOInterface tempTransactionDAOComponent = tempTransactionDAOFactory
	.getTempTransactionDAOComponent();
	ResourceBundle rb = ResourceBundle.getBundle("status");

	@Override
	public TransactionStatus payPersonToPersonMMID(String payerEWalletId,
			String payerMmid, String receiverEWalletId, String receiverMmid,
			String iPin, float amount) {
		TransactionStatus transactionStatus = new TransactionStatus();

		Wallet payerEWallet;
		try {
			payerEWallet = walletDAOComponent.readWallet(payerEWalletId,
					payerMmid);

			Wallet receiverEWallet = walletDAOComponent.readWallet(
					receiverEWalletId, receiverMmid);

			if ((payerEWallet != null) && (receiverEWallet != null)) 
			{

				if (iPin.equals(payerEWallet.getiPIN())) 
				{

					if (payerEWallet.getBalance() >= amount)
					{

						float receiverBalance = receiverEWallet.getBalance()
								+ amount;
						receiverEWallet.setBalance(receiverBalance);

						float payerBalance = payerEWallet.getBalance() - amount;

						payerEWallet.setBalance(payerBalance);

						Set<TransactionDetails> tDetails = new HashSet<TransactionDetails>();
						String payorDescription = "Payed an amount of "
								+ amount + " Rs to"
								+ payerEWallet.getWalletPK().getMMID();
						String receiverDescription = "Received an amount"
								+ amount + "Rs to"
								+ receiverEWallet.getWalletPK().getMMID();
						TransactionDetails td1 = buildTransactionDetails(
								payerEWallet.getWalletPK().getMMID(),
								payerEWallet.getWalletPK().geteWalletID(),
								TransactionConstants.DEBIT, payorDescription);
						TransactionDetails td2 = buildTransactionDetails(
								receiverEWallet.getWalletPK().getMMID(),
								receiverEWallet.getWalletPK().geteWalletID(),
								TransactionConstants.CREDIT,
								receiverDescription);
						tDetails.add(td1);
						tDetails.add(td2);

						ETransaction etrans = new ETransaction(amount,
								new Date(), tDetails);

						if (walletDAOComponent.updateWallet(payerEWallet,
								receiverEWallet, etrans)) {
							transactionStatus.setStatusCode(StatusConstants.P2PTRANSFER_SUCCESS_CODE);
							transactionStatus.setStatusMessage(rb.getString(Integer
									.toString(StatusConstants.P2PTRANSFER_SUCCESS_CODE)));
							transactionStatus.setMMID(payerMmid);
							transactionStatus.setWalletID(payerEWalletId);
							transactionStatus.setTransactionID(123);
							Wallet walletUpdated = walletDAOComponent.readWallet(payerEWalletId, payerMmid);
							transactionStatus.setBalance(walletUpdated.getBalance());
							
						} else {
							transactionStatus.setStatusCode(StatusConstants.TRANSACTION_FAILURE_CODE);
							transactionStatus.setStatusMessage(rb.getString(Integer
									.toString(StatusConstants.TRANSACTION_FAILURE_CODE)));
						}
					}
					else {
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
				transactionStatus.setStatusCode(StatusConstants.INVALID_USER_CODE);
				transactionStatus.setStatusMessage(rb.getString(Integer
						.toString(StatusConstants.INVALID_USER_CODE)));
			}
		} catch (Exception e) {
			transactionStatus.setStatusCode(StatusConstants.EXCEPTION_STATUS_CODE);
			transactionStatus.setStatusMessage(rb.getString(Integer
					.toString(StatusConstants.EXCEPTION_STATUS_CODE)));
		}
		return transactionStatus;

	}

	@Override
	public Status checkQRCodeValidity(String walletID, String MMID, String ipin) {
		Status status = new Status();
		try {
			Wallet walletObject = walletDAOComponent.readWallet(walletID, MMID);

			System.out.println(walletObject);
			if (walletObject != null) {
				if (ipin.equals(walletObject.getiPIN())) {
					status.setStatusCode(1006);
					status.setStatusMessage(rb.getString("1006"));
				} else {
					status.setStatusCode(2003);
					status.setStatusMessage(rb.getString("2003"));
				}
			} else {
				status.setStatusCode(2003);
				status.setStatusMessage(rb.getString("2003"));
			}
		} catch (Exception e) {
			status.setStatusCode(StatusConstants.EXCEPTION_STATUS_CODE);
			status.setStatusMessage(rb.getString(Integer
					.toString(StatusConstants.EXCEPTION_STATUS_CODE)));;
		}
		return status;
	}

	@Override
	public TransactionStatus checkBalance(String walletID, String MMID, String ipin) {
		TransactionStatus transactionStatus = new TransactionStatus();
		try {

			Wallet walletObject = walletDAOComponent.readWallet(walletID, MMID);

			if (walletObject != null) {
				if (ipin.equals(walletObject.getiPIN())) {
					transactionStatus.setStatusCode(StatusConstants.CHECK_BALANCE_SUCCESS);
					transactionStatus.setStatusMessage(rb.getString(Integer
							.toString(StatusConstants.CHECK_BALANCE_SUCCESS)));
					transactionStatus.setBalance(walletObject.getBalance());
					transactionStatus.setMMID(walletObject.getWalletPK().getMMID());
					transactionStatus.setWalletID(walletObject.getWalletPK()
							.geteWalletID());

				} else {
					transactionStatus.setStatusCode(StatusConstants.INVALID_IPIN_CODE);
					transactionStatus.setStatusMessage(rb.getString(Integer
							.toString(StatusConstants.INVALID_IPIN_CODE)));
					transactionStatus.setMMID(walletObject.getWalletPK().getMMID());
					transactionStatus.setWalletID(walletObject.getWalletPK()
							.geteWalletID());

				}

			} else {
				transactionStatus.setStatusCode(StatusConstants.USER_NOTEXISTS_CODE);
				transactionStatus.setStatusMessage(rb.getString(Integer
						.toString(StatusConstants.USER_NOTEXISTS_CODE)));
			}
		} catch (Exception e) {
			transactionStatus.setStatusCode(StatusConstants.EXCEPTION_STATUS_CODE);
			transactionStatus.setStatusMessage(rb.getString(Integer
					.toString(StatusConstants.EXCEPTION_STATUS_CODE)));
		}
		return transactionStatus;

	}

	@Override
	public P2MSecretCode personToMerchantQRCode(long IMEI,
			String payerEWalletID, String payerMMID, String receiverEWalletID,
			String receiverMMID, String ipin, float amount) {
		Wallet payerEWallet,receiverEWallet;
		P2MSecretCode secretCode = new P2MSecretCode();
		try
		{
		payerEWallet = walletDAOComponent.readWallet(payerEWalletID,payerMMID);

		receiverEWallet = walletDAOComponent.readWallet(receiverEWalletID, receiverMMID);
		if( (payerEWallet != null) && (receiverEWallet != null) ) 
		{
			
			if(payerEWallet.getiPIN().equals(ipin))
			{
				if(payerEWallet.getBalance() > amount) 
				{
					Random randomGenerator = new Random();
					int approvalCode = randomGenerator.nextInt(1000000);
					if (approvalCode < 10000)
						approvalCode = approvalCode + 10000;

					TempTransaction transaction = new TempTransaction(payerEWalletID, payerMMID, receiverEWalletID, receiverMMID, IMEI, approvalCode, amount, new Date());
					
					walletDAOComponent.createTempTransaction(transaction);
					secretCode.setIMEI(IMEI);
					secretCode.setMessage(rb.getString(Integer.toString(StatusConstants.PREAPPROVAL_CODE)));
					secretCode.setApprovalCode(approvalCode);
					secretCode.setTempTransactionID(transaction.getTempTransactionId());
				}
				else {
					secretCode.setMessage(rb.getString(Integer.toString(StatusConstants.INSUFFICIENT_BALANCE_CODE)));
					secretCode.setIMEI(0L);
					secretCode.setTempTransactionID(0L);
					secretCode.setApprovalCode(0);
				}
			}
			else
			{
				//ipin invalid
				secretCode.setMessage(rb.getString(Integer.toString(StatusConstants.INVALID_IPIN_CODE)));
				secretCode.setIMEI(0L);
				secretCode.setApprovalCode(0);
			}
				
		}
		else {
			secretCode.setMessage(rb.getString(Integer.toString(StatusConstants.INVALID_USER_CODE)));
			secretCode.setIMEI(0L);
			secretCode.setApprovalCode(0);
		}
		
		}
		catch (Exception e) {
			secretCode.setMessage(rb.getString(Integer.toString(StatusConstants.EXCEPTION_STATUS_CODE)));
			secretCode.setIMEI(0L);
			secretCode.setApprovalCode(0);
		}
		return secretCode;
	}

		
	private TransactionDetails buildTransactionDetails(String MMID,
			String eWalletID, String type, String description) {
		TransactionDetails transactionDetails = new TransactionDetails(MMID,
				eWalletID, type, description);
		return transactionDetails;

	}

	@Override
	public TransactionStatus personToMerchantQRCodePayment(long tempTransactionID, long IMEI,
			int approvalCode, String walletID, String MMID) 
	{
		TempTransaction  tempTransaction = tempTransactionDAOComponent.readTempTransaction(tempTransactionID, IMEI, approvalCode, walletID, MMID);
		TransactionStatus transactionStatus = new TransactionStatus();

		Wallet payerEWallet;
		try {
			payerEWallet = walletDAOComponent.readWallet(tempTransaction.getPayerEWalletID(),
					tempTransaction.getPayerMMID());

			Wallet receiverEWallet = walletDAOComponent.readWallet(
					tempTransaction.getReceiverEWalletID(), tempTransaction.getReceiverMMID());

			

				float amount = tempTransaction.getAmount();
					if (payerEWallet.getBalance() >= amount)
					{

						float receiverBalance = receiverEWallet.getBalance()
								+ amount;
						receiverEWallet.setBalance(receiverBalance);

						float payerBalance = payerEWallet.getBalance() - amount;

						payerEWallet.setBalance(payerBalance);

						Set<TransactionDetails> tDetails = new HashSet<TransactionDetails>();
						String payorDescription = "Payed an amount of "
								+ amount + " Rs to"
								+ payerEWallet.getWalletPK().getMMID();
						String receiverDescription = "Received an amount"
								+ amount + "Rs to"
								+ receiverEWallet.getWalletPK().getMMID();
						TransactionDetails td1 = buildTransactionDetails(
								payerEWallet.getWalletPK().getMMID(),
								payerEWallet.getWalletPK().geteWalletID(),
								TransactionConstants.DEBIT, payorDescription);
						TransactionDetails td2 = buildTransactionDetails(
								receiverEWallet.getWalletPK().getMMID(),
								receiverEWallet.getWalletPK().geteWalletID(),
								TransactionConstants.CREDIT,
								receiverDescription);
						tDetails.add(td1);
						tDetails.add(td2);

						ETransaction etrans = new ETransaction(amount,
								new Date(), tDetails);

						if (walletDAOComponent.updateWallet(payerEWallet,
								receiverEWallet, etrans)) {
							transactionStatus.setStatusCode(StatusConstants.P2PTRANSFER_SUCCESS_CODE);
							transactionStatus.setStatusMessage(rb.getString(Integer
									.toString(StatusConstants.P2PTRANSFER_SUCCESS_CODE)));
							transactionStatus.setMMID(tempTransaction.getPayerMMID());
							transactionStatus.setWalletID(tempTransaction.getPayerEWalletID());
							transactionStatus.setTransactionID(123);
							Wallet walletUpdated = walletDAOComponent.readWallet(tempTransaction.getPayerEWalletID(),
									tempTransaction.getPayerMMID());
							transactionStatus.setBalance(walletUpdated.getBalance());
							tempTransactionDAOComponent.deleteTempTransaction(tempTransactionID, IMEI, approvalCode, walletID, MMID);
							
							
						} else {
							transactionStatus.setStatusCode(StatusConstants.TRANSACTION_FAILURE_CODE);
							transactionStatus.setStatusMessage(rb.getString(Integer
									.toString(StatusConstants.TRANSACTION_FAILURE_CODE)));
						}
					
					
				}
			
		} catch (Exception e) {
			transactionStatus.setStatusCode(StatusConstants.EXCEPTION_STATUS_CODE);
			transactionStatus.setStatusMessage(rb.getString(Integer
					.toString(StatusConstants.EXCEPTION_STATUS_CODE)));
		}
		return transactionStatus;
		
	}

	


	
}
