package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {

	// take care of options i.e. run cases in inconginto, private

	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;

	public OptionsManager(Properties prop) {
		this.prop = prop;
	}

	public ChromeOptions getChromeOptions() {
		co = new ChromeOptions();
		// data_conversion into boolean
		if (Boolean.parseBoolean(prop.getProperty("headless")))co.setHeadless(true);
		
		if (Boolean.parseBoolean(prop.getProperty("incognito")))co.addArguments("--incognito");
		return co;

	}

	public FirefoxOptions getFirefoxOptions() {

		fo = new FirefoxOptions();
		// data_conversion into boolean
		if (Boolean.parseBoolean(prop.getProperty("headless")))fo.setHeadless(true);
		
		if (Boolean.parseBoolean(prop.getProperty("incognito")))fo.addArguments("--incognito");
		return fo;
	}

}
