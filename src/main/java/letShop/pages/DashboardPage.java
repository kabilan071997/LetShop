package letShop.pages;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import letShop.base.BasePage;

public class DashboardPage extends BasePage{

	WebDriver driver;

	public DashboardPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Web Elements
	@FindBy(xpath = "//h5")
	List<WebElement> productList;

	// Actions
	public CartPage selectProducts(List<String> productToBeSelectedList) {
		int count = 0;

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		WebElement message = driver.findElement(By.id("toast-container"));
		System.out.println(message.getText());

		for (WebElement product : productList) {
			if (productToBeSelectedList.contains(product.getText())) {
				count++;
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[contains(.,'Add To Cart')])[" + count + "]")));
				driver.findElement((By.xpath("(//button[contains(.,'Add To Cart')])[" + count + "]"))).click();
				wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
				wait.until(ExpectedConditions.visibilityOf(message));
				System.out.println(message.getText());
			} else
				count++;
		}
		return new CartPage(driver);
	}
	

}
