/**
 * 
 */
package com.virtusa.mwallet.business.factories;

import org.springframework.beans.factory.annotation.Autowired;

import com.virtusa.mwallet.business.components.interfaces.TransactionComponentInterface;

/**
 * @author gbharat
 *
 */
public class TransactionFactory {
	
	public TransactionComponentInterface transactionComponent;

	public TransactionComponentInterface getTransactionComponent() {
		return transactionComponent;
	}
	@Autowired
	public void setTransactionComponent(
			TransactionComponentInterface transactionComponent) {
		this.transactionComponent = transactionComponent;
	}

}
