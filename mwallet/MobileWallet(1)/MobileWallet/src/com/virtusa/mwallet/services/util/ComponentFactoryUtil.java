package com.virtusa.mwallet.services.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.virtusa.mwallet.business.factories.PaymentFactory;
import com.virtusa.mwallet.business.factories.ProfileFactory;
import com.virtusa.mwallet.business.factories.ToppingFactory;
import com.virtusa.mwallet.business.factories.TransactionFactory;

public class ComponentFactoryUtil

{
	public static Object getInstance(String factoryName) {
		/*
		 * ApplicationContext ctx = new FileSystemXmlApplicationContext(
		 * "context.xml");
		 */

		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"context.xml");
		Object returnFactory = new Object();
		if (factoryName == "profile")
			returnFactory = (ProfileFactory) ctx.getBean("profileFactory");
		else if (factoryName == "payment")
			returnFactory = (PaymentFactory) ctx.getBean("paymentFactory");
		else if (factoryName == "topping")
			returnFactory = (ToppingFactory) ctx.getBean("toppingFactory");
		else if (factoryName == "transaction")
			returnFactory = (TransactionFactory) ctx.getBean("transactionFactory");

		return returnFactory;
	}

}
