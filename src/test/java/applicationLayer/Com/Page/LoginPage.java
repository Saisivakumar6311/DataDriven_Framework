package applicationLayer.Com.Page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
	
	//define repository
	@FindBy(xpath = "//button[@id='btnreset']")
	WebElement ObjReset;
	@FindBy (xpath = "//input[@id='username']")
	WebElement ObjUser;
	@FindBy (xpath = "//input[@id='password']")
	WebElement ObjPass;
	@FindBy (xpath = "//button[@id='btnsubmit']")
	WebElement ObjLogin;
	
	//method for login
	//method
	public void adminlogin(String user, String password) {
		ObjReset.click();
		ObjUser.sendKeys(user);
		ObjPass.sendKeys(password);
		ObjLogin.click();
		
	}
	
		
}
