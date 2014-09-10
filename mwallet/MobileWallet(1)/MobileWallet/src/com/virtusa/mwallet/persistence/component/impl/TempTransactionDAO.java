/**
 * 
 */
package com.virtusa.mwallet.persistence.component.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.virtusa.mwallet.persistence.component.interfaces.TempTransactionDAOInterface;
import com.virtusa.mwallet.persistence.util.HibernateUtil;
import com.virtusa.mwallet.valueobjects.Statement;
import com.virtusa.mwallet.valueobjects.TempTransaction;

/**
 * @author gbharat
 *
 */
public class TempTransactionDAO implements TempTransactionDAOInterface {

	@Override
	public TempTransaction readTempTransaction(long tempTransactionID, long IMEI, int approvalCode,
			String walletID, String MMID) {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		Session session = sessionFactory.openSession();
		/** Starting the Transaction */
		Transaction tx = session.beginTransaction();

		String queryString = "from TempTransaction where IMEI = :imei and approvalCode = :approvalcode " +
				"and receiverEWalletID = :receiverewalletid and receiverMMID = :receivermmid and tempTransactionID = :temptransactionid";
		Query query = session.createQuery(queryString);
		query.setLong("imei", IMEI);
		query.setLong("temptransactionid", tempTransactionID);
		query.setInteger("approvalcode", approvalCode);
		query.setString("receiverewalletid", walletID);
		query.setString("receivermmid", MMID);
		Object queryResult = query.uniqueResult();
		TempTransaction tempTransaction = (TempTransaction) queryResult;
		/** Commiting the changes */
		tx.commit();

		/** Closing Session */
		session.close();

		return tempTransaction;
	}
	
	@Override
	public boolean deleteTempTransaction(long tempTransactionID, long IMEI, int approvalCode,
			String walletID, String MMID) {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		Session session = sessionFactory.openSession();
		/** Starting the Transaction */
		Transaction tx = session.beginTransaction();

		String queryString = "delete from TempTransaction where IMEI = :imei and approvalCode = :approvalcode " +
				"and receiverEWalletID = :receiverewalletid and receiverMMID = :receivermmid and tempTransactionID = :temptransactionid";
		Query query = session.createQuery(queryString);
		query.setLong("imei", IMEI);
		query.setLong("temptransactionid", tempTransactionID);
		query.setInteger("approvalcode", approvalCode);
		query.setString("receiverewalletid", walletID);
		query.setString("receivermmid", MMID);
		int deletedRows = query.executeUpdate();
		tx.commit();

		/** Closing Session */
		session.close();

		if (deletedRows != 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Statement> getStatement(String MMID, String walletID,
			Date startDate, Date endDate) {
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		
		Session session = sessionFactory.openSession();
		/** Starting the Transaction */
		Transaction tx = session.beginTransaction();

		Query query = session.createSQLQuery("select e.transactionid, e.date, e.amount, t.mmid, t.ewalletid, t.type " +
				"from transactiondetails t join trans_detail join etransaction e where " +
				"e.transactionid in ( select e.transactionid from transactiondetails t join trans_detail join etransaction e " +
				"where t.mmid = ? and t.ewalletid = ?) and(e.date between ? and ?)");
		query.setString(0, MMID);
		query.setString(1,walletID);
		query.setDate(2, startDate);
		query.setDate(3, endDate);
		List list = query.list();
		
		List<Statement> statements = new ArrayList<Statement>();
		
		Iterator iterator = list.iterator(); 
		while(iterator.hasNext()) {
			
			Statement statement = new Statement();
			statement = (Statement)iterator.next();
		    statements.add(statement); 
		    System.out.println("DAO");


		} 
		
		tx.commit();

		/** Closing Session */
		session.close();

		return statements;
	}
	
	

}
