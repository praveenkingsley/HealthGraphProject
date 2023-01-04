package com.Automation.TestCases;

import java.util.ArrayList;
import java.util.HashMap;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.Automation.Pages.AM_AccountHistory;
import com.Automation.Pages.AM_FeedBackPage;
import com.Automation.Pages.AM_HomePage;
import com.Automation.Pages.AM_LoginPage;
import com.Automation.Pages.AM_RecentTransaction;
import com.Automation.Pages.AM_TransferFunds;
import com.Utilities.FunctionalityLibrary;
import com.Utilities.ReadConfig;
import com.Utilities.ReadXcel;

public class E2E_01 extends FunctionalityLibrary{
	
	String valid_userName;
	String valid_passWord;
	String invalid_userName;
	String invalid_passWord;
	String view_AccountNumber;
	String available_Balance;
	String fund_transfer_from;
	String fund_transfer_to;
	String fund_transfer_amount;
	
	
	 
	
	ArrayList<HashMap<String, String>> credentialData;
	ArrayList<HashMap<String, String>> testData;
	
	@BeforeTest
	public void before() {
		ReadXcel excel=new ReadXcel();
		
		credentialData=excel.data("./src/main/resources/TestData/Credentials.xlsx", "Sheet1");
		
		for (HashMap<String, String> hashMap : credentialData) {
			if(hashMap.get("TYPE").equals("VALID")) {
				valid_userName=hashMap.get("USERNAME");
				valid_passWord=hashMap.get("PASSWORD");
			}
			else {
				invalid_userName=hashMap.get("USERNAME");
				invalid_passWord=hashMap.get("PASSWORD");
			}
		}
		ReadXcel excelData=new ReadXcel();
		testData=excelData.data("./src/main/resources/TestData/E2E01_TestData.xlsx", "Sheet1");
	
	    view_AccountNumber= testData.get(0).get("VIEW_ACCOUNT_DETAIL");		
		available_Balance= testData.get(0).get("AVAILABLE_BALANCE");	 
		fund_transfer_from= testData.get(0).get("FUND_TRANSFER_FROM");	
		fund_transfer_to= testData.get(0).get("FUND_TRANSFER_TO");	
		fund_transfer_amount= testData.get(0).get("FUND_TRANSFER_AMOUNT");	
	}
	
	@Test
	public void test() {
		initBrowser();
		AM_LoginPage loginPage=new AM_LoginPage();
		AM_HomePage homePage=new AM_HomePage();
		AM_AccountHistory accountHistory=new AM_AccountHistory();
		AM_TransferFunds transferFunds=new AM_TransferFunds();
		AM_RecentTransaction recentTransaction=new AM_RecentTransaction();
		AM_FeedBackPage feedBack=new AM_FeedBackPage();
		
		openUrl();
		loginPage.login(invalid_userName, invalid_passWord,"INVALID");
		loginPage.login(valid_userName, valid_passWord, "VALID");
		homePage.viewAccountDetails(view_AccountNumber);
		accountHistory.validateAvailableBalance(available_Balance);
		homePage.clickTransferFunds();
		transferFunds.transferAmount(fund_transfer_from, fund_transfer_to, fund_transfer_amount);
		homePage.clickRecentTransaction();
		recentTransaction.validateLastTwoTransaction(fund_transfer_from, fund_transfer_to, fund_transfer_amount);
		homePage.clickOnlineForm();
		feedBack.fillFeedBackForm("Test123456");
		homePage.clickSignOff();
	}

}
