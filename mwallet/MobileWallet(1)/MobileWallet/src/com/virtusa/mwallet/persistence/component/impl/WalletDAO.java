package com.virtusa.mwallet.persistence.component.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.virtusa.mwallet.persistence.component.interfaces.WalletDAOInterface;
import com.virtusa.mwallet.persistence.util.HibernateUtil;
import com.virtusa.mwallet.valueobjects.ETransaction;
import com.virtusa.mwallet.valueobjects.TempTransaction;
import com.virtusa.mwallet.valueobjects.Wallet;

public class WalletDAO implements WalletDAOInterface {
	
	SessionFactory sessionFactory = null;
	/**
	 * 
	 */
	public WalletDAO() {
		sessionFactory = 
			HibernateUtil.getSessionFactory();
		// TODO Auto-generated constructor stub
	}


	@Override
	public boolean createWallet(Wallet wallet) throws Exception {
		
		Session session = sessionFactory.openSession();
		/** Starting the Transaction */
		Transaction tx = session.beginTransaction();
		/** Saving POJO */
		session.save(wallet);
		
		/** Commiting the changes */
		tx.commit();

		/** Closing Session */
		session.close();
		return true;
	}
	

	@Override
	public boolean updateWallet(Wallet wallet) throws Exception {
		
		Session session = sessionFactory.openSession();
		/** Starting the Transaction */
		Transaction tx = session.beginTransaction();
		/** Saving POJO */
		session.update(wallet);
		
		/** Commiting the changes */
		tx.commit();

		/** Closing Session */
		session.close();
		return true;
		}
	
	@Override
	public boolean updateWallet(Wallet wallet,ETransaction eTransaction) throws Exception {
		
		
		
		Session session = sessionFactory.openSession();
		/** Starting the Transaction */
		Transaction tx = session.beginTransaction();
		session.update(wallet);
		session.save(eTransaction);
		tx.commit();

		/** Closing Session */
		session.close();
		return true;
		}
	@Override
	public boolean updateWallet(Wallet walletPayee,Wallet walletRecepient,ETransaction eTransaction) throws Exception {
		
		
		
		Session session = sessionFactory.openSession();
		/** Starting the Transaction */
		Transaction tx = session.beginTransaction();
		
		/** Saving POJO */
		
		session.update(walletPayee);
		session.update(walletRecepient);
		session.save(eTransaction);
		
		/** Commiting the changes */
		tx.commit();

		/** Closing Session */
		session.close();
		return true;
		}

	@Override
	public Wallet readWallet(String walletId, String MMID) throws Exception  {
			
		Session session = sessionFactory.openSession();
		/** Starting the Transaction */
		Transaction tx = session.beginTransaction();

		String queryString = "from Wallet where ewalletId = :walletid and MMID = :mmid";
		  Query query = session.createQuery(queryString);
		  query.setString("walletid", walletId);
		  query.setString("mmid", MMID);
		  Object queryResult = query.uniqueResult();
		  Wallet walletResult = (Wallet)queryResult;
		  /** Commiting the changes */
			tx.commit();


			/** Closing Session */
			session.close();
			
		 return walletResult;
		
		
		  }

	@Override
	public Wallet readWallet(String MMID) throws Exception  {
			
		Session session = sessionFactory.openSession();
		/** Starting the Transaction */
		Transaction tx = session.beginTransaction();

		String queryString = "from Wallet where MMID = :mmid";
		  Query query = session.createQuery(queryString);
		  query.setString("mmid", MMID);
		  Object queryResult = query.uniqueResult();
		  Wallet walletResult = (Wallet)queryResult;
		  /** Commiting the changes */
			tx.commit();


			/** Closing Session */
			session.close();
			
		 return walletResult;
		
		
		  }

	@Override
	public boolean deleteWallet(String walletID, String MMID) throws Exception{
		
		Session session = sessionFactory.openSession();
		/** Starting the Transaction */
		Transaction tx = session.beginTransaction();

		String queryString = "delete from Wallet where walletId = :uid and MMID = :mmid";
		  Query query = session.createQuery(queryString);
		  query.setString("uid", walletID);
		  int deletedRows = query.executeUpdate();
		  tx.commit();

			/** Closing Session */
		  session.close();

		  if(deletedRows !=0){
		  return true;
		  }
		  else {
			  return false;
		  }
		}


	@Override
	public boolean updateWallets(Wallet wallet1, Wallet wallet2)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void createTempTransaction(TempTransaction transaction) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		/** Starting the Transaction */
		Transaction tx = session.beginTransaction();
		/** Saving POJO */
		 session.save(transaction);
		
		/** Commiting the changes */
		tx.commit();

		/** Closing Session */
		session.close();
	}
		
}

