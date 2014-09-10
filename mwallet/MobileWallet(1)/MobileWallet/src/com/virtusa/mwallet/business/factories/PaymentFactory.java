package com.virtusa.mwallet.business.factories;

import org.springframework.beans.factory.annotation.Autowired;

import com.virtusa.mwallet.business.components.interfaces.PaymentComponentInterface;

public class PaymentFactory {

	public PaymentComponentInterface paymentComponent;

	public PaymentComponentInterface getPaymentComponent() {
		return paymentComponent;
	}

	@Autowired
	public void setPaymentComponent(PaymentComponentInterface paymentComponent) {
		this.paymentComponent = paymentComponent;
	}

}
