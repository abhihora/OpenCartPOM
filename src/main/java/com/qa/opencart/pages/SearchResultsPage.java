package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage {
	
	private WebDriver driver;
    private ElementUtil eleutil;
    
 // 1. By loactor
    By productCount = By.cssSelector("div.product-thumb");


 	// 2. Consstuctor

 	// the name of the cons should be same as that of class_name
 	public SearchResultsPage(WebDriver driver) {
 		this.driver = driver;
 		eleutil = new ElementUtil(driver);

 	}
 	
 	//3. page actions:
	
 		public int getSearchProductCount() {
 			return eleutil.waitForElementsToBeVisible(productCount, AppConstants.MEDIUM_DEFAULT_TIME_OUT).size();
 		}
 		
 		//page chaining concept 
 		
 		public ProductInfoPage selectProduct(String SearchProductName) {
 			By product = By.linkText(SearchProductName);
 			eleutil.doClick(product);
 			return new ProductInfoPage(driver);
 		}
 	
	

}
