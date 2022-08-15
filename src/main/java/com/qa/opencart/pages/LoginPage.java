package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleutil;

	// By locator

	// encapsulation concept used - "private"
	//dont want to give access directly to these variables, data hiding

	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");

	private By registerLink = By.linkText("Register");

	// 2. page const.... //helping to get the driver.
	public LoginPage(WebDriver driver) {
		this.driver = driver;// this keyword is used to initialize the value of driver
		eleutil = new ElementUtil(driver); //initalize with driver otherwise NPE would be thrown
	}
	
	//3. page actions
	public String getLoginPageTitle() {
		String title = eleutil.waitForTitleToBe(AppConstants.LOGIN_PAGE_TITLE, AppConstants.SMALL_DEFAULT_TIME_OUT);
		System.out.println("login page title : " + title);
		return title;
	}
	public String getLoginPageUrl() {
		String url = eleutil.waitForUrl(AppConstants.SMALL_DEFAULT_TIME_OUT, AppConstants.LOGIN_PAGE_URL_FRACTION);
		System.out.println("login page url : " + url);
		return url;
	}
	public boolean isForgotPwdLinkExist() {
		return eleutil.waitForElementPresence(forgotPwdLink, AppConstants.MEDIUM_DEFAULT_TIME_OUT).isDisplayed();
	}
	
	//needed for page chaning
	//1. should return the next page i.e. Account page
	//2. driver should be pointing to the next page
	//3. the text page should consist of the next page refrence obj 
	
	public AccountsPage doLogin(String username, String pwd) {
		System.out.println("app creds: " + username + ":" + pwd);
		eleutil.doSendKeysWithWait(emailId, AppConstants.MEDIUM_DEFAULT_TIME_OUT, username);
		eleutil.doSendKeys(password, pwd);
		eleutil.doClick(loginBtn);
		
		//return eleutil.waitForTitleToBe(AppConstants.ACCOUNTS_PAGE_TITLE, AppConstants.MEDIUM_DEFAULT_TIME_OUT);
		
		//page chaining concept
		return new AccountsPage(driver);
}
	
//	public SearchResultsPage performSearch(String name) {
//		
//		AccountsPage accPage = new AccountsPage(driver);
//		return accPage.doSearch(name);
//		
//	}
	
	public RegisterPage goToRegisterPage() {
		eleutil.doClick(registerLink);
		return new RegisterPage(driver);
	}
}
