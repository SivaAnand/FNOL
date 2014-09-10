package com.virtusa.mwallet.constants;

public interface StatusConstants {

	public int LOGIN_SUCCESS_CODE = 1001;

	public int REGISTRATION_SUCCESS_CODE = 1002;

	public int CHANGEPIN_SUCCESS_CODE = 1003;

	public int P2PTRANSFER_SUCCESS_CODE = 1004;

	public int CHECK_BALANCE_SUCCESS = 1005;

	public int CHECKWALLET_SUCCESS_CODE = 1006;

	public int TOPUP_SUCCESS_CODE = 4001;

	public int TOPDOWN_SUCCESS_CODE = 4002;

	public int LOGIN_FAILURE_CODE = 2001;

	public int REGISTRATION__USEREXISTS_CODE = 2002;

	public int CHECK_CREDENTIALS_CODE = 2003;

	public int TRANSACTION_FAILURE_CODE = 2004;

	public int INSUFFICIENT_BALANCE_CODE = 2005;

	public int INVALID_USER_CODE = 2006;

	public int REGISTRATION_FAILURE_CODE = 2007;

	public int INVALID_IPIN_CODE = 2008;

	public int USER_NOTEXISTS_CODE = 2009;

	public int TOPUP_FAILURE_CODE = 4003;

	public int TOPDOWN_FAILURE_CODE = 4004;

	public int EXCEPTION_STATUS_CODE = 9001;

	public int REGISTRATION__MMIDEXISTS_CODE = 2010;

	public int PREAPPROVAL_CODE = 2011;
	

}
