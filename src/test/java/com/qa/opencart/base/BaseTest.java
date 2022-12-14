package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultsPage;

public class BaseTest {
	
	public WebDriver driver;
	public Properties prop;
	
	// to get the value of the driver
	//non-static method, therefore creating obj refrence
	public DriverFactory df;  
	public LoginPage loginPage; 
	public AccountsPage accPage;
	public SearchResultsPage searchResPage;
	public ProductInfoPage productInfoPage;
	public SoftAssert softAssert;
	public RegisterPage regPage;
	
	@BeforeTest
	public void setup() {
		//creating refrence
		df = new DriverFactory();
		prop =df.initProp();
		
		driver=df.initDriver(prop);
		loginPage = new LoginPage(driver);
		softAssert = new SoftAssert();
		
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
