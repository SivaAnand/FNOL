package com.virtusa.mwallet.persistence.factories;

import com.virtusa.mwallet.persistence.component.interfaces.UserDAOInterface;

public class UserDAOFactory {

	public UserDAOInterface userDAOComponent;

	public UserDAOInterface getUserDAOComponent() {
		return userDAOComponent;
	}

	public void setUserDAOComponent(UserDAOInterface userDAOComponent) {
		this.userDAOComponent = userDAOComponent;
	}

}
