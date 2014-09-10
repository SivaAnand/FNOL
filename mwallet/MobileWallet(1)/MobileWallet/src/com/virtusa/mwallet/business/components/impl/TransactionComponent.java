package com.virtusa.mwallet.business.components.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.virtusa.mwallet.business.components.interfaces.TransactionComponentInterface;
import com.virtusa.mwallet.business.util.DAOFactoryUtil;
import com.virtusa.mwallet.persistence.component.interfaces.TempTransactionDAOInterface;
import com.virtusa.mwallet.persistence.factories.TempTransactionDAOFactory;
import com.virtusa.mwallet.valueobjects.Statement;

public class TransactionComponent implements TransactionComponentInterface{
	
	public TempTransactionDAOFactory tempTransactionDAOFactory = (TempTransactionDAOFactory) DAOFactoryUtil
	.getFactory("tempTransactionDAOFactory");
	private TempTransactionDAOInterface tempTransactionDAOComponent = tempTransactionDAOFactory
	.getTempTransactionDAOComponent();
	ResourceBundle rb = ResourceBundle.getBundle("status");

	@Override
	public List<Statement> getStatement() {

		List<Statement> list=new ArrayList<Statement>();
       
		Statement statement = new Statement();
		statement.setAmount(100);
		statement.setDate(new Date());
		statement.setMMID("111");
		statement.setWalletID("aaa");
		statement.setTransactionID(1);
		statement.setType("credit");
		list.add(statement);
		return list;
	}

	@Override
	public List<Statement> getStatement(String MMID, String walletID,
			Date startDate, Date endDate) {
		
		List<Statement> statements =new ArrayList<Statement>();
		statements = tempTransactionDAOComponent.getStatement(MMID, walletID, startDate, endDate);
		
		return statements;
	}

}
