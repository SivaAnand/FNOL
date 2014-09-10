package com.virtusa.mwallet.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.virtusa.mwallet.business.components.interfaces.ProfileComponentInterface;
import com.virtusa.mwallet.business.factories.ProfileFactory;
import com.virtusa.mwallet.services.util.ComponentFactoryUtil;
import com.virtusa.mwallet.valueobjects.Statement;
import com.virtusa.mwallet.valueobjects.Status;
import com.virtusa.mwallet.valueobjects.User;
import com.virtusa.mwallet.valueobjects.UserPIN;
import com.virtusa.mwallet.valueobjects.Wallet;
import com.virtusa.mwallet.valueobjects.WalletPK;
@Produces("application/json")
@Path("/mwallet")
public class ProfileService {

		@GET
	@Path("/loginUser")
	@Produces("application/json")
	public String loginUser(@QueryParam("userID") String userID,
			@QueryParam("pin") String pin) {

		ProfileFactory pf = (ProfileFactory) ComponentFactoryUtil
				.getInstance("profile");
		ProfileComponentInterface profile = pf.getProfileComponent();

		Status login_status = new Status();
		login_status = profile.login(userID, pin);
		int code = login_status.getStatusCode();
		String msg = login_status.getStatusMessage();
	
	
	
		String status = " {\"statuscode\":" + code + "," + "\"statusmessage\":"
		+ "\"" + msg + "\"" + "} ";
		
		return status;
		
		  
	}

	/* Checking for user's changePIN */

	@GET
	@Path("/changePIN")
	@Produces(MediaType.APPLICATION_JSON)
	public String changePIN(@QueryParam("userID") String userID,
			@QueryParam("oldPin") String oldPin,
			@QueryParam("newPin") String newPin) {

		ProfileFactory pf = (ProfileFactory) ComponentFactoryUtil
				.getInstance("profile");
		ProfileComponentInterface profile = pf.getProfileComponent();

		UserPIN userPIN = new UserPIN();
		userPIN.setOldPin(oldPin);
		userPIN.setNewPin(newPin);
		userPIN.setUserId(userID);

		Status change_status = profile.changePin(userPIN);

		int code = change_status.getStatusCode();
		String msg = change_status.getStatusMessage();
		String status = " {\"statuscode\":" + code + "," + "\"statusmessage\":"
				+ "\"" + msg + "\"" + "} ";
		return status;

	}

	/* Checking for user's registration */
	@GET
	@Path("/registerUser")
	@Produces(MediaType.APPLICATION_JSON)
	public String registerUser(@QueryParam("mmid") String MMID,
			@QueryParam("mobileno") String MobileNo,
			@QueryParam("pin") String pin, @QueryParam("userID") String userID,
			@QueryParam("userName") String userName) {
		ProfileFactory pf = (ProfileFactory) ComponentFactoryUtil
				.getInstance("profile");
		ProfileComponentInterface profile = pf.getProfileComponent();
		User user = new User();
		user.setMobileNo(MobileNo);
		user.setMMID(MMID);
		user.setUserId(userID);
		user.setUserName(userName);
		user.setPin(pin);
		Status register_status = profile.register(user);
		int code = register_status.getStatusCode();
		String msg = register_status.getStatusMessage();
		String status = " {\"statuscode\":" + code + "," + "\"statusmessage\":"
				+ "\"" + msg + "\"" + "} ";
		return status;

	}
	
	
	/* Getting  user's wallet details */

	@GET
	@Path("/getWallet")
	@Produces(MediaType.APPLICATION_JSON)
	public String getWalletDetails(@QueryParam("userID") String userID) {

		ProfileFactory pf = (ProfileFactory) ComponentFactoryUtil
				.getInstance("profile");
		ProfileComponentInterface profile = pf.getProfileComponent();

		Wallet wallet=new Wallet();
		wallet = profile.getWalletbyUserID(userID);
		WalletPK walletpk=new WalletPK();
		walletpk=wallet.getWalletPK();
	
		String mmid = walletpk.getMMID();
		String ewalletid = walletpk.geteWalletID();
		
	
		String status = " {\"mmid\":"+ "\"" + mmid+ "\"" + "," + "\"ewalletid\":"
		+ "\"" +ewalletid + "\"" + "} ";
		
		return status;

	}
	
	
	


}
