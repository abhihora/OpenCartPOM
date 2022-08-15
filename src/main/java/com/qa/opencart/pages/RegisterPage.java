package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {
	
	private WebDriver driver;
	private ElementUtil eleutil;

	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmpassword = By.id("input-confirm");

	private By subscribeYes = By.xpath("(//label[@class='radio-inline'])[position()=1]/input");
	private By subscribeNo = By.xpath("(//label[@class='radio-inline'])[position()=2]/input");

	private By agreeCheckBox = By.name("agree");
	private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");
	private By sucessMessg = By.cssSelector("div#content h1");

	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}
	
	public boolean userRegistration(String firstName, String lastName, String email, String phone, String password,
			String subscribe) {

		eleutil.doSendKeysWithWait(this.firstName, AppConstants.MEDIUM_DEFAULT_TIME_OUT, firstName);
		
		eleutil.doSendKeys(this.lastName, lastName);
		eleutil.doSendKeys(this.email, email);
		eleutil.doSendKeys(this.telephone, phone);
		eleutil.doSendKeys(this.password, password);
		eleutil.doSendKeys(this.confirmpassword, password);

		if (subscribe.equalsIgnoreCase("yes")) {
			eleutil.doClick(this.subscribeYes);
		} else {
			eleutil.doClick(this.subscribeNo);
		}

		eleutil.doClick(this.agreeCheckBox);
		eleutil.doClick(this.continueButton);

		String actSuccessMesg = eleutil.waitForElementVisible(this.sucessMessg, AppConstants.MEDIUM_DEFAULT_TIME_OUT).getText();
		System.out.println("user reg success mesggg================= " + actSuccessMesg);
		if (actSuccessMesg.contains(AppConstants.REGISTER_SUCCESS_MESSG)) {
		//	eleutil.doClick(this.logoutLink);
		//	eleutil.doClick(this.registerLink);
			return true;
		}
		return false;
	}
	public void goToRegisterPage() {
		eleutil.doClick(this.logoutLink);
		eleutil.doClick(this.registerLink);
	}

}

