package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleutil;

	// 1. By loactor
	private By logoutLink = By.linkText("Logout");
	private By searchField = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");
	private By accPageHeaders = By.cssSelector("div#content h2");

	// 2. Consstuctor

	// the name of the cons should be same as that of class_name
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);

	}

	// 3. page_Actions
	public String getAccountPageTitle() {
		String title = eleutil.waitForTitleToBe(AppConstants.ACCOUNTS_PAGE_TITLE, AppConstants.SMALL_DEFAULT_TIME_OUT);
		System.out.println("Account current page title : " + title);
		return title;
	}

	public String getAccountsPageUrl() {
		String url = eleutil.waitForUrlToBe(AppConstants.MEDIUM_DEFAULT_TIME_OUT,
				AppConstants.ACCOUNTS_PAGE_URL_FRACTION);
		System.out.println("login page url : " + url);
		return url;
	}

	public boolean isLogoutLinkExist() {
		return eleutil.waitForElementPresence(logoutLink, AppConstants.SMALL_DEFAULT_TIME_OUT).isDisplayed();
	}

	public boolean isSearchFieldExist() {
		return eleutil.waitForElementPresence(searchField, AppConstants.SMALL_DEFAULT_TIME_OUT).isDisplayed();
	}

	public List<String> getAccountSectionHeaderList() {
		return eleutil.getAllElementsTextList(accPageHeaders, AppConstants.SMALL_DEFAULT_TIME_OUT);
	}

	//common page actions
	public SearchResultsPage doSearch(String ProductName) {
		System.out.println("Searching For "+ ProductName);
		eleutil.doSendKeysWithWait(searchField, AppConstants.SMALL_DEFAULT_TIME_OUT, ProductName);
		eleutil.doClick(searchIcon);
		return new SearchResultsPage(driver);
	}
}
