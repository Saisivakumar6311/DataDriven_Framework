package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class AppUtil {

	public static WebDriver driver;
	public static Properties conpro;

	@BeforeTest
	public static void SetUp() throws Throwable, IOException {
		conpro = new Properties();
		conpro.load(new FileInputStream("./PropertyFiles/Environment.properties"));

		if (conpro.getProperty("browser").equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
		else if (conpro.getProperty("browser").equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		else {
			Reporter.log("Browser value is not matching", true);
		}

	}
	@AfterTest
	public static void tearDown() {
		driver.quit();
	}
}
