package com.Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.Status;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FunctionalityLibrary extends Reporting{

	public static WebDriver driver = null;
	static String browser;
	static String url;
	static ReadConfig config;
	

	public static void initBrowser() {
		config = new ReadConfig();
		browser = config.readBrowser();
		url=config.readUrl();

		if (browser != null) {

			switch (browser.toLowerCase()) {
			case "chrome":
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				break;

			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				break;

			case "ie":
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
				break;

			default:
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				break;
			}
			
           logstep(Status.PASS,"Launch Browser",false);
		}

	}
	
	public static void openUrl() {
		if(driver!=null) {
		driver.manage().window().maximize();
		driver.get(url);
		}
	}
	
	public static void takeScreenShot() {
		
	}
	
	public static void click(WebElement element,String readableName) {
		try {
			element.click();
			logstep(Status.PASS,"Click on "+ readableName, false);
		} catch (Exception e) {
			logstep(Status.FAIL, "Unable click on "+readableName, false);
		}
	}
	
	public static void setText(WebElement element,String value,String readableName) {
		try {
			element.sendKeys(value);
			logstep(Status.PASS,"Enter value as "+value+" in " +readableName+" TextBox", false);
		} catch (Exception e) {
			logstep(Status.FAIL, "Unable to enter value in " +readableName+" TextBox", false);
		}
		
	}
	
	public static void selectByText_DropDown(WebElement element,String text) {
		try {
			Select select=new Select(element);
			select.selectByVisibleText(text);
			logstep(Status.PASS, "Select "+text+" from DropDown", false);
		} catch (Exception e) {
			logstep(Status.FAIL, "Unable to select "+text+" from DropDown", false);
		}
		
	}
	

}
