package login;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.sun.net.httpserver.Authenticator.Retry;

import letShop.pages.CartPage;
import letShop.pages.DashboardPage;
import letShop.pages.PaymentPage;
import letShop.utilities.Read_XLSX;
import testComponents.BaseTest;
import testComponents.RetryFailedCase;

public class LoginTest extends BaseTest {

	@Test(dataProvider = "getData", retryAnalyzer = RetryFailedCase.class)
	public void testLoginSubmitProducts(String userName, String password) throws IOException, InterruptedException {

		// GLobal variable
		
		  String productToBeSelected[] = { "IPHONE 13 PRO", "ZARA COAT 3" }; 
		  // Convert to array to Array List 
		  List<String> productToBeSelectedList = Arrays.asList(productToBeSelected);
		  
		  DashboardPage dp = lp.login(userName, password);
		  CartPage cp = dp.selectProducts(productToBeSelectedList);
		  PaymentPage pp = cp.checkItemsAddedToCart(productToBeSelectedList);
		  pp.completePayment();
		 
		//takeScreenshot();
	}

	@DataProvider(name="getData")
	public Object[][] getDataFromExcel() throws IOException {
		return Read_XLSX.getTestData();
	}

}
