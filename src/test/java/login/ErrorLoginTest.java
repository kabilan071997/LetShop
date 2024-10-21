package login;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import letShop.pages.DashboardPage;
import testComponents.BaseTest;

public class ErrorLoginTest extends BaseTest {

	@Test
	public void testErrorLogin() throws IOException, InterruptedException {

		DashboardPage dp = lp.login("kabilan@gmail.com", "Kabilan@77");
		//Assert.assertEquals("Incorrect email or password.", lp.validateErrorMessage());

	}

}
