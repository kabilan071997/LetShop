package letShop.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import letShop.base.BasePage;

public class CartPage extends BasePage{

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// WebElements
	@FindBy(xpath = "//button[contains(.,'Cart')]")
	WebElement cart;
	
	@FindBy(xpath = "//button[contains(.,'Checkout')]")
	WebElement checkoutButton;
	
	// Actions
	public PaymentPage checkItemsAddedToCart(List<String> productToBeSelectedList) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
		wait.until(ExpectedConditions.elementToBeClickable(cart));
	cart.click();

	List<WebElement> cartItems = driver.findElements(By.xpath("//div[@class='cartSection']"));
	for (WebElement cartItem : cartItems) {
		String cartItemName = cartItem.findElement(By.tagName("h3")).getText();
		if (productToBeSelectedList.contains(cartItemName))
			System.out.println("Item added to cart " + cartItemName);
		else
			System.err.println("Wrong Item to cart " + cartItemName);
	}
	Actions action = new Actions(driver);
	action.scrollToElement(checkoutButton).build().perform();
	checkoutButton.click();
	
	return new PaymentPage(driver);
	}

}
