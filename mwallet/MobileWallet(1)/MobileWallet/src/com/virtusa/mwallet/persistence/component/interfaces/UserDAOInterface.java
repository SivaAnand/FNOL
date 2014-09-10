package com.virtusa.mwallet.persistence.component.interfaces;

import com.virtusa.mwallet.valueobjects.User;

public interface UserDAOInterface {

	public boolean createUser(User user) throws Exception;

	public User readUser(String userID) throws Exception;

	public boolean updateUser(User user) throws Exception;

	public boolean deleteUser(String userId) throws Exception;

	public User readUserByMMID(String mmid);

}
