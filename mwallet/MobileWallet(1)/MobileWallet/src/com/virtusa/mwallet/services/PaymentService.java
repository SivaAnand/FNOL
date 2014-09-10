package com.virtusa.mwallet.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.virtusa.mwallet.business.components.interfaces.PaymentComponentInterface;
import com.virtusa.mwallet.business.factories.PaymentFactory;
import com.virtusa.mwallet.services.util.ComponentFactoryUtil;
import com.virtusa.mwallet.valueobjects.P2MSecretCode;
import com.virtusa.mwallet.valueobjects.Status;
import com.virtusa.mwallet.valueobjects.TransactionStatus;


@Path("/mwalletpayment")
public class PaymentService {

	@GET
	@Path("/payment")
	@Produces(MediaType.APPLICATION_JSON)
	public String payPersonToPersonQRCodeWallet(
			@QueryParam("senderWalletID") String senderWalletID,
			@QueryParam("senderMMID") String senderMMID,
			@QueryParam("receiverWalletID") String receiverWalletID,
			@QueryParam("receiverMMID") String receiverMMID,
			@QueryParam("ipin") String ipin, @QueryParam("amount") int amount) {
		PaymentFactory pf = (PaymentFactory) ComponentFactoryUtil
				.getInstance("payment");
		PaymentComponentInterface payment = pf.getPaymentComponent();

		
		TransactionStatus transactionStatus = payment.payPersonToPersonMMID(senderWalletID,
				senderMMID, receiverWalletID, receiverMMID, ipin, amount);


		String status = " {\"statuscode\":" + transactionStatus.getStatusCode() + "," + "\"statusmessage\":"
		+ "\"" + transactionStatus.getStatusMessage()+"\""
		+ "," + "\"walletID\":" + "\"" + transactionStatus.getWalletID()+"\""
		+ "," + "\"MMID\":" + "\"" + transactionStatus.getMMID()+"\""
		+ "," + "\"balance\":" +  transactionStatus.getBalance()
		+ "," + "\"transactionID\":"  + transactionStatus.getTransactionID()+"}";

		return status;

	}

	@GET
	@Path("/checkBalance")
	@Produces(MediaType.APPLICATION_JSON)
	public String checkBalnce(@QueryParam("walletID") String walletID,
			@QueryParam("MMID") String MMID, @QueryParam("ipin") String ipin) {

		PaymentFactory pf = (PaymentFactory) ComponentFactoryUtil
				.getInstance("payment");
		PaymentComponentInterface payment = pf.getPaymentComponent();

		TransactionStatus balance = new TransactionStatus();

		balance = payment.checkBalance(walletID, MMID, ipin);

		int code = balance.getStatusCode();

		String msg = balance.getStatusMessage();

		String mmid = balance.getMMID();

		float bal = balance.getBalance();

		String walletid = balance.getWalletID();
	

		String status = " {\"statuscode\":" + code + "," + "\"statusmessage\":"

		+ "\"" + msg + "\"," + "\"MMID\":" + "\"" + mmid + "\","
				+ "\"walletid\":" + "\"" + walletid + "\"," + "\"balance\":"
				+ bal + "} ";

		return status;
	}

	@GET
	@Path("/checkQRCodeValidity")
	@Produces(MediaType.APPLICATION_JSON)
	public String CheckWallet(@QueryParam("walletID") String walletID,
			@QueryParam("MMID") String MMID, @QueryParam("ipin") String ipin) {

		PaymentFactory pf = (PaymentFactory) ComponentFactoryUtil
				.getInstance("payment");
		PaymentComponentInterface payment = pf.getPaymentComponent();

		Status statusObject = payment.checkQRCodeValidity(walletID, MMID, ipin);

		int code = statusObject.getStatusCode();
		String msg = statusObject.getStatusMessage();
		String status = " {\"statuscode\":" + code + "," + "\"statusmessage\":"
				+ "\"" + msg + "\"" + "} ";

		return status;

	}

	
	
	@GET
	@Path("/p2mbuyerpayment")
	@Produces(MediaType.APPLICATION_JSON)
	public String personToMerchantQRCode(@QueryParam("IMEI") long IMEI,
			@QueryParam("payerWalletID") String payerWalletID,@QueryParam("payerMMID") String payerMMID,@QueryParam("receiverWalletID") String receiverWalletID,@QueryParam("receiverMMID") String receiverMMID, @QueryParam("ipin") String ipin,@QueryParam("amount") float amount) {
				
		PaymentFactory pf = (PaymentFactory) ComponentFactoryUtil
				.getInstance("payment");
		PaymentComponentInterface payment = pf.getPaymentComponent();
		P2MSecretCode P2MObect=new P2MSecretCode();
		 P2MObect=  payment.personToMerchantQRCode(IMEI, payerWalletID, payerMMID, receiverWalletID, receiverMMID, ipin, amount);
				
		String msg = P2MObect.getMessage();
		int approvalCode=P2MObect.getApprovalCode();
		long imei=P2MObect.getIMEI();
		String status = " {\"approvalcode\":" + approvalCode+ ",\"imei\":" + imei+ "," + "\"statusmessage\":"
				+ "\"" + msg + "\"" + "} ";
/*
	String status = " {\"statusmessage\":"
		+ "\"" + msg + "\"" + "} ";

		*/
		return status;
		

	}
	

	
	@GET
	@Path("/p2mpaymentstatus")
	@Produces(MediaType.APPLICATION_JSON)
	public String personToMerchantQRCodeStatus(@QueryParam("tempTransactionID") long tempTransactionID, @QueryParam("IMEI") long IMEI,@QueryParam("approvalcode") int approvalcode,
			@QueryParam("walletID") String walletID,@QueryParam("MMID") String MMID) 
	{
		
		PaymentFactory pf = (PaymentFactory) ComponentFactoryUtil
				.getInstance("payment");
		PaymentComponentInterface payment = pf.getPaymentComponent();
		TransactionStatus transObect=new TransactionStatus();
		transObect=  payment.personToMerchantQRCodePayment(tempTransactionID, IMEI, approvalcode,walletID,MMID);
		
		String msg = transObect.getStatusMessage();
		int code=transObect.getTransactionID();
		String status = " {\"statuscode\":" + code + "," + "\"statusmessage\":"
				+ "\"" + msg + "\"" + "} ";


		
		return status;
		

	}
}
