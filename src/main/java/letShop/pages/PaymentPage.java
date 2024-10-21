package letShop.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import letShop.base.BasePage;

public class PaymentPage extends BasePage{

	WebDriver driver;

	public PaymentPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// WebElements
	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement countryField;
	
	@FindBy(xpath = "(//button[contains(.,'India')])[2]")
	WebElement optionIndia;
	
	@FindBy(xpath = "//div/a[contains(.,'Place Order')]")
	WebElement placeOrderButton;
	
	@FindBy(className = "hero-primary")
	WebElement confirmationMessage;

	// Actions
	public void completePayment() {
		countryField.sendKeys("India");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[contains(.,'India')]"))));
		optionIndia.click();
		placeOrderButton.click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.className("hero-primary"))));
		System.out.println(confirmationMessage.getText());
	}

}
