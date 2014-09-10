package com.virtusa.mwallet.business.components.impl;

import java.util.Random;
import java.util.ResourceBundle;

import com.virtusa.mwallet.business.components.interfaces.ProfileComponentInterface;
import com.virtusa.mwallet.business.util.DAOFactoryUtil;
import com.virtusa.mwallet.constants.StatusConstants;
import com.virtusa.mwallet.persistence.component.interfaces.UserDAOInterface;
import com.virtusa.mwallet.persistence.component.interfaces.WalletDAOInterface;
import com.virtusa.mwallet.persistence.factories.UserDAOFactory;
import com.virtusa.mwallet.persistence.factories.WalletDAOFactory;
import com.virtusa.mwallet.valueobjects.P2MSecretCode;
import com.virtusa.mwallet.valueobjects.Status;
import com.virtusa.mwallet.valueobjects.User;
import com.virtusa.mwallet.valueobjects.UserPIN;
import com.virtusa.mwallet.valueobjects.Wallet;
import com.virtusa.mwallet.valueobjects.WalletPK;

/**
 * @author gbharat
 * 
 */
public class ProfileComponent implements ProfileComponentInterface {

	private UserDAOFactory userDAOFactory = (UserDAOFactory) DAOFactoryUtil
			.getFactory("userDAOFactory");
	private UserDAOInterface userDAO = userDAOFactory.getUserDAOComponent();

	public WalletDAOFactory walletDAOFactory = (WalletDAOFactory) DAOFactoryUtil
			.getFactory("walletDAOFactory");
	private WalletDAOInterface walletDAOComponent = walletDAOFactory
			.getWalletDAOComponent();

	ResourceBundle rb = ResourceBundle.getBundle("status");

	private Status status = new Status();

	@Override
	public Status login(String userId, String pin) {
		try {
			User user = userDAO.readUser(userId);
			if ((user != null) && (user.getPin().equals(pin))) {
				status.setStatusCode(StatusConstants.LOGIN_SUCCESS_CODE);
				status.setStatusMessage(rb.getString(Integer
						.toString(StatusConstants.LOGIN_SUCCESS_CODE)));
			} else {
				status.setStatusCode(StatusConstants.LOGIN_FAILURE_CODE);
				status.setStatusMessage(rb.getString(Integer
						.toString(StatusConstants.LOGIN_FAILURE_CODE)));
			}

		} catch (Exception e) {
			status.setStatusCode(StatusConstants.EXCEPTION_STATUS_CODE);
			status.setStatusMessage(rb.getString(Integer
					.toString(StatusConstants.EXCEPTION_STATUS_CODE)));
		}
		return status;
	}

	@Override
	public Status register(User user) {
		try {
			
			User checkUserId = userDAO.readUser(user.getUserId());
			User checkMMID = userDAO.readUserByMMID(user.getMMID());
			if (checkUserId != null ) {

				status.setStatusCode(StatusConstants.REGISTRATION__USEREXISTS_CODE);
				status.setStatusMessage(rb.getString(Integer
						.toString(StatusConstants.REGISTRATION__USEREXISTS_CODE)));
			}
			else if(checkMMID != null) {
				status.setStatusCode(StatusConstants.REGISTRATION__MMIDEXISTS_CODE);
				status.setStatusMessage(rb.getString(Integer
						.toString(StatusConstants.REGISTRATION__MMIDEXISTS_CODE)));
			}

			else {
				Random randomGenerator = new Random();
				int randomInt = randomGenerator.nextInt(100);
				if (randomInt < 10)
					randomInt = randomInt + 10;

				Wallet walletObject = new Wallet();

				WalletPK walletPK = new WalletPK();

				String walletID = user.getUserId().replaceAll("\\s", "")
						.concat(Integer.toString(randomInt));

				walletPK.seteWalletID(walletID);
				walletPK.setMMID(user.getMMID());

				walletObject.setBalance(10000.00f);
				walletObject.setRechargeFrequency(2);

				randomInt = randomGenerator.nextInt(10000);
				walletObject.setiPIN(Integer.toString(randomInt));

				walletObject.setWalletPK(walletPK);
				if (userDAO.createUser(user)
						&& walletDAOComponent.createWallet(walletObject)) {
					status.setStatusCode(StatusConstants.REGISTRATION_SUCCESS_CODE);
					status.setStatusMessage(rb.getString(Integer
							.toString(StatusConstants.REGISTRATION_SUCCESS_CODE)));
				} else {
					status.setStatusCode(StatusConstants.REGISTRATION_FAILURE_CODE);
					status.setStatusMessage(rb.getString(Integer
							.toString(StatusConstants.REGISTRATION_FAILURE_CODE)));
				}
			}
		} catch (Exception e) {
			status.setStatusCode(StatusConstants.EXCEPTION_STATUS_CODE);
			status.setStatusMessage(rb.getString(Integer
					.toString(StatusConstants.EXCEPTION_STATUS_CODE)));
		}
		return status;
	}

	@Override
	public Status changePin(UserPIN userPin) {
		try {
			User user = userDAO.readUser(userPin.getUserId());

			if ((user != null) && (user.getPin().equals(userPin.getOldPin()))) {
				user.setPin(userPin.getNewPin());
				userDAO.updateUser(user);
				status.setStatusCode(StatusConstants.CHANGEPIN_SUCCESS_CODE);
				status.setStatusMessage(rb.getString(Integer
						.toString(StatusConstants.CHANGEPIN_SUCCESS_CODE)));
			} else {
				status.setStatusCode(StatusConstants.CHECK_CREDENTIALS_CODE);
				status.setStatusMessage(rb.getString(Integer
						.toString(StatusConstants.CHECK_CREDENTIALS_CODE)));
			}
		} catch (Exception e) {
			status.setStatusCode(StatusConstants.EXCEPTION_STATUS_CODE);
			status.setStatusMessage(rb.getString(Integer
					.toString(StatusConstants.EXCEPTION_STATUS_CODE)));
		}
		return status;

	}
	
	public Wallet getWalletbyUserID(String userID){
		Wallet wallet = new Wallet();
		WalletPK walletpk=new WalletPK();
		try {
			
		User user = userDAO.readUser(userID);
			if(user!=null)
			{			String MMID = user.getMMID();
			wallet = walletDAOComponent.readWallet(MMID);
			
			}
			else
			{
				
				wallet.setBalance(0);
				wallet.setiPIN("");
				wallet.setRechargeFrequency(0);
				walletpk.seteWalletID("");
				walletpk.setMMID("");
				wallet.setWalletPK(walletpk);
				
			}
				
				
				
	
			
		} catch (Exception e) {
			 
			
		e.printStackTrace();
					
			
		}
		
		return wallet;
		
	}

}
