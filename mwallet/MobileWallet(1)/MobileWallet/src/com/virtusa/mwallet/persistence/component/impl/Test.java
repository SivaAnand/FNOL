/**
 * 
 */
package com.virtusa.mwallet.persistence.component.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.virtusa.mwallet.valueobjects.Statement;

/**
 * @author gbharat
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TempTransactionDAO td = new TempTransactionDAO();
		Date startDate = new Date(2012, 8 , 14);
		System.out.println(startDate);
		Date endDate = new Date(2012, 8 , 20);
		System.out.println(endDate);
		
		List<Statement> statements = td.getStatement("88998888", "nikhil7981", startDate, endDate);
		
		Iterator iterator = statements.iterator(); 
		while(iterator.hasNext()) {
			
			System.out.println("HII");


		} 

	}

}
