package com.qa.opencart.pages;


import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleutil;

	// 1. By locator
	private By productHeader = By.cssSelector("div#content h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");

	private Map<String, String> productMap;

	// 2. constructor

	// the name of the cons should be same as that of class_name
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);

	}
	// 3. Page Actions

	public String getProductHeaderValue() {
		// By mainProduct = By.xpath("//h2[text()='"+mainProductName+"']");
		String productHeaderVal = eleutil.doElementGetText(productHeader);
		System.out.println("Product Header Value :" + productHeaderVal);
		return productHeaderVal;

	}

	public int getProductImagesCount() {
		int imagesCount = eleutil.waitForElementsToBeVisible(productImages, AppConstants.SMALL_DEFAULT_TIME_OUT).size();
		System.out.println("images count: " + imagesCount);
		return imagesCount;
	}

	public Map<String, String> getProductInfo() {

		// because the products are stored in key and value format
		// Hashmap is used for order less collection which the values user have entered

		productMap = new HashMap<String, String>();
		// Hashmap is used for order less collection which the values user have entered
		// productMap = new LinkedHashMap<String, String>();

		// TreeMap is used to maintain the collection in alphabatelical order
		// productMap = new TreeMap<String, String>();

		// add product name in map:
		productMap.put("productname", getProductHeaderValue());
		getProductMetaData();
		getPriceMetaData();
		System.out.println("=============actual product info===========");
		// to illterate the HashMap
		productMap.forEach((k, v) -> System.out.println(k + ":" + v));
		return productMap;
	}

	public void getProductMetaData() {

		List<WebElement> metaDataList = eleutil.getElements(productMetaData);
		for (WebElement e : metaDataList) {
			String text = e.getText();
			String meta[] = text.split(":");
			String key = meta[0].trim();
			String value = meta[1].trim();
			productMap.put(key, value);
		}
	}

	public void getPriceMetaData() {

		List<WebElement> metaPriceList = eleutil.getElements(productPriceData);
		String productPrice = metaPriceList.get(0).getText().trim();
		String productExTaxPrice = metaPriceList.get(1).getText().trim();

		productMap.put("productprice", productPrice);
		productMap.put("extaxprice", productExTaxPrice);

	}

}
