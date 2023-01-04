package com.Automation.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.Utilities.FunctionalityLibrary;
import com.aventstack.extentreports.Status;

public class AM_FeedBackPage extends FunctionalityLibrary {

	public AM_FeedBackPage() {
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(name="comments")
	private WebElement comment_txt;
	
	@FindBy(name="submit")
	private WebElement submit_Btn;
	
	@FindBy(xpath = "//*[text()='Thank You']")
	private WebElement msg;
	
	public void fillFeedBackForm(String comment) {
		setText(comment_txt, comment, "Comment");
		click(submit_Btn, "Submit Button");
	}
	
	public void validateResponseMsg() {
		String actual= msg.getText().trim();
		Assert.assertEquals(actual, "Thank You");
		logstep(Status.PASS, "THANK YOU message is validated", false);
		
	}
}
