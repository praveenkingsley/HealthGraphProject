package com.Automation.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.Utilities.FunctionalityLibrary;
import com.aventstack.extentreports.Status;

public class AM_LoginPage extends FunctionalityLibrary {

	public AM_LoginPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "uid")
	private WebElement userName_Txt;

	@FindBy(id = "passw")
	private WebElement password_Txt;

	@FindBy(name = "btnSubmit")
	private WebElement login_Btn;

	public void validateLogin(String userName, String type) {

		if (type.equalsIgnoreCase("VALID")) {
			String actualUsername = driver.findElement(By.xpath("//div[@class='fl']/h1")).getText().trim()
					.substring(6, 11).toUpperCase();
			if (actualUsername.equalsIgnoreCase(userName)) {
				logstep(Status.PASS, "Logged in successfully ", false);
				Assert.assertTrue(true);
			} else {
				logstep(Status.FAIL, "Login Failed  ", false);
				Assert.assertTrue(false);
			}
		} else {
			String actualUsername = driver.findElement(By.xpath("//div[@class='fl']/p/span")).getText().trim()
					.substring(0, 12).toUpperCase();
			if (actualUsername.equalsIgnoreCase("LOGIN FAILED")) {
				logstep(Status.PASS, "Unable to login with invalid credentials", false);
				Assert.assertTrue(true);
			} else {
				Assert.assertEquals(actualUsername, "LOGIN FAILED");
				logstep(Status.FAIL, "Logged in with invalid credentials ", false);
				Assert.assertTrue(false);
			}
		}

	}

	public void login(String userName, String password, String type) {
		setText(userName_Txt, userName, "Username");
		setText(password_Txt, password, "Password");
		click(login_Btn, "Login");
		validateLogin(userName, type);
		
	}
}
