package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductSearchTest extends BaseTest {

	// I have started from the LoginPage
	@BeforeClass
	public void productSearchSetup() {
		// doLOgin method is returning the AccountsPage
		// Now I am on AccountsPage
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] {

				{ "MacBook", "MacBook Air" }, { "MacBook", "MacBook Pro" }, { "Samsung", "Samsung SyncMaster 941BW" }

		};
	}

	@Test(dataProvider="getProductData")
	public void productSearchTest(String searchKey, String productName) {
		// DoSearch method is returning the SearchResultPage
		// Now I am on SearchResPage
		searchResPage = accPage.doSearch(searchKey);
		// ProductInfoPage method is returning the ProductInfoPage
		// Now I am on ProductInfoPage
		productInfoPage = searchResPage.selectProduct(productName);
		String actProductHeaderName = productInfoPage.getProductHeaderValue();
		Assert.assertEquals(actProductHeaderName, productName);
	}
	
	
	@DataProvider
	public Object[][] getProductInfoData() {
		return new Object[][] {

			{ "MacBook", "MacBook Air", 4}, 
			{ "MacBook", "MacBook Pro", 4}, 
			{ "Samsung", "Samsung SyncMaster 941BW", 1 }

		};
	}

	@Test(dataProvider="getProductInfoData")
	public void productImagesCountTest(String searchKey, String productName, int imgCount) {
		searchResPage = accPage.doSearch(searchKey);
		productInfoPage = searchResPage.selectProduct(productName);
		int actImagesCount = productInfoPage.getProductImagesCount();
		Assert.assertEquals(actImagesCount, imgCount);
	}
	
//	@DataProvider
//	public Object[][] getProductInfo() {
//		return new Object[][] {
//
//			{ "MacBook", "MacBook Air", 4}, 
//			{ "MacBook", "MacBook Pro", 4}, 
//			{ "Samsung", "Samsung SyncMaster 941BW", 1 }
//
//		};
//	}

	@Test
	public void productDataTest() {
		searchResPage = accPage.doSearch("MacBook");
		productInfoPage = searchResPage.selectProduct("MacBook Pro");
		Map<String, String> actProductInfo =productInfoPage.getProductInfo();
		
		// in case of multiple assertions it's good to go with soft assertion
		//in hard assertion, when 1 assertion got failed then rest assertion will not be executed
		// we have created object of soft assertion in base test
		softAssert.assertEquals(actProductInfo.get("Brand"), "Apple");
		softAssert.assertEquals(actProductInfo.get("Product Code"), "Product 18");
		softAssert.assertEquals(actProductInfo.get("Availability"), "In Stock");
		softAssert.assertEquals(actProductInfo.get("productname"), "MacBook Pro");
		
		softAssert.assertEquals(actProductInfo.get("productprice"), "$2,000.00");
		softAssert.assertEquals(actProductInfo.get("extaxprice"), "Ex Tax: $2,000.00");
		
		//mandatory 
		softAssert.assertAll();
	}
}
