/**
 * 
 */
package com.virtusa.mwallet.services;

import java.text.DateFormat;
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
import com.virtusa.mwallet.business.components.interfaces.TransactionComponentInterface;
import com.virtusa.mwallet.business.factories.TransactionFactory;
import com.virtusa.mwallet.services.util.ComponentFactoryUtil;
import com.virtusa.mwallet.valueobjects.Statement;

/**
 * @author gbharat
 *
 */

@Path("/mwallettransaction")
public class TransactionService {
	
	
	@GET
	@Path("/getTransactions")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Statement> getTransactions(
		@QueryParam("walletID") String walletID,
			@QueryParam("MMID") String MMID,
			@QueryParam("startDate") String startDate, 
			@QueryParam("endDate") String endDate) {
		
		TransactionFactory tf = (TransactionFactory) ComponentFactoryUtil
				.getInstance("transaction");
		TransactionComponentInterface transaction = tf.getTransactionComponent();
		List<Statement> list=new ArrayList<Statement>();
		Date start_Date=null,end_Date=null;
		try {  
		 DateFormat formatter ; 
		  
		  formatter = new SimpleDateFormat("dd-mm-yyyy");
		  start_Date = (Date)formatter.parse(startDate);
		  end_Date = (Date)formatter.parse(endDate);
		 
		  } catch (ParseException e)
		  {System.out.println("Exception :"+e);  }  
		 
		 
		//list = (List<Statement>) transaction.getStatement();
		list = (List<Statement>) transaction.getStatement(MMID, walletID, start_Date, end_Date);
		/*String status = " {\"transactionID\":" + statement.getTransactionID() + "," + "\"mmid\":"+ "\"" + statement.getMMID()+"\""
		+ "," + "\"walletID\":" + "\"" + statement.getWalletID()+"\""
		+ "," + "\"type\":" + "\"" + statement.getType()+"\""
		+ "," + "\"Date\":" + "\"" + statement.getDate()+"\""
		
		+ "," + "\"amount\":" +  statement.getAmount()
		+ "}";*/
		
		
		 
         return list;


		

	}


}
