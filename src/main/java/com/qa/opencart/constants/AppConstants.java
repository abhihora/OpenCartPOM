package com.qa.opencart.constants;

import java.util.Arrays;
import java.util.List;

public class AppConstants {
	
	///final keyword is used to create constants
	//so that the value cannot be changed and remain constant
	
	
	//should be in capital, the naming convention
	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String LOGIN_PAGE_URL_FRACTION = "route=account/login";
	public static final String ACCOUNTS_PAGE_URL_FRACTION = "route=account/account";

	public static final String ACCOUNTS_PAGE_TITLE = "My Account";
	
	public static final int SMALL_DEFAULT_TIME_OUT = 5;
	public static final int MEDIUM_DEFAULT_TIME_OUT = 10;
	public static final int LONG_DEFAULT_TIME_OUT = 20;
	
	public static final List<String> EXPECTED_ACCOUNT_HEADER_LIST = Arrays.asList("My Account","My Affiliate Account", "My Orders","Newsletter");
	
	public static final String REGISTER_SUCCESS_MESSG = "Your Account Has Been Created!";
	public static final String REGISTER_SHEET_NAME = "register";
}
