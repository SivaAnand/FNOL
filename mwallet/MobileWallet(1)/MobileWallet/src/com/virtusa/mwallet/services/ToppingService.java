package com.virtusa.mwallet.services;

import javax.ws.rs.GET;


import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.virtusa.mwallet.business.components.interfaces.ToppingComponentInterface;
import com.virtusa.mwallet.business.factories.ToppingFactory;
import com.virtusa.mwallet.services.util.ComponentFactoryUtil;
import com.virtusa.mwallet.valueobjects.TransactionStatus;

@Path("/mwallettopping")
public class ToppingService {
	
	/*  Performing topup for user's wallet account*/
	
	@GET
	@Path("/topUp")
	@Produces(MediaType.APPLICATION_JSON)
	public String topUp(@QueryParam("walletID") String walletID,
			@QueryParam("mmid") String MMID, @QueryParam("ipin") String ipin, @QueryParam("amount")float amount) {
		ToppingFactory tf = (ToppingFactory) ComponentFactoryUtil
				.getInstance("topping");
									
		ToppingComponentInterface toppingComponent = tf.getToppingComponent();

		TransactionStatus transactionStatus = toppingComponent.topUp(walletID, MMID, ipin, amount);
		
		
		String status = " {\"statuscode\":" + transactionStatus.getStatusCode() + "," + "\"statusmessage\":"
		+ "\"" + transactionStatus.getStatusMessage()+"\""
		+ "," + "\"walletID\":" + "\"" + transactionStatus.getWalletID()+"\""
		+ "," + "\"MMID\":" + "\"" + transactionStatus.getMMID()+"\""
		+ "," + "\"balance\":" +  transactionStatus.getBalance()
		+ "," + "\"transactionID\":"  + transactionStatus.getTransactionID()+"}";
			
		
		return  status;

	} 
	
/*  Performing  deduction of money from user's wallet account*/
	
	@GET
	@Path("/topDown")
	@Produces(MediaType.APPLICATION_JSON)
	public String topDown(@QueryParam("walletID") String walletID,
			@QueryParam("mmid") String MMID, @QueryParam("ipin") String ipin, @QueryParam("amount")float amount) {
			ToppingFactory tf = (ToppingFactory) ComponentFactoryUtil
				.getInstance("topping");
									
		ToppingComponentInterface toppingComponent = tf.getToppingComponent();

		TransactionStatus transactionStatus = toppingComponent.topDown(walletID, MMID, ipin, amount);

		String status = " {\"statuscode\":" + transactionStatus.getStatusCode() + "," + "\"statusmessage\":"
		+ "\"" + transactionStatus.getStatusMessage()+"\""
		+ "," + "\"walletID\":" + "\"" + transactionStatus.getWalletID()+"\""
		+ "," + "\"MMID\":" + "\"" + transactionStatus.getMMID()+"\""
		+ "," + "\"balance\":" +  transactionStatus.getBalance()
		+ "," + "\"transactionID\":"  + transactionStatus.getTransactionID()+"}";
		
		return  status;	} 
	
}
