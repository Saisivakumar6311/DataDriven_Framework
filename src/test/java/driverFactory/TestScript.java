package driverFactory;

import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import applicationLayer.Com.Page.CustomerPage;
import config.AppUtil1;
import utilities.ExcelFileUtil;

public class TestScript extends AppUtil1{
	
	String inputpath = "./FileInput/CustomerData.xlsx" ;
	String outputpath = "./FileOutput/CustomerResults.xlsx";
	
	String TCSheet = "Customer";
	
	ExtentReports report;
	ExtentTest logger;
	
	@Test
	public void startvoid() throws Throwable {
		 ExtentSparkReporter spark = new ExtentSparkReporter("./Reports/Customer.html");

		    // Initialize ExtentReports and attach the reporter
		    report = new ExtentReports();
		    report.attachReporter(spark);
		//create object for excelfileutil class
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		int rc =xl.rowCount(TCSheet);
		Reporter.log("No os rows are" + " " + rc,true);
		for(int i=1;i<=rc;i++) {
			//read cell from sheet
			logger = report.createTest("customer");
			logger.assignAuthor("Ranga");
			String cname=xl.getCellData(TCSheet, i, 0);
			String Address = xl.getCellData(TCSheet, i, 1);
			String city = xl.getCellData(TCSheet, i, 2);
			String country = xl.getCellData(TCSheet, i, 3);
			String cperson= xl.getCellData(TCSheet, i, 4);
			String pnumber = xl.getCellData(TCSheet, i, 5);
			String email = xl.getCellData(TCSheet, i, 6);
			String mnumber = xl.getCellData(TCSheet, i, 7);
			String  notes= xl.getCellData(TCSheet, i, 8);
			logger.log(Status.INFO, cname + " " + city + " " + country + " " + cperson + " " + pnumber  + " " + email +  " "+ mnumber +" " + notes ) ;
		CustomerPage customeradd = PageFactory.initElements(driver, CustomerPage.class);
		boolean res =customeradd.addCustomer(cname, Address, city, country, cperson, pnumber, email, mnumber, notes);
		
		if(res) {
			xl.setCellData(TCSheet, i, 9, "pass", outputpath);
			logger.log(Status.PASS,"customer Add success" );
		}
		else {
			xl.setCellData(TCSheet, i, 9, "fail", outputpath);
			logger.log(Status.FAIL,"customer Add success" );

		}
		report.flush();
		}
		
	}
	
	


}
