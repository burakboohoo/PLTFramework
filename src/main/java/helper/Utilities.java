package helper;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import pages.BasePage;

import java.util.concurrent.TimeUnit;

public class Utilities extends BasePage {
    public Utilities(WebDriver driver){
        super(driver);
    }

    public void implicitWait(int second) {
        driver.manage().timeouts().implicitlyWait(second,TimeUnit.SECONDS);
    }

    public void explicitWait(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void explicitWait(WebElement element1, WebElement element2) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOf(element1),
                ExpectedConditions.visibilityOf(element2)
        ));
    }

    public void scrollDownToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void waitForPageToLoad(long timeOutInSeconds) {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            wait.until(expectation);
        } catch (Throwable error) {
            error.printStackTrace();
        }
    }

    public void typeInbox(WebElement element,String text) {
        element.clear();
        element.sendKeys(text);
    }
    public void clickOnJS(WebElement locater){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", locater);
    }

    public boolean isElementPresent(WebElement locator) {
        try {
            return locator.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
