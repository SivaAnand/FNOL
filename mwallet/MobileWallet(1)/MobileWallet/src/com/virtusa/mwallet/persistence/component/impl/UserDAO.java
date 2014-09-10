package com.virtusa.mwallet.persistence.component.impl;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.virtusa.mwallet.persistence.component.interfaces.UserDAOInterface;
import com.virtusa.mwallet.persistence.util.HibernateUtil;
import com.virtusa.mwallet.valueobjects.User;

public class UserDAO implements UserDAOInterface {
	@Override
	public boolean createUser(User user) throws Exception {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		Session session = sessionFactory.openSession();
		/** Starting the Transaction */
		Transaction tx = session.beginTransaction();
		/** Saving POJO */
		session.save(user);

		/** Commiting the changes */
		tx.commit();

		/** Closing Session */
		session.close();
		return true;
	}

	@Override
	public boolean updateUser(User user) throws Exception {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		Session session = sessionFactory.openSession();
		/** Starting the Transaction */
		Transaction tx = session.beginTransaction();
		/** Saving POJO */
		session.update(user);

		/** Commiting the changes */
		tx.commit();

		/** Closing Session */
		session.close();
		return true;

	}

	@Override
	public User readUser(String userId) throws Exception {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		Session session = sessionFactory.openSession();
		/** Starting the Transaction */
		Transaction tx = session.beginTransaction();

		String queryString = "from User where userId = :userid";
		Query query = session.createQuery(queryString);
		query.setString("userid", userId);
		Object queryResult = query.uniqueResult();
		User userResult = (User) queryResult;
		/** Commiting the changes */
		tx.commit();

		/** Closing Session */
		session.close();

		return userResult;
	}

	@Override
	public boolean deleteUser(String userID) throws Exception {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		Session session = sessionFactory.openSession();
		/** Starting the Transaction */
		Transaction tx = session.beginTransaction();

		String queryString = "delete from User where userId = :uid";
		Query query = session.createQuery(queryString);
		query.setString("uid", userID);
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
	public User readUserByMMID(String mmid) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		Session session = sessionFactory.openSession();
		/** Starting the Transaction */
		Transaction tx = session.beginTransaction();

		String queryString = "from User where MMID = :mmid";
		
		Query query = session.createQuery(queryString);
		
		query.setString("mmid", mmid);
		List<User> userList = query.list();
		Iterator iterator = userList.iterator();
		User user = new User();
		if (iterator.hasNext())
		{
			user = (User) iterator.next();
		}
		else
		{
			user = null;
		}
		
		/** Commiting the changes */
		tx.commit();

		/** Closing Session */
		session.close();

		return user;
	}

}
