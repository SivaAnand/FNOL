package com.virtusa.mwallet.business.components.interfaces;

import java.util.Date;
import java.util.List;

import com.virtusa.mwallet.valueobjects.Statement;

public interface TransactionComponentInterface {

	List<Statement> getStatement();
	List<Statement> getStatement (String MMID, String walletID, Date startDate, Date endDate);
	
}
