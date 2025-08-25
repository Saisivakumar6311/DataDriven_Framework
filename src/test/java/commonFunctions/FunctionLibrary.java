package commonFunctions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.aventstack.extentreports.model.Report;

import config.AppUtil;

public class FunctionLibrary extends AppUtil{
	
	//WebDriver driver;
	//method for login
	public static boolean adminLogin(String user, String pass) throws Throwable {
		try {
		driver.get(conpro.getProperty("url"));
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath(conpro.getProperty("Objreset"))).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(conpro.getProperty("Objuser"))).sendKeys(user);
		driver.findElement(By.xpath(conpro.getProperty("Objpass"))).sendKeys(user);
		driver.findElement(By.xpath(conpro.getProperty("ObjLogin"))).sendKeys(user);
		   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	        if (wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath(conpro.getProperty("ObjAlertText")))) != null) {
	            String errormessage = driver.findElement(By.xpath(conpro.getProperty("ObjAlertText"))).getText();
	            Thread.sleep(3000);
	            driver.findElement(By.xpath(conpro.getProperty("ObjOk"))).click();
	            Reporter.log(errormessage, true);
	            return false;
	        }

	    } catch (Exception e) {
	      return true;
	    }
		String Expected = "dashboard";
		String Actual = driver.getCurrentUrl();
		if(Actual.contains(Expected)) {
			//Click logout link
			driver.findElement(By.xpath(conpro.getProperty("Objlogout"))).click();
			Reporter.log("Valid user name and password " + Expected + " " + Actual,true );
			return true;
		}
		else {
			//capture error message
			String errormessage = driver.findElement(By.xpath(conpro.getProperty("ObjAlertText"))).getText();
			Thread.sleep(3000);
			driver.findElement(By.xpath(conpro.getProperty("ObjOk"))).click();
			Reporter.log(errormessage + Actual + " " + Expected);
			return false;
		}
		
	
}
	}

