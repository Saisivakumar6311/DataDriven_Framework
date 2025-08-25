package driverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Report;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import commonFunctions.FunctionLibrary;
import config.AppUtil;
import utilities.ExcelFileUtil;

public class DriverScript extends AppUtil{
	
	String input = "./FileInput/TestData.xlsx";
	String outputpath = "./FileOutput/datadrivenResults.xlsx";
	ExtentReports report;
	ExtentTest logger;
	
	@Test
	public void starttest() throws Throwable {
		
		ExtentSparkReporter spark = new ExtentSparkReporter("./Reports/Login.html");

	    // Initialize ExtentReports and attach the reporter
	    report = new ExtentReports();
	    report.attachReporter(spark);
		
		//create object for excel file util class.
		ExcelFileUtil xl = new ExcelFileUtil(input);
		//count no of rows in login sheet
		int rc =xl.rowCount("Login");
		Reporter.log("No of rows are + "+rc,true);
		for(int i =1;i<=rc;i++) {
			logger = report.createTest("login page");
			//logger = reports.startTest("Login Test");
			String username = xl.getCellData("Login", i, 0);
			String password = xl.getCellData("Login", i, 1);
			logger.log(Status.INFO, username+"-----"+password);
			
			//call login method from function library class
			boolean res = FunctionLibrary.adminLogin(username, password);
		
			if(res) {
				xl.setCellData("Login", i, 2, "valid username and password", outputpath);
				xl.setCellData("Login", i, 3, "pass", outputpath);
				logger.log(Status.PASS, "usermase set was successfully");
			}
			else {
				
				//takescreenshot
				File screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(screen, new File("./Screenshot/Iteration/fail"+i+"Loginpage.png"));		
				xl.setCellData("Login", i, 2, "Invalid username and password", outputpath);
				xl.setCellData("Login", i, 3, "Fail", outputpath);
				logger.log(Status.FAIL, "taken screenshot");
			}
			report.flush();
		}
	}
}
