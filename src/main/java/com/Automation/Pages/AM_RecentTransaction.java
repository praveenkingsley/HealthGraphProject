package com.Automation.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.Utilities.FunctionalityLibrary;
import com.aventstack.extentreports.Status;

public class AM_RecentTransaction extends FunctionalityLibrary {
	
	public AM_RecentTransaction() {
		PageFactory.initElements(driver, this);
	}

	public WebElement amount_column(int row){
		return driver.findElement(By.xpath("(//table[contains(@id,'MyTransactions')]/tbody/tr)["+row+"]/td[5]"));
	}
	
	public WebElement accountId_column(int row) {
		return driver.findElement(By.xpath("(//table[contains(@id,'MyTransactions')]/tbody/tr)["+row+"]/td[3]"));
	}
	
	public WebElement action_column(int row) {
		return driver.findElement(By.xpath("(//table[contains(@id,'MyTransactions')]/tbody/tr)["+row+"]/td[4]"));
	}
	
	public void validateLastTwoTransaction(String fromAccount,String toAccount,String amount) {
		
		fromAccount=fromAccount.replaceAll("[A-Z,a-z]", "").trim();
		toAccount=toAccount.replaceAll("[A-Z,a-z]", "").trim();
		double amountInDecimal=Double.parseDouble(amount);
		
		
		for(int i=3;i>1;i--) {
			String action=action_column(i).getText().trim();
			
			 if(action.equalsIgnoreCase("Withdrawal")) {
				 String actualFrom= accountId_column(i).getText().trim();
				 String actualWithdrawAmount=amount_column(i).getText().replaceAll("[$]", "");
				 double actualAmount=Double.parseDouble(actualWithdrawAmount);
				 Assert.assertEquals(actualFrom, fromAccount);
		
				 Assert.assertEquals(actualAmount,-amountInDecimal);
				 
				 logstep(Status.PASS, "Amount "+amount+" withdrawn from "+fromAccount+" successfully", false);
			 }
			 else {
				 String actualTo=accountId_column(i).getText().trim();
				 String actualDepositAmount=amount_column(i).getText().replaceAll("[$]", "");
				 double actualAmount=Double.parseDouble(actualDepositAmount);
				 
				 if(actualTo.equals(toAccount) && actualAmount==amountInDecimal) {
					 Assert.assertTrue(true);
					 logstep(Status.PASS, "Amount "+amount+ " deposit on "+toAccount+" successfully", false);
				 }
				 else {
					 logstep(Status.FAIL, "Unable to validate deposit amount", false);
					 Assert.assertTrue(false);
				 }
				 
			 }
		}
	}
	
}
