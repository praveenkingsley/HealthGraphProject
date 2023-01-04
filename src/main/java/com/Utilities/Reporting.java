package com.Utilities;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Reporting implements ITestListener {

	File file;
	ExtentSparkReporter reporter;
	static ExtentReports ext;
	static ExtentTest test;
	static String testName;

	public void onStart(ITestContext testcontest) {

		file = new File("./Reports/" + testcontest.getName().concat(getDate()) + ".html");
		reporter = new ExtentSparkReporter(file);
		ext = new ExtentReports();
		ext.attachReporter(reporter);
		testName = testcontest.getName();
		test = ext.createTest(testName);
	}

	public void onFinish(ITestContext context) {
		ext.flush();
	}

	public static String getDate() {
		Date date = new Date();
		return date.toString().replace(" ", "-").replace(":", "-");
	}

	public static void logstep(Status sStatus, String stepName, boolean isScreenShot) {

		if (!isScreenShot) {
			if (sStatus.equals(Status.PASS)) {
				test.log(Status.PASS, stepName);
			} else if (sStatus.equals(Status.FAIL)) {
				test.log(Status.FAIL, stepName);
			}
		} else {
			TakesScreenshot ts = (TakesScreenshot) FunctionalityLibrary.driver;
			File src = ts.getScreenshotAs(OutputType.FILE);
			String screenShotName = testName+ " " +getDate()+ ".jpg";
			String screenShotPath = "./ScreenShot/";
			File dsc = new File(screenShotPath + screenShotName);
			
			try {
				FileUtils.copyFile(src, dsc);
			} catch (IOException e) {
			}

			if (sStatus.equals(Status.PASS)) {
				test.log(Status.PASS, stepName,MediaEntityBuilder.createScreenCaptureFromPath(screenShotPath+screenShotName).build());
				
			} else if (sStatus.equals(Status.FAIL)) {
				test.log(Status.FAIL, stepName, MediaEntityBuilder.createScreenCaptureFromPath(screenShotPath+screenShotName).build());
			}
		}

	}

}
