package com.virtusa.mwallet.business.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.virtusa.mwallet.persistence.factories.TempTransactionDAOFactory;
import com.virtusa.mwallet.persistence.factories.WalletDAOFactory;
import com.virtusa.mwallet.persistence.factories.UserDAOFactory;

public class DAOFactoryUtil implements ApplicationContextAware {

	static ApplicationContext beanFactory;

	public static Object getFactory(String factoryName) {
		Object returnFactory = new Object();

		if (factoryName == "userDAOFactory")
			returnFactory = (UserDAOFactory) beanFactory
					.getBean("userDAOFactory");

		else if (factoryName == "walletDAOFactory")
			returnFactory = (WalletDAOFactory) beanFactory
					.getBean("walletDAOFactory");
		else if (factoryName == "tempTransactionDAOFactory")
			returnFactory = (TempTransactionDAOFactory) beanFactory
					.getBean("tempTransactionDAOFactory");
		
		return returnFactory;
		

	}

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		// TODO Auto-generated method stub
		beanFactory = arg0;
	}
}
