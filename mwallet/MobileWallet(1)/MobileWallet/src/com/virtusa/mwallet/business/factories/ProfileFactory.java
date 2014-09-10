package com.virtusa.mwallet.business.factories;

import org.springframework.beans.factory.annotation.Autowired;

import com.virtusa.mwallet.business.components.interfaces.ProfileComponentInterface;

public class ProfileFactory {

	public ProfileComponentInterface profileComponent;

	public ProfileComponentInterface getProfileComponent() {
		return profileComponent;
	}

	@Autowired
	public void setProfileComponent(ProfileComponentInterface profileComponent) {
		this.profileComponent = profileComponent;
	}

}
