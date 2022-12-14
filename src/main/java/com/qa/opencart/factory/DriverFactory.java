package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManager;

	// when driver is running parallel mode, then multiple driver will hit in same
	// time, to avoid the
	// deadlock condition, we use this func
	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();

	/*
	 * return WebDriver
	 */
	public WebDriver initDriver(Properties prop) {

		String browserName = prop.getProperty("browser");

		System.out.println("browser name is : " + browserName);
		optionsManager = new OptionsManager(prop);

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			// driver = new ChromeDriver(optionsManager.getChromeOptions());
			tldriver.set(new ChromeDriver(optionsManager.getChromeOptions()));

		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			// driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tldriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));

		} else if (browserName.equalsIgnoreCase("safari")) {
			// driver = new SafariDriver();
			tldriver.set(new SafariDriver());
		}

		else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			// driver = new EdgeDriver();
			tldriver.set(new EdgeDriver());
		}

		else {
			System.out.println("please pass the right browser name : " + browserName);
		}
		getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();

	}

	// this method is used to get the allready set driver
	// synchronized is used to ensure the driver doesn't go into deadlock condition

	public static synchronized WebDriver getDriver() {
		return tldriver.get();
	}

	/**
	 * this returns properties reference with all config properties
	 * 
	 * @return this returns properties reference
	 */

	public Properties initProp() {
		// this is espically designed in java to load the properties
		prop = new Properties();
		FileInputStream ip = null;

		// command line argument with the help of maven
		// maven clean install -Denv="qa"

		// will get the system property on which the user wants to run the test cases
		//envName in this case will be qa, as state above
		String envName = System.getProperty("env");
		System.out.println("running test cases on "+ envName);
		
		
		//when user havent passed any envi. 
		if (envName == null) {
			System.out.println("No env is given...hence running it on QA env by default....");
			try {
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
			else {
				try {
					switch (envName.toLowerCase()) {
					case "qa":
						ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
						break;
					case "stage":
						ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
						break;
					case "dev":
						ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
						break;
					case "prod":
						ip = new FileInputStream("./src/test/resources/config/config.properties");
						break;
					case "uat":
						ip = new FileInputStream("./src/test/resources/config/uat.config.properties");
						break;

					default:
						System.out.println("Please pass the right environment.... " + envName);
						break;
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}

			}

			try {
				prop.load(ip);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return prop;
		}
	
	
	//take screenshot
	
	public static String getScreenshot(String methodName) {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		//Users/naveenautomationlabs/Documents/workspace1/
		String path = System.getProperty("user.dir")+"/screenshot/" + methodName + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

}


