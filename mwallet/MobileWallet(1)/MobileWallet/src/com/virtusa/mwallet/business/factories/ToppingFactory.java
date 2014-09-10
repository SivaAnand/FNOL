package com.virtusa.mwallet.business.factories;

import com.virtusa.mwallet.business.components.interfaces.ToppingComponentInterface;

public class ToppingFactory {
	public ToppingComponentInterface toppingComponent;

	public ToppingComponentInterface getToppingComponent() {
		return toppingComponent;
	}

	public void setToppingComponent(ToppingComponentInterface toppingComponent) {
		this.toppingComponent = toppingComponent;
	}

}
