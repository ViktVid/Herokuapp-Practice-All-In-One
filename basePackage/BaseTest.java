package basePackage;

import pagesPackage.LogInPage;
import pagesPackage.LogOutPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

public class BaseTest {

	public static WebDriver driver;
	public static LogInPage logInPage;
	public LogOutPage logOutPage;
	public ExcelReader excelReader;
	public String homeUrl;
	public static WebDriverWait wdwait;

	@BeforeClass
	public void setUp() throws IOException {
		WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");
		driver = new ChromeDriver();
		logInPage = new LogInPage(driver, wdwait);
		logOutPage = new LogOutPage(driver);
		excelReader = new ExcelReader("C:\\Users\\User\\Desktop\\Book1.xlsx");
		homeUrl = excelReader.getStringData("LoginTest", 1, 0);
		wdwait = new WebDriverWait(driver, 20);
	}

	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();
	}

}
