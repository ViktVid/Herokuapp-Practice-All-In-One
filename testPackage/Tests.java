package testPackage;

import basePackage.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Tests extends BaseTest {

	@BeforeMethod
	public void pageSetUp() {
		driver.manage().window().maximize();
		driver.navigate().to(homeUrl);
	}

	@Test(priority = 10)
	public void successfulLogIn() {
		String username = excelReader.getStringData("LoginTest", 1, 1);
		String password = excelReader.getStringData("LoginTest", 1, 2);
		String button = excelReader.getStringData("LoginTest", 1, 5);
		String text = excelReader.getStringData("LoginTest", 1, 7);

		logInPage.insertUsername(username);
		logInPage.insertPassword(password);
		logInPage.clickSubmitButton();

		Assert.assertTrue(logOutPage.getLogOutButton().isDisplayed());
		Assert.assertEquals(logOutPage.logOutButtonText(), button);
		Assert.assertEquals(logOutPage.successfulLogInText(), text);

	}

	@Test(priority = 20)
	public void incorrectUsername() throws InterruptedException {
		for (int i = 1; i < excelReader.getLastRowNumber(); i++) {

			String username = excelReader.getStringData("LoginTest", i, 3);
			String password = excelReader.getStringData("LoginTest", 1, 2);
			String textUsername = excelReader.getStringData("LoginTest", 3, 6);

			logInPage.insertUsername(username);
			logInPage.insertPassword(password);
			logInPage.clickSubmitButton();
			// logInPage.waiter(logInPage.getErrorText());
			wdwait.until(ExpectedConditions.presenceOfElementLocated(By.id("error")));

			Assert.assertEquals(logInPage.errorText(), textUsername);
			try {
				Assert.assertFalse(logOutPage.getLogOutButton().isDisplayed());
			} catch (Exception NoSuchElement) {

			}

		}

	}

	@Test(priority = 30)
	public void incorrectPassword() {
		logInPage.insertUsername("student");
		logInPage.insertPassword("123");
		logInPage.clickSubmitButton();
	}

	@AfterMethod
	public void deleteCookies() {
		driver.manage().deleteAllCookies();
	}
}
