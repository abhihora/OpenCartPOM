package com.qa.opencart.tests;

import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountsPageTest extends BaseTest {
	
	//to access the account page user has to be logged in
	@BeforeClass
	public void accSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void isLogoutLinkExistTest() {
		Assert.assertEquals(accPage.isLogoutLinkExist(), true);
	}
	
	
	@Test
	public void accPageTitleTest() {
		Assert.assertEquals(accPage.getAccountPageTitle(), AppConstants.ACCOUNTS_PAGE_TITLE);
	}
	@Test
	public void accPageHeaderTest() {
		List<String>accSecHeaderList = accPage.getAccountSectionHeaderList();
		//sort the header list
		Collections.sort(accSecHeaderList);
		System.out.println("---ACT HEADER---"+ accSecHeaderList);
		Assert.assertEquals(accSecHeaderList, AppConstants.EXPECTED_ACCOUNT_HEADER_LIST);
		
	}
	
}
