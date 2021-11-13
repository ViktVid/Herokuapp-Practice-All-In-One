package pagesPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogInPage {

    public WebDriver driver;
    WebElement username;
    WebElement password;
    WebElement submitButton;
    public WebDriverWait wdwait;

  /*  public void LogInPage() {
        PageFactory.initElements(driver, this);
    }*/

    public LogInPage(WebDriver driver, WebDriverWait wdwait) {
        this.driver = driver;
        this.wdwait = wdwait;
    }

    public WebElement getUsername() {
        return driver.findElement(By.id("username"));
    }

    public WebElement getPassword() {
        return driver.findElement(By.id("password"));
    }

    public WebElement getSubmitButton() {
        return driver.findElement(By.id("submit"));
    }

    public WebElement getErrorText() {
        return driver.findElement(By.id("error"));
    }

    public void insertUsername(String usernameData) {
        this.getUsername().clear();
        this.getUsername().sendKeys(usernameData);
    }

    public void insertPassword(String passwordData) {
        this.getPassword().clear();
        this.getPassword().sendKeys(passwordData);
    }

    public void clickSubmitButton() {
        this.getSubmitButton().click();
    }

    public String errorText() {
        wdwait.until(ExpectedConditions.elementToBeClickable(By.id("error")));
        return getErrorText().getText();
    }

    public void waiter(WebElement element) throws InterruptedException{
        wdwait.until(ExpectedConditions.elementToBeClickable(element));
    }

}
