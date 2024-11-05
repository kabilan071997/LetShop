package testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import letShop.pages.LoginPage;

public class BaseTest {

	public WebDriver driver;
	public LoginPage lp;

	public WebDriver initializeDriver() throws IOException {

		// Reading the config.properties file
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\resources\\properties\\config.properties");
		prop.load(fis);
		String browser = prop.getProperty("browser");

		if (browser.equalsIgnoreCase("Chrome"))
			driver = new ChromeDriver();
		else if (browser.equalsIgnoreCase("Firefox"))
			driver = new FirefoxDriver();
		else if (browser.equalsIgnoreCase("Edge"))
			driver = new EdgeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
		return driver;
	}
	
	public void takeScreenshot(){
		WebDriver driver = this.driver;
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File file = new File("D:\\Playground\\Workspace\\LetShop\\screenshots\\LoginTest.png");
		try {
			FileUtils.copyFile(source, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@BeforeTest
	public void beforeTest() {
		
	}
	
	@BeforeMethod
	public LoginPage launchApplication() throws IOException {
		  driver = initializeDriver();
		  lp = new LoginPage(driver); 
		  lp.goTo();
		  return lp;
		 
	}
	
	@AfterMethod
	public void tearDown() throws InterruptedException {
		Thread.sleep(500);
		driver.quit();
	}
	
	
}
