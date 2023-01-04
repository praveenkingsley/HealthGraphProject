package com.Automation.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.Utilities.FunctionalityLibrary;
import com.aventstack.extentreports.Status;

public class AM_TransferFunds extends FunctionalityLibrary {
	
	public AM_TransferFunds(){
		PageFactory.initElements(driver, this);
	}
    
	@FindBy(id = "fromAccount")
	private WebElement fromAccount_dropDown;
	
	@FindBy(id="toAccount")
	private WebElement toAccount_dropDown;
	
	@FindBy(id = "transferAmount")
	private WebElement accountToTransfer_txt;
	
	@FindBy(id = "transfer")
	private WebElement transferMoney_Btn;
	
	@FindBy(xpath = "//span[contains(text(),'successfully transferred from Account')]")
	private WebElement successMsg;
	
	
	public void transferAmount(String fromAccount,String toAccount,String amount) {
		
		selectByText_DropDown(fromAccount_dropDown, fromAccount);
		selectByText_DropDown(toAccount_dropDown, toAccount);
		setText(accountToTransfer_txt, amount, "Amount to be transfered");
		click(transferMoney_Btn, "Transfer Money Button");
		String msg= successMsg.getText().substring(0,successMsg.getText().indexOf("was"));
		double actualAmount=Double.parseDouble(msg);
		double expectedAmount=Double.parseDouble(amount);
		
		if(actualAmount==expectedAmount) {
			Assert.assertTrue(true);
			logstep(Status.PASS, "Transfer Detail message is validated", false);
		}
		else {
			Assert.assertTrue(false);
			logstep(Status.FAIL, "Unable to validate transfer msg", false);
		}
			
	}
}
