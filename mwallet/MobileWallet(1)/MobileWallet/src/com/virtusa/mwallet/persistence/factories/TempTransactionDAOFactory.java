/**
 * 
 */
package com.virtusa.mwallet.persistence.factories;

import com.virtusa.mwallet.persistence.component.interfaces.TempTransactionDAOInterface;

/**
 * @author gbharat
 *
 */
public class TempTransactionDAOFactory {

	public TempTransactionDAOInterface tempTransactionDAOComponent;

	public TempTransactionDAOInterface getTempTransactionDAOComponent() {
		return tempTransactionDAOComponent;
	}

	public void setTempTransactionDAOComponent(
			TempTransactionDAOInterface tempTransactionDAOComponent) {
		this.tempTransactionDAOComponent = tempTransactionDAOComponent;
	}


}
