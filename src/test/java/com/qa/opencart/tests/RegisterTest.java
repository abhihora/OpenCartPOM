package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterTest extends BaseTest{
	
	@BeforeClass
	public void regSetup() {
		regPage = loginPage.goToRegisterPage();
	}
	 
	
	
	
//	@DataProvider
//	public Object[][] getRegData() {
//		return new Object[][] {
//
//			{ "Abhiroop", "Hora", "abhi10@gmail.com", "1234", "12345", "yes"}, 
//			{ "Abhiroop", "Hora", "abhi@gmail.com", "1234", "12345", "no"}, 
//			{ "Abhiroop", "Hora", "abhi5@gmail.com", "1234", "12345", "yes" }
//
//		};
//	}
	
	
	@DataProvider
	public Object[][] getRegExcelData() {
		Object regData[][] = ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
		return regData;
	}
	
//	public String randomEmail() {
//		Random random = new Random();
//		String email = "automation" + random.nextInt(1000) + "@gmail.com";
//		return email;
//	}
//	
	
	@Test(dataProvider = "getRegExcelData")
	public void userRegTest(String firstName, String lastName, String email,String phone, String password, String subscribe) {

		boolean successFlag = regPage.userRegistration(firstName, lastName, email, phone, password, subscribe);
		regPage.goToRegisterPage();
		Assert.assertEquals(successFlag, true);

	}
	

}
