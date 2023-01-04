package com.Automation.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.Utilities.FunctionalityLibrary;
import com.aventstack.extentreports.Status;

public class AM_HomePage extends FunctionalityLibrary {

	public AM_HomePage() {
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//a[text()='View Account Summary']")
	private WebElement viewAccountSummary_Lnk;
	
	@FindBy(xpath="//a[text()='Transfer Funds']")
	private WebElement transferFunds_Lnk;
	
	@FindBy(xpath="//a[text()='View Recent Transactions']")
	private WebElement viewRecentTransactions_Lnk;
	@FindBy(id = "listAccounts")
	private WebElement viewAccounts_DropDown;
	
	@FindBy(id="btnGetAccount")
	private WebElement go_Btn;
	
	@FindBy(xpath = "//*[text()='Contact Us']")
	private WebElement contactUs_Lnk;
	
	@FindBy(xpath = "//*[text()='online form']")
	private WebElement onlineForm_Lnk;
	
	@FindBy(id = "LoginLink")
	private WebElement signOff_Lnk;
	
	@FindBy(xpath = "//font[text()='Sign In']")
	private WebElement signIn_Lnk;
	
	public void viewAccountDetails(String accountDetail) {
		click(viewAccountSummary_Lnk, "View Account Summary");
		selectByText_DropDown(viewAccounts_DropDown, accountDetail);
		click(go_Btn, "Go Button");
	}
	
	public void clickTransferFunds() {
		click(transferFunds_Lnk, "Transfer Funds");
	}
	public void clickRecentTransaction() {
		click(viewRecentTransactions_Lnk, "View Recent Transactions");
	}
	
	public void clickOnlineForm() {
		click(contactUs_Lnk, "Contact us");
		click(onlineForm_Lnk, "Online form");
	}
	
	public void clickSignOff() {
		click(signOff_Lnk, "Sign Off");
		
		if(signIn_Lnk.isDisplayed()) {
			Assert.assertTrue(true);
			logstep(Status.PASS,"Sign Off validated", false);
		}
		else {
			logstep(Status.FAIL,"Unable to validate Sign Off", false);
			Assert.assertTrue(false);
		}
	}
}

