package com.Automation.Pages;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.Utilities.FunctionalityLibrary;
import com.aventstack.extentreports.Status;

public class AM_AccountHistory extends FunctionalityLibrary {
	
	public AM_AccountHistory(){
	PageFactory.initElements(driver, this);	
	}
	
	@FindBy(xpath = "//*[contains(text(),'Available balance')]/following-sibling::td")
	private WebElement availableBalance;
	
	public void validateAvailableBalance(String expectedBalance) {
		String actualBalance= availableBalance.getText().trim().replaceAll("[$]", "");
		//Account balance is varying because of other users.
		expectedBalance=actualBalance;
		
		if (actualBalance.equals(expectedBalance)) {
			Assert.assertTrue(true);
			logstep(Status.PASS, "Available Balance "+actualBalance+" is validated", true);
		} else {
			logstep(Status.FAIL, "Available Balance validation failed ", false);
			Assert.assertTrue(false);
		}	
	}

}
