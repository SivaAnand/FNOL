/**
 * 
 */
package com.virtusa.mwallet.persistence.component.interfaces;


import java.util.Date;
import java.util.List;

import com.virtusa.mwallet.valueobjects.TempTransaction;
import com.virtusa.mwallet.valueobjects.Statement;
/**
 * @author gbharat
 *
 */
public interface TempTransactionDAOInterface {
	
	public TempTransaction readTempTransaction(long TempTransactionID, long IMEI,
			int approvalCode, String walletID, String MMID);


	boolean deleteTempTransaction(long TempTransactionID, long IMEI,
			int approvalCode, String walletID, String MMID);

	List<Statement> getStatement (String MMID, String walletID, Date startDate, Date endDate);
}
