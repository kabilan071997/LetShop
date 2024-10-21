package letShop.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import letShop.base.BasePage;

public class LoginPage extends BasePage{
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Web Elements
	@FindBy(id = "userEmail")
	WebElement usernameField;
	
	@FindBy(id = "userPassword")
	WebElement passwordField;
	
	@FindBy(id = "login")
	WebElement loginButton;
	
	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage;
	
	//Actions
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public DashboardPage login(String username, String password) {
		usernameField.sendKeys(username);
		passwordField.sendKeys(password);
		loginButton.click();
		return new DashboardPage(driver);
	}
	
	public String validateErrorMessage() {
		String errorMsg = errorMessage.getText();
		System.out.println(errorMsg);
		return errorMsg;
	}
	

}
